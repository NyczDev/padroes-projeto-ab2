package questao_4;

/**
 * Validador de regras fiscais.
 * Terceiro validador da cadeia.
 */
public class ValidadorRegrasFiscais extends ValidadorBase {
    public ValidadorRegrasFiscais(ValidadorBase proximo) {
        super(proximo, 3000); 
    }

    @Override
    protected boolean executarValidacao(DocumentoFiscal doc) throws Exception {
        // dummy
        double valor = ((Number) doc.get("valorTotal")).doubleValue();
        double impostos = ((Number) doc.get("impostos")).doubleValue();

        if (impostos < valor * 0.1) { // 10% de impostos
            System.out.println("[FISCAL] Falha: Impostos abaixo do mínimo");
            return false;
        }
        System.out.println("[FISCAL] Ok: Cálculos fiscais válidos");
        return true;
    }
}