package kz.demo.test.controller;

import kz.demo.test.db.DBConnector;
import kz.demo.test.db.DBManager;
import kz.demo.test.model.Car;
import org.apache.catalina.users.SparseUserDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarsController {

    @GetMapping(value = "/") //http://localhost:8080/
    public String mainPage(Model model){
        model.addAttribute("cars", DBConnector.getAllCars());
        return "index";
    }

    @GetMapping(value = "/add-car") // http:localhost:8080/add-car
    public String addCarPage(){
        return "add-car-page";
    }

    @PostMapping(value = "/add-car")
    public String addCar(Car car){
        DBManager.addCar(car);
        return "redirect:/";
    }

    @PostMapping(value = "/delete-car")
    public String deleteCar(@RequestParam int id){
        DBManager.deleteCarById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/get-car/{id}")
    public String updateCar(@PathVariable int id,
            Model model){

        model.addAttribute("car",DBConnector.getCarById(id));

        return "update-car-page";
    }

    @PostMapping(value = "/update-car")
    public String updateCar(Car car){

        DBManager.updateCar(car);

        return "redirect:/get-car/" + car.getId();

    }

}
