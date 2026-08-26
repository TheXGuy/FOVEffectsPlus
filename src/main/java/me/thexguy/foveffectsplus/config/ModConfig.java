package me.thexguy.foveffectsplus.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "foveffectsplus")
public class ModConfig implements ConfigData {

    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public GlobalOptions globalOptions = new GlobalOptions();

    @ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
    public IndividualOptions individualOptions = new IndividualOptions();

    public static class GlobalOptions {
        @ConfigEntry.Gui.Tooltip
        public int minFOV = 30;

        @ConfigEntry.Gui.Tooltip
        public int maxFOV = 110;

        @ConfigEntry.Gui.Tooltip
        public boolean fovEffectsToggle = true;

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
        public long globalFOVModifier = 100;

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
        public long globalFOVMinClamp = 10;

        @ConfigEntry.Gui.Tooltip
        @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
        public long globalFOVMaxClamp = 150;
    }

    public static class IndividualOptions {
        @ConfigEntry.Gui.CollapsibleObject
        public SprintingOptions sprintingOptions = new SprintingOptions();

        public static class SprintingOptions {
            @ConfigEntry.Gui.Tooltip
            public boolean sprintingToggle = true;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long sprintingModifier = 100;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMinClamp = 10;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMaxClamp = 150;
        }

        @ConfigEntry.Gui.CollapsibleObject
        public SpeedEffectOptions speedEffectOptions = new SpeedEffectOptions();

        public static class SpeedEffectOptions {
            @ConfigEntry.Gui.Tooltip
            public boolean speedEffectToggle = true;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long speedEffectModifier = 100;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMinClamp = 10;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMaxClamp = 150;
        }

        @ConfigEntry.Gui.CollapsibleObject
        public SlownessEffectOptions slownessEffectOptions = new SlownessEffectOptions();

        public static class SlownessEffectOptions {
            @ConfigEntry.Gui.Tooltip
            public boolean slownessEffectToggle = true;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long slownessEffectModifier = 100;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMinClamp = 10;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMaxClamp = 150;
        }

        @ConfigEntry.Gui.CollapsibleObject
        public FlyingOptions flyingOptions = new FlyingOptions();

        public static class FlyingOptions {
            @ConfigEntry.Gui.Tooltip
            public boolean flyingToggle = true;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long flyingModifier = 100;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMinClamp = 10;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMaxClamp = 150;
        }

        @ConfigEntry.Gui.CollapsibleObject
        public BowOptions bowOptions = new BowOptions();

        public static class BowOptions {
            @ConfigEntry.Gui.Tooltip
            public boolean bowToggle = true;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long bowModifier = 100;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMinClamp = 10;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMaxClamp = 150;
        }

        @ConfigEntry.Gui.CollapsibleObject
        public PowderedSnowOptions powderedSnowOptions = new PowderedSnowOptions();

        public static class PowderedSnowOptions {
            @ConfigEntry.Gui.Tooltip
            public boolean powderedSnowToggle = true;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long powderedSnowModifier = 100;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMinClamp = 10;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMaxClamp = 150;
        }

        @ConfigEntry.Gui.CollapsibleObject
        public SoulSpeedOptions soulSpeedOptions = new SoulSpeedOptions();

        public static class SoulSpeedOptions {
            @ConfigEntry.Gui.Tooltip
            public boolean soulSpeedToggle = true;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long soulSpeedModifier = 100;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMinClamp = 10;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMaxClamp = 150;
        }

        @ConfigEntry.Gui.CollapsibleObject
        public SubmergedOptions submergedOptions = new SubmergedOptions();

        public static class SubmergedOptions {
            @ConfigEntry.Gui.Tooltip
            public boolean submergedToggle = true;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long submergedModifier = 100;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMinClamp = 10;

            @ConfigEntry.Gui.Tooltip
            @ConfigEntry.BoundedDiscrete(min = 1, max = 200)
            public long FOVMaxClamp = 150;
        }
    }
}
