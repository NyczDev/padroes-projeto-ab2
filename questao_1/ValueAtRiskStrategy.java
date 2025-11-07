package questao_1;
public class ValueAtRiskStrategy implements RiskAnalysisStrategy {
    @Override
    public void calculate(FinancialContext context) {
        System.out.println("Executando cálculo de Risco: Value at Risk (VaR)");
        System.out.println("Contexto utilizado: " + context);
        System.out.println("Resultado: Perda potencial máxima calculada com sucesso.");
    }
}