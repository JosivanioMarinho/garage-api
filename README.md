# garage-api

<h3>Estórias de usuário</h3>

1 - Criar um novo usuário

   Juninho pretende acessar nosso sistema para arquivar seus carros,
   mas para isso, ele deve fazer um cadastro no sistema. Com isso 
   em mente, ele deve informar os seguintes dados: 
    
   * Nome
   * E-mail
   * Data de nascimento
   * Login
   * Senha
   * Telefone
   * Dados do veículo
   
   Os dados referentes para seu veículo são:
   
   * Ano
   * Placa
   * Modelo
   * Cor
   
   Após informar essas informações corretamente, Juninho terá uma conta
   acessível em nosso sistema. Caso ele envie dados que não condizem com
   as especificações como por exemplo e-mail ou tentar enviar algum campo
   vazio, irá receber uma mensagem de erro.

2 - Listar todos os usuários

   O Juninho pode ter uma listagem de todos os usuários cadastrados.
   
3 - Busca de um usuário pelo ID

   Ao acessar o sistema, nosso usuário Juninho pode fazer uma busca de 
   usuários pelo ID.
   
4 - Deletar um usuário peloID

   No sistema, Juninho pode deletar um usuário informando o ID de quem 
   ele quer excluir.
 
5 - Atualizar um usuário pelo ID

   Digamos que Juninho durante a criação de sua conta no sistema, tenha digitado 
   alguma informação errada sem perseber, é bem provavel que ele queira consertar
   o erro. Para isso ele pode atualizar os dados com de acordo com seu ID.
   
6 - Autenticação de usuário
   
   Em nosso sistema existem algumas rotas -endereços-  que necessitam de autenticação
   para realizar algumas funções. Tendo isso em mente, para que o Juninho consiga 
   excluir um de seus carros ou buscar a lista de todos os seus carros, ele precisa 
   fazer um login, usando seu login e senha informados no cadastro que deve ser 
   feito de antemão.

7 - Minhas informações

   Após realizar seu login, Juninho pode ter acesso as suas informações cadastradas
   mo sistema, exceto, ID e senha por questões de segurança.
   
8 - Cadastrar carro para usuário logado

   Após realizar o seu login, nosso usuário Juninho pode adicionar mais carros na sua
   conta. Digamos que ele tenha comprado mais dois carros e, queira cadastralos no sis-
   tema, ele poderá.
   
9 - Listar todos os carros do usuário logado

   Nosso usuário Juninho é uma pessoa muito rica, por isso ele tem muitos carros em nosso
   sistema. Digamos que ele queira consultar todos os seus carros, para isso ele precisa 
   fazer login, e acessando a função "listar todos", terá acesso a sua lista de carros 
   cadastrados.

10 - Buscar carro do usuário logado pelo ID

   Além de buscar todos os carros de uma só vez, o Juninho também pode buscar um de seus
   carros informando o id do mesmo.
   
