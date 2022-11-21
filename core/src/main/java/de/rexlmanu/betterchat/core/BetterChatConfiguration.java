package de.rexlmanu.betterchat.core;

import lombok.Getter;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;

@SuppressWarnings("FieldMayBeFinal")
@ConfigName("settings")
@Getter
public class BetterChatConfiguration extends AddonConfig {

  @SwitchSetting
  private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);

  @SwitchSetting
  private final ConfigProperty<Boolean> keepHistory = new ConfigProperty<>(true);

  @SwitchSetting
  private final ConfigProperty<Boolean> hideChatSignature = new ConfigProperty<>(true);

  @SwitchSetting
  private final ConfigProperty<Boolean> hideChatSignatureNotification = new ConfigProperty<>(true);
}
