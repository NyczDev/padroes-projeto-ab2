package questao_2;

public class MainTransacoes {
    public static void main(String[] args) throws Exception {
        SistemaBancarioLegado legado = new LegacySystemSimulado();
        ProcessadorTransacoes processador = new ModernToLegacyAdapter(legado, "0001");

        String cartao = "4111111111111111";
        double valor = 2500.0;

        boolean autorizado = processador.autorizar(cartao, valor, "BRL");
        System.out.println("Autorizacao moderna via adapter: " + autorizado);

        java.util.HashMap<String, Object> params = new java.util.HashMap<>();
        params.put("cardNumber", cartao);
        params.put("amount", 15000.0);
        params.put("currencyCode", 1);
        params.put("branchCode", "0001");
        java.util.HashMap<String, Object> resp = legado.processarTransacao(params);

        boolean moderno = LegacyToModernConverter.toBoolean(resp);
        System.out.println("Resposta legado convertida para moderno: " + moderno + " msg=" + LegacyToModernConverter.toMessage(resp));
    }
}
