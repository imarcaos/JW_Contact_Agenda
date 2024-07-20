# JAVA Projeto 2 - Web Agenda de Contacto

Projeto Java Web Servlet - JSP - JDBC - MySQL - MVC - Eclipse IDE

#### Os exercícios e explicações poderão estar comentados no próprio código ou nos pontos descritos a seguir.

## Projeto 2 - Java Web - Agenda de Contacto
Iniciado: 2024-07-16

### Logs
- 2024-07-20 - Criado uma nova "branch eclipse_proj", o projeto feito no Eclipse, estará armazenado nesta "branch".
- 2024-07-20 - Vou tentar converter o projeto do Eclipse para o Netbeans ( Ponto 3 Eclipse -> ponto 3.1 NetBeans)
- 2024-07-20 - Cheio de Erros com o Eclipse que não consigo resolver `@WebServlet`, acredito ser por não estar a seguir o projeto original com JDK 11 e Tomcat9.
- 2024-07-16 - Início do Projeto

### Ferramentas que vou Utilizar:
- JDK 21 LTS
- IDE Eclipse 2024-06
- Tomcat Servidor Web 10.1
- MySQL Base de Dados (MariaDB 10.4.32)
- Dbeaver SGBD

### 1 - Criando a BD no MySQL
- Adicionar uma nova folha de SQL Queries e aplicar os comandos a seguir:
    ``` sql
        create database dbagenda; 

        use dbagenda; 

        create table contatos (
            idcon int primary key auto_increment,
            nome varchar(50) not null,
            telefone varchar(15) not null,
            email varchar(50)
        ); 

        show tables;

        describe contatos;
    ```

### 2 - Atualização da Estrutura depois do Eclipse 2021-03
2024-07-16
- No antigo tínhamos o "src" onde ficavam os servlets e as classe java, agora ficam em "src/main/java"
- Os ficheiros do site ficavam em "WebContent", agora ficam: "Web App Libraries/Main/webapp".

