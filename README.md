# 🌌 VOID - Telemetria Espacial e Reabilitação Biométrica

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.6-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Oracle](https://img.shields.io/badge/Oracle_DB-F80000?style=for-the-badge&logo=oracle&logoColor=white)
![Azure](https://img.shields.io/badge/Microsoft_Azure-0089D6?style=for-the-badge&logo=microsoft-azure&logoColor=white)

O **VOID** é uma plataforma inovadora de telemetria IoT focada na saúde e bem-estar (**ODS 3**). Inspirado nos sistemas de monitoramento da Estação Espacial Internacional (ISS), o projeto visa revolucionar a reabilitação física.

Através da integração com sensores wearables (**ESP32**), o sistema coleta dados biométricos em tempo real durante as sessões de fisioterapia. O objetivo principal é calcular o desgaste acumulado e emitir alertas críticos de fadiga, prevenindo lesões musculares severas antes que elas ocorram, garantindo um ambiente de recuperação seguro e controlado.

---

## 👥 Equipe de Desenvolvimento (Turma 2TDSPO)

* **Pedro Henrique Luiz Alves Duarte - RM563405**
* **Guilherme Macedo Martins - RM562396**
* **Henrique Martins - RM563620**

---

## 🔗 Links Oficiais do Projeto

* **Deploy da API (Nuvem):** `https://java-advanced-5ce0.onrender.com/api/`
* **Documentação Viva (Swagger):** `https://java-advanced-5ce0.onrender.com/swagger-ui/index.html`
* **Apresentação Técnica:** https://youtu.be/ZbYV409GIwE?si=OZPmUUPoVhhv5WYY
* **Pitch Comercial:** https://youtube.com/shorts/QCIUYX5_Plg?si=Fmh7yEGMkcL-Z1oD

> **Nota:** Como utilizamos o plano gratuito do Render, a API "dorme" após períodos de inatividade. O primeiro acesso pode levar até 50 segundos para responder enquanto o serviço é iniciado.

---

## 📊 Diagramas do Sistema

Para visualização completa da modelagem e arquitetura adotada, consulte os arquivos e diagramas abaixo.

### 🏗️ Arquitetura Corporativa (TOGAF/ArchiMate)

#### Diagrama ArchiMate

<a href="https://ibb.co/pBmm50rY"><img src="https://i.ibb.co/ZpZZ7L1q/VOID.png" alt="Arquitetura VOID ArchiMate" border="0" width="100%"/></a>

O modelo ArchiMate do VOID está estruturado em quatro camadas:


### 🗄️ Modelo Entidade-Relacionamento (Banco de Dados)

#### Modelo Lógico

<a href="https://ibb.co/zh2X9yXs"><img src="https://i.ibb.co/hRdBQPBs/Logical-3.png" alt="Modelo Lógico VOID" border="0" width="100%"/></a>

#### Modelo Relacional

<a href="https://ibb.co/x8zSbCgY"><img src="https://i.ibb.co/HpGDyFPC/Relational-1-3.png" alt="Modelo Relacional VOID" border="0" width="100%"/></a>

---

## ⚙️ Principais Funcionalidades (Endpoints)

A API RESTful do VOID foi desenhada de forma direta e objetiva, cumprindo rigorosamente os requisitos de negócio e integração:

* **Autenticação (Auth):** Geração de Token JWT Stateless para controle rigoroso de acesso (Compliance/LGPD).
* **Gestão de Usuários (CRUD):** Cadastro e gerenciamento de `Pacientes` e `Fisioterapeutas`.
* **Sessões de Reabilitação:** Registro de treinos, vinculando o paciente, o fisioterapeuta responsável e o protocolo espacial aplicado.
* **Telemetria de Fadiga:** Ingestão de dados contínuos de sensores com armazenamento otimizado de payloads JSON para análise de desgaste físico.

---

## 🏗️ Arquitetura e Padrões de Engenharia

O projeto foi construído priorizando simplicidade, código limpo e as melhores práticas de engenharia de software.

### 🔹 Modelagem Relacional Avançada

Utilização da estratégia de herança (`InheritanceType.JOINED`) entre a classe base `Usuario` e suas derivadas (`Paciente` e `Fisioterapeuta`), além do uso de chaves compostas (`@EmbeddedId`) para garantir a integridade dos dados de sessão e telemetria no Oracle Database.

### 🔹 Segurança e Filtros

Proteção global das rotas da API através de um `SecurityFilter`.

Somente requisições contendo o cabeçalho:

```http
Authorization: Bearer <token>
```

podem acessar ou modificar dados sensíveis.

### 🔹 Tratamento Global de Exceções

Um `@RestControllerAdvice` centralizado captura:

* Erros de validação (`400 Bad Request`)
* Falhas internas (`500 Internal Server Error`)

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

A arquitetura de implantação foi otimizada para máxima eficiência de custos, utilizando recursos da Microsoft Azure para garantir estabilidade, disponibilidade e escalabilidade.

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
| Azure Cloud | Infraestrutura e Deploy |
| ESP32 | Coleta de dados biométricos |

---

## 🚀 Como Executar o Projeto Localmente

### 1️⃣ Clonar o Repositório

```bash
git clone https://github.com/GS-Void/java-advanced.git
```

### 2️⃣ Importar na IDE

* IntelliJ IDEA
* VS Code
* Eclipse

### 3️⃣ Atualizar Dependências

```bash
mvn clean install
```

### 4️⃣ Configurar o Banco Oracle

```plaintext
src/main/resources/application.properties
```

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

### 5️⃣ Configurar o DDL

```properties
spring.jpa.hibernate.ddl-auto=none
```

### 6️⃣ Executar a Aplicação

```java
VoidApplication.java
```

---

## 📖 Documentação da API

```plaintext
http://localhost:8080/swagger-ui.html
```

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

* Monitorar dados biométricos em tempo real.
* Identificar sinais precoces de fadiga muscular.
* Reduzir riscos de lesões durante a reabilitação.
* Auxiliar fisioterapeutas na tomada de decisão.
* Aplicar conceitos inspirados na tecnologia aeroespacial para a saúde humana.
* Contribuir para a **ODS 3 — Saúde e Bem-Estar**.

---

## 📄 Licença

Este projeto foi desenvolvido exclusivamente para fins acadêmicos como parte da **Global Solution FIAP 2026**.

---

# 🌌 VOID

### Space Technology Applied to Human Recovery

**Projeto desenvolvido para a Global Solution FIAP 2026 🚀**
