<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>E-mail marketing - Locaweb</title>
</head>
<body>
	It works!! ${variable} ${linkTo[LocawebController].includeContact}
	
	<form action="${linkTo[LocawebController].includeContact}" method="POST">
		<input type="text" name="contato.nome" placeholder="Nome"><br>
		<input type="email" name="contato.email" placeholder="E-mail"><br>
		<input type="reset" value="Limpar"> 
		<input type="submit" value="Cadastrar">
	</form>
</body>
</html>