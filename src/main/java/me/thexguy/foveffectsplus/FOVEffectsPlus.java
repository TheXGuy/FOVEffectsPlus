package me.thexguy.foveffectsplus;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.thexguy.foveffectsplus.config.ModConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class FOVEffectsPlus implements ModInitializer {
    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isModLoaded("cloth-config")) {
            AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        }
    }
}
