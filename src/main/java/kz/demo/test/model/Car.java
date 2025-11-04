package kz.demo.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    private int id;
    private String model;
    private double engine;
    private double cost;
    private int year;
    private String country;
}
