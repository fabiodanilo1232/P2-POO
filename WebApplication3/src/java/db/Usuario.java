/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import web.DbListener;

/**
 *
 * @author Fabio
 */
public class Usuario {
    private String name;
    private String login;
    
    public static String getCreateStatement(){
        return "create table if not exists users("
                + "login varchar(50) unique not null,"
                + "name varchar(200) not null,"
                + "password_hash long not null"
                + ")";
    }
    
    public static ArrayList<Usuario> getUsers() throws Exception{
        ArrayList<Usuario> list = new ArrayList<>();
        Connection con = DbListener.getConnection();
        Statement stmt = con.createStatement();
        String sql = "select * from users";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            String login = rs.getString("login");
            String name = rs.getString("name");
            list.add(new Usuario(login,name));
        }
        stmt.close();
        con.close();
        
        return list;
    }
    
    public static void insertUser(String name, String login, String password) throws Exception{
        Connection con = DbListener.getConnection();
        String sql = "insert into users(login,name,password_hash)"
                + "VALUES (?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setString(2, name);
        stmt.setLong(3, password.hashCode());
        
        stmt.execute();
        
        stmt.close();
        con.close();
    }
    
    public static Usuario getUser(String login, String password) throws Exception{
        Usuario user = null;
        Connection con = DbListener.getConnection();
        String sql = "select * from users where login=? AND password_hash=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, login);
        stmt.setLong(2, password.hashCode());
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            String name = rs.getString("name");
            user = new Usuario(login, name);
        }
        stmt.close();
        con.close();
        
        return user;
    }

    public Usuario(String name, String login) {
        this.name = name;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
