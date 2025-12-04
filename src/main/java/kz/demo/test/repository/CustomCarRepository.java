package kz.demo.test.repository;

import kz.demo.test.model.Car;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomCarRepository {

    List<Car> findCarsByCostMore(double cost);
    List<Car> findCarsByModelOrCost(String model, Double cost);
    List<Car> sortCarsByCost();

}
