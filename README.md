# JAVA Projeto 2 - Web Agenda de Contacto

Projeto Java Web Servlet - JSP - JDBC - MySQL - MVC - Eclipse IDE

#### Os exercícios e explicações poderão estar comentados no próprio código ou nos pontos descritos a seguir.

## Projeto 2 - Java Web - Agenda de Contacto
Iniciado: 2024-07-16

#### Janela de Operações do Contato
![Operações da Agenda de Contato](./other_files/java_proj2-contatos.png)

#### Print da impressão do PDF
![PDF Gerado](./other_files/java_proj2-contatos-pdf.png)


### Logs
- 2024-08-05 - Projeto finalizado
- 2024-07-20 - Criado uma nova "branch eclipse_proj", o projeto feito no Eclipse, estará armazenado nesta "branch".
- 2024-07-20 - Vou tentar converter o projeto do Eclipse para o Netbeans ( Ponto 3 Eclipse -> ponto 3.1 NetBeans)
- 2024-07-20 - Cheio de Erros com o Eclipse que não consigo resolver `@WebServlet`, acredito ser por não estar a seguir o projeto original com JDK 11 e Tomcat9.
- 2024-07-16 - Início do Projeto

### Ferramentas que vou Utilizar:
- JDK 21 LTS
- ~~IDE Eclipse 2024-06~~
- IDE Netbeans 18
- Tomcat Servidor Web 10.1
- MySQL Base de Dados (MariaDB 10.4.32)
- Dbeaver SGBD

### Abreviaturas:
- BT DT (Botão Direito do Rato/Mouse)
- BD (Base de Dados)

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


## **Projeto convertido para ser desenvolvido no Netbeans**
2024-07-20
- O projeto foi reestruturado para o Netbeans a partir do ponto 3 e os pontos para o Netbeans renomeados para `número.1`, que inicia a partir do `3.1`.
- O projeto antigo que foi iniciado no Eclipse (como erros), está com a numeração normal a partir do `3`, no fim do Readme.


