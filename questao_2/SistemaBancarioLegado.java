package questao_2;

import java.util.HashMap;

public interface SistemaBancarioLegado {
    HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros);
}
