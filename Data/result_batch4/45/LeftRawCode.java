// https://github.com/zfile-dev/zfile/tree/5c5d2be0cb36898dcabe0828d7eb5825dc539ade/src/main/java/im/zhaojun/zfile/module/storage/service/impl/LocalServiceImpl.java#L61-L85
public class TempClass {
    @Override
    public List<FileItemResult> fileList(String folderPath) throws FileNotFoundException {
        checkPathSecurity(folderPath);

        List<FileItemResult> fileItemList = new ArrayList<>();

        String fullPath = StringUtils.concat(param.getFilePath(), getCurrentUserBasePath(), folderPath);

        File file = new File(fullPath);

        if (!(file.isDirectory() && file.exists())) {
            throw new BizException(ErrorCode.BIZ_FOLDER_NOT_EXIST);
        }

        File[] files = file.listFiles();

        if (files == null) {
            return fileItemList;
        }
        for (File f : files) {
            fileItemList.add(fileToFileItem(f, folderPath));
        }

        return fileItemList;
    }

}