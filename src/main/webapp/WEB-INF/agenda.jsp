<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Agenda</title>
<link rel="icon" href="imagens/fone.png">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class="cabecalho">
	<h1>Agenda de Contatos</h1>
</header>
<section class="conteudo">
	<div id="coluna1">
		<img src="imagens/listagem.png" width="100%"> 
		<br>
		<a href="formulario.jsp" class="botao">Novo Contato</a>
	</div>
	<div id="coluna2">
		<table id="customers">
			<tr>
				<th>Código</th>
				<th>Nome</th>
				<th>Telefone</th>
				<th>Email</th>
				<th colspan="2">Opções</th>
			</tr>
			<tr>
				<%for(int i=0; i<lista.size(); i++){ %>
				<tr>
					<td><%=lista.get(i).getIdCod()%></td>
					<td><%=lista.get(i).getNome()%></td>
					<td><%=lista.get(i).getFone()%></td>
					<td><%=lista.get(i).getEmail()%></td>
					<td><a href="select?idcon=<%=lista.get(i).getIdCod()%>" class="botao">Editar</a></td>
					<td><a href="javascript:confirma(<%=lista.get(i).getIdCod()%>)"class="botao">Excluir</a></td>
				</tr>
			<%} %>
		</table>
	</div>

</section>
<footer class="rodape">
	<p>Romildo Rodrigues ©</p>
</footer>
<script src="js/confirmador.js"></script>
</body>
</html>