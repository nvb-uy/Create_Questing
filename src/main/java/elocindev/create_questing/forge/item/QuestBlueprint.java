package elocindev.create_questing.forge.item;

import java.util.List;

import dev.ftb.mods.ftbquests.FTBQuests;
import dev.ftb.mods.ftbquests.client.ClientQuestFile;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class QuestBlueprint extends Item {
    public QuestBlueprint() {
        super((new Properties())
         .stacksTo(1)
         .tab(CreativeModeTab.TAB_TOOLS)
        );
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
      if (world.isClientSide()) {
         FTBQuests.PROXY.openGui();
      }

      return new InteractionResultHolder(InteractionResult.SUCCESS, player.getItemInHand(hand));
   }

   @OnlyIn(Dist.CLIENT)
   public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
      if (ClientQuestFile.exists() && ClientQuestFile.INSTANCE.disableGui && !ClientQuestFile.INSTANCE.canEdit()) {
         tooltip.add(Component.translatable("item.ftbquests.book.disabled").withStyle(ChatFormatting.RED));
      } else {
         tooltip.add(Component.translatable("item.ftbquests.book.tooltip").withStyle(ChatFormatting.GRAY));
      }

   }
}
