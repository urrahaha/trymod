package net.trymod.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static net.trymod.registry.TryRegistry.ORANGEJUICE_CUP;
import static net.trymod.registry.TryRegistry.TRY_TAB;

public class OrangeJuiceCup extends Item {
    private static final int DRINK_DURATION = 32;
    private final boolean empty;

    public OrangeJuiceCup(boolean empty) {
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
            if (!itemStack.isEmpty()) ((Player) livingEntity).getInventory().add(new ItemStack(ORANGEJUICE_CUP));
        }

        if (!level.isClientSide) {
            livingEntity.removeAllEffects();
        }

        return itemStack.isEmpty() ? new ItemStack(Items.BUCKET) : itemStack;
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
