package com.test.sharkpos.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="inventory_items")
public class InventoryItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "available_quantity", nullable = false)
    private int availableQuantity;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "amount_sold", nullable = false)
    private int amountSold;

    @ManyToOne(cascade = CascadeType.ALL)
     @JsonBackReference

    @JoinColumn(name = "vendor",referencedColumnName = "id")
    private  Vendor vendor;


}
