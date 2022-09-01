package com.test.sharkpos.service;


import com.test.sharkpos.model.Vendor;
import com.test.sharkpos.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendorService {
    @Autowired
    private VendorRepository vendorRepository;
    //GetAllVendors Mapping
    public List<Vendor> getVendors() {
        List<Vendor> vendors = new ArrayList<>();
       vendorRepository.findAll().
                forEach(vendors::add);
        return vendors;

    }
    //Post Mapping
    public Vendor addVendor(Vendor vendor){

        return vendorRepository.save(vendor);
    }

// Delete Mapping
    public String deleteById(Long id){
     //   Vendor vendor = vendorRepository.getReferenceById(id);

 vendorRepository.deleteById(id);
 return "vendor Deleted Successfully of id "+" "+id;
    }

    public Vendor update(Vendor vendor) {
       Vendor updateVendor = vendorRepository.findById(vendor.getId()).orElse(null);


        if (updateVendor != null) {
            updateVendor.setAddress(vendor.getAddress());
        }
        assert updateVendor != null;
        updateVendor.setActive(vendor.getActive());
       updateVendor.setPhone(vendor.getPhone());
        return vendorRepository.save(updateVendor);

    }
}
