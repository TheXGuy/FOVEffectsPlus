package me.thexguy.foveffectsplus.mixin;

import me.thexguy.foveffectsplus.FOVOptionInstance;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Options.class)
public abstract class OptionsMixin {
    @Mutable
    @Final
    @Shadow
    private final OptionInstance<Integer> fov;

    protected OptionsMixin(OptionInstance<Integer> fov) {
        this.fov = fov;
    }

    @Inject(method = "fov", at = @At("RETURN"), cancellable = true)
    private void fov(CallbackInfoReturnable<OptionInstance<Integer>> cir) {
        fov.set(FOVOptionInstance.fov.get());
        cir.setReturnValue(FOVOptionInstance.fov);
    }

    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/OptionInstance$IntRange;<init>(II)V", ordinal = 10))
    private void options(Args args) {
        args.set(0, Integer.MIN_VALUE);
        args.set(1, Integer.MAX_VALUE);
    }
}
