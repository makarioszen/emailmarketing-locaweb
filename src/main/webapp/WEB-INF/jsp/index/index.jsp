<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E-mail marketing - Locaweb</title>
</head>
<body>
	${variable} ${linkTo[IndexController].index}
	
	<form action="${linkTo[IndexController].sendContato}" method="POST">
		<input type="text" name="contato.nome" placeholder="Nome"><br>
		<input type="email" name="contato.email" placeholder="E-mail"><br>
		<input type="reset" value="Limpar"> 
		<input type="submit" value="Cadastrar">
	</form>
</body>
</html>