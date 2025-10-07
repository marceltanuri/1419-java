import java.io.*;
import java.net.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class SimpleHttpServerSingleThread {

    public static void main(String[] args) throws IOException {
        int port = 8080;

        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.printf("Servidor HTTP (single-thread) rodando na porta %d%n", port);
            while (true) {
                // Aceita uma conexão (bloqueante)
                Socket clientSocket = serverSocket.accept();

                // Processa a requisição diretamente (sem criar thread)
                handleClient(clientSocket);
            }
        }
    }

    private static void handleClient(Socket socket) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        Random rnd = new Random();
        String client = socket.getRemoteSocketAddress().toString();
        LocalTime start = LocalTime.now();

        System.out.printf("[%s] Nova conexão de %s (thread principal)%n",
                fmt.format(start), client);

        try (socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             OutputStream out = socket.getOutputStream()) {

            // lê a primeira linha da requisição
            String requestLine = in.readLine();
            if (requestLine == null) return;

            System.out.printf("[%s] Processando requisição: %s%n",
                    fmt.format(LocalTime.now()), requestLine);

            // simula trabalho (0.5s a 2s)
            int workMs = 500 + rnd.nextInt(1500);
            Thread.sleep(workMs);

            String body = """
                <html><body>
                <h1>Servidor HTTP Single-Thread</h1>
                <p>Thread: principal</p>
                <p>Cliente: %s</p>
                <p>Hora: %s</p>
                <p>Tempo de trabalho simulado: %d ms</p>
                </body></html>
                """.formatted(client, fmt.format(LocalTime.now()), workMs);

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

            System.out.printf("[%s] Resposta enviada em %d ms%n",
                    fmt.format(LocalTime.now()),
                    (System.currentTimeMillis() - start.toSecondOfDay() * 1000));

        } catch (IOException | InterruptedException e) {
            System.err.printf("[%s] Erro: %s%n", fmt.format(LocalTime.now()), e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}
