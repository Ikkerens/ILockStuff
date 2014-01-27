package com.ikkerens.ils;

import com.ikkerens.ils.model.cost.ItemCost;

public class Config {
    private final ItemCost[] item_cost;

    public Config() {
        this.item_cost = new ItemCost[] { new ItemCost() };
    }

    public ItemCost[] getItemCost() {
        return this.item_cost;
    }
}
