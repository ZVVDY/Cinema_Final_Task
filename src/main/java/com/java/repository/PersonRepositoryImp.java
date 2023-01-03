package com.java.repository;

import com.java.model.Person;
import com.java.model.RoleClient;
import com.java.util.ConnectionManager;

import java.sql.*;

public class PersonRepositoryImp implements PersonRepository {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3307/cinema";
    private static String username = "root";
    private static String password = "";
    private Connection connect;
    private PreparedStatement ps;
    private String sql;

    private ConnectionManager connectionManager;
    private RoleClient roleClient;

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
            sql = String.format("SELECT EXISTS (SELECT * FROM person WHERE password = '%s' AND username = '%s')", personPassword, userName);
            ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);

                while (rs.next()) {
                    int count = rs.getInt(1);
                    if (count == 1) {
                        isExistPerson = true;
                    }
                if (count==0) {
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
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePerson(Long id) {

    }
}