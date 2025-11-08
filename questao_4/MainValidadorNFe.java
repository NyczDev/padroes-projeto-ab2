package questao_4;

/**
 * Demonstrador da cadeia de validação com circuit breaker, timeout e rollback.
 */
public class MainValidadorNFe {
    public static void main(String[] args) {
        // Monta cadeia de validação 
        ValidadorBase sefaz = new ValidadorSEFAZ(null);
        ValidadorBase bd = new ValidadorBancoDados(sefaz);
        ValidadorBase fiscal = new ValidadorRegrasFiscais(bd);
        ValidadorBase cert = new ValidadorCertificado(fiscal);
        ValidadorBase xml = new ValidadorXML(cert);

        // Documento válido
        DocumentoFiscal doc1 = new DocumentoFiscal();
        doc1.put("xml", "<nfe><id>123</id></nfe>");
        doc1.put("certificado", "VALID-123");
        doc1.put("valorTotal", 1000.0);
        doc1.put("impostos", 150.0);
        doc1.put("numero", "NFE001");
        doc1.put("chaveAcesso", "12345678901234567890123456789012345678901234");

        // Documento com erro fiscal
        DocumentoFiscal doc2 = new DocumentoFiscal();
        doc2.put("xml", "<nfe><id>124</id></nfe>");
        doc2.put("certificado", "VALID-124");
        doc2.put("valorTotal", 1000.0);
        doc2.put("impostos", 50.0); // < 10%
        doc2.put("numero", "NFE002");
        doc2.put("chaveAcesso", "12345678901234567890123456789012345678901234");

        try {
            System.out.println("\nValidando documento 1 (deve passar):");
            boolean ok = xml.validar(doc1);
            System.out.println("Resultado final: " + (ok ? "VÁLIDO" : "INVÁLIDO"));

            System.out.println("\nValidando documento 2 (deve falhar regras fiscais):");
            ok = xml.validar(doc2);
            System.out.println("Resultado final: " + (ok ? "VÁLIDO" : "INVÁLIDO"));
            xml.rollback(doc2);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}