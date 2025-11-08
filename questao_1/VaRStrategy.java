package questao_1;

import java.text.DecimalFormat;

/**
 * Strategy para Value at Risk.
 * Strategy permite trocar algoritmo em tempo de execução sem expor detalhes.
 */
public class VaRStrategy implements RiscoStrategy {
    @Override
    public String calcular(RiscoContext contexto) {
        Object pv = contexto.get("portfolioValue");
        Object conf = contexto.get("confidenceLevel");
        double value = pv instanceof Number ? ((Number) pv).doubleValue() : 100000.0;
        double c = conf instanceof Number ? ((Number) conf).doubleValue() : 0.99;
        // Cálculo dummy
        double var = value * (1 - c) * 0.05;
        DecimalFormat df = new DecimalFormat("0.00");
        return "VaR (dummy) = " + df.format(var) + " para portfolio=" + value + " conf=" + c;
    }
}
