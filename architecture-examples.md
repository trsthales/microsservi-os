# Exemplos bons, ok e ruins em microsserviços

> Comentário geral: sempre busque baixo acoplamento, alta coesão, autonomia por serviço e observabilidade.

## Bom

- Banco por serviço (schema isolado). Motivo: autonomia para evoluir modelos sem coordenação global; reduz blast radius.
- Comunicação assíncrona para integração cross-serviço (eventos), síncrona apenas quando necessário. Motivo: reduz acoplamento temporal e melhora resiliência.
- Contratos explícitos (APIs ou eventos versionados). Motivo: facilita evolução sem quebrar consumidores.
- Backwards compatibility + feature flags. Motivo: implantações seguras e iterativas.
- Observabilidade (logs estruturados, métricas, tracing distribuído). Motivo: debugar em produção.
- DDD tático: entidades/agregados e invariantes no domínio. Motivo: regras concentradas e testáveis.

## Ok (aceitável com trade-offs)

- Shared library contendo utilitários genéricos (ex.: idempotência, eventos genéricos). Evite colocar modelos de negócio.
- Comunicação síncrona entre poucos serviços críticos, com timeouts/circuit breakers. Aceitável quando latência e consistência forte são requisitos.
- Um gateway central com autenticação/autorização. Atenção para não virar monolito acoplado.

## Ruim (evitar)

- Banco compartilhado entre serviços diferentes. Problema: acoplamento físico/organizacional, migrações coordenadas, lock-in de schema.
- Reutilizar entidades de negócio via jar compartilhado. Problema: evolução acopla serviços; mudanças quebram outros times.
- Orquestração síncrona profunda (cadeia longa de chamadas). Problema: fragilidade, efeito dominó, tempos de resposta altos.
- Transações 2PC entre serviços. Problema: complexidade e disponibilidade.
- Repositórios múltiplos no mesmo serviço (copia/cola de código). Problema: inconsistência e dívida técnica.

## Exemplos de código (sintéticos)

Ruim (entidade compartilhada):

```java
// shared-domain.jar
public class Product { Long id; String name; BigDecimal price; }

// cart-service depende de shared-domain.jar e product-service também
```

Problema: se `price` muda para `Money`, ambos serviços precisam alterar e publicar juntos.

Bom (contrato separado e mapeamento local):

```java
// product-service publica API
record ProductView(UUID id, String name, BigDecimal price) {}

// cart-service consome e mapeia para seu próprio modelo interno
record ItemPrice(UUID productId, BigDecimal price) {}
```

Motivo: cada serviço controla seu modelo interno; contratos evoluem com versionamento.
