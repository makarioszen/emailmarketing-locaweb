<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E-mail marketing - Locaweb</title>
</head>
<body>
		
	<form action="${linkTo[IndexController].sendContato}" method="POST">
		<input type="text" name="contato.nome" placeholder="Nome" style="width: 450px" pattern="[a-zA-Z0-9]+"><br>
		<input type="email" name="contato.email" placeholder="E-mail"style="width: 450px"><br>
		<input type="submit" value="Cadastrar">
		<input type="reset" value="Limpar"> 		
	</form>
</body>
</html>