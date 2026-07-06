// https://github.com/knowm/XChange/tree/784a88e954fe0db135766be75124d95c1d2b0efe/xchange-quoine/src/main/java/org/knowm/xchange/quoine/dto/account/QuoineTradingAccountInfo.java#L132-L155
public class TempClass {
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("QuoineTradingAccountInfo [id=");
    builder.append(id);
    builder.append(", leverageLevel=");
    builder.append(leverageLevel);
    builder.append(", equity=");
    builder.append(equity);
    builder.append(", margin=");
    builder.append(margin);
    builder.append(", currencyPairCode=");
    builder.append(currencyPairCode);
    builder.append(", pnl=");
    builder.append(pnl);
    builder.append(", position=");
    builder.append(position);
    builder.append(", balance=");
    builder.append(balance);
    builder.append(", marginPercent=");
    builder.append(marginPercent);
    builder.append("]");
    return builder.toString();
  }

}