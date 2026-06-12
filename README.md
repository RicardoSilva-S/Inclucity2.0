# IncluCity - Spring Boot MVC 🌳🌐

Este projeto é a versão 2.0 do IncluCity, evoluída de Java SE Puro para Spring Boot 3, utilizando arquitetura MVC em camadas com templates Thymeleaf. Ele oferece uma experiência Web completa (HTML/CSS/JS) com organização profissional de código, validação de formulários e separação clara de responsabilidades.


📋 Funcionalidades e Diferenciais


Interface Web Completa: Páginas HTML estilizadas com a paleta verde (sustentabilidade/natureza), agora renderizadas via Thymeleaf com dados dinâmicos injetados pelo servidor.
Spring Boot MVC: Servidor Tomcat embutido com roteamento declarativo (@GetMapping, @PostMapping) e injeção de dependência automática.
POO Avançada em Camadas: Separação explícita entre Controller, Service e Repository — nenhuma regra de negócio no controller.
Validação de Formulários: Campos obrigatórios validados com @Valid + BindingResult, com mensagens de erro exibidas inline na tela.
Acessibilidade (ODS 11): Foco em usabilidade para cidadãos e ferramentas de gestão eficientes, com campos grandes, labels explícitos e feedback visual claro.
Persistência em Memória: ConcurrentHashMap via SolicitacaoRepository, seguro para ambiente multithread.



📁 Estrutura do Projeto

inclucity-springboot/
├── src/
│   └── main/
│       ├── java/com/inclucity/web/
│       │   ├── controller/
│       │   │   ├── IndexController.java         # GET /
│       │   │   ├── SolicitacaoController.java   # Fluxos do cidadão
│       │   │   └── PainelGestorController.java  # Painel do gestor
│       │   ├── service/
│       │   │   └── SolicitacaoService.java      # Regras de negócio
│       │   ├── repository/
│       │   │   └── SolicitacaoRepository.java   # Persistência em memória
│       │   ├── model/
│       │   │   ├── Solicitacao.java             # Entidade principal
│       │   │   ├── HistoricoStatus.java         # Registro de movimentações
│       │   │   ├── StatusSolicitacao.java       # Enum de estados
│       │   │   ├── SolicitacaoForm.java         # DTO do formulário cidadão
│       │   │   └── AtualizacaoStatusForm.java   # DTO do formulário gestor
│       │   └── IncluCityApplication.java        # Ponto de entrada Spring Boot
│       └── resources/
│           ├── templates/                       # Páginas HTML (Thymeleaf)
│           │   ├── index.html
│           │   ├── nova-solicitacao.html
│           │   ├── confirmacao-solicitacao.html
│           │   ├── consultar-solicitacao.html
│           │   ├── detalhes-solicitacao.html
│           │   └── painel-gestor.html
│           ├── static/
│           │   └── css/style.css               # Estilos customizados
│           └── application.properties          # Configurações da aplicação
└── pom.xml


🔀 Rotas Disponíveis

MétodoRotaDescriçãoGET/Página inicialGET/solicitacoes/novaFormulário de nova solicitaçãoPOST/solicitacoes/novaProcessa e salva a solicitaçãoGET/solicitacoes/confirmacaoExibe o protocolo geradoGET/solicitacoes/consultarFormulário de busca por protocoloPOST/solicitacoes/consultarRedireciona para detalhesGET/solicitacoes/detalhesDetalhes + histórico da solicitaçãoGET/solicitacoes/painelPainel do gestor com filtrosPOST/solicitacoes/painel/atualizarAtualiza status de uma solicitação


🚀 Como Executar

Pré-requisitos


Java JDK 17 ou superior instalado.
Maven 3.8 ou superior instalado.


Passos


Abra o terminal na pasta raiz do projeto (inclucity-springboot).
Compile e execute o projeto:


bashmvn spring-boot:run


Acesse no seu navegador:

http://localhost:8080





Compilar e gerar o JAR executável

bashmvn clean package
java -jar target/inclucity-web-2.0.0.jar


🔄 O que mudou da Versão 1.0 (Java Puro) para a Versão 2.0 (Spring Boot)

AspectoVersão 1.0 (Java SE Puro)Versão 2.0 (Spring Boot)Servidor HTTPcom.sun.net.httpserver manualSpring MVC + Tomcat embutidoTemplates HTMLSubstituição manual de [[${var}]]Thymeleaf com atributos th:*Roteamentoservidor.createContext() manual@GetMapping, @PostMapping declarativosCamadasTudo concentrado em Main.javaController / Service / Repository separadosValidaçãoManual, sem feedback na tela@Valid + BindingResult + mensagens inlineInjeção de dependêncianew ServicoSolicitacoes() manualGerenciada pelo Spring (@Service, @Repository)DTOs de formulárioMap<String, String> parseado manualmenteSolicitacaoForm, AtualizacaoStatusFormDependênciasZero (Java puro)Maven + Spring Boot 3 + Thymeleaf


🛠️ Notas Técnicas


Os templates HTML utilizam o dialeto Thymeleaf (th:field, th:each, th:if, th:text) para renderização server-side, substituindo a abordagem de substituição manual de placeholders da versão anterior.
O sistema mantém persistência em memória via ConcurrentHashMap no SolicitacaoRepository, garantindo segurança em ambiente multithread e sendo ideal para demonstrações e MVPs.
Toda regra de negócio está encapsulada no SolicitacaoService — os controllers apenas recebem a requisição, delegam ao service e retornam a view.
As mensagens de sucesso e erro do painel gestor são transmitidas via RedirectAttributes (Flash Attributes), evitando reenvio de formulário no F5.



IncluCity - Tecnologia a serviço da cidadania e sustentabilidade. 🤝🌳
