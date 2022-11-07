package com.jeleniozaur.carrental.car;

import com.jeleniozaur.carrental.userdata.UserDataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    CarRepository carRepository;
    @Autowired
    UserDataController userDataController;

    @GetMapping("/all")
    public List<Car> getAll() {
        return carRepository.getAll();
    }

    @GetMapping("/{id}")
    public Car getById(@PathVariable("id") int id) {
        return carRepository.getById(id);
    }

    @GetMapping("/rent/{id}")
    public int rentCar(@PathVariable("id") int id, @RequestHeader("user_id") Integer userId) {
        if(entryExists(id) && userDataController.exists(userId))
            return !getById(id).isRented() ? carRepository.setRent(id,userId, true) : 0;
        return 0;
    }

    @GetMapping("/return/{id}")
    public int returnCar(@PathVariable("id") int id) {
        Integer nullInteger = null;
        if(entryExists(id))
            return getById(id).isRented() ? carRepository.setRent(id, nullInteger,false) : 0;
        return 0;
    }

    @PostMapping("/add")
    public int add(@RequestBody Car car) {
        return carRepository.add(car);
    }

    @DeleteMapping("/delete")
    public int delete(@RequestBody Car car) {
        return entryExists(car) ? carRepository.delete(car) : 0;
    }

    @PatchMapping("/update")
    public int update(@RequestBody Car car) {
        if(entryExists(car)) {
            Car oldCar = carRepository.getById(car.getId());
            if(car.getBrand()!=null) oldCar.setBrand(car.getBrand());
            if(car.getModel()!=null) oldCar.setModel(car.getModel());

            carRepository.update(oldCar);
            return 1;
        }
        return 0;
    }

    public boolean entryExists(Car car) {
        return carRepository.getById(car.getId()) != null;
    }
    public boolean entryExists(int id) {
        return carRepository.getById(id) != null;
    }
}
