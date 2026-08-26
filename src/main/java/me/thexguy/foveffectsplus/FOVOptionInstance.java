package me.thexguy.foveffectsplus;

import com.mojang.serialization.Codec;
import me.shedaniel.autoconfig.AutoConfig;
import me.thexguy.foveffectsplus.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.text.Text;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Consumer;

public class FOVOptionInstance {
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    private static int currentFOV = setCurrentFOV();
    public static SimpleOption<Integer> fov = makeFOV();

    private static int setCurrentFOV() {
        try (Scanner myReader = new Scanner(MinecraftClient.getInstance().options.getOptionsFile())) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains("fov:")) {
                    return (int) (40 * Double.parseDouble(data.split(":")[1]) + 70);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return 70;
    }

    public static SimpleOption<Integer> makeFOV() {
        if (fov != null) {
            currentFOV = fov.getValue();
        }

        fov = new SimpleOption<>("options.fov", SimpleOption.emptyTooltip(), (caption, value) -> {
            Text var10000;
            switch (value) {
                case 70 -> var10000 = GameOptions.getGenericValueText(caption, Text.translatable("options.fov.min"));
                case 110 -> var10000 = GameOptions.getGenericValueText(caption, Text.translatable("options.fov.max"));
                default -> var10000 = GameOptions.getGenericValueText(caption, value);
            }

            return var10000;
        }, new SimpleOption.ValidatingIntSliderCallbacks(config.globalOptions.minFOV, config.globalOptions.maxFOV), Codec.DOUBLE.xmap((value) -> (int)(value * (double)40.0F + (double)70.0F), (value) -> ((double)value - (double)70.0F) / (double)40.0F), currentFOV, (value) -> refreshWorldRenderer(WorldRenderer::scheduleTerrainUpdate));
        return fov;
    }

    private static void refreshWorldRenderer(Consumer<WorldRenderer> refresher) {
        WorldRenderer worldRenderer = MinecraftClient.getInstance().worldRenderer;
        if (worldRenderer != null) {
            refresher.accept(worldRenderer);
        }
    }

}
