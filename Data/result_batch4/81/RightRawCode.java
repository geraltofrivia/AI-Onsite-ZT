// https://github.com/emanuele-f/PCAPdroid/tree/017c5d5c8932396b71228317666c31234949185d/app/src/main/java/com/emanuelef/remote_capture/model/BlacklistDescriptor.java#L93-L112
public class TempClass {
    public String getStatusLabel(Context ctx) {
        int id = -1;

        switch(getStatus()) {
            case NOT_LOADED:
                id = R.string.status_not_loaded;
                break;
            case OUTDATED:
                id = R.string.status_outdated;
                break;
            case UPDATING:
                id = R.string.status_updating;
                break;
            case UP_TO_DATE:
                id = R.string.status_uptodate;
                break;
        }

        return ctx.getString(id);
    }

}