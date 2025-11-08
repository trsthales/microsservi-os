# E-commerce Microservices (Aprendizado guiado com TDD)

Objetivo: aprender microsserviços com exemplos bons, medianos e ruins, construindo um e-commerce didático (cadastro de usuário, listagem de produtos, carrinho e pagamento) usando:

- JDK 17
- Maven (multi-módulo)
- Spring Boot 3
- Arquitetura Limpa (domínio, casos de uso, adapters)
- TDD (sempre começar por testes que falham)

## Módulos

- `common`: contratos compartilhados de propósito geral (ex.: eventos de domínio). Não contém lógica de negócio.
- `user-service`: cadastro e consulta de usuários.
- `product-service`: catálogo/listagem de produtos.
- `cart-service`: carrinho de compras (em breve).
- `payment-service`: pagamento (em breve).

## Como rodar os testes (TDD)

No início alguns testes vão FALHAR de propósito (prática de TDD).

```bash
mvn -q test
```

Você verá testes vermelhos em `user-service` e `product-service`. A missão é fazê-los passar implementando a lógica mínima.

## Prioridade de negócio inicial

1) Cadastro de usuário (essencial para identificar o comprador)
2) Listagem de produtos (sem compra não há negócio)
3) Carrinho de compras
4) Pagamento

## Decisões iniciais

- Banco por serviço (cada serviço controla seu schema). Comunicação inicial síncrona reduzida (somente onde inevitável). 
- Compartilhamento mínimo entre serviços: apenas contratos genéricos em `common`. Sem compartilhar entidades/DTOs de negócio entre serviços.
- Testes primeiro: começamos com casos de uso e portas (interfaces) + testes, deixando implementações propositalmente pendentes.

Mais detalhes em `architecture-examples.md` e `exercises.md`.
