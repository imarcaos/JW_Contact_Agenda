# JAVA Projeto 2 - Web Agenda de Contacto

Projeto Java Web Servlet - JSP - JDBC - MySQL - MVC - Eclipse IDE

#### Os exercícios e explicações poderão estar comentados no próprio código ou nos pontos descritos a seguir.

## Projeto 2 - Java Web - Agenda de Contacto
Iniciado: 2024-07-16

### Ferramentas que vou Utilizar:
- JDK 21 LTS
- IDE Eclipse 2024-06
- Tomcat Servidor Web 10.1
- MySQL Base de Dados (MariaDB 10.4.32)
- Dbeaver SGBD

### Criando a BD no MySQL
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

### Criando o Projeto e as camadas MVC (Model View Controller)
- New Dynamic Web Project > Nome: "Contact_Agenda" > Gerar "web.xml"
- Expandir "Java Resources" > BT DT em cima de "src/mains/java" > new package > nome: "controller"
- Repetir o processo anterior e criar o pacote "model"
- BT DT em cima de controller > New > Servlet > nome: "Controller" > Next > Next > desmarcar o "doPost", mantendo o "doGet" > Finish.
- BT DT em cima de model > New > Class > nome: "DAO" > Finish.
- BT DT em cima de model > New > Class > nome: "JavaBeans" > Finish.
- Nossa camada View:
	- Expandir "src > main", BT DT em de "webapp" > New > HTML File > nome: index.html > escolher template do "html 5" > Finish.

### Configurando o Servlet e estilo página Index
2024-07-16
- Dentro da pasta "webapp" criei uma pasta "assets" que terá todos os ficheiros do site.
- Criar as pastas images e css dentro de assets.
- Adicionei ao index uma imagem a representar a agenda e um ícone favicon.
- Foi criado um botão "Entrar" com um link a apontar para o "main", que irei criar a rota no servlet.
- Adicionar a seguinte annotation antes da "Class Controller"
	- `@WebServlet("/main")`

### Camada Model (MVC)
2024-07-17
- Agora vamos editar o nosso ficheiro `JavaBeans.java` e começar a encapsular, criando as variáveis com os nomes dos campos da nossa tabela do tipo String e private:
	- `idcon, nome, telefone e email`.
- Criamos os Construtores,  Getters e Setters.
	- BT DT dentro da nossa classe > Source > 
		- > Generate Constructor using Fields
		- > Generate Getters and Setters