### 3.1 - Configuração do Tomcat no Netbeans
- - Instalar o Tomcat, pesquisar por Tomcat e no [site oficial](https://tomcat.apache.org/), fazer download da versão que vamos utilizar no formato zip, descompactar e colar na pasta que deseja utilizar, ex: `c:\`
- Menu Principal > Tools > Servers >
	1. Add Server...
	2. Selecionar "Apache Tomcat or TomEE" > Next >
	3. Server Locations (navegar até a pasta da instalação do Tomcat) selecionar a pasta do Tomcat
		- exemplo no Windows: `c:\Tomcat 10.1`
		- colocar as credenciais configuradas no Tomcat
			- User: `admin` e Password `admin` (Pode ser qualquer credencial)
	4. Finish.


### 4.1 - Criando o Projeto e as camadas MVC (Model View Controller)  no Netbeans
2024-07-20
Obs. Como venho com o projeto a 50% feito no Eclipse, tentei fazer o import do projeto dentro do Netbeans, mas não carregou quase nada, teria de fazer manualmente, optei por criar do zero, obviamente vou reaproveitando o código que já escrevi no Eclipse.

#### Criando o Projeto Java Web / Servidor Tomcat
- File > New Project > Java with Ant > Java Web > Web Application > Nome: "Contact_Agenda" > Next > Verificar se o Servidor Tomcat esta selecionado > Next > Nesta momento não selecionamos nenhuma framework > Finish.
- A estrutura das pastas no [[NetBeans]] são:
	- Web Pages (Views)
	- Source Packages (Controllers e Models)

#### Criando as Camadas MVC
- Expandir a pasta do projeto > BT DT em cima de "Source Packages" > New > Java Packages... > Package Name: "controller"
- Repetir o processo anterior e criar o pacote "model"
- BT DT em cima do Package "controller" > New > Servlet > nome: "Controller" > Next > Finish.
	- Mais abaixo pode expandir os métodos "HttpServelet Methods" para ver o método "doGet" e "doPost", o método "doPost" pode ser apagado, pois não o vamos utilizar.
- BT DT em cima de model > New > Java Class > Class Name: "DAO" > Finish.
- BT DT em cima de model > New > Java Class > Class Name: "JavaBeans" > Finish.
- Nossa camada View:
	- O NetBeans por padrão já cria o `index.html`, caso não tenha, criar:
	- BT DT em de "Web Pages" > New > HTML... > HTML File Name: index.html > Finish.
- Testar o Projeto para ver se esta tudo "OK" até este ponto.
	- Escolher o Browser e dar "Play" botão verde.
	- Aconteceu um erro, em que aparecia esta mensagem na hora do deploy: `FAIL - Application already exists at path [/Contact_Agenda]`, tentei apagar a pasta temporária do Windows para limpar o conflito e nada.
	- Fechei o NetBeans e abri-o novamente (Restart), o projeto correu perfeitamente a seguir.


### 5.1 - Configurando o Servlet e estilo página Index
2024-07-20

#### Criando a pastas, ficheiros e algumas configurações
- Criar pastas para receber os ficheiros de configurações:
	1. BT DT em "Web Pages" > New Folder... > Folder Name: 
		- assets
	2. Dentro da pasta assets, repetir o processo "1" para as três pastas a seguir:
		- css
		- js
		- images
- Escolha uma imagem para representar sua "Agenda de Contatos" e um ícone "favicon" para seu site e copie (arraste) para dentro da pasta "images".
- Criar um ficheiro "style.css" que irá receber as configurações (aproveitei o criado anteriormente, só arrastei para a pasta):
	- BT DT em cima da pasta "css" > New > Other > Web > Cascading Style Sheet
	- ver configurações no ficheiro.
- Foi adicionado algumas configurações no HTML (ver ficheiro) e criado um botão "Acessar" com um link para "main", que irei criar a rota no servlet. **Ter atenção neste ponto, para evitar erros.**

#### Configurando o Servlet
- Na linha onde está o `@WebServlet`, está assim:
	- `@WebServlet(name = "Controller", urlPatterns = {"/Controller"})`
	- Vamos agora adicionar uma nova rota para o `main` dentro do `urlPatterns`:
		- NOTA: essa parte começou os conflitos no Eclipse
	- `@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/main"})`
- Testar para ver está tudo "OK"
	- Correu Perfeitamente aqui
	- Não sabem a felicidade que sinto, já ando a muitas horas (dias) a tentar por isso a trabalhar. Estou a refazer este projeto em menos de 1 hora no [[NetBeans]] e está a correr tudo sem stress até o momento.


### 6.1 - Camada Model (MVC)
2024-07-20
#### Criando o Construtor, Getters e Setters
- Agora vamos editar o nosso ficheiro `JavaBeans.java` e começar a encapsular, criando as variáveis com os nomes dos campos da nossa tabela do tipo String e private:
	- `idcon, nome, telefone e email`.
- Criamos os Construtores,  Getters e Setters.
	- BT DT dentro da nossa classe (onde queremos inserir o código) > Insert Code... > 
		- Inserir a seguir as variáveis: 
			- > Constructor > Não Selecionar nada > Generate 
		- Inserir a seguir ao Construtor vazio: 
			- > Constructor > Selecionar todas as variáveis > Generate
		- Inserir a seguir aos Construtores:
		- > Getter and Setter... >  Selecionar todas as variáveis > Generate


### 7.1 - Conexão com a DB MySQL
2024-07-20
#### Drive Connector
- Para descarregar a library do MySQL Connector, fazemos uma pesquisa por MySQL Connector, e fazemos download do `JDBC Driver for MySQL (Connector/J)` > selecionamos "Platform Independent" > ficheiro zip (se preferir) > em baixo da página "No thanks, just start my download" > Extrair.
- Agora importamos para dentro do nosso projeto:
- BT DT na pasta libraries (`Nome_Projeto > libraries`) > Add Jar/Folder > caminho onde extraíste o connector (na pasta onde extraímos o drive connector, buscamos o jar `mysql-connector-j-version.jar`) > Open (o ficheiro já deve aparecer na librarie)
#### Classe DAO - Módulo e Parâmetros de Conexão
- Abrir a nossa classe "`DAO.java`"
- Dentro da classe criamos 4 variáveis do tipo `String` para conexão:
	- `driver`, `url`, `user`, `pass`.
	- para encontrar o formato de `String` vamos ao site [Oficial do MySQL](https://dev.mysql.com/doc/) > Documentations > Rolamos a página para baixo até encontrar "Connector/J" e entrar > Clicamos em "Connector/J Examples" > Clicamos na primeira opção Example 7.1, “Connector/J: Obtaining a connection from the DriverManager” (poderá ser outra versão).
	- Copiamos a String Driver `com.mysql.cj.jdbc.Driver` para a nossa variável `driver` 
	- Copiamos a String Connection `jdbc:mysql://localhost/test` para a nossa variável `url`, onde está "teste" será o nome da nossa DB `contact_javap1`.
	- Variáveis `user` e `pass` conforme utilizamos em nosso servidor MySQL.
	- O meu Netbeans, não carregou os imports automáticos, são estes:
``` java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
```

- Agora criamos o Método  `Connection conectar()` responsável pela conexão com a BD.

- Vou Criar o Método temporário `testeConexao()` para efetuar a ligação e teste.
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
- Atenção: o Netbeans para apresentar o teste, pode ser visto em output "Apache Tomcat  or TomEE".
- Teste sem erros a primeira.


### 8.1 - Configurando a Camada Controller no Servlet
2024-07-20
- Com o BT DT do rato em cima da pasta "Web Pages" > New > JSP > File Name: `agenda.jsp`.
- Adicionado o botão "Novo Contato" na `agenda.jsp` e estilos.
- Configurado na Servlet "Controller" teste e impressão da rota `/main`.


### 9.1 - Adicionar Contato e Validação do Formulário
2024-07-20

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

- Criar a página `novo.html` > BT DT em cima da pasta "Web Pages" > New > HTML... > HTML File Name: "novo.html".
- Dentro da página `novo.html` criamos um formulário para receber os dados ao criar um Novo Contato.
- Na página `agenda.jsp` adicionar a ligação dentro do botão "Criar Novo Contato", apontando para a página `novo.html`.
- Fazer o estilo do formulário no nosso ficheiro "style.css"
- Na pasta "js" dentro da pasta "assets", adicionar um novo ficheiro com o nome: `validar.js`
	- Em cima da pasta "js" > BT DT > New > JavaScript File
	- Criar um script com uma função simples `validar()` para validar os campos se estão preenchidos.
- Adicionar o script antes do fecho da tag `</body>` e chamar a função `onclick="validar()"` dentro do botão `adicionar`.
- Teste "OK" até aqui. Passos feitos: 1 a 3.


### 10.1 - Controller - Ação Insert no doGet
2024-07-21
- Dentro da página `novo.html` no nosso formulário, adicionamos a action `insert` dentro do formulário `frmContato`.
- Vamos adicionar mais uma rota dentro da nossa classe `Controller` no  `urlPatterns` a rota `/insert`, ficando assim:
	- `@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/main", "/insert"})`
- Dentro do Método `doGet`, adicionamos mais uma condição para fazer a rota para o `insert` apontar para o novo método `novoContato(request, response)` que iremos criar.
- Agora criamos o método  `novoContato(request, response)` e um pequeno código para testar se recebemos os dados:
``` java
 // teste de recebimento dos dados do formulário
    System.out.println(request.getParameter("nome"));
    System.out.println(request.getParameter("telefone"));
    System.out.println(request.getParameter("email"));
```
- Dados apresentados com sucesso!
- Feitos os testes, ainda na classe `Controller`, criamos um objeto que aponta para a classe `JavaBeans` para termos acesso aos métodos `set` e armazenar os dados nas variáveis encapsuladas.
``` java
// Armazenar na variáveis JavaBeans
    contato.setNome(request.getParameter("nome"));
    contato.setTelefone(request.getParameter("telefone"));
    contato.setEmail(request.getParameter("email"));
```
- Mais um teste, retornou tudo sem erros, já podemos apagar o nosso código de teste e manter o último. Foram feitos os passos 4 e 5.


### 11.1 - Model - SQL Create
2024-07-21
- Na nossa model `DAO.java` vamos criar um novo método `inserirContato(JavaBeans contato)` e adicionar o comandos necessários para buscar e inserir os dados na nossa BD.
- Dentro do nosso `Controller.java`, no método `novoContato` aproveitamos o objeto `contato` criado anteriormente e enviamos para a model `DAO.java` chamando o método `dao.inserirContato(contato);`.
- Ainda dentro do método `novoContato`, depois de inserir o "contato" redirecionamos para a página `agenda.jsp` com o código `response.sendRedirect("main");`.
- Teste feito, dados inseridos com sucesso na nossa BD `dbagenda`. Passos feitos: 6 a 10.


### 12.1 - Listar Contatos - ArrayList
2024-07-22
- Na classe `DAO.java` vamos criar um método responsável pela listagem de dados:
	- `public ArrayList<JavaBeans> listarContatos()`
- Na classe `Controller.java` dentro do método `contatos` vamos adicionar um objeto `lista` para receber o resultado da consulta na DB.
- Novamente na classe `DAO.java` já podemos buscar os dados do método `contatos` na classe `Controller.java`, armazenando em nossa array list no método `listarContatos()`.
- Dentro da classe `controller.java` adicionamos um loop `for` para listar os para verificar se está tudo a funcionar.
``` java
 // teste de recebimento da lista
for (int i = 0; i < lista.size(); i++) {
    System.out.println(lista.get(i).getIdcon());
    System.out.println(lista.get(i).getNome());
    System.out.println(lista.get(i).getTelefone());
    System.out.println(lista.get(i).getEmail());
}
```
- Rodar nossa aplicação para testar, correu tudo perfeitamente.
2024-07-28
- Encaminhar o objeto lista criado dentro do método `contatos` na classe `Controller.java` para o documento (página) `agenda.jsp`.
- No nosso documento `agenda.jsp` vamos configurar para apresentar a lista de contatos obtidas.
	- Fazemos um pequeno código de teste para verificar se conseguimos obter os dados. (código idêntico ao anterior)
- Melhorar o estilo do formulário de listagem dos contatos na página `agenda.jsp`.


### 13.1 - Editar Contatos - CRUD Update

#### Editando um Contato - Botão e Requisição
2024-07-30
- Dentro da página `agenda.jsp`, adicionar um botão `Editar` na tabela de listagem de contatos, com uma ligação para uma requisição ao servlet com o nome `select`.
- Na nossa classe `Controller.java`, vamos adicionar o caminho `/select` dentro da nossa servlet no `@WebServlet`, ficando assim:
	- `@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/main", "/insert", "/select"})`.
- Ainda na classe `Controller.java`, adicionamos mais um `else if` para chamar o método que iremos criar`listarContato` quando solicitada a requisição `select`.
- Depois de criado o método `listarContato` (deixar o método vazio) , fazemos um teste, executando a nossa aplicação, ao clicar no botão `Editar` deverá aparecer a impressão `/select` na consola do Netbeans.
- Dentro da `agenda.jsp`, na ligação do nosso botão editar, adicionamos o código que irá buscar o id do contato, passando como parâmetro para o nosso novo método na classe `Controller.java`: 
	- `select?idcon=<%=lista.get(i).getIdcon()%>`
- Na classe `Controller.java` adicionamos dentro do nosso método um código para requisitar o id do contato e fazemos um teste até este ponto para verificar se está tudo bem.
	- Código do método até aqui:
``` java
// Recebe o id do contato que será editado
String idcon = request.getParameter("idcon");
System.out.println(idcon); // teste
```
- Teste OK.
- Configurar a variável para o `JavaBeans.java`, código:  `contato.setIdcon(idcon);`
- Parte 1/4.

#### Editando um Contato - Pesquisar o Contato
2024-07-31
- Na classe `DAO.java`, vamos adicionar 2 novos métodos para podermos fazer o update do contato:
	- `public void selecionarContato(JavaBeans contato)`
	- 
- Na classe `Controller.java` dentro do método `listarContato` chamamos o nosso novo método passando os dados recebidos por parâmetros `dao.selecionarContato(contato);`
- Depois de construir o código do nosso método `selecionarContato`, dentro da classe `Controller.java` no método `listarContato` adicionamos uma código para fazermos mais um teste se obtemos os dados do contato selecionado, código do teste:
``` java
// teste de recebimento
System.out.println(contato.getIdcon());
System.out.println(contato.getNome());
System.out.println(contato.getTelefone());
System.out.println(contato.getEmail());
```
- teste OK
- Parte 2/4.

#### Editando um Contato - Formulário para Editar
2024-08-01
- Criar uma página `jsp` para criar um formulário e editar nosso contato:
	- BT DT em cima de "Web Pages" > New... > JSP > Nome: `editar.jsp` > Finish
	- Podemos aproveitar o código do formulário da página `novo.html` e alterar algumas partes.
- Na classe `Controller.java` , dentro do método `listarContato` , configuramos os atributos recebidos do formulário para o conteúdo do JavaBeans.
``` java
request.setAttribute("idcon", contato.getIdcon());
request.setAttribute("nome", contato.getNome());
request.setAttribute("telefone", contato.getTelefone());
request.setAttribute("email", contato.getEmail());
```
- Encaminhar as informações para a página `editar.jsp`.
``` java
RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
rd.forward(request, response);
```
- testar -> OK
- parte 3/4

#### Editando um Contato - Update do Contato
2024-08-01
- Na página `editar.jsp` adicionamos uma nome`update` para a ação do nosso formulário:
	- `<form name="frmContato" action="update">`
- Na nossa classe `Controller.java` vamos adicionar a rota `/update` dentro do servlet `@WebServlet` para receber nossa requisição:
	- `@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/main", "/insert", "/select", "/update"})`
- O processo é o mesmo dos anteiros, adicionar dentro do método `doGet`, mais um if para direcionar para o método que iremos criar `editarContato`:
``` java
} else if (action.equals("/update")) {
            editarContato(request, response);
```
- Agora criamos o método `editarContato` e adicionamos o nosso código de teste (igual aos anteriores) e testamos. (teste -> OK)
- Adicionamos o código para configurar nossas variáveis no `JavaBeans`
``` java
contato.setIdcon(request.getParameter("idcon"));
contato.setNome(request.getParameter("nome"));
contato.setTelefone(request.getParameter("telefone"));
contato.setEmail(request.getParameter("email"));
```
- Ainda na classe `Controller.java` chamamos o método `alterarContato` que iremos criar na classe `DAO.java` (pode ser criado já e depois voltar aqui para chamar o método):
	- `dao.alterarContato(contato);`
- Na classe `DAO.java` criamos o método ` alterarContato`, criamos o código para a conexão com a BD e enviamos as informações atualizadas.
- Na classe `Controller.java` dentro do método `editarContato` fazemos o redirecionamento para a página main com as atualizações feitas.
	- `response.sendRedirect("main");`
- Fazemos um teste -> OK
- parte 4/4.

### 14.1 Apagar Contato - CRUD Delete
2024-08-03
- Na página `agenda.jsp` vamos adicionar um botão "Apagar" ao lado do "Editar" e estilizar a cor do botão para vermelho.
- Na pasta "js" dentro da pasta "assets", adicionar um novo ficheiro com o nome: `confirmador.js`
	- Em cima da pasta "js" > BT DT > New > JavaScript File - Nome: `confirmador.js` > Finish.
	- Criar um código com uma função simples `confirmar()` para pedir confirmação da exclusão do contato.
	- Em um primeiro momento adicionamos um código para abrir a caixa de diálogo com o id do contato a excluir:
		- `alert(idcon)`
- Na página `agenda.jsp`, antes do fecho da tag `</body>` , adicionar a tag para chamar o script que acabamos de criar:
	- `<script type="text/javascript" src="assets/js/confirmador.js"></script>`
-  Na ligação do nosso botão "Apagar", adicionamos o código para chamar a função `confirmar(idcon)`:
	- `<a href="javascript: confirmar(<%=lista.get(i).getIdcon()%>)" class="btn2">Apagar</a>`
- Fazemos um teste para ver se retorna o id do contato que queremos excluir, a seguir ao teste vamos terminar o código da função `confirmar(idcon)`:
	- `window.location.href = 'delete?idcon=' + idcon`
-  Na nossa classe `Controller` vamos adicionar a rota `/delete` dentro do servlet `@WebServlet` para receber nossa requisição:
	- `@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/main", "/insert", "/select", "/update", "/delete"})`
- O processo é o mesmo dos anteriores, fazemos um teste de encaminhamento da rota, a seguir adicionar dentro do método `doGet`, mais um if para direcionar para o método que iremos criar `apagarContato`.
- Agora vamos criar o método `apagarContato` e adicionamos o código para fazer um teste para ver se o id do contato estar a chegar ao método.
- Na classe `DAO` adicionamos o método `eliminarContato` e já logo voltamos para escrever o restante do código.
- Novamente na classe `Controller`, dentro do método `apagarContato` vamos chamar e executar o método que acabamos de criar na classe `DAO`:
	- `dao.eliminarContato(contato);`
- Retornando ao nosso método `eliminarContato` na classe `DAO`, vamos escrever o código no nosso método.
 - Na classe `Controller`, dentro do método `apagarContato`, fazemos o redirecionamento para a `main` (`agenda.jsp`) atualizando as alterações. 


### 15.1 Criar Relatórios em PDF com itexpdf
2024-08-05
- Para trabalhar com a biblioteca itexpdf, precisamos fazer o download, fazer a pesquisa por "itextpdf" e escolher a que retorna no Github: https://github.com/itext/itextpdf, rolar a tela abaixo, escolher a última versão `itextpdf-version-x` que aparece core, clicar em cima da ligação e fazer o download (na altura que estou escrevendo esta documentação está nesta versão: `itextpdf-5.5.13.2.zip`).
	- Fazemos o download do zip para a nossa máquina.
	- Extraímos para um local reservando para as nossa bibliotecas Java em que não te esqueças.
	- Dentro do Projeto no Netbeans > Libraries > BT DT > Add Jar/Folder... > navegamos até a pasta onde extraímos os ficheiros e vamos buscar o nosso jar `itextpdf-5.5.13.2.jar` > Open
	- Já deve aparecer na nossa biblioteca.
- Na página `agenda.jsp` vamos criar o botão de relatório.
	- Abaixo do botão `Novo Contacto`, adicionamos o novo botão `Relatório`
		- `<a href="report" class="btn2">Relatório</a>`
	- Salvamos tudo e fazemos um teste para verificar.
- Na classe `Controller` vamos adicionar a nova rota Scriptlet `/report`
	- `@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report"})`
	- No método `doGet` adicionamos outro `elseif` para receber a requisição apontando para um novo método `gerarRelatorio` que iremos criar.
	- Ao adicionar o código necessário ele deve importar automaticamente a biblioteca que adicionamos: `import com.itextpdf.text.Document;` (ter atenção no código a outros imports que ele necessita para criarmos os documentos). 
	- No Netbeans para adicionar bibliotecas podemos usar como atalho o "CTRL+SHIFT+i" ou clicar na lâmpada do lado esquerdo e escolher o import se este for listado, em última situação adicionamos manualmente.
	- Dentro do método `gerarRelatorio` depois de escrito boa parte do código, fazemos um teste para ver se a rota está a funcionar.
``` java
// código do gerarRelatorio até o momento.
Document documento = new Document();        
        try {
            // tipo de conteúdo
            response.setContentType("application/pdf");            
            // nome do documento
            response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");            
            // criar o documento
            PdfWriter.getInstance(documento, response.getOutputStream());            
            // abir o documento -> conteúdo
            documento.open();
            documento.add(new Paragraph("Lista de Contatos:"));
            documento.close();              
        } catch (Exception e) {
            System.out.println("Erro ao Gerar Documento PDF: " + e);
            documento.close();
        }
```
- Até este ponto, se tudo correu bem, ao executar nosso programa e carregar no botão `Relatório` ele deve retornar um PDF apenas com título "Lista de Contatos:"
- Agora vamos escrever o código para carregar as informações da nossa BD.
- Teste feito, PDF gerado com os contatos.


### 16.1 Projeto Finalizado
2024-08-05
- O código contém comentários e algum código extra que não irei remover a título de estudos, mas em um projeto a ser entregue, deve ser limpo e documentado.
- Deixo a ligação para o vídeo da aula deste projeto que não é de minha autoria, serviu apenas para meus estudos.
	- Projeto [Agenda de Contatos](https://youtube.com/playlist?list=PLbEOwbQR9lqz9AnwhrrOLz9cz1-TxoiUg&si=Xd54IpYM6KSkSF3R).




## **Projeto antigo iniciado com o Eclipse**
2024-07-20
- Este projeto no Eclipse está com erros no annotation `@WebServlet`, no ponto 10
- Fica a descrição dos passo no Eclipse para uma eventual consulta, converti o projeto para trabalhos com o Netbeans.

### 3 - Configuração do Tomcat no Eclipse
2024-07-16
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
2024-07-16
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