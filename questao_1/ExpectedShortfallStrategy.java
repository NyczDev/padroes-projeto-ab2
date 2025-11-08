package questao_1;

import java.text.DecimalFormat;

/**
 * Strategy para Expected Shortfall .
 */
public class ExpectedShortfallStrategy implements RiscoStrategy {
    @Override
    public String calcular(RiscoContext contexto) {
        Object pv = contexto.get("portfolioValue");
        Object severity = contexto.get("stressSeverity");
        double value = pv instanceof Number ? ((Number) pv).doubleValue() : 100000.0;
        double s = severity instanceof Number ? ((Number) severity).doubleValue() : 1.0;
        // Cálculo dummy
        double es = value * 0.02 * s;
        DecimalFormat df = new DecimalFormat("0.00");
        return "Expected Shortfall (dummy) = " + df.format(es) + " for portfolio=" + value + " severity=" + s;
    }
}
