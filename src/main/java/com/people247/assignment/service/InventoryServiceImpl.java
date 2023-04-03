package com.people247.assignment.service;

import com.people247.assignment.Entity.Item;
import com.people247.assignment.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private ItemDao itemDao;
    @Override
    public List<Item> getItems() {
        return itemDao.findAll();
    }

    @Override
    public boolean addItem(Item item) {
        Item savedItem = itemDao.save(item);
        if(savedItem != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateItem(int itemID, Item item) {
        Optional<Item> prevItem = itemDao.findById(itemID);
        Item updatedItem = itemDao.save(item);
        if(updatedItem != null){
            return true;
        }
        return false;
    }

    @Override
    public Item removeItem(int itemID) {
        Optional<Item> deletedItem = itemDao.findById(itemID);
        if(deletedItem.isPresent()){
            itemDao.delete(deletedItem.get());
            return deletedItem.get();
        }
        return null;
    }

    @Override
    public String getItemStockStatus(int itemID) {
        Optional<Item> item = itemDao.findById(itemID);
        if(item.isPresent()){
            return item.get().getStatus();
        }
        return null;
    }
}
