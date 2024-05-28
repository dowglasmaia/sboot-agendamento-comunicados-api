## Agendamento de Comunicado Api

* API desenvolvida com Java 17 e Spring Boot 3.x

---

### ⚡ Tecnologias

Estas são algumas das tecnologias e ferramentas usadas neste projeto.

![Java 17](https://img.shields.io/badge/-Java%2017-007396?style=flat-square&logo=java&logoColor=white)
[![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-6DB33F?style=flat-square&logo=spring&logoColor=white)](https://spring.io/projects/spring-boot)
[![JUnit](https://img.shields.io/badge/-JUnit-25A162?style=flat-square&logo=JUnit5&logoColor=white)](https://junit.org/junit5/)
[![Apache Kafka](https://img.shields.io/badge/-Apache%20Kafka-231F20?style=flat-square&logo=Apache%20Kafka&logoColor=white)](https://kafka.apache.org/)
[![Docker](https://img.shields.io/badge/-Docker-2496ED?style=flat-square&logo=Docker&logoColor=white)](https://www.docker.com/)
[![Swagger](https://img.shields.io/badge/-Swagger-85EA2D?style=flat-square&logo=swagger&logoColor=black)](https://swagger.io/)

---

### Descrição e Recursos
Esta API gerencia o agendamento de comunicados para serem enviadas em uma data e hora especificadas. Ela suporta diversos canais de comunicação, incluindo email, SMS, notificações push e mensagens WhatsApp. A API fornece endpoints para agendar, consultar o status e remover comunicações agendadas.

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

### Referências Tecnológicas

---
* [Java](https://dev.java/)
* [Spring Boot 3](https://docs.spring.io/spring-boot/docs/3.1.11/reference/html/)
* [Documentação do Docker](https://docs.docker.com/)
* [Documentação do JUnit](https://junit.org/junit5/docs/current/user-guide/)
