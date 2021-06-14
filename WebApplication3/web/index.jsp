<%-- 
    Document   : index
    Created on : 13 de jun. de 2021, 23:49:25
    Author     : Fabio
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="db.Disciplina"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    ArrayList<Disciplina> disciplina = new ArrayList<>();
  
    disciplina = Disciplina.getDisciplinas();
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Início</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <h1>Início </h1>
        
        <% if(session.getAttribute("user.login") != null){%>
        <table border="1">
                <tr>
                    <th>RA</th>
                    <th>Nome</th>
                    <th>Semestre</th>
                    <th>Github</th>               
                </tr>
                <tr>
                    <td>1290481923048</td>
                    <td>FABIO DANILO FIGUEIRA DO NASCIMENTO</td>
                    <td>2º/2019</td>
                    <td><a href="https://github.com/fabiodanilo1232"> Ir ao site </a></td>   
                </tr>
        </table>
        
        <br>
            
            <table border="1">
                <tr>
                    <th>Nome</th>
                    <th>Média</th>
                </tr>

                <% for(Disciplina disciplinas: disciplina){%>
                <tr>
                    <td><%= disciplinas.getNome()%></td>
                    <td><%= (Double)(disciplinas.getNotaP1() + disciplinas.getNotaP2())/2 %></td>
                </tr>

                <%}%>
                </table>
        <%}%>
    </body>
</html>
