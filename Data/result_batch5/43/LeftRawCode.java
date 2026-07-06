// https://github.com/redis/jedis/tree/8cdc90f7cd170cbda2a25778233489a779177650/src/main/java/redis/clients/jedis/timeseries/TSAddParams.java#L148-L160
public class TempClass {
  @Override
  public int hashCode() {
    int result = Objects.hashCode(retentionPeriod);
    result = 31 * result + Objects.hashCode(encoding);
    result = 31 * result + Objects.hashCode(chunkSize);
    result = 31 * result + Objects.hashCode(duplicatePolicy);
    result = 31 * result + Objects.hashCode(onDuplicate);
    result = 31 * result + Boolean.hashCode(ignore);
    result = 31 * result + Long.hashCode(ignoreMaxTimediff);
    result = 31 * result + Double.hashCode(ignoreMaxValDiff);
    result = 31 * result + Objects.hashCode(labels);
    return result;
  }

}