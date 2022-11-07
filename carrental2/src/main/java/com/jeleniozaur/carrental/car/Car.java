package com.jeleniozaur.carrental.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private int id;
    private String brand;
    private String model;
    private boolean rented;
    private Integer rented_to_user_id;
}
