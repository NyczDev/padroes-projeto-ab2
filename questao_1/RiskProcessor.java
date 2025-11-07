package questao_1;
public class RiskProcessor {
    private RiskAnalysisStrategy strategy;

    public void setStrategy(RiskAnalysisStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeAnalysis(FinancialContext context) {
        if (strategy == null) {
            System.out.println("Erro: Nenhuma estratégia de análise de risco foi definida.");
            return;
        }
        strategy.calculate(context);
    }
}