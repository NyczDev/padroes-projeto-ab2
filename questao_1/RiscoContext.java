package questao_1;

import java.util.Map;

public class RiscoContext {
    private Map<String, Object> parametros;

    public RiscoContext(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public Object get(String chave) {
        return parametros.get(chave);
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }
}
