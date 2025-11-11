package kz.demo.test.db;

import kz.demo.test.model.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnector {

    private static Connection connection;
    private static String login = "postgres";
    private static String password = "postgres";
    private static String url = "jdbc:postgresql://localhost:5433/postgres?currentSchema=test_car";

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, login, password);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Car> getAllCars(){

        ArrayList<Car> cars = new ArrayList<>();


        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * " +
                    "FROM test_car.cars");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Car car = new Car();
                car.setId(resultSet.getInt("id"));
                car.setCost(resultSet.getDouble("cost"));
                car.setYear(resultSet.getInt("year"));
                car.setModel(resultSet.getString("model"));
                car.setEngine(resultSet.getDouble("engine"));
                car.setCountry(resultSet.getString("country"));

                cars.add(car);
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return cars;

    }

    public static Car getCarById(int id){

        Car car = new Car();

        try{
            PreparedStatement statement  = connection.prepareStatement("SELECT * FROM test_car.cars WHERE id=?");
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                car.setId(resultSet.getInt("id"));
                car.setCost(resultSet.getDouble("cost"));
                car.setYear(resultSet.getInt("year"));
                car.setModel(resultSet.getString("model"));
                car.setEngine(resultSet.getDouble("engine"));
                car.setCountry(resultSet.getString("country"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return car;
    }

}
