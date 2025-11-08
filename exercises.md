# Exercícios (faça com TDD)

Progrida em ordem. Sempre escreva o teste primeiro, rode e veja falhar, implemente o mínimo, rode novamente.

## Nível 1 – Usuário

1. Cadastro: rejeitar e-mails inválidos e senhas curtas (< 8). Teste: input inválido deve lançar exceção de validação.
2. Cadastro: impedir duplicidade de e-mail. Teste: se `existsByEmail` retorna true, deve falhar.
3. Cadastro: ao criar, retornar `userId`. Teste: verifique que o id não é nulo e que `save` foi chamado.

## Nível 2 – Produto

4. Listagem: retornar produtos ordenados por nome. Teste com 3 produtos embaralhados.
5. Paginação simples: `page,size`. Teste bordas: página vazia, última página.
6. Filtro por categoria (campo opcional). Teste: sem categoria retorna todos; com categoria filtra.

## Nível 3 – Carrinho

7. Adicionar item: não permitir quantidade 0 ou negativa.
8. Atualizar item: somar quantidades para o mesmo produto.
9. Remover item: carrinho fica vazio após remover todos.
10. Cálculo de total: considerar preço atual do produto via consulta ao `product-service` (mock).

## Nível 4 – Pagamento

11. Criar tentativa de pagamento: status inicial PENDING.
12. Integrar com processador (mock): sucesso muda para PAID, falha para DECLINED.
13. Idempotência: mesma `paymentKey` não cria nova tentativa.

## Bônus

14. Publicar evento `UserRegistered` e consumir em `cart-service` para criar carrinho inicial.
15. Implementar correlação (traceId) no log.
16. Circuit breaker numa chamada síncrona.

Dicas: use Mockito para mocks; mantenha o domínio limpo, sem anotações de framework nas entidades/objetos de valor.
