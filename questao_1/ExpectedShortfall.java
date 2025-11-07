public class ExpectedShortfall implements RiskAlgorithm {

    @Override
    public String calculate(FinancialData data) {
        // Cálculo dummy
        String result = String.format(
            "Cálculo [Expected Shortfall (ES)] com volatilidade de %.2f. Foco nas perdas extremas.",
            data.volatility()
        );
        System.out.println(result);
        return result;
    }
}