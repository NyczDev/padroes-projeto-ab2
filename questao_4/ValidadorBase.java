package questao_4;

/**
 * Classe base para validadores.
 * Implementa timeout e gerencia próximo validador na cadeia.
 */
public abstract class ValidadorBase implements Validador {
    protected final ValidadorBase proximo;
    protected final long timeoutMillis;
    protected int falhasAcumuladas = 0;
    protected static final int MAX_FALHAS = 3;

    public ValidadorBase(ValidadorBase proximo, long timeoutMillis) {
        this.proximo = proximo;
        this.timeoutMillis = timeoutMillis;
    }

    @Override
    public final boolean validar(DocumentoFiscal doc) throws Exception {
        if (falhasAcumuladas >= MAX_FALHAS) {
            throw new Exception("Circuit breaker ativado após " + MAX_FALHAS + " falhas");
        }

        long inicio = System.currentTimeMillis();
        try {
            boolean ok = executarValidacao(doc);
            if (!ok) falhasAcumuladas++;

            if (System.currentTimeMillis() - inicio > timeoutMillis) {
                falhasAcumuladas++;
                throw new Exception("Timeout na validação após " + timeoutMillis + "ms");
            }

            if (!ok) return false;

            return proximo == null || proximo.validar(doc);
        } catch (Exception e) {
            falhasAcumuladas++;
            throw e;
        }
    }

    protected abstract boolean executarValidacao(DocumentoFiscal doc) throws Exception;

    @Override
    public void rollback(DocumentoFiscal doc) {
        if (proximo != null) proximo.rollback(doc);
    }
}