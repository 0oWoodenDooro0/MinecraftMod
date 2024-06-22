package com.google.vincent031525.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "minecraft");

    public static final RegistryObject<Item> EATABLE_WATER_BUCKET = ITEMS.register("water_bucket", EatableWaterBucket::new);

    public static final RegistryObject<Item> EGG_BOW_ITEM = ITEMS.register("bow", EggBowItem::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
