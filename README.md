# QuadraJá - Sistema de aluguel de quadras esportivas

Serviços: Autenticação, Reserva, Quadra, Pagamento, Service Discovery, API Gateway

Para iniciar no Codespace: docker compose up

Outros comandos:
- docker logs [pacote]

Para criar outro pacote:
1. Criar novo projeto spring (F1 -> java: new java project). Copiar as dependências do pacote auth-service ou quadra
1.5. Verificar se a formatação no pom.xml não está quebrada
2. Copiar o arquivo Dockerfile de outro pacote para a pasta raiz
3. Copiar 3 linhas do eureka no arquivo application.properties (do auth-service ou quadra) para o pacote criado
4. Atualizar o arquivo docker-compose.yml com informações do novo pacote (copiar/colar e mudar o nome, porta)
5. Rodar
6. Para abrir as rotas, acessar o link da porta na aba "Portas" do VSCode

Adicionar quadra:
```
curl -X POST http://localhost:8082/quadras \
-H "Content-Type: application/json" \
-d '{"name": "Quadra Central", "local": "Pátio 2", "price": 100.0, "category": "FUTEBOL"}'
```
no terminal do Codespace