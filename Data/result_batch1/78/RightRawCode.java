// https://github.com/fyhertz/spydroid-ipcamera/tree/779f1035ac8fd91be5dfba99516da1b9f29f8768/src/net/majorkernelpanic/http/TinyHttpServer.java#L603-L626
public class TempClass {
		public void run() {
			while (!Thread.interrupted()) {
				try {
					// Set up HTTP connection
					Socket socket = this.mServerSocket.accept();
					DefaultHttpServerConnection conn = new DefaultHttpServerConnection();
					Log.d(TAG,"Incoming connection from " + socket.getInetAddress());
					conn.bind(socket, mParams);

					// Start worker thread
					Thread t = new WorkerThread(this.mHttpService, conn, socket);
					t.setDaemon(true);
					t.start();
				} catch (SocketException e) {
					break;
				} catch (InterruptedIOException ex) {
					Log.e(TAG,"Interrupted !");
					break;
				} catch (IOException e) {
					Log.d(TAG,"I/O error initialising connection thread: " + e.getMessage());
					break;
				}
			}
		}

}