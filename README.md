# QuadraJá - Sistema de aluguel de quadras esportivas

Serviços: Autenticação, Reserva, Quadra, Pagamento, Service Discovery, API Gateway

Para iniciar no Codespace: docker compose up

Outros comandos:
- docker logs [pacote]
- docker restart [pacote]

Para criar outro pacote:
1. Criar novo projeto spring (F1 -> java: new java project). Copiar as dependências do pacote auth-service ou quadra
1.5. Verificar se a formatação no pom.xml não está quebrada
2. Copiar o arquivo Dockerfile de outro pacote para a pasta raiz
3. Copiar 3 linhas do eureka no arquivo application.properties (do auth-service ou quadra) para o pacote criado
4. Atualizar o arquivo docker-compose.yml com informações do novo pacote (copiar/colar e mudar o nome, porta)
5. Rodar
6. Para abrir as rotas, acessar o link da porta na aba "Portas" do VSCode


no terminal do Codespace:
Adicionar quadra:
```
curl -X POST http://localhost:8082/quadras \
-H "Content-Type: application/json" \
-d '{"name": "Quadra Central", "local": "Pátio 2", "price": 100.0, "category": "FUTEBOL"}'
```
Adicionar pagamento:
```
curl -X POST http://localhost:8086/pagamentos \
-H "Content-Type: application/json" \
-d '{"nome": "CrediTop", "tipo": "Crédito"}'
```
Adicionar usuário:
```
curl -X POST http://localhost:8084/users/register \
     -H "Content-Type: application/json" \
     -d '{
           "name": "Murilo Teste",
           "email": "murilo.teste@example.com",
           "password": "12345678"
         }'
```
Adicionar pagamento ao usuário:
```
curl -X POST http://localhost:8084/users/pagamento \
     -H "Content-Type: application/json" \
     -d '{
           "pagamentoNome": "CrediTop",
           "usuarioEmail": "murilo.teste@example.com"
         }'
```
Adicionar reserva:
```
curl -X POST http://localhost:8085/reservas \
    -H "Content-Type: application/json" \
    -d '{
        "quadraName": "Quadra Central",
        "usuarioEmail": "murilo.teste@example.com",
        "periodo": "2025-11-20T15:30:00",
        "pagamento": "CrediTop"
    }'
```
Alugar quadra:
```
curl -X POST http://localhost:8082/quadras/alugar \
-H "Content-Type: application/json" \
-d '{"name": "Quadra Central", "alugar": "true"}'
```