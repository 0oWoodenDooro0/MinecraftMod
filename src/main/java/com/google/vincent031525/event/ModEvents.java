package com.google.vincent031525.event;

import com.google.vincent031525.TestMod;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TestMod.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void takeSkeletonBow(PlayerInteractEvent.EntityInteract event) {
        if (event.getLevel().isClientSide) return;
        if (event.getEntity().getMainHandItem() != ItemStack.EMPTY) return;
        if (event.getTarget() instanceof AbstractSkeleton target) {
            ItemStack itemStack = target.getMainHandItem();
            target.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            event.getEntity().setItemInHand(InteractionHand.MAIN_HAND, itemStack);
        }
        if (event.getTarget() instanceof Pillager target) {
            ItemStack itemStack = target.getMainHandItem();
            target.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            event.getEntity().setItemInHand(InteractionHand.MAIN_HAND, itemStack);
        }
    }
}
