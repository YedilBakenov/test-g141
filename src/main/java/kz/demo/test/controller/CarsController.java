package kz.demo.test.controller;

import kz.demo.test.model.Car;
import kz.demo.test.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CarsController {

    private final CarRepository carRepository;

    @GetMapping(value = "/") //http://localhost:8080/
    public String mainPage(Model model){
        model.addAttribute("cars", carRepository.findAll());
        return "index";
    }

    @GetMapping(value = "/add-car") // http:localhost:8080/add-car
    public String addCarPage(Model model){
        return "add-car-page";
    }

    @PostMapping(value = "/add-car")
    public String addCar(Car car){
       carRepository.save(car);
        return "redirect:/";
    }

    @PostMapping(value = "/delete-car")
    public String deleteCar(@RequestParam int id){
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping(value = "/get-car/{id}")
    public String updateCar(@PathVariable int id,
            Model model){

        model.addAttribute("car",carRepository.findById(id));

        return "update-car-page";
    }

    @PostMapping(value = "/update-car")
    public String updateCar(Car car){

        carRepository.save(car);

        return "redirect:/get-car/" + car.getId();

    }

}
