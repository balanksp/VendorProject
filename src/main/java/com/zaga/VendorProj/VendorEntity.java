package com.zaga.VendorProj;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "vendor_order")
public class VendorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long productId;
    private String productName;
    private Long orderId;
    private Long customerId;
    private String customerName;
    private Long customerPhoneNumber;

    private String modelNo;
    private String productAddress;
    private int quantity;

}
