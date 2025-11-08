package questao_2;

import java.util.HashMap;

/**
 * Converte respostas do legado para formatos modernos.
 */
public class LegacyToModernConverter {
    public static boolean toBoolean(HashMap<String, Object> resposta) {
        if (resposta == null) return false;
        Object status = resposta.get("status");
        if (status == null) return false;
        return "OK".equalsIgnoreCase(status.toString());
    }

    public static String toMessage(HashMap<String, Object> resposta) {
        if (resposta == null) return "sem resposta";
        Object msg = resposta.get("message");
        return msg != null ? msg.toString() : "sem mensagem";
    }
}
