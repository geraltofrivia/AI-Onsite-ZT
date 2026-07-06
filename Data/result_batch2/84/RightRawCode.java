// https://github.com/gedoor/MyBookshelf/tree/bb5a99058f387dc08d727cfe1418d294a53a9f48/app/src/main/java/com/kunfei/bookshelf/view/activity/QRCodeScanActivity.java#L160-L175
public class TempClass {
        super.onActivityResult(requestCode, resultCode, data);

        binding.zxingview.startSpotAndShowRect(); // 显示扫描框，并开始识别

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_QR_IMAGE) {
            final String picturePath = RealPathUtil.getPath(this, data.getData());
            // 本来就用到 QRCodeView 时可直接调 QRCodeView 的方法，走通用的回调
            binding.zxingview.decodeQRCode(picturePath);
        }
    }

    private void chooseFromGallery() {
        try {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");

}