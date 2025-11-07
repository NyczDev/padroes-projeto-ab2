/**
 * 1. Mantém uma referência à interface 'RiskAlgorithm' (a Estratégia),
 * não a uma implementação concreta.
 * 2. Fornece um método 'setAlgorithm()' que permite ao cliente (ou à
 * lógica de negócios) *trocar o algoritmo em tempo de execução*.
 * 3. Mantém os dados financeiros (FinancialData) que são o contexto
 * compartilhado necessário para todos os algoritmos.
 * 4. Delega a execução do cálculo para o objeto de estratégia atual.
 */
public class RiskAnalysisContext {

    private RiskAlgorithm currentAlgorithm;
    private final FinancialData financialData;

    public RiskAnalysisContext(FinancialData data) {
        this.financialData = data;

    }

    public void setAlgorithm(RiskAlgorithm algorithm) {
        this.currentAlgorithm = algorithm;
    }

    public String executeAnalysis() {
        if (currentAlgorithm == null) {
            String error = "ERRO: Nenhum algoritmo de risco foi selecionado.";
            System.err.println(error);
            return error;
        }

        return currentAlgorithm.calculate(this.financialData);
    }
}