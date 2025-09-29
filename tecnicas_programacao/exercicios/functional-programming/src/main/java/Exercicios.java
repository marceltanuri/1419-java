import java.util.Arrays;
import java.util.List;


public class Exercicios{

    public static void main(String[] args) {

        int numeros[] = {2, 5, 8, 1, 10, 7, 4};
        List<Usuario> usuarios = Arrays.asList(
            new Usuario("Ana", 28), 
            new Usuario("Bruno", 35), 
            new Usuario("Carla", 22), 
            new Usuario("Diego", 40)
        );

        //Exericio 1.1
        System.out.println("Exercício 1.1");
        System.out.println(Arrays.toString(dobrarValores(numeros)));

        //Exercicio1.2
        System.out.println("Exercício 1.2");
        System.out.println(Arrays.toString(extraiNomes(usuarios)));

        //Exercicio2.1


        //Exercicio2.2

        //Exercicio3.1

        //Exercicio3.2

        //Exercicio4
    }

    static int[] dobrarValores(int[] numeros){
        return Arrays.stream(numeros).map(numero -> numero).toArray();
    }

    static String[] extraiNomes(List<Usuario> usuarios){
        return usuarios.stream().map(usuario -> usuario.toString()).toArray(size -> new String[size]);
    }
}

class Usuario{
        private String nome;
        private int idade;

        public Usuario(String nome, int idade) {
            this.nome = nome;
            this.idade = idade;
        }

        public String getNome() {
            return nome;
        }

        public int getIdade() {
            return idade;
        }
    }