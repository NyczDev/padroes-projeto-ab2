
public class StressTesting implements RiskAlgorithm {

    @Override
    public String calculate(FinancialData data) {
        // Cálculo dummy 
        String result = "Executando [Stress Testing]... Simulando cenário de crise de mercado.";
        System.out.println(result);
        return result;
    }
}