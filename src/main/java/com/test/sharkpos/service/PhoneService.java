package com.test.sharkpos.service;

import com.test.sharkpos.dto.PhoneInfoDto;
import com.test.sharkpos.exception.RecordNotFoundException;
import com.test.sharkpos.model.Phone;
import com.test.sharkpos.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneService {

    @Autowired
    PhoneRepository phoneRepository;
//post Mapping
    public String buyPhone(int phoneId, int quantity) throws RecordNotFoundException {
        Phone phone = phoneRepository.findById(phoneId).orElse(null);
        if(phone == null){
            throw new RecordNotFoundException("Record Not Found");
        }
       else if(quantity<0 || quantity==0){
            throw new RecordNotFoundException("plz enter a positive value!");
        }else{
            if(phone.getAvailableQuantity() > quantity){
                phone.setAvailableQuantity(phone.getAvailableQuantity()-quantity);
                phone.setSoldNumber(phone.getSoldNumber()+quantity);
                phoneRepository.save(phone);
                return "Phone Bought with Id: "+phoneId;
            }
                else{
                return "Out of Stock!";
            }

        }


    }
 //Get All Mapping
    public List<Phone> getPhoneDetails() {
        List<Phone> phone= new ArrayList<>();
        phoneRepository.findAll().
                forEach(phone::add);
        return phone;
    }

    //Post Mapping
public Phone addPhone(Phone phone){
        return phoneRepository.save(phone);
}
    //Put Mapping
    public Phone update(Phone phone) {
        Phone updatePhone=phoneRepository.findById(phone.getId()).orElse(null);
        assert updatePhone != null;
        updatePhone.setName(phone.getName());
        updatePhone.setSoldNumber(phone.getSoldNumber());
        updatePhone.setAvailableQuantity(phone.getAvailableQuantity());
        updatePhone.setModel(phone.getModel());
      return   phoneRepository.save(updatePhone);


    }
    //Delete Mapping
    public String deletePhoneById(int id) {

        phoneRepository.deleteById(id);
        return "Phone is Deleted Successfully of ID : "+id;
    }
// Get Dto mapping By id
    public PhoneInfoDto getPhoneInfo(int id) {
        Phone phone=phoneRepository.findById(id).orElse(null);
        PhoneInfoDto phoneInfoDto=new PhoneInfoDto();
        assert phone != null;
        phoneInfoDto.setId(phone.getId());
       phoneInfoDto.setName(phone.getName());
       phoneInfoDto.setModel(phone.getModel());
       return phoneInfoDto;
     //  return phoneRepository.save(phoneInfoDto);

    }
}
