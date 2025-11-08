package questao_1;

import java.text.DecimalFormat;

/**
 * Strategy para Stress Testing .
 */
public class StressTestingStrategy implements RiscoStrategy {
    @Override
    public String calcular(RiscoContext contexto) {
        Object pv = contexto.get("portfolioValue");
        Object scenario = contexto.get("scenarioName");
        double value = pv instanceof Number ? ((Number) pv).doubleValue() : 100000.0;
        String s = scenario != null ? scenario.toString() : "cenario-padrao";
        // Cálculo dummy
        double impact = value * 0.1;
        DecimalFormat df = new DecimalFormat("0.00");
        return "StressTest (dummy) [" + s + "] impacto=" + df.format(impact) + " em portfolio=" + value;
    }
}
