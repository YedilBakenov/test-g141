package kz.demo.test.db;

import kz.demo.test.model.Car;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    @Getter
    private static List<Car> carList = new ArrayList<>();

    private static int id = 6;

    static {
        carList.add(new Car(1, "BMW X5", 5.0, 70000, 2023, "Germany"));
        carList.add(new Car(2, "BMW X6", 3.0, 100000, 2025, "Germany"));
        carList.add(new Car(3, "BMW X3", 2.0, 60000, 2024, "Germany"));
        carList.add(new Car(4, "MERCEDES S-500", 5.0, 170000, 2024, "Germany"));
        carList.add(new Car(5, "MERCEDES G-500", 5.5, 240000, 2025, "Germany"));
    }

    public static void addCar(Car car){
        car.setId(id);
        carList.add(car);
        id++;
    }


    public static void deleteCarById(int id) {
        carList.removeIf(c-> c.getId()==id);
    }

    public static Car getCarById(int id) {
        return carList.stream().filter(c-> c.getId()==id).findFirst().get();
    }

    public static void updateCar(Car car) {

        for(Car c: carList){
            if(c.getId()==car.getId()){
                c.setCost(car.getCost());
                c.setYear(car.getYear());
                c.setCountry(car.getCountry());
                c.setModel(car.getModel());
                c.setEngine(car.getEngine());
            }
        }

    }
}
