package com.test.sharkpos.repository;

import com.test.sharkpos.model.InventoryItems;
import com.test.sharkpos.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository  extends JpaRepository<Phone, Integer> {
}
