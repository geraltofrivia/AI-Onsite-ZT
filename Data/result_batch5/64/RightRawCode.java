// https://github.com/MeteorDevelopment/meteor-client/tree/4da2b5cb1e515d592cf6e723480cf640ddfb5df0/src/main/java/meteordevelopment/meteorclient/systems/proxies/Proxy.java#L127-L139
public class TempClass {
        public Proxy build() {
            Proxy proxy = new Proxy();

            if (!type.equals(proxy.type.getDefaultValue())) proxy.type.set(type);
            if (!address.equals(proxy.address.getDefaultValue())) proxy.address.set(address);
            if (port != proxy.port.getDefaultValue()) proxy.port.set(port);
            if (!name.equals(proxy.name.getDefaultValue())) proxy.name.set(name);
            if (!username.equals(proxy.username.getDefaultValue())) proxy.username.set(username);
            if (!password.equals(proxy.password.getDefaultValue())) proxy.password.set(password);
            if (enabled != proxy.enabled.getDefaultValue()) proxy.enabled.set(enabled);

            return proxy;
        }

}