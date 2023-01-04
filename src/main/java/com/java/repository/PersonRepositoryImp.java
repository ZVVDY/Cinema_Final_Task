package com.java.repository;

import com.java.model.Person;
import com.java.model.RoleClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class PersonRepositoryImp implements PersonRepository {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3307/cinema";
    private static String username = "root";
    private static String password = "";
    private Connection connect;
    private PreparedStatement ps;
    private String sql;
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void createPerson(Person person) throws ClassNotFoundException {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            sql = "INSERT INTO person (username, password, role_client) VALUES (?,?,?)";
            ps = connect.prepareStatement(sql);
            ps.setString(1, person.getLoginPerson());
            ps.setString(2, person.getPasswordPerson());
            ps.setString(3, String.valueOf(RoleClient.CLIENT_PERSON));
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection MySQL");

        }
    }

    @Override
    public boolean searchForARegisteredPerson(String userName) {
        boolean isExistPerson = false;
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            sql = String.format("SELECT EXISTS (SELECT * FROM `person` WHERE  username = '%s')", userName);
            ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count == 1) {
                    isExistPerson = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return isExistPerson;
    }

    @Override
    public boolean searchForARegisteredPersonPassword(String userName, String personPassword) {
        boolean isExistPerson = false;
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            sql = String.format("SELECT EXISTS (SELECT * FROM person WHERE password = '%s' AND username = '%s')",
                    personPassword, userName);
            ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                int count = rs.getInt(1);
                if (count == 1) {
                    isExistPerson = true;
                }
                if (count == 0) {
                    System.err.println("Пароль введен не верно(Password entered is incorrect)");
                    isExistPerson = false;
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("No connection MySQL");


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return isExistPerson;
    }

    //public  RoleClient seachRole(){
//    try {
//        Class.forName(driver);
//        connect = DriverManager.getConnection(url, username, password);
//        sql = String.format("SELECT EXISTS (SELECT * FROM person WHERE password = '%s' AND username = '%s', )", personPassword, userName);
//        ps = connect.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery(sql);
//
//        while (rs.next()) {
//            int count = rs.getInt(1);
//            if (count == 1) {
//                isExistPerson = true;
//            }
//            if (count==0) {
//                System.err.println("Password entered is incorrect");//Пароль введен не верно
//                isExistPerson = false;
//            }
//        }
//
//
//    } catch (SQLException e) {
//        e.printStackTrace();
//        //System.out.println("No connection MySQL");
//
//
//    } catch (ClassNotFoundException e) {
//        throw new RuntimeException(e);
//    }
//
//}
//}
    @Override
    public void updatePerson() {
        Person person = new Person();
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Список  пользователей для редактирования (List of users to edit)");
            sql = "SELECT * FROM `person` WHERE role_client = 'CLIENT_PERSON'";
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + " Person " + resultSet.getString("username") +
                        " Password " +
                        resultSet.getString("password"));
            }
            System.out.println("Введите id пользователя для редактирования (Enter event person id to edit)");
            int scanId = Integer.parseInt(reader.readLine());
            sql = String.format("SELECT id, username, password FROM person WHERE id = %d", scanId);
            ps = connect.prepareStatement(sql);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                System.out.println("Введите новое имя пользователя (Enter a new name for the person)");
                person.setLoginPerson(reader.readLine());
                System.out.println("Введите  новый пароль пользователя (Enter a new user password)");
                person.setPasswordPerson(reader.readLine());
                sql = String.format(("UPDATE `person` SET `username`='%s', `password` ='%s' WHERE `id`=%d"),
                        person.getLoginPerson(), person.getPasswordPerson(), scanId);
                ps = connect.prepareStatement(sql);
                ps.execute();
                System.out.println("Изменения внесены успешно пользователь с новым именем(Changes made successfully) "
                        + person.getLoginPerson());
            }
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePerson() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Список пользователей (A list of users)");
            sql = "SELECT * FROM `person` `username` WHERE `role_client`='CLIENT_PERSON'";
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println("id" + resultSet.getString("id") + " username " + resultSet.getString("username"));
            }
            System.out.println("Введите id пользователя (Enter user id)");
            int idPerson = Integer.parseInt(reader.readLine());
            System.out.println("Введите username пользователя(Enter the username of the user)");
            String namePerson = reader.readLine();
            sql = String.format("SELECT EXISTS (SELECT * FROM person WHERE id = %d AND username = '%s')",
                    idPerson, namePerson);
            ps = connect.prepareStatement(sql);
            resultSet = ps.executeQuery(sql);
            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count == 1) {
                    sql = String.format("DELETE FROM `person` WHERE `id`= %d ", idPerson);
                    ps = connect.prepareStatement(sql);
                    ps.executeUpdate();
                    System.out.println("Удаление пользователя(Deleting a user) " + namePerson +
                            " выполнено (performed)");
                    System.out.println("Происходит очистка билетов занятых пользователем " +
                            "(Tickets occupied by the user are cleared)" + namePerson);
                    sql = String.format(("UPDATE `ticket` SET `username`=NULL, `flagTicketPurchased` =0 WHERE " +
                            "`username`='%s'"), namePerson);
                    ps = connect.prepareStatement(sql);
                    ps.execute();
                    System.out.println("Возрат билета выполнен (Ticket refund completed)");
                }

                if (count == 0) {
                    System.err.println("Ошибка удаления пользователя(Error deleting user) " + namePerson);
                }
            }

        } catch (SQLException | ClassNotFoundException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String searchForAPersonInTheDatabase() {

        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            System.out.println("Список пользователей ()");
            sql = "SELECT * FROM `person` `username` WHERE `role_client`='CLIENT_PERSON'";
            ps = connect.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                System.out.println("id" + resultSet.getString("id") + " username " + resultSet.getString("username"));
            }
            System.out.println("Введите и выберите пользователя");
            String nameLogin = reader.readLine();

            return nameLogin;

        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}