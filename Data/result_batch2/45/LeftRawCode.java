// https://github.com/testcontainers/testcontainers-java/tree/768a2af266bf20be4800281053c83d4d1f345942/modules/jdbc/src/main/java/org/testcontainers/jdbc/ContainerDatabaseDriver.java#L78-L155
public class TempClass {
    @Override
    public synchronized Connection connect(String url, final Properties info) throws SQLException {
        /*
          The driver should return "null" if it realizes it is the wrong kind of driver to connect to the given URL.
         */
        if (!acceptsURL(url)) {
            return null;
        }

        ConnectionUrl connectionUrl = ConnectionUrl.newInstance(url);

        synchronized (jdbcUrlContainerCache) {
            String queryString = connectionUrl.getQueryString().orElse("");
            /*
              If we already have a running container for this exact connection string, we want to connect
              to that rather than create a new container
             */
            JdbcDatabaseContainer container = jdbcUrlContainerCache.get(connectionUrl.getUrl());
            if (container == null) {
                LOGGER.debug("Container not found in cache, creating new instance");

                Map<String, String> parameters = connectionUrl.getContainerParameters();

                /*
                  Find a matching container type using ServiceLoader.
                 */
                ServiceLoader<JdbcDatabaseContainerProvider> databaseContainers = ServiceLoader.load(
                    JdbcDatabaseContainerProvider.class
                );
                for (JdbcDatabaseContainerProvider candidateContainerType : databaseContainers) {
                    if (candidateContainerType.supports(connectionUrl.getDatabaseType())) {
                        container = candidateContainerType.newInstance(connectionUrl);
                        container.withTmpFs(connectionUrl.getTmpfsOptions());
                        delegate = container.getJdbcDriverInstance();
                    }
                }
                if (container == null) {
                    throw new UnsupportedOperationException(
                        "Database name " + connectionUrl.getDatabaseType() + " not supported"
                    );
                }

                /*
                  Cache the container before starting to prevent race conditions when a connection
                  pool is started up
                 */
                jdbcUrlContainerCache.put(url, container);

                /*
                  Pass possible container-specific parameters
                 */
                container.setParameters(parameters);

                /*
                  Start the container
                 */
                container.start();
            }

            /*
              Create a connection using the delegated driver. The container must be ready to accept connections.
             */
            Connection connection = container.createConnection(queryString, info);

            /*
              If this container has not been initialized, AND
              an init script or function has been specified, use it
             */
            if (!initializedContainers.contains(container.getContainerId())) {
                DatabaseDelegate databaseDelegate = new JdbcDatabaseDelegate(container, queryString);
                runInitScriptIfRequired(connectionUrl, databaseDelegate);
                runInitFunctionIfRequired(connectionUrl, connection);
                initializedContainers.add(container.getContainerId());
            }

            return wrapConnection(connection, container, connectionUrl);
        }
    }

}