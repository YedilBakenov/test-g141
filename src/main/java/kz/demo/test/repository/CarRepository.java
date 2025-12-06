package kz.demo.test.repository;

import kz.demo.test.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("SELECT car FROM Car car " +
            "WHERE car.model ilike concat('%', :word, '%') " +
            "OR car.owner.fullName ilike concat('%', :word, '%')")
    List<Car> findByName(String word);

    Page<Car> findAll(Pageable pageable);

    Page<Car> findByCostGreaterThan(double cost, Pageable pageable);
}
