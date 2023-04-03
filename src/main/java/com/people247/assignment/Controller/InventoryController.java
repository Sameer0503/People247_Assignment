package com.people247.assignment.Controller;

import com.people247.assignment.Entity.Item;
import com.people247.assignment.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "rest/v1/")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    //get, post, update, delete

    @GetMapping(path = "items/")
    public ResponseEntity getItems(){
        List<Item> response = inventoryService.getItems();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(path = "item/")
    public ResponseEntity createItem(@RequestBody Item item){
        return inventoryService.addItem(item) ? new ResponseEntity(item, HttpStatus.CREATED) : new ResponseEntity(item, HttpStatus.BAD_REQUEST);
    }

    @PatchMapping(path = "item/{id}/")
    public ResponseEntity updateItem(@PathVariable int id, @RequestBody Item item){
        return inventoryService.updateItem(id, item) ? new ResponseEntity<>(item, HttpStatus.OK) : new ResponseEntity<>(item, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "item/{id}/")
    public ResponseEntity deleteItem(@PathVariable int id){
        Item deletedItem = inventoryService.removeItem(id);
        if(deletedItem != null){
            return new ResponseEntity(deletedItem, HttpStatus.OK);
        }
        return new ResponseEntity("Failed to delete", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "item/status/{id}/")
    public ResponseEntity getItemStockStatus(@PathVariable int id){
        String response = inventoryService.getItemStockStatus(id);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
