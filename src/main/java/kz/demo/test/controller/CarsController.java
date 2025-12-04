package kz.demo.test.controller;

import kz.demo.test.model.Car;
import kz.demo.test.model.City;
import kz.demo.test.repository.CarRepository;
import kz.demo.test.repository.CityRepository;
import kz.demo.test.repository.CustomCarRepository;
import kz.demo.test.repository.impl.CustomCarRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarsController {

    private final CarRepository carRepository;
    private final CityRepository cityRepository;

    @Autowired
    @Qualifier("main")
    private  CustomCarRepository customRepository;

    @GetMapping(value = "/") //http://localhost:8080/
    public String mainPage(Model model){
        model.addAttribute("cars", carRepository.findAll(Sort.by(Sort.Direction.ASC, "id")));
        return "index";
    }

    @GetMapping(value = "/search")//http://localhost:8080/search
    public String search(Model model,
                         @RequestParam String word){
        model.addAttribute("cars", carRepository.findByName(word));
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

        Car car = carRepository.findById(id).orElseThrow();

        model.addAttribute("car",car);

        List<City> cities = cityRepository.findAll();

        cities.removeAll(car.getCities());

        model.addAttribute("cities", cities);

        return "update-car-page";
    }

    @PostMapping(value = "/update-car")
    public String updateCar(Car car){

        carRepository.save(car);

        return "redirect:/get-car/" + car.getId();

    }

    @PostMapping(value = "/delete-city")
    public String deleteCity(@RequestParam int car_id,
                             @RequestParam int city_id){

        Car car = carRepository.findById(car_id).get();
        City city = cityRepository.findById(city_id).get();

        car.getCities().remove(city);

        carRepository.save(car);

        return "redirect:/get-car/" + car_id;


    }

    @PostMapping(value = "/add-city")
    public String addCity(@RequestParam int car_id,
                             @RequestParam int city_id){

        Car car = carRepository.findById(car_id).get();
        City city = cityRepository.findById(city_id).get();

        car.getCities().add(city);

        carRepository.save(car);

        return "redirect:/get-car/" + car_id;


    }

    @GetMapping(value = "/greater-cost")
    public String getCarsGreaterThan(@RequestParam double cost,
                                        Model model){
        model.addAttribute("cars", customRepository.findCarsByCostMore(cost));
        return "index";
    }

    @GetMapping(value = "/model-or-cost")
    public String getCarByModelOrCost(@RequestParam(required = false) String model,
                                      @RequestParam(required = false) Double cost,
                                      Model m){
        m.addAttribute("cars", customRepository.findCarsByModelOrCost(model, cost));
        return "index";
    }

    @GetMapping(value = "/sorted-cost")
    public String sortedCost(Model model){
        model.addAttribute("cars", customRepository.sortCarsByCost());
        return "index";
    }

}
