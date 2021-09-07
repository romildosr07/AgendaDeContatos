<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title> Agenda de Contatos</title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	
</head>
<body>
	<header class="cabecalho">
		<h1> Formulario de Contato</h1>
	</header>
	<section class="conteudo">
	<div id="coluna1" >
		<form class="forma" name="forme" action="insert">
			
			<label>Codigo</label><br>
			<input type="text" name="idcon" placeholder="Codigo Automatico" 
			<%if(request.getAttribute("idcon")== null){%>
				value=""	
			<%} else {%>
				value="<%out.println(request.getAttribute("idcon"));%>"
			<%} %>readonly><br>
			<label>Nome</label><br>
			<input type="text" name="nome" 
			<%if(request.getAttribute("nome")== null){%>
				value=""	
			<%} else {%>
				value="<%out.println(request.getAttribute("nome"));%>"
			<%} %>><br>
			<label>Telefone</label><br>
			<input type="text" name="telefone"
			<%if(request.getAttribute("telefone")== null){%>
				value=""	
			<%} else {%>
				value="<%out.println(request.getAttribute("telefone"));%>"
			<%} %>><br>
			<label>Email</label><br>
			<input type="text" name="email" 
			<%if(request.getAttribute("email")== null){%>
				value=""	
			<%} else {%>
				value="<%out.println(request.getAttribute("email"));%>"
			<%} %>><br><br>
			<a href="" type="button" class="botao" onclick="validar()">Salvar</a>
			
	</form>	
	<script type="text/javascript" src="js/validador.js"></script>
	</div>
	<div id="coluna2">	
		<img width=80%  src="imagens\contato.png">
	</div>
		
	</section>
	<footer class="rodape">
		<p>Romildo Rodrigues ©</p>
	</footer>
	
	<script type="text/javascript" src="js/validador.js"></script>
</body>
</html>