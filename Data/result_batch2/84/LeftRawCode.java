// https://github.com/JZ-Darkal/AndroidHttpCapture/tree/e0aa4f6fcda60adf4f1c77d2ecadc6c383ba1a86/app/src/main/java/com/google/zxing/QrCodeScanActivity.java#L307-L353
public class TempClass {
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_GALLERY:
                if (resultCode == -1) {
                    try {
                        Uri uri = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                this.getContentResolver(), uri);

                        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
                        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(),
                                bitmap.getHeight());

                        RGBLuminanceSource rgbLuminanceSource = new RGBLuminanceSource(
                                bitmap.getWidth(), bitmap.getHeight(), pixels);
                        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                                rgbLuminanceSource));
                        QRCodeReader qrCodeReader = new QRCodeReader();
                        HashMap<DecodeHintType, String> decodeHintTypeStringHashMap = new HashMap<DecodeHintType, String>();
                        decodeHintTypeStringHashMap.put(DecodeHintType.CHARACTER_SET, "utf-8");
                        Result result = qrCodeReader.decode(binaryBitmap,
                                decodeHintTypeStringHashMap);
                        String url = result.getText();
                        playBeepSoundAndVibrate();

                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse("jdhttpmonitor://webview?param=" + Uri.encode("{\"url\":\"" + url + "\"" + ", \"from\":\"app\"" + "}")));
                        startActivity(intent);

                        // 启动二级页之后，关闭二维码扫描窗口
                        finish();

                    } catch (com.google.zxing.NotFoundException e) {
                        Toast.makeText(this, "未发现二维码", Toast.LENGTH_SHORT).show();
                    } catch (com.google.zxing.ChecksumException e) {
                        Toast.makeText(this, "未发现二维码", Toast.LENGTH_SHORT).show();
                    } catch (com.google.zxing.FormatException e) {
                        Toast.makeText(this, "未发现二维码", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "未发现二维码", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

}