package com.google.vincent031525.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.List;
import java.util.function.Predicate;

public class EggBowItem extends BowItem {
    public EggBowItem() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public void releaseUsing(ItemStack p_40667_, Level p_40668_, LivingEntity p_40669_, int p_40670_) {
        if (p_40669_ instanceof Player player) {
            ItemStack itemstack = player.getProjectile(p_40667_);
            if (!itemstack.isEmpty()) {
                int i = this.getUseDuration(p_40667_) - p_40670_;
                i = ForgeEventFactory.onArrowLoose(p_40667_, p_40668_, player, i, true);
                if (i < 0) return;

                float f = getPowerForTime(i);
                if (!((double) f < 0.1)) {
                    if (itemstack.getItem() instanceof ArrowItem) {
                        List<ItemStack> list = draw(p_40667_, itemstack, player);
                        if (!p_40668_.isClientSide() && !list.isEmpty()) {
                            this.shoot(p_40668_, player, player.getUsedItemHand(), p_40667_, list, f * 3.0F, 1.0F, f == 1.0F, null);
                        }

                        p_40668_.playSound(
                                null,
                                player.getX(),
                                player.getY(),
                                player.getZ(),
                                SoundEvents.ARROW_SHOOT,
                                SoundSource.PLAYERS,
                                1.0F,
                                1.0F / (p_40668_.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                        );

                        player.awardStat(Stats.ITEM_USED.get(this));
                    } else if (itemstack.getItem() instanceof EggItem) {
                        p_40668_.playSound(
                                null,
                                player.getX(),
                                player.getY(),
                                player.getZ(),
                                SoundEvents.EGG_THROW,
                                SoundSource.PLAYERS,
                                0.5F,
                                0.4F / (p_40668_.getRandom().nextFloat() * 0.4F + 0.8F)
                        );

                        if (!p_40668_.isClientSide()) {
                            ThrownEgg thrownegg = new ThrownEgg(p_40668_, player);
                            thrownegg.setItem(new ItemStack(Items.EGG));
                            thrownegg.shootFromRotation(player, player.getXRot(), player.getYRot(), f * 3.0F, 1.0F, 1.0F);
                            p_40668_.addFreshEntity(thrownegg);
                        }

                        player.awardStat(Stats.ITEM_USED.get(this));
                        itemstack.consume(1, player);
                    }
                }
            }
        }
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return super.getAllSupportedProjectiles().or(item -> item.is(Items.EGG));
    }
}
