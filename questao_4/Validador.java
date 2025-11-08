package questao_4;

public interface Validador {
    boolean validar(DocumentoFiscal doc) throws Exception;
    void rollback(DocumentoFiscal doc);
}
