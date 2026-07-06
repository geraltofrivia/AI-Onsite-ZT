// https://github.com/MiPushFramework/MiPushFramework/tree/1425051fdf5dc20a5d4c6bed2dd821ccd5123acc/push/src/main/java/top/trumeet/mipushframework/utils/ShellUtils.java#L200-L216
public class TempClass {
            }
        }
    }
    
    private static boolean isEmpty (String s) {
        return s == null || s.trim().equals("");
    }

    private static final String TAG = "Shell";

    /**
     * Exec shell using root
     * @param command command line
     * @return success
     */
    public static boolean exec (String command) {
        ShellUtils.CommandResult result = ShellUtils.execCmd(command, true, true);

}