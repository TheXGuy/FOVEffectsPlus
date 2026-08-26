package me.thexguy.foveffectsplus.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import me.thexguy.foveffectsplus.config.ModConfig;
import net.minecraft.client.Camera;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Camera.class)
public abstract class CameraMixin {
    @Unique
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    @ModifyArg(method = "modifyFovBasedOnDeathOrFluid", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;lerp(FFF)F"),index=2)
    private float modifyFovBasedOnDeathOrFluid(float alpha1) {
        // Submerged FOV Modifier
        if (config.individualOptions.submergedOptions.submergedToggle) {
            return Mth.clamp(1.0f - ((1.0f - alpha1) * (config.individualOptions.submergedOptions.submergedModifier / 100f)), (config.individualOptions.submergedOptions.FOVMinClamp / 100f), (config.individualOptions.submergedOptions.FOVMaxClamp / 100f));
        } else {
            return 1.0f;
        }
    }

    @ModifyArgs(method = "tickFov", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(FFF)F"))
    private void tickFov(Args args) {
        args.set(1,config.globalOptions.globalFOVMinClamp / 100f);
        args.set(2,config.globalOptions.globalFOVMaxClamp / 100f);
    }
}
