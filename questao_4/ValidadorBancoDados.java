package questao_4;

/**
 * Validador de duplicidade no banco.
 * Quarto validador da cadeia, com rollback em caso de falha.
 */
public class ValidadorBancoDados extends ValidadorBase {
    private String ultimoNumeroInserido = null;

    public ValidadorBancoDados(ValidadorBase proximo) {
        super(proximo, 1000); // timeout 1s
    }

    @Override
    protected boolean executarValidacao(DocumentoFiscal doc) throws Exception {
        String numero = (String) doc.get("numero");
        if (numero == null) {
            System.out.println("[BD] Falha: Número não informado");
            return false;
        }

        // Simula inserção no BD guardando último número
        ultimoNumeroInserido = numero;
        System.out.println("[BD] Ok: Documento inserido no banco");
        return true;
    }

    @Override
    public void rollback(DocumentoFiscal doc) {
        if (ultimoNumeroInserido != null) {
            String numero = (String) doc.get("numero");
            if (numero != null && numero.equals(ultimoNumeroInserido)) {
                System.out.println("[BD] Rollback: Removendo documento " + numero);
                ultimoNumeroInserido = null;
            }
        }
        super.rollback(doc);
    }
}