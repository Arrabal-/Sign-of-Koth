package com.arrabal.koth.api.biome;

import net.minecraft.world.biome.BiomeGenBase;

/**
 * Created by Arrabal on 3/21/2016.
 */
public class SoKBiome extends BiomeGenBase {


    // default constructor.  Probably needs to be replaced.
    public SoKBiome(BiomeProperties properties) {
        super(properties);
    }

    protected static class PropertiesBuilder {
        private final String biomeName;

        private int biomeMapColor = 0xffffff;
        private float baseHeight = 0.1F;
        private float heightVariation = 0.2F;
        private float temperature = 0.5F;
        private float rainfall = 0.5F;
        private int waterColor = 16777215;
        private boolean enableSnow;
        private boolean enableRain = true;
        private String baseBiomeRegName;

        public PropertiesBuilder(String name) {
            this.biomeName = name;
        }

        public PropertiesBuilder withMapColor(Integer color){
            if (color != null) this.biomeMapColor = color;
            return this;
        }

        public PropertiesBuilder withTemperature(Float temperature){
            if (temperature != null) this.temperature = temperature;
            return this;
        }

        public PropertiesBuilder withRainfall(Float rainfall){
            if (rainfall != null) this.rainfall = rainfall;
            return this;
        }

        public PropertiesBuilder withBaseHeight(Float baseHeight){
            if (baseHeight != null) this.baseHeight = baseHeight;
            return this;
        }

        public PropertiesBuilder withHeightVariation(Float heightVariation){
            if (heightVariation != null) this.heightVariation = heightVariation;
            return this;
        }

        public PropertiesBuilder withRainDisabled(){
            this.enableRain = false;
            return this;
        }

        public PropertiesBuilder withSnowEnabled(){
            this.enableSnow = true;
            return this;
        }

        public PropertiesBuilder withWaterColor(Integer waterColor){
            if (waterColor != null) this.waterColor = waterColor;
            return this;
        }

        public PropertiesBuilder withBaseBiome(String name){
            if (name != null) this.baseBiomeRegName = name;
            return this;
        }

        public SoKBiomeProperties buildBiome(){
            return new SoKBiomeProperties(this.biomeName, this.temperature, this.rainfall, this.baseHeight, this.heightVariation,
                    this.enableRain, this.enableSnow, this.waterColor, this.baseBiomeRegName, this.biomeMapColor);
        }
    }

    private static class SoKBiomeProperties extends BiomeProperties {

        private int biomeMapColor = 0xffffff;

        private SoKBiomeProperties(String name, float temperature, float rainfall, float baseHeight, float heightVariation,
                                   boolean enableRain, boolean enableSnow, int waterColor, String baseBiomeRegName, int biomeMapColor){
            super(name);
            this.setTemperature(temperature);
            this.setRainfall(rainfall);
            this.setBaseHeight(baseHeight);
            this.setHeightVariation(heightVariation);
            if (!enableRain) this.setRainDisabled();
            if (enableSnow) this.setSnowEnabled();
            this.setWaterColor(waterColor);
            this.setBaseBiome(baseBiomeRegName);

            this.biomeMapColor = biomeMapColor;
        }
    }
}
