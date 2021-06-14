<%-- 
    Document   : disciplina
    Created on : 14 de jun. de 2021, 00:37:47
    Author     : Fabio
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="db.Disciplina"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
   String requestError = null;
   ArrayList<Disciplina> disciplina = new ArrayList<>();
   try{
        if(request.getParameter("insert") != null){
            String nome = request.getParameter("nome");
            String diaAula = request.getParameter("diaAula");
            String horaAula = request.getParameter("horaAula");
            int qtAula = Integer.parseInt(request.getParameter("qtAula"));
            double notaP1 = Double.parseDouble(request.getParameter("notaP1"));
            double notaP2 = Double.parseDouble(request.getParameter("notaP2"));
            Disciplina.insertDisciplina(nome, diaAula, horaAula, qtAula, notaP1, notaP2);
            response.sendRedirect(request.getRequestURI());
        }else if(request.getParameter("delete") != null){
            String nome = request.getParameter("nome");
            Disciplina.deleteDisciplina(nome);
            response.sendRedirect(request.getRequestURI());
        }else if(request.getParameter("update") != null){
            String nome = request.getParameter("nome");
            double notaP1 = Double.parseDouble(request.getParameter("notaP1"));
            double notaP2 = Double.parseDouble(request.getParameter("notaP2"));
            Disciplina.updateDisciplina(nome, notaP1, notaP2);
            response.sendRedirect(request.getRequestURI());
        }
        disciplina = Disciplina.getDisciplinas();
    
   }catch(Exception ex){
    requestError = ex.getMessage();
   }
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Disciplina</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <h2>Disciplina</h2>
        <% if(requestError!=null){%>
        <div><%= requestError %></div>
        <%}%>
        
        <% if(DbListener.exception != null){%>
        <div style="color:red">
        <%= DbListener.exception.getMessage() %>
        </div>
        <%}%>
        <% if(session.getAttribute("user.login") != null){%>
        <form method="post">
            Nome: <input type="text" name="nome"><br><br>
            Dia da Aula: <input type="text" name="diaAula"><br><br>
            Horario da Aula: <input type="text" name="horaAula"><br><br>
            Quantidade de Aulas: <input type="number" name="qtAula"><br><br>
            Nota P1: <input type="text" name="notaP1"><br><br>
            Nota P2: <input type="text" name="notaP2"><br><br>
            <input type="submit" name="insert" value="Cadastrar">
        </form>
        
        <h2>Lista de disciplinas</h2>
        
        <table border="1">
            <tr>
                <th>Nome</th>
                <th>Dia da Aula</th>
                <th>Horario da Aula</th>
                <th>Quantidade de Aulas</th>
                <th>Nota P1</th>
                <th>Nota P2</th>
            </tr>
            
            <% for(Disciplina disciplinas: disciplina){%>
            <tr>
                <td><%= disciplinas.getNome()%></td>
                <td><%= disciplinas.getDiaAula()%></td>
                <td><%= disciplinas.getHoraAula()%></td>
                <td><%= disciplinas.getQtAulas()%></td>
                <form method="post">
                    <input type="hidden" name="nome" value="<%= disciplinas.getNome()%>">
                    <td><input type="text" name="notaP1" value="<%= disciplinas.getNotaP1()%>"></td>
                    <td><input type="text" name="notaP2" value="<%= disciplinas.getNotaP2()%>"></td>
                    <td><input type="submit" name="update" value="Alterar"></td>
                </form>
                <td>
                    <form method="post">
                        <input type="hidden" name="nome" value="<%= disciplinas.getNome()%>">
                        <input type="submit" name="delete" value="Excluir">
                    </form>
                </td>
            </tr>
            <%}%>
            
        <%}else{%>
            <h2 style="color:red">Sem acesso</h2>
        <%}%>
    </body>
</html>
