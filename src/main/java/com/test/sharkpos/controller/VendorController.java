package com.test.sharkpos.controller;

import com.test.sharkpos.exception.RecordNotFoundException;
import com.test.sharkpos.model.Vendor;
import com.test.sharkpos.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendors")
public class VendorController {
    @Autowired
    private VendorService vendorService;
    @GetMapping
    public List<Vendor> getVendors(){
        return vendorService.getVendors();
    }
    @PostMapping
    public  Vendor addVendor(@RequestBody Vendor vendor ){
        return  vendorService.addVendor(vendor);
    }
    @DeleteMapping("{id}")
    public String deleteVendor(@PathVariable Long id) throws RecordNotFoundException {
        return vendorService.deleteById(id);
    }
    @PutMapping("{id}")
    public  Vendor update(@RequestBody Vendor vendor){
      return vendorService.update(vendor);
       // return vendor;
    }

}
