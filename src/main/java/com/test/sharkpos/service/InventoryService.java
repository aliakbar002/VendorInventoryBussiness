package com.test.sharkpos.service;

import com.test.sharkpos.dto.InventoryItemsDto;
import com.test.sharkpos.exception.RecordNotFoundException;
import com.test.sharkpos.model.InventoryItems;
import com.test.sharkpos.model.Vendor;
import com.test.sharkpos.repository.InventoryRepository;
import com.test.sharkpos.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
     VendorRepository vendorRepository;


    //GetAll Mapping
    public List<InventoryItems> getInventoryItems() {
        return new ArrayList<>(inventoryRepository.findAll());

    }

    //Post Mapping

    public InventoryItems addInventory(InventoryItems inventoryItems) throws RecordNotFoundException {

        Vendor vendor = inventoryItems.getVendor();
        if (vendor.getActive()==0){
            throw new RecordNotFoundException("Vendor is not Active");
        }
        else{
            return inventoryRepository.save(inventoryItems);
        }

      }

    //Delete Mapping By Id
    public String deleteInventoryById(Long id) {
        inventoryRepository.deleteById(id);
        return "Inventory Items Deleted Successfully of id"+" "+id;
    }

    //Put Mapping By Id
    public InventoryItems updateInventory(InventoryItems inventoryItems){

        InventoryItems updateInventory =inventoryRepository.findById(inventoryItems.getId()).orElse(null);


        assert updateInventory != null;
        updateInventory.setAvailableQuantity(inventoryItems.getAvailableQuantity());
        updateInventory.setAmountSold(inventoryItems.getAmountSold());


        return inventoryRepository.save(updateInventory);
      
    }

    public String  buyInventory(Long id, int quantity) throws RecordNotFoundException {
        InventoryItems inventoryItems = inventoryRepository.findById(id).orElse(null);
        assert inventoryItems != null;
          if (quantity<0 || quantity==0){
            throw new RecordNotFoundException("Plz Enter a Valid Quantity");
        }
     else if (inventoryItems.getAvailableQuantity() >=quantity){
            inventoryItems.setAvailableQuantity(inventoryItems.getAvailableQuantity()-quantity);
            inventoryItems.setAmountSold(inventoryItems.getAmountSold()+quantity);
            inventoryRepository.save(inventoryItems);
            return "Inventory Item Bought Successfully of ID : "+id;
        }

        else{
            return "Out Of Stock";
        }
    }
    //Get Dto By Id
    public  InventoryItemsDto getInventoryItemsDto(Long id) {
        InventoryItems inventoryItems = inventoryRepository.findById(id).orElse(null);
        InventoryItemsDto inventoryItemsDto = new InventoryItemsDto();
        assert inventoryItems != null;
        inventoryItemsDto.setName(inventoryItems.getName()+" "+(inventoryItems.getType()) );
       // inventoryItemsDto.setType(inventoryItems.getType());
        return inventoryItemsDto;
    }


}

