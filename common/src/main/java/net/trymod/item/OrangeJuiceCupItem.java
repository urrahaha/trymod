package net.trymod.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.trymod.fluid.OrangeJuiceFluid;
import org.jetbrains.annotations.NotNull;

import static net.trymod.registry.TryRegistry.*;

public class OrangeJuiceCupItem extends Item {
    private static final int DRINK_DURATION = 32;
    private final boolean empty;

    public OrangeJuiceCupItem(boolean empty) {
        super(new Item.Properties()
                .arch$tab(TRY_TAB)
                .fireResistant()
        );
        this.empty = empty;
    }

    @NotNull
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        if (livingEntity instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverPlayer, itemStack);
            serverPlayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (livingEntity instanceof Player && !((Player)livingEntity).getAbilities().instabuild) {
            itemStack.shrink(1);
            if (!itemStack.isEmpty()) ((Player) livingEntity).getInventory().add(new ItemStack(ORANGEJUICE_CUP_EMPTY));

            BlockPos blockPos = BlockPos.containing(livingEntity.position());
            BlockState blockState = level.getBlockState(blockPos);
            Fluid fluid = OrangeJuiceFluid.FLUID.sourceRegistry.get();

            if (blockState.canBeReplaced(fluid)) {
                level.setBlock(BlockPos.containing(livingEntity.position()), fluid.defaultFluidState().createLegacyBlock(), 11);
            }
        }

        if (!level.isClientSide) {
            livingEntity.removeAllEffects();
        }

        return itemStack.isEmpty() ? new ItemStack(ORANGEJUICE_CUP_EMPTY) : itemStack;
    }

    public int getUseDuration(ItemStack itemStack) {
        return 32;
    }

    @NotNull
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }

    @NotNull
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        return empty ? InteractionResultHolder.pass(player.getItemInHand(interactionHand)) : ItemUtils.startUsingInstantly(level, player, interactionHand);
    }
}
