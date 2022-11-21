package de.rexlmanu.betterchat.v1_19.mixins;

import de.rexlmanu.betterchat.core.BetterChatAddon;
import net.labymod.api.inject.LabyGuice;
import net.minecraft.client.GuiMessage;
import net.minecraft.client.GuiMessageTag;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatComponent.class)
public class MixinChatComponent {

  @Inject(method = "clearMessages", at = @At("HEAD"), cancellable = true)
  public void clear(boolean $$0, CallbackInfo ci) {
    if (LabyGuice.getInstance(BetterChatAddon.class).configuration().enabled().get() && LabyGuice.getInstance(
        BetterChatAddon.class).configuration().keepHistory().get()) {
      ci.cancel();
    }
  }

  @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/GuiMessage$Line;tag()Lnet/minecraft/client/GuiMessageTag;"))
  private GuiMessageTag tag(GuiMessage.Line line) {
    if (LabyGuice.getInstance(BetterChatAddon.class).configuration().enabled().get() && LabyGuice.getInstance(
        BetterChatAddon.class).configuration().hideChatSignature().get()) {
      return null;
    }
    return line.tag();
  }
}
