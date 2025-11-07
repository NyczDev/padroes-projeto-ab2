
public class ValueAtRisk implements RiskAlgorithm {

    @Override
    public String calculate(FinancialData data) {
        // Cálculo dummy
        String result = String.format(
            "Cálculo [Value at Risk (VaR)] a %.1f%% de confiança para %d dias. Valor da Carteira: %.2f",
            data.confidenceLevel() * 100,
            data.timeHorizonDays(),
            data.portfolioValue()
        );
        System.out.println(result);
        return result;
    }
}