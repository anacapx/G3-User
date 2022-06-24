<h1 align="left">
  <img src="https://scontent.fcgh8-1.fna.fbcdn.net/v/t39.30808-6/289976373_1831753467021092_1415066520732617_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=730e14&_nc_eui2=AeHemTm85mGYGpEQE56ucVfxXf8q3M7fx65d_yrczt_HrtiTJxnjL4TKNWLpXSrAbicqtQZR1gObqpPUfH2jro06&_nc_ohc=1JQFZXHIX5sAX-PzHkv&_nc_ht=scontent.fcgh8-1.fna&oh=00_AT8mmJn7emQopNDKuBMeB-y9j7MPhwbSFLdGRpG5ZKrZ2A&oe=62B9E6F3" title="Kimchi Logo" width="400" />
</h1>

<p>Projeto elaborado para o desafio final do iLab, programa de aceleração de talentos em tech.
Consiste em um sistema que permite ao administrador, devidamente autenticado e autorizado, cadastrar e consultar pedidos e clientes. Foi realizado com uso de microsserviços, mensageria e deploy por pipeline em Kubernetes.</p>

- Para ver o repositório da **API Auth**, clique [aqui](https://github.com/anacapx/g3-auth)

- Para ver o repositório da **API Order**, clique [aqui](https://github.com/anacapx/g3-order)

- Para ver o repositório do **consumer Kafka**, clique [aqui](https://github.com/anacapx/g3-consumer)

- Para ver o repositório de **front-end**, clique [aqui](https://github.com/anacapx/g3-front)

- Para acessar a aplicação diretamente no seu browser, acesse: http://g3kimchi.tk

### 👩🏽‍💻 Pessoas Desenvolvedoras

- [Ana Carolina Amaral](https://github.com/anacapx)
- [Alessandro Costa](https://github.com/ab-costa)
- [Debora Brum](https://github.com/DeboraBrum)
- [Ester Lourenco](https://github.com/elolourenco)
- [Lisandre Andreolo](https://github.com/lisdrl)

## 💡 Mentor

- [Rafael Oliveira](https://www.linkedin.com/in/rafaelsomartins/)

## 💻 Tecnologias

Tecnologias que utilizamos para desenvolver esta API Rest:

- [Java](https://www.java.com/pt-BR/) 
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Swagger](https://swagger.io/)
- [Apache HttpClient](https://hc.apache.org/httpcomponents-client-5.1.x/)
- [PostgreSQL](https://www.postgresql.org/) 
- [JUnit](https://junit.org/junit5/) 
- [Mockito](https://site.mockito.org/) 
- [New Relic](https://newrelic.com/) 
- [Docker](https://www.docker.com/)
- [Kubernetes](https://kubernetes.io/pt-br/docs/concepts/overview/what-is-kubernetes/)
- [GitLab](https://gitlab.com/)
- [AWS](https://aws.amazon.com/)

## 🏁 Iniciando o projeto

### Pré-requisitos

- Configure um banco de dados [PostgreSQL](https://www.postgresql.org/) na sua máquina e crie um novo banco de acordo com o script encontrado neste repositório.
- Siga os passos para rodar a API Auth.

### Passo a passo

Para criar uma cópia do projeto na sua máquina local, siga os passos abaixo.

- Clone a API em sua máquina
- Importe o projeto na IDE de sua preferência
    - No Eclipse: File > Import > Existing Maven Projects
- Inicie a API
    - No Eclipse: 
		- selecione o projeto no Package Explorer;
		- clique em “Run” e escolha “Java Application”;
		- selecione a aplicação e clique em “Finish”.

- Edite o arquivo "./src/main/resources/application.properties" com as configurações do seu banco de dados:

```
spring.datasource.username = seu_usuario
spring.datasource.password = sua_senha
spring.datasource.url = jdbc:postgresql://localhost:5432/nome_do_seu_banco
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQL10Dialect
spring.jpa.show_sql = true
spring.jpa.format_sql = true
springdoc.api-docs.path=/api-docs
```

- Configure variáveis de ambiente:
```
API_AUTH_URL = http://localhost:8081/validate
```
- Rode o projeto na sua IDE.

Tudo pronto! A aplicação está rodando e pode receber requisições.

+ Documentação dos endpoints disponível na rota:
**localhost:8082/swagger-ui/index.html**

## ⚙️ Funcionalidades
Funcionalidades que o sistema oferece:
- Autenticação do token (integração com API Auth)
- Cadastrar um novo usuário
- Listar todos os usuários
- Buscar usuário por id
- Atualizar os dados do usuário
- Deletar um usuário
- Buscar um usuário a partir de um parâmetro (nome, email ou CPF)
- Validações das requisições
- Exceções Customizadas


