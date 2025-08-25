package com.dizi.dz.entity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class DiziOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Date placedAt;

    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    private String deliveryStreet;

    private String deliveryCity;

    private String deliveryState;

    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    private List<Dizi> dz = new ArrayList<>();

    public void addDizi(Dizi dz) {
        this.dz.add(dz);
    }

    public String toString() {
        return "DiziOrder {" + "\n" +
                " id=" + id + ",\n" +
                " placedAt=" + placedAt + ",\n" +
                " deliveryName='" + deliveryName + ",\n" +
                " deliveryStreet='" + deliveryStreet + ",\n" +
                " deliveryCity='" + deliveryCity + ",\n" +
                " deliveryState='" + deliveryState + ",\n" +
                " deliveryZip='" + deliveryZip + ",\n" +
                " ccNumber='" + ccNumber + ",\n" +
                " ccExpiration='" + ccExpiration + ",\n" +
                " ccCVV='" + ccCVV + ",\n" +
                " dz=" + dz + ",\n" +
                '}';
    }
}
