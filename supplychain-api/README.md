# API de Transparência da Cadeia de Suprimentos (SupplyChain)
  
## Visão Geral

Esta API foi desenvolvida para gerenciar a transparência da cadeia de suprimentos com foco em práticas sustentáveis, rastreamento de pegada de carbono e relatórios de conformidade. O projeto é construído com Spring Boot e implementa padrões RESTful para facilitar a integração com outros sistemas.

## Requisitos Técnicos

- Java 17
- Docker e Docker Compose
- Maven (opcional, para execução local)
- Oracle Database (configurado automaticamente via Docker)

## Configuração e Execução com Docker

### Passo 1: Executar com Docker Compose

O projeto inclui um arquivo `compose.yml` que configura todos os serviços necessários, incluindo o banco de dados Oracle e a aplicação Spring Boot.


A API estará disponível em: http://localhost:8080

A documentação Swagger estará disponível em: http://localhost:8080/swagger-ui.html

## Endpoints da API

A API é dividida em três principais recursos:

### 1. Fornecedores (Suppliers)

Gerencia informações sobre fornecedores e suas práticas sustentáveis.

#### Listar todos os fornecedores
```
GET /api/suppliers
```

#### Obter fornecedor por ID
```
GET /api/suppliers/{id}
```

#### Criar novo fornecedor
```
POST /api/suppliers
```
Exemplo de corpo da requisição:
```json
{
  "name": "Fornecedor Sustentável LTDA",
  "email": "contato@fornecedor.com.br",
  "sustainablePracticesScore": 85,
  "fairLaborScore": 90,
  "certificationStatus": "CERTIFICADO",
  "lastAuditDate": "2025-05-10T14:30:00"
}
```

#### Atualizar fornecedor
```
PUT /api/suppliers/{id}
```

#### Excluir fornecedor
```
DELETE /api/suppliers/{id}
```

#### Filtrar fornecedores por pontuação de sustentabilidade
```
GET /api/suppliers/sustainable?minScore=80
```

### 2. Pegadas de Carbono (Carbon Footprints)

Gerencia o rastreamento das emissões de carbono dos fornecedores.

#### Listar pegadas de carbono por fornecedor
```
GET /api/carbon-footprints/supplier/{supplierId}
```

#### Registrar nova pegada de carbono
```
POST /api/carbon-footprints
```
Exemplo de corpo da requisição:
```json
{
  "supplier": {
    "id": 1
  },
  "emissionDate": "2025-04-15T10:00:00",
  "productionEmissions": 125.5,
  "transportationEmissions": 75.3,
  "emissionSource": "Produção de componentes",
  "mitigationMeasures": "Uso de energia renovável e otimização logística"
}
```

#### Calcular emissões totais por fornecedor
```
GET /api/carbon-footprints/total-emissions/supplier/{supplierId}
```

### 3. Relatórios de Conformidade (Compliance Reports)

Gerencia os relatórios de conformidade dos fornecedores.

#### Listar relatórios por fornecedor
```
GET /api/compliance-reports/supplier/{supplierId}
```

#### Criar novo relatório de conformidade
```
POST /api/compliance-reports
```
Exemplo de corpo da requisição:
```json
{
  "supplier": {
    "id": 1
  },
  "reportDate": "2025-05-01T09:30:00",
  "complianceStatus": "COMPLIANT",
  "regulatoryRequirements": "Atende todas as regulamentações ambientais necessárias",
  "auditFindings": "Melhorias na rastreabilidade de materiais recomendadas",
  "correctiveActions": "Implementação de sistema de rastreabilidade até agosto/2025"
}
```

## Modelagem de Dados

### Supplier (Fornecedor)
- **id**: Identificador único
- **name**: Nome do fornecedor
- **email**: Email de contato
- **sustainablePracticesScore**: Pontuação de práticas sustentáveis (0-100)
- **fairLaborScore**: Pontuação de práticas trabalhistas justas (0-100)
- **certificationStatus**: Status de certificação
- **lastAuditDate**: Data da última auditoria

### CarbonFootprint (Pegada de Carbono)
- **id**: Identificador único
- **supplier**: Referência ao fornecedor
- **emissionDate**: Data das emissões
- **productionEmissions**: Emissões da produção
- **transportationEmissions**: Emissões do transporte
- **emissionSource**: Fonte das emissões
- **mitigationMeasures**: Medidas de mitigação

### ComplianceReport (Relatório de Conformidade)
- **id**: Identificador único
- **supplier**: Referência ao fornecedor
- **reportDate**: Data do relatório
- **complianceStatus**: Status de conformidade
- **regulatoryRequirements**: Requisitos regulatórios
- **auditFindings**: Descobertas da auditoria
- **correctiveActions**: Ações corretivas

