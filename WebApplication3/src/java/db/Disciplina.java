/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.util.ArrayList;
import web.DbListener;

/**
 *
 * @author Fabio
 */
public class Disciplina {
    private String nome;
    private String diaAula;
    private String horaAula;
    private int qtAulas;
    private double notaP1;
    private double notaP2;

    public static String getCreateStatement(){
        return "create table if not exists disciplina("
                    + "nome varchar(50) unique not null,"
                    + "diaAula varchar(50) not null,"
                    + "horaAula varchar(5) not null,"
                    + "qtAula int not null,"
                    + "notaP1 decimal(10,2) not null,"
                    + "notaP2 decimal(10,2) not null"
                + ")";
    }
    
    public static ArrayList<Disciplina> getDisciplinas() throws Exception{
        ArrayList<Disciplina> list = new ArrayList<>();
        
        Connection con = DbListener.getConnection();
        Statement stmt = con.createStatement();
        String sql = "select * from disciplina";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            String nome = rs.getString("nome");
            String diaAula = rs.getString("diaAula");
            String horaAula = rs.getString("horaAula");
            int qtAula = rs.getInt("qtAula");
            double notaP1 = rs.getDouble("notaP1");
            double notaP2 = rs.getDouble("notaP2");
            list.add(new Disciplina(nome, diaAula, horaAula, qtAula, notaP1, notaP2));
        }
            stmt.close();
            con.close();            
            return list;
    }
    
    public static void insertDisciplina(String nome, String diaAula, String horaAula, int qtAula, double notaP1, double notaP2) throws Exception{
        Connection con = DbListener.getConnection();
        String sql = "insert into disciplina(nome, diaAula, horaAula, qtAula, notaP1, notaP2)"
                + "VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setString(2, diaAula);
        stmt.setString(3, horaAula);
        stmt.setInt(4, qtAula);
        stmt.setDouble(5, notaP1);
        stmt.setDouble(6, notaP2);
        
        stmt.execute();
        
        stmt.close();
        con.close();
    }
    
    public static void deleteDisciplina(String nome) throws Exception{
        Connection con = DbListener.getConnection();
        String sql = "delete from disciplina where nome=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nome);
        
        stmt.execute();
        
        stmt.close();
        con.close();
    }
    
    public static void updateDisciplina(String nome, double notaP1, double notaP2) throws Exception{
        Connection con = DbListener.getConnection();
        String sql = "update disciplina set notaP1=?, notaP2=? where nome=?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setDouble(1, notaP1);
        stmt.setDouble(2, notaP2);
        stmt.setString(3, nome);
        
        stmt.execute();
        
        stmt.close();
        con.close();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDiaAula() {
        return diaAula;
    }

    public void setDiaAula(String diaAula) {
        this.diaAula = diaAula;
    }

    public String getHoraAula() {
        return horaAula;
    }

    public void setHoraAula(String horaAula) {
        this.horaAula = horaAula;
    }

    public int getQtAulas() {
        return qtAulas;
    }

    public void setQtAulas(int qtAulas) {
        this.qtAulas = qtAulas;
    }

    public double getNotaP1() {
        return notaP1;
    }

    public void setNotaP1(double notaP1) {
        this.notaP1 = notaP1;
    }

    public double getNotaP2() {
        return notaP2;
    }

    public void setNotaP2(double notaP2) {
        this.notaP2 = notaP2;
    }

    public Disciplina(String nome, String diaAula, String horaAula, int qtAulas, double notaP1, double notaP2) {
        this.nome = nome;
        this.diaAula = diaAula;
        this.horaAula = horaAula;
        this.qtAulas = qtAulas;
        this.notaP1 = notaP1;
        this.notaP2 = notaP2;
    }
}
