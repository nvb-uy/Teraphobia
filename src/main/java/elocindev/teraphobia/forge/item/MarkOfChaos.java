package elocindev.teraphobia.forge.item;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class MarkOfChaos extends FlintAndSteelItem {
    public MarkOfChaos(Properties arg) {
        super(arg);
    }
 
    @SuppressWarnings("null")
    @Override
    public void appendHoverText(ItemStack arg, @Nullable Level arg2, List<Component> list, TooltipFlag arg3) {
        super.appendHoverText(arg, arg2, list, arg3);

        list.add(Component.translatable("item.teraphobia.mark_of_chaos.tooltip").setStyle(Style.EMPTY.withColor(ChatFormatting.GRAY)));
    }
}
