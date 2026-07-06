// https://github.com/PaperMC/Velocity/tree/e99407132fcfcf90f03b2554e0ae0bd5f8c83452/proxy/src/main/java/com/velocitypowered/proxy/command/builtin/VelocityCommand.java#L153-L194
public class TempClass {
    @Override
    public int run(final CommandContext<CommandSource> context) {
      final CommandSource source = context.getSource();
      final ProxyVersion version = server.getVersion();

      final Component velocity = Component.text()
          .content(version.getName() + " ")
          .decoration(TextDecoration.BOLD, true)
          .color(VELOCITY_COLOR)
          .append(Component.text()
                  .content(version.getVersion())
                  .decoration(TextDecoration.BOLD, false))
          .build();
      final Component copyright = Component
          .translatable("velocity.command.version-copyright",
              Component.text(version.getVendor()),
                  Component.text(version.getName()),
                  Component.text(LocalDate.now().getYear()));
      source.sendMessage(velocity);
      source.sendMessage(copyright);

      if (version.getName().equals("Velocity")) {
        final TextComponent embellishment = Component.text()
            .append(Component.text()
                .content("PaperMC")
                .color(NamedTextColor.GREEN)
                .clickEvent(
                    ClickEvent.openUrl("https://papermc.io/software/velocity"))
                .build())
            .append(Component.text(" - "))
            .append(Component.text()
                .content("GitHub")
                .color(NamedTextColor.GREEN)
                .decoration(TextDecoration.UNDERLINED, true)
                .clickEvent(ClickEvent.openUrl(
                    "https://github.com/PaperMC/Velocity"))
                .build())
            .build();
        source.sendMessage(embellishment);
      }
      return Command.SINGLE_SUCCESS;
    }

}