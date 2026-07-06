// https://github.com/HMCL-dev/HMCL/tree/195db2ee6d0c64a31b476530da0433bbab8b2c18/HMCL/src/main/java/org/jackhuang/hmcl/ui/nbt/NBTFileType.java#L167-L177
public class TempClass {
    public static NBTFileType ofFile(Path file) {
        String ext = FileUtils.getExtension(file);
        for (NBTFileType type : types) {
            for (String extension : type.extensions) {
                if (extension.equals(ext))
                    return type;
            }
        }

        return null;
    }

}