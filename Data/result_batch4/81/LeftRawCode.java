// https://github.com/citerus/dddsample-core/tree/d8b896254ed4e18ba8117dcd5bf9a54708bdeaaf/src/main/java/se/citerus/dddsample/interfaces/tracking/CargoTrackingViewAdapter.java#L77-L98
public class TempClass {
  public String getStatusText() {
    final Delivery delivery = cargo.delivery();
    final String code = "cargo.status." + delivery.transportStatus().name();

    final Object[] args;
    switch (delivery.transportStatus()) {
      case IN_PORT:
        args = new Object[] {getDisplayText(delivery.lastKnownLocation())};
        break;
      case ONBOARD_CARRIER:
        args = new Object[] {delivery.currentVoyage().voyageNumber().idString()};
        break;
      case CLAIMED:
      case NOT_RECEIVED:
      case UNKNOWN:
      default:
        args = null;
        break;
    }
    
    return messageSource.getMessage(code, args, "[Unknown status]", locale);
  }

}