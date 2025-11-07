package questao_1;
public class StressTestingStrategy implements RiskAnalysisStrategy {
    @Override
    public void calculate(FinancialContext context) {
        System.out.println("Executando cálculo de Risco: Stress Testing");
        System.out.println("Contexto utilizado: " + context);
        System.out.println("Resultado: Simulação de cenários de crise concluída.");
    }
}