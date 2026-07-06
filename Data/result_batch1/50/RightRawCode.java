// https://github.com/HMCL-dev/HMCL/tree/195db2ee6d0c64a31b476530da0433bbab8b2c18/HMCLCore/src/main/java/org/jackhuang/hmcl/util/platform/windows/WindowsHardwareDetector.java#L49-L96
public class TempClass {
    @Override
    public List<GraphicsCard> detectGraphicsCards() {
        if (!OperatingSystem.isWindows7OrLater())
            return null;

        try {
            String getCimInstance = OperatingSystem.SYSTEM_VERSION.startsWith("6.1")
                    ? "Get-WmiObject"
                    : "Get-CimInstance";

            List<Map<String, String>> videoControllers = SystemUtils.run(Arrays.asList(
                            "powershell.exe",
                            "-NoProfile",
                            "-Command",
                            String.join(" | ",
                                    getCimInstance + " -Class Win32_VideoController",
                                    "Select-Object Name,AdapterCompatibility,DriverVersion,AdapterDACType",
                                    "Format-List"
                            )),
                    inputStream -> KeyValuePairUtils.loadList(new BufferedReader(new InputStreamReader(inputStream, OperatingSystem.NATIVE_CHARSET))));

            ArrayList<GraphicsCard> cards = new ArrayList<>(videoControllers.size());
            for (Map<String, String> videoController : videoControllers) {
                String name = videoController.get("Name");
                String adapterCompatibility = videoController.get("AdapterCompatibility");
                String driverVersion = videoController.get("DriverVersion");
                String adapterDACType = videoController.get("AdapterDACType");

                if (StringUtils.isNotBlank(name)) {
                    cards.add(GraphicsCard.builder().setName(GraphicsCard.cleanName(name))
                            .setVendor(HardwareVendor.of(adapterCompatibility))
                            .setDriverVersion(driverVersion)
                            .setType(StringUtils.isBlank(adapterDACType)
                                    || "Internal".equalsIgnoreCase(adapterDACType)
                                    || "InternalDAC".equalsIgnoreCase(adapterDACType)
                                    ? GraphicsCard.Type.Integrated
                                    : GraphicsCard.Type.Discrete)
                            .build()
                    );
                }
            }

            return cards;
        } catch (Throwable e) {
            LOG.warning("Failed to get graphics card info", e);
            return Collections.emptyList();
        }
    }

}