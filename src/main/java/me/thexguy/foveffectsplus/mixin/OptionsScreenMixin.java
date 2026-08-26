package me.thexguy.foveffectsplus.mixin;

import me.thexguy.foveffectsplus.FOVOptionInstance;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptionsScreen.class)
public abstract class OptionsScreenMixin {

    @Inject(method = "init", at = @At("HEAD"))
    private void init(CallbackInfo ci) {
        FOVOptionInstance.makeFOV();
    }
}
