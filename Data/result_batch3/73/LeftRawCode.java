// https://github.com/oshi/oshi/tree/35badc331ff64b262629f552d8b81034a08ab432/oshi-core/src/main/java/oshi/hardware/platform/mac/MacGraphicsCard.java#L42-L78
public class TempClass {
    public static List<GraphicsCard> getGraphicsCards() {
        List<GraphicsCard> cardList = new ArrayList<>();
        List<String> sp = ExecutingCommand.runNative("system_profiler SPDisplaysDataType");
        String name = Constants.UNKNOWN;
        String deviceId = Constants.UNKNOWN;
        String vendor = Constants.UNKNOWN;
        List<String> versionInfoList = new ArrayList<>();
        long vram = 0;
        int cardNum = 0;
        for (String line : sp) {
            String[] split = line.trim().split(":", 2);
            if (split.length == 2) {
                String prefix = split[0].toLowerCase(Locale.ROOT);
                if (prefix.equals("chipset model")) {
                    // Save previous card
                    if (cardNum++ > 0) {
                        cardList.add(new MacGraphicsCard(name, deviceId, vendor,
                                versionInfoList.isEmpty() ? Constants.UNKNOWN : String.join(", ", versionInfoList),
                                vram));
                        versionInfoList.clear();
                    }
                    name = split[1].trim();
                } else if (prefix.equals("device id")) {
                    deviceId = split[1].trim();
                } else if (prefix.equals("vendor")) {
                    vendor = split[1].trim();
                } else if (prefix.contains("version") || prefix.contains("revision")) {
                    versionInfoList.add(line.trim());
                } else if (prefix.startsWith("vram")) {
                    vram = ParseUtil.parseDecimalMemorySizeToBinary(split[1].trim());
                }
            }
        }
        cardList.add(new MacGraphicsCard(name, deviceId, vendor,
                versionInfoList.isEmpty() ? Constants.UNKNOWN : String.join(", ", versionInfoList), vram));
        return cardList;
    }

}