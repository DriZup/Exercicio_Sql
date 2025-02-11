
### **1. Configuração do Projeto**
Crie um projeto Spring Boot com as seguintes dependências:
- **Spring Web**: Para criar endpoints REST.
- **Spring Data JPA**: Para interagir com o banco de dados usando JPA.
- **PostgreSQL Driver**: Para conectar ao banco de dados PostgreSQL.

Você pode usar o [Spring Initializr](https://start.spring.io/) para gerar o projeto. Configure o projeto com:
- Linguagem: **Java**
- Versão do Spring Boot: **3.1.0** (ou a mais recente)
- Dependências: **Spring Web**, **Spring Data JPA**, **PostgreSQL Driver**

Baixe o projeto gerado e importe-o para sua IDE (IntelliJ IDEA, Eclipse, etc.).

---

### **2. Configuração do Banco de Dados**
#### a) Criação do Banco de Dados e Tabela
No terminal do PostgreSQL ou em uma ferramenta como **pgAdmin**, execute os seguintes comandos para criar o banco de dados e a tabela:

```sql
-- Criar o banco de dados
CREATE DATABASE escola;

-- Conectar ao banco de dados
\c escola

-- Criar a tabela alunos
CREATE TABLE alunos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    idade INT NOT NULL
);
```

#### b) Inserir Dados na Tabela
Insira alguns dados na tabela `alunos` para teste:

```sql
INSERT INTO alunos (nome, idade) VALUES 
('João', 20), 
('Maria', 22), 
('Pedro', 19);
```

---

### **3. Configuração do `application.properties`**
No arquivo `src/main/resources/application.properties`, configure a conexão com o banco de dados PostgreSQL:

```properties
# Configuração do banco de dados
spring.datasource.url=jdbc:postgresql://localhost:5432/escola
spring.datasource.username=usuario_padrao
spring.datasource.password=senha_padrao

# Configuração do JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> **Nota:** Substitua `usuario_padrao` e `senha_padrao` pelas credenciais do seu banco de dados PostgreSQL.

---

### **4. Criação da Entidade `Aluno`**
Crie uma classe para representar a tabela `alunos` no banco de dados:

```java
package com.example.escola.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private int idade;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
```

---

### **5. Criação do Repositório**
Crie uma interface para acessar os dados no banco de dados:

```java
package com.example.escola.repository;

import com.example.escola.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
```

---

### **6. Criação do Controlador**
Crie um controlador para expor o endpoint GET que lista os alunos:

```java
package com.example.escola.controller;

import com.example.escola.model.Aluno;
import com.example.escola.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public List<Aluno> listarAlunos() {
        return alunoRepository.findAll();
    }
}
```

---

### **7. Testar a Aplicação**
#### a) Executar a Aplicação
1. No terminal, execute o seguinte comando para iniciar a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```
2. Certifique-se de que o banco de dados PostgreSQL está rodando.

#### b) Acessar o Endpoint
Abra o navegador ou uma ferramenta como **Postman** e acesse o endpoint:
```
http://localhost:8080/alunos
```

Você verá a lista de alunos cadastrados no banco de dados.

---

## Estrutura do Banco de Dados
- **Tabela `alunos`:**
  - `id` (chave primária, tipo SERIAL)
  - `nome` (tipo VARCHAR)
  - `idade` (tipo INT)

## Tecnologias Utilizadas
- Java
- Spring Boot
- PostgreSQL

