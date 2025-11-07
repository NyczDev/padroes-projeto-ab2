/**
 * - Define uma abstração (Princípio da Inversão de Dependência) que permite
 * ao 'Contexto' (RiskAnalysisContext) depender de uma interface, não de
 * uma implementação concreta.
 * - Permite que novos algoritmos sejam adicionados sem modificar o contexto
 * (Princípio Aberto/Fechado).
 */
public interface RiskAlgorithm {

    String calculate(FinancialData data);
}