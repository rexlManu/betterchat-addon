package de.rexlmanu.betterchat.v1_19.mixins;

import de.rexlmanu.betterchat.core.BetterChatAddon;
import net.labymod.api.inject.LabyGuice;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.protocol.game.ClientboundServerDataPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPacketListener.class)
public abstract class MixinClientPacketListener {

  @Redirect(method = "handleServerData", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/protocol/game/ClientboundServerDataPacket;enforcesSecureChat()Z", ordinal = 1))
  private boolean enforcesSecureChat(ClientboundServerDataPacket packet) {
    if (LabyGuice.getInstance(BetterChatAddon.class).configuration().enabled().get()
        && LabyGuice.getInstance(BetterChatAddon.class).configuration()
        .hideChatSignatureNotification().get()) {
      return true;
    }
    return packet.enforcesSecureChat();
  }

  @Redirect(method = "handleServerData", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/multiplayer/ServerData$ChatPreview;isAcknowledged()Z"))
  private boolean isAcknowledged(ServerData.ChatPreview preview) {
    if (LabyGuice.getInstance(BetterChatAddon.class).configuration().enabled().get()
        && LabyGuice.getInstance(BetterChatAddon.class).configuration()
        .hideChatSignatureNotification().get()) {
      return true;
    }
    return preview.isAcknowledged();
  }

}
