
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Sistema de Análise de Risco Financeiro ---");

        FinancialData dadosFinanceiros = new FinancialData(
            1000000.00, // portfolioValue
            0.15,       // volatility
            30,         // timeHorizonDays
            0.95        // confidenceLevel (95%)
        );

        RiskAnalysisContext processadorRisco = new RiskAnalysisContext(dadosFinanceiros);

        System.out.println("\n Calcular VaR");
        processadorRisco.setAlgorithm(new ValueAtRisk());
        processadorRisco.executeAnalysis();

        System.out.println("\nExecutar Stress Test");
        processadorRisco.setAlgorithm(new StressTesting());
        processadorRisco.executeAnalysis();

        System.out.println("\n Calcular Expected Shortfall");
        processadorRisco.setAlgorithm(new ExpectedShortfall());
        processadorRisco.executeAnalysis();

    }
}