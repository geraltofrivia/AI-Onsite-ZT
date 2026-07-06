// https://github.com/knowm/XChange/tree/784a88e954fe0db135766be75124d95c1d2b0efe/xchange-cexio/src/main/java/org/knowm/xchange/cexio/dto/trade/CexIOCancelReplaceOrderResponse.java#L52-L65
public class TempClass {
  @Override
  public String toString() {
    final StringBuffer buffer = new StringBuffer("{");
    buffer.append("id='").append(id).append('\'');
    buffer.append(", complete=").append(complete);
    buffer.append(", price=").append(price);
    buffer.append(", amount=").append(amount);
    buffer.append(", time=").append(time);
    buffer.append(", type='").append(type).append('\'');
    buffer.append(", pending=").append(pending);
    buffer.append(", error=").append(error);
    buffer.append('}');
    return buffer.toString();
  }

}