import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class Exercicios{

    public static void main(String[] args) {

        int numeros[] = {2, 5, 8, 1, 10, 7, 4};
        List<Usuario> usuarios = Arrays.asList(
            new Usuario("Marcel", 40), 
            new Usuario("Ana", 28), 
            new Usuario("Bruno", 35), 
            new Usuario("Carla", 22), 
            new Usuario("Diego", 40)
        );

        //Exericio 1.1
        System.out.println("Exercício 1.1");
        System.out.println(Arrays.toString(dobrarValores(numeros)));
        System.out.println("======================================");


        //Exercicio1.2
        System.out.println("Exercício 1.2");
        System.out.println(Arrays.toString(extraiNomes(usuarios)));
        System.out.println("======================================");

        //Exercicio2.1
        System.out.println("Exercício 2.1");
        System.out.println(Arrays.toString(filtrarNumerosMaioresQue(numeros, 5)));
        System.out.println("======================================");


        //Exercicio2.2
        System.out.println("Exercício 2.2");
        System.out.println(Arrays.toString(filtrarPorIdadeMaiorQue(usuarios, 30))); 
        System.out.println("======================================");


        //Exercicio3.1
        System.out.println("Exercício 3.1");
        System.out.println(somarNumeros(numeros));
        System.out.println("======================================");


        //Exercicio3.2
        System.out.println("Exercício 3.2");
        if(encontrarUsuarioMaisVelho(usuarios).isPresent()){
            System.out.println(encontrarUsuarioMaisVelho(usuarios).get());
        }
        else{
            System.out.println("Nenhum usuário encontrado");
        }
        System.out.println("======================================");


        //Exercicio4
        System.out.println("Exercício 4");
        System.out.println(somaDeIdadesDeUsuariosMaisNovosQue(usuarios, 30));
        System.out.println("======================================");
    }

    static int[] dobrarValores(int[] numeros){
        return Arrays.stream(numeros).map(numero -> numero * 2).toArray();
    }

    static String[] extraiNomes(List<Usuario> usuarios){
        return usuarios.stream().map(usuario -> usuario.getNome()).toArray(size -> new String[size]);
    }

    static int[] filtrarNumerosMaioresQue(int[] numeros, int valor){
        return Arrays.stream(numeros).filter(numero -> numero > valor).toArray();
    }

    static Usuario[] filtrarPorIdadeMaiorQue(List<Usuario> usuarios, int idade){
        return usuarios.stream().filter(usuario -> usuario.getIdade() >= idade).toArray(size -> new Usuario[size]);
    }

    static int somarNumeros(int[] numeros){
        return Arrays.stream(numeros).reduce(0, (contador, numero) -> contador + numero);
    }
    

    static Optional<Usuario> encontrarUsuarioMaisVelho(List<Usuario> usuarios){
        return usuarios.stream().reduce((usuario1, usuario2) -> usuario1.getIdade() > usuario2.getIdade() ? usuario1 : usuario2);
    }

    static int somaDeIdadesDeUsuariosMaisNovosQue(List<Usuario> usuarios, int idade){
        return 0;
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

        @Override
        public String toString() {
            return "Usuario{" +
                    "nome='" + nome + '\'' +
                    ", idade=" + idade +
                    '}';
        }
    }