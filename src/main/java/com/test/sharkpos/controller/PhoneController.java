package com.test.sharkpos.controller;

import com.test.sharkpos.dto.PhoneDTO;
import com.test.sharkpos.dto.PhoneInfoDto;
import com.test.sharkpos.exception.RecordNotFoundException;
import com.test.sharkpos.model.Phone;
import com.test.sharkpos.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("phoneDetails")
public class PhoneController {

    @Autowired
    PhoneService phoneService;

    @PostMapping("/buy")
    public String buyPhone(@RequestParam(name = "id") int id, @RequestParam(name = "quantity") int quantity) throws RecordNotFoundException {
        return phoneService.buyPhone(id, quantity);

    }

    @PostMapping("/buyPhoneDTO")
    public String buyPhoneDTO(PhoneDTO dto) throws RecordNotFoundException {
        return phoneService.buyPhone(dto.getPhoneId(), dto.getQuantity());
    }

//    @PostMapping("{phoneId}/{phoneQuantity}")
//    public  String buyPhone(@PathVariable (name = "phoneId")int id, @PathVariable(name = "phoneQuantity")int quantity) throws  RecordNotFoundException{
//        return phoneService.buyPhone(id, quantity);
//    }

    @GetMapping
    public List<Phone> getPhoneDetails(){
        return phoneService.getPhoneDetails();
    }
    @PostMapping
    public Phone addPhone(@RequestBody Phone phone){
        return  phoneService.addPhone(phone);
    }

    @PutMapping("{id}")
    public  Phone update(@RequestBody Phone phone){
        return  phoneService.update(phone);

    }

    @DeleteMapping("{id}")
    public String deletePhone(@PathVariable int id){
        return  phoneService.deletePhoneById(id);
    }

    //Get Mapping
    @GetMapping("/phoneInfoDto/{id}")

    public PhoneInfoDto getPhoneInfo(@PathVariable int id){
        return phoneService.getPhoneInfo(id);
    }

}

