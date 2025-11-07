package questao_1;
public class ExpectedShortfallStrategy implements RiskAnalysisStrategy {
    @Override
    public void calculate(FinancialContext context) {
        System.out.println("Executando cálculo de Risco: Expected Shortfall (ES)");
        System.out.println("Contexto utilizado: " + context);
        System.out.println("Resultado: Média de perda em cenários extremos calculada.");
    }
}