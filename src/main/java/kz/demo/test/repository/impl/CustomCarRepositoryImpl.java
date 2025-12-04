package kz.demo.test.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kz.demo.test.model.Car;
import kz.demo.test.repository.CustomCarRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("main")
public class CustomCarRepositoryImpl implements CustomCarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> findCarsByCostMore(double cost) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car>query = cb.createQuery(Car.class);
        Root<Car> root = query.from(Car.class);

        Predicate costPredicate = cb.greaterThan(root.get("cost"), cost);

        query.select(root).where(costPredicate);


        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Car> findCarsByModelOrCost(String model, Double cost) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car>query = cb.createQuery(Car.class);
        Root<Car> root = query.from(Car.class);

        List<Predicate> predicates = new ArrayList<>();

        if(model!=null && !model.isEmpty()){
            predicates.add(cb.like(root.get("model"), model));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        if(cost!=null){
            predicates.add(cb.greaterThanOrEqualTo(root.get("cost"), cost));
            query.select(root).where(predicates.toArray(new Predicate[0]));
        }

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Car> sortCarsByCost() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car>query = cb.createQuery(Car.class);
        Root<Car> root = query.from(Car.class);

        query.select(root).orderBy(cb.desc(root.get("cost")));

        return entityManager.createQuery(query).getResultList();
    }
}
