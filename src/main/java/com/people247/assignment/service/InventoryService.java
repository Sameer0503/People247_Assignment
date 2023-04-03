package com.people247.assignment.service;

import com.people247.assignment.Entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InventoryService {
    // add item
    // update item
    // get items
    // remove item
    // get the stock ? check the status of the item

    List<Item> getItems();
    boolean addItem(Item item);

    boolean updateItem(int itemID , Item item);

    Item removeItem(int itemID);

    String getItemStockStatus(int itemID);

}
