// https://github.com/crossoverJie/cim/tree/e0eb57edf2e671dcc6ef23b956920aa0891dd905/cim-common/src/main/java/com/crossoverjie/cim/common/util/SnowflakeIdWorker.java#L28-L45
public class TempClass {
    public synchronized long nextId() {
        long ts = System.currentTimeMillis();
        if (ts < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards.");
        }
        if (ts == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                ts = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = ts;
        return ((ts - EPOCH) << TIMESTAMP_SHIFT)
                | (workerId << WORKER_ID_SHIFT)
                | sequence;
    }

}