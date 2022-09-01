package com.test.sharkpos.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vendor")
public class Vendor {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Long id;
    @Column(name = "vendor_name", nullable = false)

    private String vendorName;
    @Column(name = "address", nullable = false)

    private String address;
    @Column(name = "phone", nullable = false)

    private String phone;
    @Column(name = "active", nullable = false)

    private int active;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "vendor" ,orphanRemoval = true)
    @JsonManagedReference

    private List<InventoryItems> inventoryItems = new ArrayList<>();


}
