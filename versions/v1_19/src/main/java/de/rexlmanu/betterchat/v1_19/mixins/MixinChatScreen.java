package de.rexlmanu.betterchat.v1_19.mixins;

import com.mojang.blaze3d.vertex.PoseStack;
import de.rexlmanu.betterchat.core.BetterChatAddon;
import java.util.List;
import net.labymod.api.inject.LabyGuice;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ProfileKeyPairManager;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Signer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChatScreen.class)
public abstract class MixinChatScreen extends Screen {

  protected MixinChatScreen(Component $$0) {
    super($$0);
  }

  @Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ServerData$ChatPreview;showToast()Z"))
  private boolean showToast(ServerData.ChatPreview packet) {
    if (LabyGuice.getInstance(BetterChatAddon.class).configuration().enabled().get() && LabyGuice.getInstance(
        BetterChatAddon.class).configuration().hideChatSignatureNotification().get()) {
      return false;
    }
    return packet.showToast();
  }

  @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/ChatScreen;renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/util/List;II)V"))
  private void renderTooltipRedirect(ChatScreen screen, PoseStack poseStack,
      List<? extends FormattedCharSequence> list, int i, int j) {
    if (LabyGuice.getInstance(BetterChatAddon.class).configuration().enabled().get() && LabyGuice.getInstance(
        BetterChatAddon.class).configuration().hideChatSignature().get()) {
      return;
    }

    this.renderTooltip(poseStack, list, i, j);
  }

  @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ProfileKeyPairManager;signer()Lnet/minecraft/util/Signer;"))
  private Signer signer(ProfileKeyPairManager manager) {
    if (LabyGuice.getInstance(BetterChatAddon.class).configuration().enabled().get() && LabyGuice.getInstance(
        BetterChatAddon.class).configuration().hideChatSignature().get()) {
      return null;
    }
    return manager.signer();
  }

}