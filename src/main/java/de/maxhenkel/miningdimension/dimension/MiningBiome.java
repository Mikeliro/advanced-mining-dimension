package de.maxhenkel.miningdimension.dimension;

import de.maxhenkel.miningdimension.Config;
import de.maxhenkel.miningdimension.Main;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class MiningBiome extends Biome {

    public MiningBiome() {
        super(new Builder()
                .surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
                .precipitation(RainType.NONE)
                .category(Category.PLAINS)
                .depth(0.125F)
                .scale(0.05F)
                .temperature(0.8F)
                .downfall(0.4F)
                .waterColor(4159204)
                .waterFogColor(329011)
                .parent(null)
        );

        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.SKELETON, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
        addSpawn(EntityClassification.MONSTER, new SpawnListEntry(EntityType.WITCH, 5, 1, 1));
    }

    public static final ConfiguredFeature LAKE = Feature.LAKE.func_225566_b_(new BlockStateFeatureConfig(Blocks.LAVA.getDefaultState())).func_227228_a_(Placement.LAVA_LAKE.func_227446_a_(new ChanceConfig(80)));
    public static final ConfiguredFeature MONSTER_ROOM = Feature.MONSTER_ROOM.func_225566_b_(IFeatureConfig.NO_FEATURE_CONFIG).func_227228_a_(Placement.DUNGEONS.func_227446_a_(new ChanceConfig(8)));

    public static final ConfiguredFeature DIRT = Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIRT.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 256)));
    public static final ConfiguredFeature GRAVEL = Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRAVEL.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(8, 0, 0, 256)));
    public static final ConfiguredFeature GRANITE = Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GRANITE.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 80)));
    public static final ConfiguredFeature DIORITE = Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIORITE.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 80)));
    public static final ConfiguredFeature ANDESITE = Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.ANDESITE.getDefaultState(), 33)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(10, 0, 0, 80)));

    public static final ConfiguredFeature COAL_ORE = Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.COAL_ORE.getDefaultState(), 17)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 0, 0, 128)));
    public static final ConfiguredFeature IRON_ORE = Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.IRON_ORE.getDefaultState(), 9)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(20, 0, 0, 64)));
    public static final ConfiguredFeature GOLD_ORE = Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.GOLD_ORE.getDefaultState(), 9)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(2, 0, 0, 32)));
    public static final ConfiguredFeature REDSTONE_ORE = Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.REDSTONE_ORE.getDefaultState(), 8)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(8, 0, 0, 16)));
    public static final ConfiguredFeature DIAMOND_ORE = Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.DIAMOND_ORE.getDefaultState(), 8)).func_227228_a_(Placement.COUNT_RANGE.func_227446_a_(new CountRangeConfig(1, 0, 0, 16)));
    public static final ConfiguredFeature LAPIS_ORE = Feature.ORE.func_225566_b_(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.LAPIS_ORE.getDefaultState(), 7)).func_227228_a_(Placement.COUNT_DEPTH_AVERAGE.func_227446_a_(new DepthAverageConfig(1, 16, 16)));

    public void initializeFeatures() {
        flowers.clear();
        structures.clear();

        carvers.values().forEach(carvers -> {
            carvers.removeIf(configuredCarver -> {
                if (configuredCarver.carver instanceof CaveWorldCarver) {
                    return true;
                } else if (configuredCarver.carver instanceof CanyonWorldCarver) {
                    return true;
                }
                return false;
            });
        });

        features.values().forEach(features -> {
            features.removeIf(feature -> {
                return feature == LAKE ||
                        feature == MONSTER_ROOM ||
                        feature == DIRT ||
                        feature == GRAVEL ||
                        feature == GRANITE ||
                        feature == DIORITE ||
                        feature == ANDESITE ||
                        feature == COAL_ORE ||
                        feature == IRON_ORE ||
                        feature == GOLD_ORE ||
                        feature == REDSTONE_ORE ||
                        feature == DIAMOND_ORE ||
                        feature == LAPIS_ORE;
            });
        });

        addCarver(GenerationStage.Carving.AIR, Biome.createCarver(Main.CAVE_CARVER, new ProbabilityConfig(Config.CAVE_PERCENTAGE.get().floatValue())));

        addCarver(GenerationStage.Carving.AIR, Biome.createCarver(Main.CANYON_CARVER, new ProbabilityConfig(Config.CANYON_PERCENTAGE.get().floatValue())));

        if (Config.GENERATE_LAVA_LAKES.get()) {
            addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, LAKE);
        }

        if (Config.GENERATE_SPAWNERS.get()) {
            addFeature(GenerationStage.Decoration.UNDERGROUND_STRUCTURES, MONSTER_ROOM);
        }

        if (Config.GENERATE_STONE_VARIANTS.get()) {
            addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DIRT);
            addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GRAVEL);
            addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GRANITE);
            addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, ANDESITE);
            addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DIORITE);
        }

        if (Config.GENERATE_ORES.get()) {
            addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, COAL_ORE);
            addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, IRON_ORE);
            addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, GOLD_ORE);
            addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, REDSTONE_ORE);
            addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, DIAMOND_ORE);
            addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, LAPIS_ORE);
        }
    }

}
