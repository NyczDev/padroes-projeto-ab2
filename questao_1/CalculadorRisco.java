package questao_1;

/**
 * Contexto que encapsula o algoritmo de cálculo de risco.
 * Implementa o princípio aberto/fechado: novos algoritmos adicionam-se como novas Strategies.
 */
public class CalculadorRisco {
    private RiscoStrategy estrategia;

    public CalculadorRisco(RiscoStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public void setEstrategia(RiscoStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public String calcular(RiscoContext contexto) {
        return estrategia.calcular(contexto);
    }
}
