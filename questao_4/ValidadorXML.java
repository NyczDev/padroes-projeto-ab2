package questao_4;

/**
 * Validador de Schema XML contra XSD.
 * Primeiro validador da cadeia.
 */
public class ValidadorXML extends ValidadorBase {
    public ValidadorXML(ValidadorBase proximo) {
        super(proximo, 2000); 
    }

    @Override
    protected boolean executarValidacao(DocumentoFiscal doc) throws Exception {
        // dummy
        String xml = (String) doc.get("xml");
        if (xml == null || !xml.contains("<nfe>")) {
            System.out.println("[XML] Falha: XML inválido");
            return false;
        }
        System.out.println("[XML] Ok: XML válido");
        return true;
    }
}