## Agendamento de Comunicado Api

* API desenvolvida com Java 17 e Spring Boot 3.x

---

### ⚡ Tecnologias

Estas são algumas das tecnologias e ferramentas usadas neste projeto.

![Java 17](https://img.shields.io/badge/-Java%2017-007396?style=flat-square&logo=java&logoColor=white)
[![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?style=flat-square&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/-Spring%20Security-6DB33F?style=flat-square&logo=Spring%20Security&logoColor=white)](https://spring.io/projects/spring-security)
[![Keycloak](https://img.shields.io/badge/-Keycloak-000000?style=flat-square&logo=keycloak&logoColor=white)](https://www.keycloak.org/)
[![JUnit](https://img.shields.io/badge/-JUnit-25A162?style=flat-square&logo=JUnit5&logoColor=white)](https://junit.org/junit5/)
[![Apache Kafka](https://img.shields.io/badge/-Apache%20Kafka-231F20?style=flat-square&logo=Apache%20Kafka&logoColor=white)](https://kafka.apache.org/)
[![Docker](https://img.shields.io/badge/-Docker-2496ED?style=flat-square&logo=Docker&logoColor=white)](https://www.docker.com/)
[![Swagger](https://img.shields.io/badge/-Swagger-85EA2D?style=flat-square&logo=swagger&logoColor=black)](https://swagger.io/)
[![PostgreSQL](https://img.shields.io/badge/-PostgreSQL-336791?style=flat-square&logo=postgresql&logoColor=white)](https://www.postgresql.org/)


---

### Descrição e Recursos
Esta API gerencia o agendamento de comunicados para serem enviados em uma data e hora especificadas. Ela suporta diversos canais de comunicação, incluindo email, SMS, notificações push e mensagens WhatsApp. A API fornece endpoints para agendar, consultar o status e remover comunicações agendadas.

1. **Endpoint para Agendar Comunicação**:
   - Este endpoint recebe uma solicitação para agendar o envio de uma comunicação.
   - Canais de comunicação suportados: email, SMS, notificações push e WhatsApp.

2. **Endpoint para Consultar Status da Comunicação Agendada**:
   - Permite consultar o status de uma comunicação agendada.

3. **Endpoint para Remover Comunicação Agendada**:
   - Permite remover uma comunicação agendada.

### Execução

---
* Toda a aplicação é conteinerizada com Docker, facilitando a configuração e execução dos testes. Para testar a aplicação, siga estes passos:

1. Certifique-se de que o Docker está instalado na sua máquina.
2. Baixe o projeto do repositório.
3. Navegue até o diretório do projeto no seu terminal.
4. Execute o seguinte comando para construir o projeto e iniciar todos os serviços necessários:

```bash
mvn clean -U install
docker-compose up --build
```

Isso iniciará todos os serviços necessários, incluindo o banco de dados, Kafka, Zookeeper e a aplicação Spring Boot. O Docker Compose construirá automaticamente a imagem da API conforme definido no arquivo `docker-compose.yml`.

Além disso, na pasta `resources/postman_collection`, você encontrará uma coleção do Postman com requisições de exemplo para testar a API.

Com esses passos, sua aplicação estará pronta e funcionando, pronta para testes.

### Autenticação e Autorização

---
Para garantir a segurança da aplicação, utilizamos o Keycloak em conjunto com Spring Security para implementar a autenticação e autorização.

O Keycloak é uma solução open source de gerenciamento de identidade e acesso, que facilita a adição de funcionalidades de segurança, como login único (SSO), autenticação de usuário e gerenciamento de permissões.

#### Processo de Autenticação:
1. **Login**:
   - O usuário deve fornecer suas credenciais (login e senha) para autenticar-se no sistema.
   - O Keycloak valida as credenciais fornecidas e emite um token de acesso (JWT) se a autenticação for bem-sucedida.

2. **Autorização**:
   - O token de acesso é usado para autorizar as solicitações às APIs.
   - Spring Security, configurado para trabalhar com Keycloak, valida o token e controla o acesso aos recursos com base nas permissões atribuídas ao usuário.

#### Configuração:
- **Keycloak**: Configuramos um servidor Keycloak para gerenciar os usuários e as permissões. As configurações específicas do Keycloak, como os clientes e as roles, são definidos no servidor Keycloak.
- **Spring Security**: Configuramos o Spring Security para integrar-se com o Keycloak, utilizando o adaptador de segurança do Keycloak para Spring Boot. As configurações de segurança incluem a definição de filtros de segurança e a integração com o provedor de tokens do Keycloak.

Com essa configuração, garantimos que apenas usuários autenticados e autorizados possam acessar e manipular os recursos da API.

### Referências Tecnológicas

---
* [Java](https://dev.java/)
* [Spring Boot 3](https://docs.spring.io/spring-boot/docs/3.1.11/reference/html/)
* [Documentação do Docker](https://docs.docker.com/)
* [Documentação do JUnit](https://junit.org/junit5/docs/current/user-guide/)
* [Keycloak](https://www.keycloak.org/documentation.html)