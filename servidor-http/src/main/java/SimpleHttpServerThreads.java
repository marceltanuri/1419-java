import java.io.*;

import java.net.ServerSocket;
import java.net.Socket;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Random;

public class SimpleHttpServerThreads {

    private static final int PORTA_PADRAO = 8080;

    public static void main(String[] args) throws IOException {
        int port = PORTA_PADRAO;

        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.printf("Servidor HTTP (multithread manual) rodando na porta %d%n", port);
            while (true) {
                // Espera até alguém se conectar
                Socket clientSocket = serverSocket.accept();

                // Cria uma nova thread para tratar essa conexão
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
            }
        }
    }

    static class ClientHandler implements Runnable {
        private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        private static final Random rnd = new Random();
        private final Socket socket;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            String client = socket.getRemoteSocketAddress().toString();
            LocalTime start = LocalTime.now();

            System.out.printf("[%s] Nova conexão de %s (thread: %s)%n",
                    fmt.format(start), client, threadName);

            try (socket;
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 OutputStream out = socket.getOutputStream()) {

                // lê a primeira linha da requisição (ex: "GET / HTTP/1.1")
                String requestLine = in.readLine();
                if (requestLine == null) return;

                System.out.printf("[%s] %s recebeu: %s%n",
                        fmt.format(LocalTime.now()), threadName, requestLine);

                // simula trabalho (0.5s a 2s)
                int workMs = 500 + rnd.nextInt(1500);
                Thread.sleep(workMs);

                String body = """
                    <html><body>
                    <h1>Servidor HTTP Multithread (sem pool)</h1>
                    <p>Thread: %s</p>
                    <p>Cliente: %s</p>
                    <p>Hora: %s</p>
                    <p>Tempo de trabalho simulado: %d ms</p>
                    </body></html>
                    """.formatted(threadName, client, fmt.format(LocalTime.now()), workMs);

                byte[] bodyBytes = body.getBytes("UTF-8");
                String response = """
                    HTTP/1.1 200 OK
                    Content-Type: text/html; charset=UTF-8
                    Content-Length: %d
                    Connection: close

                    """.formatted(bodyBytes.length);

                out.write(response.getBytes("UTF-8"));
                out.write(bodyBytes);
                out.flush();

                System.out.printf("[%s] %s terminou em %d ms%n",
                        fmt.format(LocalTime.now()), threadName,
                        (System.currentTimeMillis() - start.toSecondOfDay() * 1000));

            } catch (IOException | InterruptedException e) {
                System.err.printf("[%s] Erro na thread %s: %s%n",
                        fmt.format(LocalTime.now()), threadName, e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
