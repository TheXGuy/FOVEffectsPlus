package me.thexguy.foveffectsplus.mixin;

import me.thexguy.foveffectsplus.FOVOptionInstance;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(GameOptions.class)
public abstract class GameOptionsMixin {
    @Mutable
    @Final
    @Shadow
    private final SimpleOption<Integer> fov;

    protected GameOptionsMixin(SimpleOption<Integer> fov) {
        this.fov = fov;
    }

    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void fov(CallbackInfoReturnable<SimpleOption<Integer>> cir) {
        fov.setValue(FOVOptionInstance.fov.getValue());
        cir.setReturnValue(FOVOptionInstance.fov);
    }

    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/SimpleOption$ValidatingIntSliderCallbacks;<init>(II)V", ordinal = 10))
    private void gameOptions(Args args) {
        args.set(0, Integer.MIN_VALUE);
        args.set(1, Integer.MAX_VALUE);
    }
}
