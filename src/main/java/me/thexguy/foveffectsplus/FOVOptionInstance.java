package me.thexguy.foveffectsplus;

import com.mojang.serialization.Codec;
import me.shedaniel.autoconfig.AutoConfig;
import me.thexguy.foveffectsplus.config.ModConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FOVOptionInstance {
    private static final ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    private static int currentFOV = setCurrentFOV();
    public static OptionInstance<Integer> fov = makeFOV();

    private static int setCurrentFOV() {
        try (Scanner myReader = new Scanner(Minecraft.getInstance().options.getFile())) {
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

    public static OptionInstance<Integer> makeFOV() {
        if (fov != null) {
            currentFOV = fov.get();
        }

        fov = new OptionInstance<>("options.fov", OptionInstance.noTooltip(), (caption, value) -> {
            Component var10000;
            switch (value) {
                case 70 -> var10000 = Options.genericValueLabel(caption, Component.translatable("options.fov.min"));
                case 110 -> var10000 = Options.genericValueLabel(caption, Component.translatable("options.fov.max"));
                default -> var10000 = Options.genericValueLabel(caption, value);
            }

            return var10000;
        }, new OptionInstance.IntRange(config.globalOptions.minFOV, config.globalOptions.maxFOV), Codec.DOUBLE.xmap((value) -> (int)(value * (double)40.0F + (double)70.0F), (value) -> ((double)value - (double)70.0F) / (double)40.0F), currentFOV, OptionInstance.NO_ACTION);
        return fov;
    }
}
