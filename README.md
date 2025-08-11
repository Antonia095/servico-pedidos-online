# Sistema de pedidos online

## 📝 Descrição

O projeto consiste em um sistema distribuido em arquitetura de microsserviços, utiliza
tecnologias como Spring Boot, Apache Kafka, DTOs, Lombok, banco de dados Postgres e Docker. 

O projeto possue 3 microsserviços que se comunicam entre si via Kafka. São eles:

- **pedido-service**: Cria o pedido e publica no tópico novo-pedido
- **pagamento-service**: Escuta pedido e publica o pagamento no tópico pagamento-processado
- **atualiza-service**: Escuta pagamento e atualiza status do pedido 

---

## 🚀 Tecnologias Utilizadas
- **Spring Boot 3.4.4**: Framework Java para desenvolvimento de aplicações
- **Spring Data JPA**: Para persistência de dados
- **PostgreSQL**: Banco de dados relacional
- **Lombok**: Redução de código boilerplate
- **Kafka + Zookeeper**: Para comunicação entre os serviços
- **Docker e Docker Compose**: Containerização da aplicação

## 🛠️ Requisitos
- Java 17
- Maven
- Docker e Docker Compose

## Passos para Execução

Na pasta raiz do projeto executar os seguintes passos

### 1. Executar com Docker Compose
```bash
docker-compose up --build
```
Este comando irá:
- Subir Kafka, Zookeeper e Postgres
- Construir e iniciar os microsserviços
- Expor os serviços nad portas 8080, 8081, 8082

### 2. Testar a aplicação

Para testar a aplicação envie um pedido usando o curl no Postman ou qualque cliente HTTP

```bash
curl -X POST http://localhost:8080/pedidos \
     -H "Content-Type: application/json" \
     -d '{"id":1,"produto":"Livro", "quantidade":1, "status":"CRIADO"  "valor":35.90}'
```
Com isso o sistema seguirar o seguinte fluxo:

- O pedido será publicado via pedido-service
- O processamento de pagamento será simulado no pagamento-service
- O status do pedido será atualizado no atualizar-service



