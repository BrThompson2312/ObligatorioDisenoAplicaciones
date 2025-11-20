package ort.da.obligatorio.utils;
import java.util.List;
import java.util.ArrayList;

public class Respuesta {
    private String id;
    private Object parametro;

    public Respuesta(String id, Object parametro) {
        this.id = id;
        this.parametro = parametro;
    }

    public Respuesta() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getParametro() {
        return parametro;
    }

    public void setParametro(Object parametro) {
        this.parametro = parametro;
    }

    public static List<Respuesta> lista(Respuesta... respuestas){
        List<Respuesta> retorno = new ArrayList<Respuesta>();
        for (Respuesta r : respuestas) {
            retorno.add(r);
        }
        return retorno;
    }
}
