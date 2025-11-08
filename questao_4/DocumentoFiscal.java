package questao_4;

import java.util.HashMap;

public class DocumentoFiscal {
    private HashMap<String, Object> dados = new HashMap<>();

    public void put(String k, Object v) { dados.put(k, v); }
    public Object get(String k) { return dados.get(k); }
}
