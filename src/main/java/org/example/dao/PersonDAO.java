package org.example.dao;

import org.example.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    int PEOPLE_COUNTER;

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Person getPersonById(int id) {
        Person person = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person = new Person();
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setMail(resultSet.getString("mail"));
            person.setId(resultSet.getInt("id"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return person;
    }

    public List<Person> getPeople() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Person person = new Person();

                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setMail(resultSet.getString("mail"));

                people.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public void addNewPerson(Person person) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person VALUES(1, ?, ?, ?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getMail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updatePersonById(int id, Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Person SET name=?, age=?, mail=? WHERE id=?");

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getMail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePersonById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
