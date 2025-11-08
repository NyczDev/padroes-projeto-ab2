# Avaliação Prática - 2º Bimestre

## Como Executar
```bat
javac -d out questao_1\*.java
java -cp out questao_1.MainRisco
```

## Explicação dos Padrões de Projeto

### Questão 1: Sistema de Análise de Risco Financeiro
Implementei o padrão Strategy para criar um sistema que muda os cálculos de risco dinamicamente. O sistema precisava isolar diferentes tipos de cálculo (VaR, ES, Stress) em classes separadas e permitir a troca entre eles em tempo real. A interface `RiscoStrategy` define o contrato comum, enquanto a classe `RiscoContext` guarda os dados compartilhados. O `CalculadorRisco` gerencia a troca entre as três implementações diferentes. Este padrão permite a fácil expansão do sistema com novos algoritmos no futuro.

### Questão 2: Integração com Sistema Bancário Legado
O padrão Adapter foi usado para conectar um sistema moderno com um legado de interface incompatível. O `ModernToLegacyAdapter` converte as chamadas entre os dois sistemas, realizando o mapeamento de moedas (USD=1, EUR=2, BRL=3) e preenchendo campos obrigatórios do sistema legado. A conversão bidirecional é feita via `LegacyToModernConverter`. Esta solução permitiu a integração sem modificar o código de nenhum dos sistemas.

### Questão 3: Controle de Usina Nuclear
O padrão State foi implementado para controlar transições complexas entre estados da usina nuclear. Cada estado possui comportamento e regras próprias, com validações de temperatura e tempo. O sistema garante que transições só ocorram por caminhos permitidos e inclui um modo de manutenção especial que preserva o contexto. O resultado é um código mais seguro e previsível para o controle da usina.

### Questão 4: Validação de Documentos Fiscais
Utilizei o padrão Chain of Responsibility para criar um sistema de validação em etapas. O sistema possui cinco validadores encadeados com timeouts individuais e um circuit breaker que atua após três falhas. Algumas validações são condicionais e dependem do sucesso das anteriores. Em caso de falha, existe um sistema de rollback automático. Esta implementação criou um processo de validação robusto e sequencial.