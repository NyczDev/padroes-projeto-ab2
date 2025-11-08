package questao_2;

import java.util.HashMap;

/**
 * Adapter que permite usar um SistemaBancarioLegado através da interface moderna ProcessadorTransacoes.
 * Preenche campos obrigatórios do legado e mapeia moeda para códigos.
 */
public class ModernToLegacyAdapter implements ProcessadorTransacoes {
    private final SistemaBancarioLegado legado;
    private final String defaultBranchCode;

    public ModernToLegacyAdapter(SistemaBancarioLegado legado, String defaultBranchCode) {
        this.legado = legado;
        this.defaultBranchCode = defaultBranchCode;
    }

    @Override
    public boolean autorizar(String cartao, double valor, String moeda) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("cardNumber", cartao);
        params.put("amount", valor);
        params.put("currencyCode", mapMoeda(moeda));
        if (defaultBranchCode == null || defaultBranchCode.isEmpty()) {
            throw new IllegalArgumentException("branchCode requerido pelo sistema legado");
        }
        params.put("branchCode", defaultBranchCode);

        HashMap<String, Object> resposta = legado.processarTransacao(params);
        return LegacyToModernConverter.toBoolean(resposta);
    }

    private int mapMoeda(String moeda) {
        if (moeda == null) return 0;
        switch (moeda.toUpperCase()) {
            case "USD": return 1;
            case "EUR": return 2;
            case "BRL": return 3;
            default: return 0;
        }
    }
}
