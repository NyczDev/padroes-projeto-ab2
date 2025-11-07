public record FinancialData(
    double portfolioValue,
    double volatility,
    int timeHorizonDays,
    double confidenceLevel
) {
}