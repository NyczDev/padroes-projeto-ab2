package questao_1;

import java.util.HashMap;

public class MainRisco {
    public static void main(String[] args) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("portfolioValue", 500000.0);
        params.put("confidenceLevel", 0.975);
        params.put("stressSeverity", 1.5);
        params.put("scenarioName", "queda-mercado") ;

        RiscoContext contexto = new RiscoContext(params);

        CalculadorRisco calc = new CalculadorRisco(new VaRStrategy());
        System.out.println("Usando VaR: " + calc.calcular(contexto));

        // Troca dinâmica de algoritmo durante execução
        calc.setEstrategia(new ExpectedShortfallStrategy());
        System.out.println("Agora ES: " + calc.calcular(contexto));

        calc.setEstrategia(new StressTestingStrategy());
        System.out.println("Finalmente Stress: " + calc.calcular(contexto));
    }
}
