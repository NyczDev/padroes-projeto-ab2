package questao_3;

/**
 * Controlador da usina. Implementa regras de transição entre estados,
 * prevenção de ciclos perigosos e modo manutenção que sobrescreve estados.
 *
 * Design: State representado por enum simples e lógica de transição centralizada
 * para manter validações complexas e histórico mínimo .
 */
public class Usina {
    private EstadoUsina estado = EstadoUsina.DESLIGADA;
    private EstadoUsina antesManutencao = null;
    private boolean manutencao = false;

    // histórico simples para prevenir ciclos: último estado e timestamp
    private EstadoUsina ultimoEstado = null;
    private long ultimoTimestamp = 0;

    // flags necessárias para regras
    private boolean passouAlertaVermelho = false;
    private long tempExcedeu400Desde = 0; // millis quando temp > 400 começou

    public synchronized EstadoUsina getEstado() { return estado; }

    public synchronized void ativarManutencao() {
        if (!manutencao) {
            antesManutencao = estado;
            manutencao = true;
            estado = EstadoUsina.MANUTENCAO;
            System.out.println("Modo manutenção ativado (estado anterior: " + antesManutencao + ")");
        }
    }

    public synchronized void desativarManutencao() {
        if (manutencao) {
            manutencao = false;
            estado = antesManutencao != null ? antesManutencao : EstadoUsina.DESLIGADA;
            antesManutencao = null;
            System.out.println("Modo manutenção desativado. Estado restaurado para " + estado);
        }
    }

    private boolean proibidoCircular(EstadoUsina novo) {
        long now = System.currentTimeMillis();
        // Previne voltar ao último estado em menos de 60s
        if (ultimoEstado == novo && (now - ultimoTimestamp) < 60_000) {
            System.out.println("Transição proibida: evitar ciclo rápido para " + novo);
            return true;
        }
        return false;
    }

    public synchronized void atualizarSensores(double temperatura, double pressao, double radiacao, boolean sistemaResfriamentoOk) {
        if (manutencao) {
            System.out.println("Atualização ignorada: em manutenção");
            return;
        }

        long now = System.currentTimeMillis();

       //OPERACAO_NORMAL -> ALERTA_AMARELO se temperatura > 300
        if (estado == EstadoUsina.OPERACAO_NORMAL && temperatura > 300) {
            if (!proibidoCircular(EstadoUsina.ALERTA_AMARELO)) {
                transitar(EstadoUsina.ALERTA_AMARELO);
            }
        }

        // Gerenciamento temp>400 por 30s: mantém timestamp
        if (temperatura > 400) {
            if (tempExcedeu400Desde == 0) tempExcedeu400Desde = now;
        } else {
            tempExcedeu400Desde = 0;
        }

        // ALERTA_AMARELO -> ALERTA_VERMELHO se temperatura > 400 por >30s
        if (estado == EstadoUsina.ALERTA_AMARELO && tempExcedeu400Desde > 0 && (now - tempExcedeu400Desde) >= 30_000) {
            if (!proibidoCircular(EstadoUsina.ALERTA_VERMELHO)) {
                transitar(EstadoUsina.ALERTA_VERMELHO);
            }
        }

        // ALERTA_VERMELHO -> EMERGENCIA se sistema de resfriamento falhar
        if (estado == EstadoUsina.ALERTA_VERMELHO && !sistemaResfriamentoOk) {
            if (!passouAlertaVermelho) {
                passouAlertaVermelho = true;
            }
            if (passouAlertaVermelho) {
                if (!proibidoCircular(EstadoUsina.EMERGENCIA)) {
                    transitar(EstadoUsina.EMERGENCIA);
                }
            }
        }

        // exemplo OPERACAO_NORMAL <-> ALERTA_AMARELO (retorno se temp <= 300)
        if (estado == EstadoUsina.ALERTA_AMARELO && temperatura <= 300) {
            if (!proibidoCircular(EstadoUsina.OPERACAO_NORMAL)) {
                transitar(EstadoUsina.OPERACAO_NORMAL);
            }
        }

        // Alerta vermelho pode regredir para OPERACAO_NORMAL apenas via intervenção manual (não automática)

        if (estado == EstadoUsina.ALERTA_VERMELHO) passouAlertaVermelho = true;

        if ((pressao > 1000 || radiacao > 500) && estado != EstadoUsina.EMERGENCIA) {
            System.out.println("Condição crítica detectada (pressao/radiacao) -> forçando EMERGENCIA");
            if (!proibidoCircular(EstadoUsina.EMERGENCIA)) transitar(EstadoUsina.EMERGENCIA);
        }
    }

    // marca que temperatura excedeu 400 há "millisAgo" ms
    public synchronized void forceTempExceededSince(long millisAgo) {
        if (millisAgo <= 0) tempExcedeu400Desde = 0;
        else tempExcedeu400Desde = System.currentTimeMillis() - millisAgo;
    }

    private void transitar(EstadoUsina novo) {
        System.out.println("Transição: " + estado + " -> " + novo);
        ultimoEstado = estado;
        ultimoTimestamp = System.currentTimeMillis();
        estado = novo;
    }

    // Método para reset manual
    public synchronized void resetPara(EstadoUsina destino) {
        // permitir apenas resets controlados: se vindo de EMERGENCIA, vai para DESLIGADA por segurança
        if (estado == EstadoUsina.EMERGENCIA && destino != EstadoUsina.DESLIGADA) {
            System.out.println("Após EMERGENCIA, reset só permite DESLIGADA por segurança");
            estado = EstadoUsina.DESLIGADA;
        } else {
            System.out.println("Reset manual: " + estado + " -> " + destino);
            estado = destino;
        }
    }
}
