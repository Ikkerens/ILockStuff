package com.ikkerens.ils;

import com.ikkerens.ils.model.cost.ItemCost;

public class Config {
    private final ItemCost[] item_cost;
    private final int        money_cost;

    public Config() {
        this.item_cost = new ItemCost[] { new ItemCost() };
        this.money_cost = 0;
    }

    public ItemCost[] getItemCost() {
        return this.item_cost;
    }

    public int getMoneyCost() {
        return this.money_cost;
    }
}
