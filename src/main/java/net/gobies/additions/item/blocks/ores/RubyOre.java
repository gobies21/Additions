package net.gobies.additions.item.blocks.ores;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class RubyOre extends DropExperienceBlock {
    public RubyOre(BlockBehaviour.Properties properties) {
        super(properties, UniformInt.of(1, 3));
    }
}
