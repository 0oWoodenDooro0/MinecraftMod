package com.google.vincent031525.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class EatableWaterBucket extends Item {
    public EatableWaterBucket() {
        super(new Item.Properties()
                .stacksTo(1)
                .food(new FoodProperties.Builder()
                        .alwaysEdible()
                        .build()
                )
        );
    }
}