# Exemplos de Requisições com cURL

Este arquivo contém exemplos de comandos cURL para testar a API de Transparência da Cadeia de Suprimentos diretamente do terminal.

## Fornecedores (Suppliers)

### Criar um novo fornecedor

```bash
curl -X POST "http://localhost:8080/api/suppliers" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Fornecedor Sustentável LTDA",
    "email": "contato@fornecedor.com.br",
    "sustainablePracticesScore": 85,
    "fairLaborScore": 90,
    "certificationStatus": "CERTIFICADO",
    "lastAuditDate": "2025-05-10T14:30:00"
  }'
```

### Listar todos os fornecedores

```bash
curl -X GET "http://localhost:8080/api/suppliers"
```

### Obter um fornecedor específico por ID

```bash
curl -X GET "http://localhost:8080/api/suppliers/1"
```

### Atualizar um fornecedor existente

```bash
curl -X PUT "http://localhost:8080/api/suppliers/1" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Fornecedor Sustentável LTDA - Atualizado",
    "email": "novo-contato@fornecedor.com.br",
    "sustainablePracticesScore": 95,
    "fairLaborScore": 92,
    "certificationStatus": "CERTIFICADO_PREMIUM",
    "lastAuditDate": "2025-05-15T10:00:00"
  }'
```

### Excluir um fornecedor

```bash
curl -X DELETE "http://localhost:8080/api/suppliers/1"
```

### Filtrar fornecedores por pontuação de sustentabilidade

```bash
curl -X GET "http://localhost:8080/api/suppliers/sustainable?minScore=80"
```

## Pegadas de Carbono (Carbon Footprints)

### Registrar uma nova pegada de carbono

```bash
curl -X POST "http://localhost:8080/api/carbon-footprints" \
  -H "Content-Type: application/json" \
  -d '{
    "supplier": {
      "id": 1
    },
    "emissionDate": "2025-04-15T10:00:00",
    "productionEmissions": 125.5,
    "transportationEmissions": 75.3,
    "emissionSource": "Produção de componentes",
    "mitigationMeasures": "Uso de energia renovável e otimização logística"
  }'
```

### Listar pegadas de carbono por fornecedor

```bash
curl -X GET "http://localhost:8080/api/carbon-footprints/supplier/1"
```

### Calcular emissões totais por fornecedor

```bash
curl -X GET "http://localhost:8080/api/carbon-footprints/total-emissions/supplier/1"
```

## Relatórios de Conformidade (Compliance Reports)

### Criar um novo relatório de conformidade

```bash
curl -X POST "http://localhost:8080/api/compliance-reports" \
  -H "Content-Type: application/json" \
  -d '{
    "supplier": {
      "id": 1
    },
    "reportDate": "2025-05-01T09:30:00",
    "complianceStatus": "COMPLIANT",
    "regulatoryRequirements": "Atende todas as regulamentações ambientais necessárias",
    "auditFindings": "Melhorias na rastreabilidade de materiais recomendadas",
    "correctiveActions": "Implementação de sistema de rastreabilidade até agosto/2025"
  }'
```

### Listar relatórios de conformidade por fornecedor

```bash
curl -X GET "http://localhost:8080/api/compliance-reports/supplier/1"
```

### Obter um relatório de conformidade específico

```bash
curl -X GET "http://localhost:8080/api/compliance-reports/1"
```

## Dicas

1. Substitua os IDs nos exemplos (como `/1`) pelos IDs reais gerados após a criação dos registros
2. Para formatar a saída JSON e facilitar a leitura, adicione `| python -m json.tool` ao final dos comandos GET (se tiver Python instalado)
3. Em sistemas Windows, pode ser necessário escapar as aspas duplas de forma diferente
4. Todos os exemplos assumem que a API está rodando em localhost na porta 8080


## Solução de Problemas

### Problemas de Conexão com o Banco de Dados

Se você encontrar problemas de conexão com o banco de dados, verifique:

1. Se o container Oracle está rodando: `docker ps | grep oracle`
2. Os logs do container: `docker logs <container_id_oracle>`
3. As configurações de conexão no arquivo `application.yml`

### A API não Inicia

1. Verifique os logs da aplicação: `docker logs <container_id_app>`
2. Certifique-se de que o banco de dados está acessível e inicializado

## Notas Adicionais

- A API utiliza autenticação JWT para endpoints seguros, mas para facilitar os testes, todos os endpoints estão temporariamente liberados
- Os dados são persistidos no volume Docker, portanto serão mantidos entre reinicializações
- Para desenvolvimento local sem Docker, configure um banco de dados Oracle e atualize as configurações em `application.yml`

---

Este projeto foi desenvolvido como trabalho acadêmico para a FIAP. 