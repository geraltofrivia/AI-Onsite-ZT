// https://github.com/PaperMC/Velocity/tree/e99407132fcfcf90f03b2554e0ae0bd5f8c83452/proxy/src/main/java/com/velocitypowered/proxy/command/invocation/AbstractCommandInvocation.java#L49-L64
public class TempClass {
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final AbstractCommandInvocation<?> that = (AbstractCommandInvocation<?>) o;

    if (!this.source.equals(that.source)) {
      return false;
    }
    return this.arguments.equals(that.arguments);
  }

}