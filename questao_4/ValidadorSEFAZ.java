package questao_4;

/**
 * Validador SEFAZ.
 * Quinto validador da cadeia.
 */
public class ValidadorSEFAZ extends ValidadorBase {
    public ValidadorSEFAZ(ValidadorBase proximo) {
        super(proximo, 10000); 
    }

    @Override
    protected boolean executarValidacao(DocumentoFiscal doc) throws Exception {
        Thread.sleep(50);
        
        String chave = (String) doc.get("chaveAcesso");
        if (chave == null || chave.length() != 44) {
            System.out.println("[SEFAZ] Falha: Chave de acesso inválida");
            return false;
        }
        System.out.println("[SEFAZ] Ok: Documento autorizado");
        return true;
    }
}