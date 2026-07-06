// https://github.com/JZ-Darkal/AndroidHttpCapture/tree/e0aa4f6fcda60adf4f1c77d2ecadc6c383ba1a86/app/src/main/java/cn/darkal/networkdiagnosis/Activity/MainActivity.java#L523-L541
public class TempClass {
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Boolean shouldDispatchTouchEvent = false;
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null && v != null) {
                    if (isKeyboardOpen) {
                        shouldDispatchTouchEvent = true;
                    }
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return shouldDispatchTouchEvent || super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return getWindow().superDispatchTouchEvent(ev) || onTouchEvent(ev);
    }

}