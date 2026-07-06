// https://github.com/mik3y/usb-serial-for-android/tree/c608aadc59d2d09f5e34ab928de42c16bf69864f/usbSerialForAndroid/src/main/java/com/hoho/android/usbserial/driver/CommonUsbSerialPort.java#L240-L296
public class TempClass {
    @Override
    public void write(final byte[] src, int length, final int timeout) throws IOException {
        int offset = 0;
        long startTime = MonotonicClock.millis();
        length = Math.min(length, src.length);

        testConnection(false);
        while (offset < length) {
            int requestTimeout;
            final int requestLength;
            final int actualLength;

            synchronized (mWriteBufferLock) {
                final byte[] writeBuffer;

                if (mWriteBuffer == null) {
                    mWriteBuffer = new byte[mWriteEndpoint.getMaxPacketSize()];
                }
                requestLength = Math.min(length - offset, mWriteBuffer.length);
                if (offset == 0) {
                    writeBuffer = src;
                } else {
                    // bulkTransfer does not support offsets, make a copy.
                    System.arraycopy(src, offset, mWriteBuffer, 0, requestLength);
                    writeBuffer = mWriteBuffer;
                }
                if (timeout == 0 || offset == 0) {
                    requestTimeout = timeout;
                } else {
                    requestTimeout = (int)(startTime + timeout - MonotonicClock.millis());
                    if(requestTimeout == 0)
                        requestTimeout = -1;
                }
                if (requestTimeout < 0) {
                    actualLength = -2;
                } else {
                    actualLength = mConnection.bulkTransfer(mWriteEndpoint, writeBuffer, requestLength, requestTimeout);
                }
            }
            long elapsed = MonotonicClock.millis() - startTime;
            if (DEBUG) {
                Log.d(TAG, "Wrote " + actualLength + "/" + requestLength + " offset " + offset + "/" + length + " time " + elapsed + "/" + requestTimeout);
            }
            if (actualLength <= 0) {
                String msg = "Error writing " + requestLength + " bytes at offset " + offset + " of total " + src.length + " after " + elapsed + "msec, rc=" + actualLength;
                if (timeout != 0) {
                    // could be buffer full because: writing to fast, stopped by flow control
                    testConnection(elapsed < timeout, msg);
                    throw new SerialTimeoutException(msg, offset);
                } else {
                    throw new IOException(msg);

                }
            }
            offset += actualLength;
        }
    }

}