package com.test.sharkpos.controller;

import com.test.sharkpos.dto.InventoryItemsDto;
import com.test.sharkpos.exception.RecordNotFoundException;
import com.test.sharkpos.model.InventoryItems;
import com.test.sharkpos.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventoryItems")
public class InventoryController {
    //Get Mapping
    @Autowired
    private InventoryService inventoryService;
    @GetMapping
    public List<InventoryItems> getInventoryItems(){
        return inventoryService.getInventoryItems();
    }

    @PostMapping
    public  InventoryItems addInventory(@RequestBody InventoryItems inventoryItems ) throws RecordNotFoundException {
        return  inventoryService.addInventory(inventoryItems);
    }

    @DeleteMapping("{id}")
    public  String deleteInventory(@PathVariable Long id)  {
        return inventoryService.deleteInventoryById(id);
    }
    @PutMapping("{id}")
    public  InventoryItems update(@RequestBody InventoryItems inventoryItems) {
        return inventoryService.updateInventory(inventoryItems);
        // return vendor;
    }
    //for generating a request using request param
    @PutMapping("/buyInventory")
    public String buyInventory(@RequestParam (name = "id") Long id,@RequestParam(name = "quantity") int quantity) throws RecordNotFoundException {
        return inventoryService.buyInventory(id,quantity);
    }
    // getting a specific data from Dto and hiding other
  @PostMapping("/inventoryItemsDto/{id}")
    public  InventoryItemsDto getInventoryDto(@PathVariable Long id){
        return inventoryService.getInventoryItemsDto(id);
  }
}
