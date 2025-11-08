package questao_2;

import java.util.HashMap;
import java.util.Random;

/**
 * Simula o comportamento do sistema bancário legado.
 */
public class LegacySystemSimulado implements SistemaBancarioLegado {
    private final Random rnd = new Random();

    @Override
    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        HashMap<String, Object> resp = new HashMap<>();
        if (!parametros.containsKey("cardNumber") || !parametros.containsKey("amount") || !parametros.containsKey("branchCode")) {
            resp.put("status", "ERROR");
            resp.put("message", "missing required fields");
            return resp;
        }

        double amount = ((Number) parametros.get("amount")).doubleValue();
        // Simulação simples
        boolean ok = amount < 10000 || rnd.nextDouble() > 0.7;
        resp.put("status", ok ? "OK" : "DECLINED");
        resp.put("authCode", ok ? "AUTH" + Math.abs(rnd.nextInt()%10000) : null);
        resp.put("message", ok ? "authorized" : "declined");
        return resp;
    }
}
