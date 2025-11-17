- Método Reservar recebe email -> verifica se o usuário existe -> verifica se o pagamento na requisição está na lista de pagamentos do usuário -> adicionar a reserva na lista de reservas do usuário
- no usuário, criar um listener para, toda vez que for criado uma reserva com o email, ele adicionar a reserva na lista de reservas
- autenticação nas rotas
- listar as rotas no readme



serviço1 (solicitar) (out) -> precisa do exchange e routing key
serviço2 (receber) (in) -> precisa do queue, exchange, routing key e binding 