package org.dfood.item;

import net.minecraft.block.Block;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;

public class ModGlassBottleItem extends GlassBottleItem implements HaveBlock {
    private final Block block;

    public ModGlassBottleItem(Block block, Settings settings) {
        super(settings);
        this.block = block;
    }

    @Override
    public Block getBlock() {
        return block;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (super.useOnBlock(context) != ActionResult.PASS || shouldPassToVanilla(context)) {
            return ActionResult.PASS;
        }
        return placeOrConsume(context, () -> context.getStack().contains(DataComponentTypes.FOOD), c -> this.use(c.getWorld(), c.getPlayer(), c.getHand()));
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        appendBlockTooltip(stack, context, tooltip, type);
    }
}
