package com.test.sharkpos.repository;

import com.test.sharkpos.model.InventoryItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryItems, Long> {
}
