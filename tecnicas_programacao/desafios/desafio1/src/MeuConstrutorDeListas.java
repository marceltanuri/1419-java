import java.util.List;
import java.util.ArrayList;

public class MeuConstrutorDeListas {

 public static <T> List<T> criarLista(T... elementos){
    List<T> lista = new ArrayList<>();
    for(T elemento : elementos){
        lista.add(elemento);
    }
    return lista;
 }
}