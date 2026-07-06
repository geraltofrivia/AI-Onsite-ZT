// https://github.com/oshi/oshi/tree/35badc331ff64b262629f552d8b81034a08ab432/oshi-core/src/main/java/oshi/hardware/platform/windows/WindowsGraphicsCard.java#L67-L121
public class TempClass {
    public static List<GraphicsCard> getGraphicsCards() {
        List<GraphicsCard> cardList = new ArrayList<>();

        int index = 1;
        String[] keys = Advapi32Util.registryGetKeys(WinReg.HKEY_LOCAL_MACHINE, DISPLAY_DEVICES_REGISTRY_PATH);
        for (String key : keys) {
            if (!key.startsWith("0")) {
                continue;
            }

            try {
                String fullKey = DISPLAY_DEVICES_REGISTRY_PATH + key;
                if (!Advapi32Util.registryValueExists(WinReg.HKEY_LOCAL_MACHINE, fullKey, ADAPTER_STRING)) {
                    continue;
                }

                String name = Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, fullKey, DRIVER_DESC);
                String deviceId = "VideoController" + index++;
                String vendor = Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, fullKey, VENDOR);
                String versionInfo = Advapi32Util.registryGetStringValue(WinReg.HKEY_LOCAL_MACHINE, fullKey, DRIVER_VERSION);
                long vram = 0L;

                if (Advapi32Util.registryValueExists(WinReg.HKEY_LOCAL_MACHINE, fullKey, QW_MEMORY_SIZE)) {
                    vram = Advapi32Util.registryGetLongValue(WinReg.HKEY_LOCAL_MACHINE, fullKey, QW_MEMORY_SIZE);
                } else if (Advapi32Util.registryValueExists(WinReg.HKEY_LOCAL_MACHINE, fullKey, MEMORY_SIZE)) {
                    Object genericValue = Advapi32Util.registryGetValue(WinReg.HKEY_LOCAL_MACHINE, fullKey, MEMORY_SIZE);
                    if (genericValue instanceof Long) {
                        vram = (long) genericValue;
                    } else if (genericValue instanceof Integer) {
                        vram = Integer.toUnsignedLong((int) genericValue);
                    } else if (genericValue instanceof byte[]) {
                        byte[] bytes = (byte[]) genericValue;
                        vram = ParseUtil.byteArrayToLong(bytes, bytes.length, false);
                    }
                }

                cardList.add(new WindowsGraphicsCard(
                    Util.isBlank(name) ? Constants.UNKNOWN : name,
                    Util.isBlank(deviceId) ? Constants.UNKNOWN : deviceId,
                    Util.isBlank(vendor) ? Constants.UNKNOWN : vendor,
                    Util.isBlank(versionInfo) ? Constants.UNKNOWN : versionInfo,
                    vram));
            } catch (Win32Exception e) {
                if (e.getErrorCode() != WinError.ERROR_ACCESS_DENIED) {
                    // Ignore access denied errors, re-throw others
                    throw e;
                }
            }
        }

        if (cardList.isEmpty()) {
            return getGraphicsCardsFromWmi();
        }
        return cardList;
    }

}