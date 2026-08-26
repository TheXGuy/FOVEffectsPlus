package me.thexguy.foveffectsplus.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import me.thexguy.foveffectsplus.config.ModConfig;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Unique
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    @ModifyArg(method = "getFov", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;lerp(FFF)F"),index=2)
    private float getFov(float alpha1) {
        // Submerged FOV Modifier
        if (config.individualOptions.submergedOptions.submergedToggle) {
            return MathHelper.clamp(1.0f - ((1.0f - alpha1) * (config.individualOptions.submergedOptions.submergedModifier / 100f)), (config.individualOptions.submergedOptions.FOVMinClamp / 100f), (config.individualOptions.submergedOptions.FOVMaxClamp / 100f));
        } else {
            return 1.0f;
        }
    }

    @ModifyArgs(method = "updateFovMultiplier", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(FFF)F"))
    private void updateFovMultiplier(Args args) {
        args.set(1,config.globalOptions.globalFOVMinClamp / 100f);
        args.set(2,config.globalOptions.globalFOVMaxClamp / 100f);
    }
}
