import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ExemploMultithread {

    // Simula o sistema de notificação que seria chamado após a conclusão total
    public static void notificarUsuario(String resultado) {
        System.out.println("\n#######################################################");
        System.out.println("[SISTEMA DE NOTIFICAÇÃO]");
        System.out.println("Status final do pedido: " + resultado);
        System.out.println("#######################################################");
    }

    public static void main(String args[]) {
        System.out.println("Thread principal (Web Server) iniciada.");
        System.out.println("Recebendo novo pedido e iniciando processamento...");

        List<String> passosDoPagamento = List.of(
            "Validar operadora",
            "Consultar validade",
            "Consumir API operadora cartão",
            "Analise fraude"
        );
        
        List<Thread> threadsDeTrabalho = new ArrayList<>();
        // Variável atômica para garantir contagem segura em multithread
        AtomicInteger tarefasConcluidas = new AtomicInteger(0); 

        // 1. DISPARA TODAS AS TAREFAS EM PARALELO
        for (String info : passosDoPagamento) {
            
            Runnable task = () -> {
                try {
                    System.out.println("\n[TAREFA] Iniciando " + info + "... Thread:" + Thread.currentThread().getName());
                    int sleepTime = ThreadLocalRandom.current().nextInt(6000, 15001);
                    Thread.sleep(sleepTime); 
                    
                    System.out.println("[TAREFA] " + info + " CONCLUÍDO. Thread:" + Thread.currentThread().getName() + " Tempo: " + sleepTime + "ms");
                    
                    // Incrementa o contador de tarefas concluídas
                    tarefasConcluidas.incrementAndGet();
                    
                } catch (InterruptedException e) {
                    System.err.println("Thread interrupted!");
                    Thread.currentThread().interrupt();
                }
            };
            
            Thread thread = new Thread(task);
            threadsDeTrabalho.add(thread);
            thread.start();
        }

        // 2. RETORNO IMEDIATO: A thread principal não bloqueia
        System.out.println("\n=============================================");
        System.out.println("-> RETORNO AO CLIENTE: Status: PAGAMENTO EM ANÁLISE.");
        System.out.println("=============================================");
        
        // 3. CRIAÇÃO DA THREAD DE GERENCIAMENTO (Monitoramento)
        // Esta thread será responsável por esperar e notificar o resultado.
        Runnable monitor = () -> {
            System.out.println("[MONITOR] Iniciando monitoramento das threads de pagamento...");
            for (Thread thread : threadsDeTrabalho) {
                try {
                    // A thread monitora BLOQUEIA e espera cada thread de trabalho terminar
                    thread.join(); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("[MONITOR] O monitoramento foi interrompido.");
                    return;
                }
            }
            
            // Este código só roda quando todas as threads de trabalho terminaram
            if (tarefasConcluidas.get() == passosDoPagamento.size()) {
                notificarUsuario("Pagamento Aprovado e Concluído!");
            } else {
                notificarUsuario("Pagamento Rejeitado (Uma ou mais etapas falharam).");
            }
        };

        new Thread(monitor, "Thread-Monitor").start();

        // 4. Thread Principal termina IMEDIATAMENTE.
        // A notificação (passo 3) será feita pela 'Thread-Monitor' em segundo plano.
        System.out.println("Thread principal (Web Server) finalizada e pronta para um novo pedido.");
    }
}