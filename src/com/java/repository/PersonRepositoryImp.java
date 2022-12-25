package com.java.repository;

import com.java.model.Person;
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
   // private ResultSet rs;
    private ConnectionManager connectionManager;


    @Override
    public void createPerson(Person person) throws ClassNotFoundException {
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            sql="INSERT INTO person (username, password) VALUES (?,?)";
            ps = connect.prepareStatement(sql);
            ps.setString(1, person.getLoginPerson());
            ps.setString(2, person.getPasswordPerson());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection MySQL");

        }
    }

    @Override
    public boolean searchForARegisteredPerson(String userName) {
        boolean isExistPerson=false;
        try {
            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            sql =String.format("SELECT EXISTS (SELECT * FROM `person` WHERE  username = '%s')",userName);
            ps = connect.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count==1){
                    isExistPerson=true;
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

    @Override
    public boolean readPerson(Person person) {
        try {

            Class.forName(driver);
            connect = DriverManager.getConnection(url, username, password);
            sql ="SELECT username FROM `person` WHERE " +person.getLoginPerson()+"=?";
            ps = connect.prepareStatement(sql);
            ps.setString(1, person.getLoginPerson());
//                        rs= ps.executeQuery();
//                        if ((rs.next())){
//                            System.out.println(person);
//                            System.out.println(rs);
//                        }

            //String s=statement.
            //2statement.executeQuery();
           // SELECT password  FROM person WHERE username = userName
            //s1.equal(s2);

            //statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("No connection MySQL");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return true;

    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePerson(Long id) {

    }


}
