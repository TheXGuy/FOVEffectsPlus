package me.thexguy.foveffectsplus.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import me.thexguy.foveffectsplus.config.ModConfig;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = AbstractClientPlayerEntity.class, priority = 1)
public abstract class AbstractClientPlayerMixin {
    @Unique
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    @Unique
    public int powderSnowTickCount = 0;
    @Unique
    private static final int powderSnowTransitionTickCount = 120;

    /**
     * @author TheXGuy
     * @reason Modifying FOV.
     */
    @Overwrite
    public float getFovMultiplier(final boolean firstPerson, final float effectScale) {
        AbstractClientPlayerEntity abstractClientPlayer = (AbstractClientPlayerEntity) (Object) this;

        if (!config.globalOptions.fovEffectsToggle) {
            return 1.0f;
        }

        float globalFOVMod = config.globalOptions.globalFOVModifier / 100f;
        float modifier = 1.0F;

        // Sprinting FOV Modifier
        if (config.individualOptions.sprintingOptions.sprintingToggle && abstractClientPlayer.isSprinting()) {
            modifier *= MathHelper.clamp((1F + (0.15F * (config.individualOptions.sprintingOptions.sprintingModifier / 100f))) * globalFOVMod, (config.individualOptions.sprintingOptions.FOVMinClamp / 100f), (config.individualOptions.sprintingOptions.FOVMaxClamp / 100f));
        }

        // Speed Effect FOV Modifier
        StatusEffectInstance speedInstance = abstractClientPlayer.getStatusEffect(StatusEffects.SPEED);
        if (config.individualOptions.speedEffectOptions.speedEffectToggle && speedInstance != null) {
            modifier *= MathHelper.clamp((1F + ((Math.clamp(0.15F * speedInstance.getAmplifier(), 0, 10)) * (config.individualOptions.speedEffectOptions.speedEffectModifier / 100f))) * globalFOVMod, (config.individualOptions.speedEffectOptions.FOVMinClamp / 100f), (config.individualOptions.speedEffectOptions.FOVMaxClamp / 100f));
        }

        // Slowness Effect FOV Modifier
        StatusEffectInstance slownessInstance = abstractClientPlayer.getStatusEffect(StatusEffects.SLOWNESS);
        if (config.individualOptions.slownessEffectOptions.slownessEffectToggle && slownessInstance != null) {
            modifier *= MathHelper.clamp((1F - ((Math.clamp(0.15F * slownessInstance.getAmplifier(), 0, 10)) * (config.individualOptions.slownessEffectOptions.slownessEffectModifier / 100f))) * globalFOVMod, (config.individualOptions.slownessEffectOptions.FOVMinClamp / 100f), (config.individualOptions.slownessEffectOptions.FOVMaxClamp / 100f));
        }

        // Flying FOV Modifier
        if (config.individualOptions.flyingOptions.flyingToggle && abstractClientPlayer.getAbilities().flying) {
            modifier *= MathHelper.clamp((1.1F * (config.individualOptions.flyingOptions.flyingModifier / 100f)) * globalFOVMod, (config.individualOptions.flyingOptions.FOVMinClamp / 100f), (config.individualOptions.flyingOptions.FOVMaxClamp / 100f));
        }

        // Bow FOV Modifier
        if (abstractClientPlayer.isUsingItem()) {
            if (config.individualOptions.bowOptions.bowToggle && abstractClientPlayer.getActiveItem().isOf(Items.BOW)) {
                float scale = Math.min((float)abstractClientPlayer.getItemUseTime() / 20.0F, 1.0F);
                modifier *= MathHelper.clamp((1.0F - ((MathHelper.square(scale) * 0.15F) * (config.individualOptions.bowOptions.bowModifier / 100f))) * globalFOVMod, (config.individualOptions.bowOptions.FOVMinClamp / 100f), (config.individualOptions.bowOptions.FOVMaxClamp / 100f));
            // Spyglass
            } else if (firstPerson && abstractClientPlayer.isUsingSpyglass()) {
                return 0.1F;
            }
        }

        // Powdered Snow FOV Modifier
        if (config.individualOptions.powderedSnowOptions.powderedSnowToggle) {
            if (abstractClientPlayer.inPowderSnow) {
                if (powderSnowTickCount < powderSnowTransitionTickCount) {
                    powderSnowTickCount += 1;
                }
            } else if (powderSnowTickCount > 0) {
                powderSnowTickCount -= 2;
                if (powderSnowTickCount < 0) {
                    powderSnowTickCount = 0;
                }
            }
            modifier *= MathHelper.clamp((1.0F - (((0.25F * (config.individualOptions.powderedSnowOptions.powderedSnowModifier / 100f)) / powderSnowTransitionTickCount) * powderSnowTickCount)) * globalFOVMod, (config.individualOptions.powderedSnowOptions.FOVMinClamp / 100f), (config.individualOptions.powderedSnowOptions.FOVMaxClamp / 100f));
        }

        // Soul Speed FOV Modifier
        if (config.individualOptions.soulSpeedOptions.soulSpeedToggle && abstractClientPlayer.getSteppingBlockState().isIn(BlockTags.SOUL_SPEED_BLOCKS)) {
            ItemStack itemStack = abstractClientPlayer.getEquippedStack(EquipmentSlot.FEET);
            ItemEnchantmentsComponent itemEnchantments = itemStack.getEnchantments();
            for (RegistryEntry<Enchantment> enchantmentHolder : itemEnchantments.getEnchantments()) {
                if (enchantmentHolder.matchesId(Enchantments.SOUL_SPEED.getValue())) {
                    modifier *= MathHelper.clamp((1F + ((Math.clamp(0.15F * itemEnchantments.getLevel(enchantmentHolder), 0, 10)) * (config.individualOptions.soulSpeedOptions.soulSpeedModifier / 100f))) * globalFOVMod, (config.individualOptions.soulSpeedOptions.FOVMinClamp / 100f), (config.individualOptions.soulSpeedOptions.FOVMaxClamp / 100f));
                    break;
                }
            }
        }

        return MathHelper.lerp(effectScale, 1.0F, modifier);
    }
}
