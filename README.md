# IncluCity - Spring Boot MVC 🌳🌐

Este projeto é a versão 2.0 do IncluCity, evoluída de Java SE Puro para Spring Boot 3, utilizando arquitetura MVC em camadas com templates Thymeleaf. Ele oferece uma experiência Web completa com organização profissional de código, validação de formulários e separação clara de responsabilidades.

🔄**Como rodar**
* **Baixar o zip do repositório**
* **Extraia o ZIP: Descompacte o arquivo.**
* **Abra na IDE:**
    * 1 No IntelliJ, vá em File > Open e selecione a pasta inclucity-springboot.
    * 2 No VS Code, abra a pasta e aceite a importação do projeto Maven quando solicitado.
* **Execute a aplicação:**
    * 1 Localize o arquivo principal em: src/main/java/com/inclucity/web/IncluCityApplication.java.
    * 2 Clique com o botão direito nele e selecione "Run 'IncluCityApplication'".
**Acesse o site:**
Após o terminal indicar que o Spring iniciou com sucesso, abra o seu navegador e acesse: **http://localhost:8080**

## 📋 Funcionalidades e Diferenciais

* **Interface Web Completa:** Páginas HTML estilizadas com a paleta verde (sustentabilidade/natureza), renderizadas via Thymeleaf com dados dinâmicos injetados pelo servidor.
* **Spring Boot MVC:** Servidor Tomcat embutido com roteamento declarativo (`@GetMapping`, `@PostMapping`) e injeção de dependência automática.
* **POO Avançada em Camadas:** Separação explícita entre Controller, Service e Repository — nenhuma regra de negócio no controller.
* **Validação de Formulários:** Campos obrigatórios validados com `@Valid` + `BindingResult`, com mensagens de erro exibidas inline na tela.
* **Acessibilidade (ODS 11):** Foco em usabilidade para cidadãos e ferramentas de gestão eficientes, com campos grandes, labels explícitos e feedback visual claro.
* **Persistência em Memória:** `ConcurrentHashMap` via `SolicitacaoRepository`, seguro para ambiente multithread, ideal para demonstrações e MVPs.

## 📁 Estrutura do Projeto

```text
inclucity-springboot/
├── pom.xml
└── src/
    └── main/
        ├── java/com/inclucity/web/
        │   ├── IncluCityApplication.java
        │   ├── controller/
        │   │   ├── IndexController.java
        │   │   ├── SolicitacaoController.java
        │   │   └── PainelGestorController.java
        │   ├── service/
        │   │   └── SolicitacaoService.java
        │   ├── repository/
        │   │   └── SolicitacaoRepository.java
        │   └── model/
        │       ├── Solicitacao.java
        │       ├── HistoricoStatus.java
        │       ├── StatusSolicitacao.java
        │       ├── SolicitacaoForm.java
        │       └── AtualizacaoStatusForm.java
        └── resources/
            ├── application.properties
            ├── templates/
            │   ├── index.html
            │   ├── nova-solicitacao.html
            │   ├── confirmacao-solicitacao.html
            │   ├── consultar-solicitacao.html
            │   ├── detalhes-solicitacao.html
            │   └── painel-gestor.html
            └── static/
                └── css/
                    └── style.css
```

## 🔀 Rotas Disponíveis

| Método | Rota                           | Descrição                           |
| ------ | ------------------------------ | ----------------------------------- |
| GET    | /                              | Página inicial                      |
| GET    | /solicitacoes/nova             | Formulário de nova solicitação      |
| POST   | /solicitacoes/nova             | Processa e salva a solicitação      |
| GET    | /solicitacoes/confirmacao      | Exibe o protocolo gerado            |
| GET    | /solicitacoes/consultar        | Formulário de busca por protocolo   |
| POST   | /solicitacoes/consultar        | Redireciona para detalhes           |
| GET    | /solicitacoes/detalhes         | Detalhes + histórico da solicitação |
| GET    | /solicitacoes/painel           | Painel do gestor com filtros        |
| POST   | /solicitacoes/painel/atualizar | Atualiza status de uma solicitação  |

## 🚀 Como Executar

### Pré-requisitos

* Java JDK 17 ou superior instalado
* Maven 3.8 ou superior instalado

### Passos

Abra o terminal na pasta raiz do projeto:

```bash
cd inclucity-springboot
```

Compile e execute o projeto:

```bash
mvn spring-boot:run
```

Acesse no navegador:

```text
http://localhost:8080
```

### Gerar o JAR executável

```bash
mvn clean package
java -jar target/inclucity-web-2.0.0.jar
```

## 🔄 O que mudou da Versão 1.0 para a Versão 2.0

| Aspecto                | Versão 1.0 (Java SE Puro)           | Versão 2.0 (Spring Boot)                    |
| ---------------------- | ----------------------------------- | ------------------------------------------- |
| Servidor HTTP          | com.sun.net.httpserver manual       | Spring MVC + Tomcat embutido                |
| Templates HTML         | Substituição manual de `[[${var}]]` | Thymeleaf com atributos `th:*`              |
| Roteamento             | `server.createContext()` manual     | `@GetMapping` e `@PostMapping`              |
| Camadas                | Tudo concentrado em Main.java       | Controller / Service / Repository           |
| Validação              | Manual, sem feedback visual         | `@Valid` + `BindingResult`                  |
| Injeção de dependência | Instanciação manual com `new`       | Gerenciada pelo Spring                      |
| DTOs de formulário     | `Map<String,String>`                | `SolicitacaoForm` e `AtualizacaoStatusForm` |
| Dependências           | Java puro                           | Maven + Spring Boot + Thymeleaf             |

## 🛠️ Notas Técnicas

* Os templates HTML utilizam o dialeto Thymeleaf (`th:field`, `th:each`, `th:if`, `th:text`) para renderização server-side.
* O sistema mantém persistência em memória via `ConcurrentHashMap` no `SolicitacaoRepository`, garantindo segurança em ambiente multithread.
* Toda regra de negócio está encapsulada no `SolicitacaoService`; os controllers apenas recebem a requisição, delegam ao service e retornam a view.
* As mensagens de sucesso e erro do painel gestor são transmitidas via `RedirectAttributes` (Flash Attributes), evitando reenvio de formulário ao atualizar a página.

---

**IncluCity - Tecnologia a serviço da cidadania e sustentabilidade. 🤝🌳**