### 3 - Configuração do Tomcat no Eclipse
- Instalar o Tomcat, pesquisar por Tomcat e no [site oficial](https://tomcat.apache.org/), fazer download da versão que vamos utilizar no formato zip, descompactar e colar na pasta que deseja utilizar, ex: `c:/`
- **Sempre que mudarmos de Workspace temos de configurar novamente o Tomcat para aquele Workspace.**
- No Eclipse, na parte inferior direita, próximo ao terminal:	
	1. Clicar em Server...
	2. Embaixo aparece um link "No servers are available" > clicar aqui.
	3. Escolhemos "Apache" > A versão do nosso Tomcat instalado `10.1` no meu caso > Next
	4. clica em "Browse" e navega até a pasta do Tomcat e seleciona a pasta do Tomcat "c:/qualquer_coisa/apache-tomcat-10.1.25" 
	5. Em "JRE" selecionamos o nosso "JDK"
	6. Finish

### 4 - Criando o Projeto e as camadas MVC (Model View Controller)
- New Dynamic Web Project > Nome: "Contact_Agenda" > Gerar "web.xml"
- Expandir "Java Resources" > BT DT em cima de "src/mains/java" > new package > nome: "controller"
- Repetir o processo anterior e criar o pacote "model"
- BT DT em cima de controller > New > Servlet > nome: "Controller" > Next > Next > desmarcar o "doPost", mantendo o "doGet" > Finish.
- BT DT em cima de model > New > Class > nome: "DAO" > Finish.
- BT DT em cima de model > New > Class > nome: "JavaBeans" > Finish.
- Nossa camada View:
	- Expandir "src > main", BT DT em de "webapp" > New > HTML File > nome: index.html > escolher template do "html 5" > Finish.

### 5 - Configurando o Servlet e estilo página Index
2024-07-16
- Dentro da pasta "webapp" criei uma pasta "assets" que terá todos os ficheiros do site.
- Criar as pastas images e css dentro de assets.
- Adicionei ao index uma imagem a representar a agenda e um ícone favicon.
- Foi criado um botão "Acessar" com um link para "main", que irei criar a rota no servlet.
- Ao adicionar a rota na annotation "@WebServlet" ocorreu um erro em que ele não reconhece as strings internas, despendi muitas horas a procura de uma solução e nada, acredito que por não ter conhecimento sufiente até o momento, mas hei de lá chegar. 
	- Pela aula que estou a seguir, o professor adicionou duas rotas dentro da  `urlPatterns`, motivo de todos os erros que tive, só é possível apontar para uma rota (era o erro que o servidor apontava), obviamente ainda preciso de mais estudos para certificar estes dados.
	- Na aula era dito para adicionar a seguinte annotation antes da "Class Controller"
		- `@WebServlet(urlPatterns ["/Controller", "/main"])`
	- Solucionei deixando apenas desta forma:
		- `@WebServlet("/main")`

### 6 - Camada Model (MVC)
2024-07-17
- Agora vamos editar o nosso ficheiro `JavaBeans.java` e começar a encapsular, criando as variáveis com os nomes dos campos da nossa tabela do tipo String e private:
	- `idcon, nome, telefone e email`.
- Criamos os Construtores,  Getters e Setters.
	- BT DT dentro da nossa classe > Source > 
		- > Generate Constructor using Fields
		- > Generate Getters and Setters

### 7 - Conexão com a DB MySQL
2024-07-17

#### Drive Connector
- Para descarregar a library do MySQL Connector, fazemos uma pesquisa por MySQL Connector, e fazemos download do `JDBC Driver for MySQL (Connector/J)` > selecionamos "Platform Independent" > ficheiro zip (se preferir) > em baixo da página "No thanks, just start my download" > Extrair.
- Agora importamos para dentro do nosso projeto:
- Expandimos as pastas dentro do nosso projeto até chegar na pasta lib, conforme caminha exemplo abaixo: /nome_projeto/src/main/webapp/WEB-INF/lib
- na pasta onde extraímos o drive connector, buscamos o jar `mysql-connector-j-version.jar`. e arrastamos (copy files) para dentro da pasta lib indicada anteriormente ou pode fazer "copy" em cima do ficheiro e "paste" em cima da pasta lib.

#### Classe DAO - Módulo e Parâmetros de Conexão
- Abrir a nossa classe "`DAO.java`"
- Dentro da classe criamos 4 variáveis do tipo `String` para conexão:
	- `driver`, `url`, `user`, `pass`.
	- para encontrar o formato de `String` vamos ao site [Oficial do MySQL](https://dev.mysql.com/doc/) > Documentations > Rolamos a página para baixo até encontrar "Connector/J" e entrar > Clicamos em "Connector/J Examples" > Clicamos na primeira opção Example 7.1, “Connector/J: Obtaining a connection from the DriverManager” (poderá ser outra versão).
	- Copiamos a String Driver `com.mysql.cj.jdbc.Driver` para a nossa variável `driver` 
	- Copiamos a String Connection `jdbc:mysql://localhost/test` para a nossa variável `url`, onde está "teste" será o nome da nossa DB `contact_javap1`.
	- Variáveis `user` e `pass` conforme utilizamos em nosso servidor MySQL.
- Criamos o método para efetuar a ligação e um para teste.
``` java
// teste de conexão
	public void testeConexao() {
		try {
			Connection con = conexao();
			System.out.println("Conectado " + con);
			con.close();
			System.out.println("Fechada " + con);
		} catch (Exception e) {
			System.out.println("Erro Teste de Conexão: " + e);
		}
	}
```
- Na Classe "Controller" chamamos o método para teste da classe "DAO".
	- fazemos o import da classe "DAO": `import model.DAO;`
	- dentro do método da classe "Controller" criamos um objeto "DAO" para ter acesso ao método teste: `DAO dao = new DAO();`
	- dentro do método "`doGET`" chamamos o método teste da classe "DAO": `dao.testeConexao();`.
- Teste sem erros a primeira.

### 8 - Configurando a Camada Controller no Servlet
2024-07-18
- Com o BT DT do rato em cima da pasta "webapp" > New > JSP File > nome: `agenda.jsp`.
- Configurado na Servlet "Controller" as rotas para a `agenda.jsp`.


### 9 - Adicionar Contato e Validação do Formulário
2024-07-18
- Passos para Inserir novo Contato:
	1. `agenda.jsp` ao clicar em "Adicionar Contato" > `novo.html` que irá conter o formulário.
	2. `novo.html` > redireciona para um documento `validar.js` para validar o formulário.
	3. `validar.js` > depois de validar, retorna ao `novo.html`
	4. `novo.html` > após validar os dados seguem para a camada "controller" `Controller.java`
	5. `Controller.java` > dados são encaminhados para a camada "model" `JavaBeans.java`
	6. `Controller.java` > invoca o método inserir na camada "model" `DAO.java`
	7. `DAO.java` > requisita os dados no model `JavaBeans.java`
	8. `JavaBeans.java` > retorna os dados para o model `DAO.java`
	9. `DAO.java` > executa a inserção dos dados no BD.
	10. `Controller.java` > após a inserção redireciona para a "view" `agenda.jsp`

- Criar a página `novo.html` > BT DT em cima da pasta "webapp" > New > HTML File > nome: "novo.html".
- Criar uma nova pasta "js" dentro da pasta "assets"  e adicionar um novo ficheiro `validar.js`
	- Em cima da pasta "js" > BT DT > New > JavaScript File

### 10 - Controller - Ação Insert no doGet
- Dentro da página `novo.html` no nosso formulário, adicionamos a action `insert`.
- Vamos adicionar mais uma rota dentro do nosso "Controller" no  `urlPatterns` a rota `/insert`.
- Ao adicionar a nova rota no annotation `@WebServlet`, voltou o conflito novamente descrito anteriormente, já tentei por mais algumas horas resolver e nada, de certeza que é algo que desconheço no Eclipse.
- Já estava com sentido de fazer este projeto no NetBeans e esta é a chamada final kkk, vou testar para ver se corre melhor, preciso estudar mais coisas, não posso perder demasiado tempo aqui.