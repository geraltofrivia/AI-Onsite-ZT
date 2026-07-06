// https://github.com/jenkinsci/jenkins/tree/235d237f32835ccc1064c95c701f331d7dece7a3/core/src/main/java/hudson/model/UpdateCenter.java#L1793-L1831
public class TempClass {
        @Override
        public void run() {
            try {
                PluginWrapper installed = plugin.getInstalled();
                synchronized (installed) {
                    if (!installed.isEnabled()) {
                        try {
                            installed.enable();
                        } catch (IOException e) {
                            LOGGER.log(Level.SEVERE, "Failed to enable " + plugin.getDisplayName(), e);
                            error = e;
                            status = new DownloadJob.Failure(e);
                        }

                        if (dynamicLoad) {
                            try {
                                // remove the existing, disabled inactive plugin to force a new one to load
                                pm.dynamicLoad(getDestination(), true, null);
                            } catch (Exception e) {
                                LOGGER.log(Level.SEVERE, "Failed to dynamically load " + plugin.getDisplayName(), e);
                                error = e;
                                requiresRestart = true;
                                status = new DownloadJob.Failure(e);
                            }
                        } else {
                            requiresRestart = true;
                        }
                    }
                }
            } catch (Throwable e) {
                LOGGER.log(Level.SEVERE, "An unexpected error occurred while attempting to enable " + plugin.getDisplayName(), e);
                error = e;
                requiresRestart = true;
                status = new DownloadJob.Failure(e);
            }
            if (status instanceof DownloadJob.Pending) {
                status = new DownloadJob.Success();
            }
        }

}