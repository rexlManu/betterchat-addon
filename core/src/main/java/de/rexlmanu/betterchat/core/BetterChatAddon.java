package de.rexlmanu.betterchat.core;

import com.google.inject.Singleton;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonListener;

@Singleton
@AddonListener
public class BetterChatAddon extends LabyAddon<BetterChatConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();
  }

  @Override
  protected Class<BetterChatConfiguration> configurationClass() {
    return BetterChatConfiguration.class;
  }
}
