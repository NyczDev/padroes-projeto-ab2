package questao_1;
public class FinancialContext {
    private double portfolioValue;
    private double volatility;
    private double interestRate;

    public FinancialContext(double portfolioValue, double volatility, double interestRate) {
        this.portfolioValue = portfolioValue;
        this.volatility = volatility;
        this.interestRate = interestRate;
    }

    public double getPortfolioValue() {
        return portfolioValue;
    }

    public double getVolatility() {
        return volatility;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return String.format("Contexto [Valor do Portfólio=R$%.2f, Volatilidade=%.2f, Taxa de Juros=%.2f]",
                portfolioValue, volatility, interestRate);
    }
}