//package kz.demo.test.db;
//
//import kz.demo.test.model.Car;
//import kz.demo.test.model.City;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//
//@Component
//public class DBConnector {
//
//    private static Connection connection;
//    private static String login = "postgres";
//    private static String password = "postgres";
//    private static String url = "jdbc:postgresql://localhost:5436/g141?currentSchema=test";
//
//    static {
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager.getConnection(url, login, password);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public static ArrayList<Car> getAllCars(){
//
//        ArrayList<Car> cars = new ArrayList<>();
//
//
//        try {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test.cars car " +
//                    "JOIN test.cities city " +
//                    "ON car.city_id = city.id ORDER BY car.id ASC");
//
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()){
//                Car car = new Car();
//                car.setId(resultSet.getInt("id"));
//                car.setCost(resultSet.getDouble("cost"));
//                car.setYear(resultSet.getInt("year"));
//                car.setModel(resultSet.getString("model"));
//                car.setEngine(resultSet.getDouble("engine"));
//                car.setCountry(resultSet.getString("country"));
//
//                City city = new City();
//                city.setId(resultSet.getInt("city_id"));
//                city.setCode(resultSet.getString("code"));
//                city.setName(resultSet.getString("name"));
//                city.setCountPeople(resultSet.getInt("count_people"));
//
//                car.setCity(city);
//
//                cars.add(car);
//            }
//
//            statement.close();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return cars;
//
//    }
//
//    public static Car getCarById(int id){
//
//        Car car = new Car();
//
//        try{
//            PreparedStatement statement  = connection.prepareStatement("SELECT * FROM test.cars car " +
//                    "JOIN test.cities city " +
//                    "ON car.city_id = city.id WHERE car.id=?");
//            statement.setInt(1, id);
//
//            ResultSet resultSet = statement.executeQuery();
//
//            if(resultSet.next()){
//                car.setId(resultSet.getInt("id"));
//                car.setCost(resultSet.getDouble("cost"));
//                car.setYear(resultSet.getInt("year"));
//                car.setModel(resultSet.getString("model"));
//                car.setEngine(resultSet.getDouble("engine"));
//                car.setCountry(resultSet.getString("country"));
//
//                City city = new City();
//                city.setId(resultSet.getInt("city_id"));
//                city.setCode(resultSet.getString("code"));
//                city.setName(resultSet.getString("name"));
//                city.setCountPeople(resultSet.getInt("count_people"));
//
//                car.setCity(city);
//
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return car;
//    }
//
//    public static void addCar(Car car){
//
//        try {
//
//            PreparedStatement statement = connection.prepareStatement("INSERT INTO test.cars " +
//                    "(model, engine, cost, year, country, city_id) VALUES (?, ?, ?, ?, ?, ?)");
//            statement.setString(1, car.getModel());
//            statement.setDouble(2, car.getEngine());
//            statement.setDouble(3, car.getCost());
//            statement.setInt(4, car.getYear());
//            statement.setString(5, car.getCountry());
//            statement.setInt(6, car.getCity().getId());
//
//            statement.executeUpdate();
//            statement.close();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void updateCar(Car car){
//
//        try {
//
//            PreparedStatement statement = connection.prepareStatement("UPDATE test.cars SET model=?, engine=?, " +
//                    "cost=?, year=?, country=?, city_id=? WHERE id=?");
//            statement.setString(1, car.getModel());
//            statement.setDouble(2, car.getEngine());
//            statement.setDouble(3, car.getCost());
//            statement.setInt(4, car.getYear());
//            statement.setString(5, car.getCountry());
//            statement.setInt(6, car.getCity().getId());
//            statement.setInt(7, car.getId());
//
//            statement.executeUpdate();
//            statement.close();
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public static void deleteCar(int id){
//
//        try {
//
//            PreparedStatement statement = connection.prepareStatement("DELETE FROM test.cars WHERE id=?");
//            statement.setInt(1, id);
//
//            statement.executeUpdate();
//            statement.close();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    public static ArrayList<City> getAllCities() {
//
//        ArrayList<City> cities = new ArrayList<>();
//
//        try {
//
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM test.cities");
//
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()){
//                City city = new City();
//                city.setId(resultSet.getInt("id"));
//                city.setCode(resultSet.getString("code"));
//                city.setName(resultSet.getString("name"));
//                city.setCountPeople(resultSet.getInt("count_people"));
//
//                cities.add(city);
//            }
//
//            statement.close();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return cities;
//    }
//}
