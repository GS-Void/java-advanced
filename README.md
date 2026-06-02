# 🌌 VOID - Telemetria Espacial e Reabilitação Biométrica

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.6-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle_DB-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![Azure](https://img.shields.io/badge/Microsoft_Azure-0089D6?style=for-the-badge&logo=microsoft-azure&logoColor=white)

O **VOID** é uma plataforma inovadora de telemetria IoT focada na saúde e bem-estar (ODS 3). Inspirado nos sistemas de monitoramento da Estação Espacial Internacional (ISS), o projeto visa revolucionar a reabilitação física.

Através da integração com sensores wearables (ESP32), o sistema coleta dados biométricos em tempo real durante as sessões de fisioterapia. O objetivo principal é calcular o desgaste acumulado e emitir alertas críticos de fadiga, prevenindo lesões musculares severas antes que elas ocorram, garantindo um ambiente de recuperação seguro e controlado.

---

## 👥 Equipe de Desenvolvimento (Turma 2TDSPO)

- **Pedro Henrique Luiz Alves Duarte**
- **Guilherme Macedo Martins**
- **Henrique Martins**

---

## 🔗 Links Oficiais do Projeto

- **Deploy da API (Nuvem):** `Insira o link base da Azure aqui`
- **Documentação Viva (Swagger):** `Insira o link do Swagger em produção aqui`
- **Apresentação Técnica (Arquitetura e Demo):** `Insira o link do vídeo de 10 min aqui`
- **Pitch Comercial:** `Insira o link do vídeo de 3 min aqui`

---

## ⚙️ Principais Funcionalidades (Endpoints)

A API RESTful do VOID foi desenhada de forma direta e objetiva, cumprindo rigorosamente os requisitos de negócio e integração:

- **Autenticação (Auth):** Geração de Token JWT Stateless para controle rigoroso de acesso (Compliance/LGPD).
- **Gestão de Usuários (CRUD):** Cadastro e gerenciamento de `Pacientes` e `Fisioterapeutas`.
- **Sessões de Reabilitação:** Registro de treinos, vinculando o paciente, o fisioterapeuta responsável e o protocolo espacial aplicado.
- **Telemetria de Fadiga:** Ingestão de dados contínuos de sensores com armazenamento otimizado de payloads JSON para análise de desgaste físico.

---

## 🏗️ Arquitetura e Padrões de Engenharia

O projeto foi construído priorizando a simplicidade, código limpo e o cumprimento estrito das melhores práticas de engenharia de software:

### 🔹 Modelagem Relacional Avançada

Utilização da estratégia de herança (`InheritanceType.JOINED`) entre a classe base `Usuario` e suas derivadas (`Paciente` e `Fisioterapeuta`), além do uso de chaves compostas (`@EmbeddedId`) para garantir a integridade dos dados de sessão e telemetria no Oracle Database.

### 🔹 Segurança e Filtros

Proteção global das rotas da API através de um `SecurityFilter`. Somente requisições contendo o cabeçalho:

```http
Authorization: Bearer <token>
```

podem acessar ou modificar dados sensíveis.

### 🔹 Tratamento Global de Exceções

Um `@RestControllerAdvice` centralizado captura:

- Erros de validação (`400 Bad Request`)
- Falhas internas (`500 Internal Server Error`)

Retornando respostas JSON padronizadas e impedindo o vazamento de informações sensíveis para o cliente.

### 🔹 Validação de Entrada

Utilização do Jakarta Bean Validation diretamente nos DTOs:

```java
@NotBlank
@Positive
@Email
```

Garantindo proteção contra payloads inválidos ou maliciosos.

### 🔹 Infraestrutura em Nuvem

A arquitetura de implantação foi otimizada para máxima eficiência de custos, utilizando recursos da Microsoft Azure para garantir estabilidade, disponibilidade e escalabilidade sem desperdício de recursos.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Finalidade |
|------------|------------|
| Java 17+ | Linguagem principal |
| Spring Boot 4.0.6 | Framework Backend |
| Spring Security | Autenticação e autorização |
| JWT | Controle de acesso |
| Oracle Database | Persistência de dados |
| JPA / Hibernate | ORM |
| Maven | Gerenciamento de dependências |
| Swagger / OpenAPI | Documentação da API |
| Azure Cloud | Infraestrutura e deploy |
| ESP32 | Coleta de dados biométricos |

---

## 🚀 Como Executar o Projeto Localmente

### 1️⃣ Clonar o repositório

```bash
git clone <url-do-seu-repositorio>
```

### 2️⃣ Importar na IDE

Importe o projeto em uma das IDEs abaixo:

- IntelliJ IDEA
- VS Code
- Eclipse

### 3️⃣ Atualizar dependências

```bash
mvn clean install
```

### 4️⃣ Configurar o banco Oracle

Edite o arquivo:

```text
src/main/resources/application.properties
```

Configure:

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

### 5️⃣ Configurar o DDL

> **Importante:** O banco de dados é gerenciado através de scripts SQL próprios (DDL), garantindo maior controle sobre tabelas, sequences e constraints.

```properties
spring.jpa.hibernate.ddl-auto=none
```

### 6️⃣ Executar a aplicação

Execute a classe principal:

```java
VoidApplication.java
```

---

## 📖 Documentação da API

Após iniciar a aplicação, acesse:

```text
http://localhost:8080/swagger-ui.html
```

A interface Swagger permitirá testar todos os endpoints diretamente pelo navegador.

---

## 📡 Fluxo de Funcionamento

```text
ESP32 Wearable
        ↓
Coleta de Dados Biométricos
        ↓
API Spring Boot
        ↓
Validação e Processamento
        ↓
Oracle Database
        ↓
Análise de Fadiga
        ↓
Alertas de Segurança
        ↓
Fisioterapeuta / Paciente
```

---

## 🎯 Objetivos do Projeto

- Monitorar dados biométricos em tempo real.
- Identificar sinais precoces de fadiga muscular.
- Reduzir riscos de lesões durante a reabilitação.
- Auxiliar fisioterapeutas na tomada de decisão.
- Aplicar conceitos inspirados na tecnologia aeroespacial para a saúde humana.
- Contribuir para a **ODS 3 — Saúde e Bem-Estar**.

---

## 📄 Licença

Este projeto foi desenvolvido exclusivamente para fins acadêmicos como parte da **Global Solution FIAP 2026**.

---

<div align="center">

### 🌌 VOID

**Space Technology Applied to Human Recovery**

Projeto desenvolvido para a **Global Solution FIAP 2026** 🚀

</div>
