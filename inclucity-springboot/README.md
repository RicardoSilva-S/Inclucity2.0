# IncluCity — Spring Boot MVC 🌳🌐

Versão 2.0 do **IncluCity**, migrada de Java SE puro para **Spring Boot 3** com arquitetura MVC em camadas e templates **Thymeleaf**.

---

## 🏗️ Arquitetura — Camadas

```
src/main/java/com/inclucity/web/
├── IncluCityApplication.java       # Ponto de entrada Spring Boot
├── controller/
│   ├── IndexController.java        # GET /
│   ├── SolicitacaoController.java  # Fluxos do cidadão
│   └── PainelGestorController.java # Painel do gestor
├── service/
│   └── SolicitacaoService.java     # Regras de negócio
├── repository/
│   └── SolicitacaoRepository.java  # Persistência em memória
└── model/
    ├── Solicitacao.java            # Entidade principal
    ├── HistoricoStatus.java        # Registro de movimentações
    ├── StatusSolicitacao.java      # Enum de estados
    ├── SolicitacaoForm.java        # DTO do formulário (cidadão)
    └── AtualizacaoStatusForm.java  # DTO do formulário (gestor)
```

---

## 📋 Rotas disponíveis

| Método | Rota                          | Descrição                          |
|--------|-------------------------------|------------------------------------|
| GET    | `/`                           | Página inicial                     |
| GET    | `/solicitacoes/nova`          | Formulário de nova solicitação     |
| POST   | `/solicitacoes/nova`          | Processa e salva a solicitação     |
| GET    | `/solicitacoes/confirmacao`   | Exibe protocolo gerado             |
| GET    | `/solicitacoes/consultar`     | Formulário de busca por protocolo  |
| POST   | `/solicitacoes/consultar`     | Redireciona para detalhes          |
| GET    | `/solicitacoes/detalhes`      | Detalhes + histórico               |
| GET    | `/solicitacoes/painel`        | Painel do gestor com filtros       |
| POST   | `/solicitacoes/painel/atualizar` | Atualiza status de solicitação  |

---

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 17+
- Maven 3.8+

### Passos

```bash
# 1. Entrar na pasta do projeto
cd inclucity-springboot

# 2. Compilar e executar
mvn spring-boot:run

# 3. Acessar no navegador
http://localhost:8080
```

---

## 🔄 O que mudou da Versão Beta (Java Puro) para a Versão 2 (Spring Boot)

| Aspecto              | Versão Beta (Java SE)                      | Versão 2 (Spring Boot)                          |
|----------------------|--------------------------------------------|-------------------------------------------------|
| Servidor HTTP        | `com.sun.net.httpserver` manual            | Spring MVC embutido (Tomcat)                    |
| Templates HTML       | Substituição manual de `[[${var}]]`        | Thymeleaf com atributos `th:*`                  |
| Roteamento           | `servidor.createContext()` manual          | `@GetMapping`, `@PostMapping` declarativos      |
| Camadas              | Tudo em `Main.java`                        | Controller / Service / Repository separados     |
| Validação            | Manual (sem feedback)                      | `@Valid` + `BindingResult` + mensagens na tela  |
| Injeção de dependência | `new ServicoSolicitacoes()` manual       | `@Autowired` / Construtor gerenciado pelo Spring|
| DTOs de formulário   | `Map<String, String>` parseado manualmente | Classes `SolicitacaoForm`, `AtualizacaoStatusForm` |

---

## 🛠️ Tecnologias

- Java 17
- Spring Boot 3.2.5
- Spring MVC + Thymeleaf
- Spring Validation (Jakarta)
- Bootstrap 5.3.3
- Maven

---

*IncluCity — Tecnologia a serviço da cidadania e sustentabilidade. 🤝🌳*
