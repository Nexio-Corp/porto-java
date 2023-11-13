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
    <p><a href="webapi/api/marcas">/marcas</a> Método: GET - Buscar Todas as Marcas</p>
	<p>/marca/{id} Método: GET -  Buscar Marca por ID </p>
    <p>/marca/{id} Método: DELETE - Deletar Marca</p>
    <p>/cadastro-marca Método: POST - Adicionar Nova Marca</p>
    <p>/atualiza-marca/{id} Método: PUT - Atualizar Marca</p>
    
	<h3>Endpoints Usuário</h3>
    <p>/cadastro-usuario Método: POST - Adicionar Novo Usuário</p>
    <p>/usuario/{email} Método: GET - Buscar Usuário por Email</p>
    <p>/login/{email} Método: POST - Validar Usuário</p>
    
    <h3>Endpoints Cliente </h3>
    <p>/cadastro-cliente Método: POST - Adicionar Novo Cliente </p>
    
    <h3>Endpoints ModeloBike  </h3>
    <p>/modelos Método: GET - Obtém todos os modelos de bikes cadastradas.</p>
    <p>/modelo/{id} Método: GET - Obtém um modelo de bike específico com base no ID fornecido</p>
    
    <h3>Endpoints Acessorio  </h3>
    <p>/acessorios Método: GET - Obtém todos os acessorios de bikes cadastradas.</p>
    <p>/acessorio/{id} Método: GET - Obtém um acessorio específico com base no ID fornecido</p>
   
</body>
</html>
