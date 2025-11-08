package questao_3;

/**
 * Simula atualizações de sensores e mostra transições de estados,
 * o requisito de 30s para ALERTA_VERMELHO e o modo manutenção.
 */
public class MainUsina {
    public static void main(String[] args) throws Exception {
        Usina usina = new Usina();

        System.out.println("Estado inicial: " + usina.getEstado());

        // Subir para OPERACAO_NORMAL
        usina.resetPara(EstadoUsina.OPERACAO_NORMAL);
        System.out.println("Setado para: " + usina.getEstado());

        // Atinge temperatura > 300 -> ALERTA_AMARELO
        usina.atualizarSensores(310, 100, 1, true);
        System.out.println("Após temp 310: " + usina.getEstado());

        // Agora simular temperatura > 400 por 31s para disparar ALERTA_VERMELHO
    usina.atualizarSensores(410, 100, 1, true);
    System.out.println("Temp >400 registrado; simulando 31s passados...");
    // Simular que já faz 31s que temperatura > 400 (evita bloquear a demo)
    usina.forceTempExceededSince(31_000);
    usina.atualizarSensores(410, 100, 1, true);
        System.out.println("Após 31s: " + usina.getEstado());

        // Simular falha no sistema de resfriamento -> EMERGENCIA
        usina.atualizarSensores(450, 100, 1, false);
        System.out.println("Após falha resfriamento: " + usina.getEstado());

        // Ativar manutenção (sobrescreve estado)
        usina.ativarManutencao();
        System.out.println("Após ativar manutencao: " + usina.getEstado());

        // Atualizações durante manutenção são ignoradas
        usina.atualizarSensores(500, 200, 600, false);
        System.out.println("Durante manutencao, estado: " + usina.getEstado());

        // Desativar manutenção -> restaura estado anterior
        usina.desativarManutencao();
        System.out.println("Após desativar manutencao: " + usina.getEstado());
    }
}
