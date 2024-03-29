<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Estados API</title>

</head>
<body>
    <h2>Nexio Bike API</h2>
    <p>Api de vistoria de bike</p>
    
    <h3>Endpoints Marca</h3>
    <p><a href="webapi/api/marcas">/marcas</a> M�todo: GET - Buscar Todas as Marcas</p>
	<p>/marca/{id} M�todo: GET -  Buscar Marca por ID </p>
    <p>/marca/{id} M�todo: DELETE - Deletar Marca</p>
    <p>/cadastro-marca M�todo: POST - Adicionar Nova Marca</p>
    <p>/atualiza-marca/{id} M�todo: PUT - Atualizar Marca</p>
    
	<h3>Endpoints Usu�rio</h3>
    <p>/cadastro-usuario M�todo: POST - Adicionar Novo Usu�rio</p>
    <p>/usuario/{email} M�todo: GET - Buscar Usu�rio por Email</p>
    <p>/login/{email} M�todo: POST - Validar Usu�rio</p>
    
    <h3>Endpoints Cliente </h3>
    <p>/cadastro-cliente M�todo: POST - Adicionar Novo Cliente </p>
    
    <h3>Endpoints ModeloBike  </h3>
    <p>/modelos M�todo: GET - Obt�m todos os modelos de bikes cadastradas.</p>
    <p>/modelo/{id} M�todo: GET - Obt�m um modelo de bike espec�fico com base no ID fornecido</p>
    
    <h3>Endpoints Acessorio  </h3>
    <p>/acessorios M�todo: GET - Obt�m todos os acessorios de bikes cadastradas.</p>
    <p>/acessorio/{id} M�todo: GET - Obt�m um acessorio espec�fico com base no ID fornecido</p>
   
</body>
</html>
