package questao_4;

/**
 * Validador de certificado digital.
 * Segundo validador da cadeia.
 */
public class ValidadorCertificado extends ValidadorBase {
    public ValidadorCertificado(ValidadorBase proximo) {
        super(proximo, 5000);
    }

    @Override
    protected boolean executarValidacao(DocumentoFiscal doc) throws Exception {
        // dummy
        String cert = (String) doc.get("certificado");
        if (cert == null || !cert.startsWith("VALID")) {
            System.out.println("[CERT] Falha: Certificado inválido/revogado");
            return false;
        }
        System.out.println("[CERT] Ok: Certificado válido");
        return true;
    }
}