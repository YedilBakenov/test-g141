package kz.demo.test.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cars", schema = "project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String model;
    private double engine;
    private double cost;
    private int year;
    private String country;

    @ManyToMany
    private List<City> cities;

    @OneToOne
    @JoinColumn(name = "car_passport")
    private CarPassport carPassport;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
