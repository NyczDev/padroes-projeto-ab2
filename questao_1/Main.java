package questao_1;

public class Main {
    public static void main(String[] args) {
        RiskProcessor riskProcessor = new RiskProcessor();
        FinancialContext context = new FinancialContext(1_000_000.00, 0.15, 0.05);

        System.out.println("Sistema de Análise de Risco Financeiro\n");

        System.out.println(">> Requisito de negócio: Realizar análise padrão (Value at Risk).");
        riskProcessor.setStrategy(new ValueAtRiskStrategy());
        riskProcessor.executeAnalysis(context);

        System.out.println(">> Requisito de negócio: Aprofundar análise de perdas extremas (Expected Shortfall).");
        riskProcessor.setStrategy(new ExpectedShortfallStrategy());
        riskProcessor.executeAnalysis(context);

        System.out.println(">> Requisito de negócio: Simular crise no mercado (Stress Testing).");
        riskProcessor.setStrategy(new StressTestingStrategy());
        riskProcessor.executeAnalysis(context);
    }
}