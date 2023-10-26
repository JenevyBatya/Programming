package org.lab;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ProxyServer {
    private static final int BUFFER_SIZE = 8192; // Размер буфера

    private final ServerInstance serverInstance;

    public ProxyServer(ServerInstance serverInstance) {
        this.serverInstance = serverInstance;
    }

    public void forwardRequest(Socket clientSocket) throws IOException {
        // Создаем соединение с выбранным сервером
        try (Socket serverSocket = new Socket(serverInstance.getAddress().getHostAddress(), serverInstance.getPort())) {
            // Получаем входной и выходной потоки клиента и сервера
            InputStream clientInput = clientSocket.getInputStream();
            OutputStream clientOutput = clientSocket.getOutputStream();
            InputStream serverInput = serverSocket.getInputStream();
            OutputStream serverOutput = serverSocket.getOutputStream();

            // Создаем буфер для передачи данных
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            // Читаем данные от клиента и перенаправляем их на сервер
            while ((bytesRead = clientInput.read(buffer)) != -1) {
                serverOutput.write(buffer, 0, bytesRead);
                serverOutput.flush();
            }

            // Читаем данные от сервера и отправляем их обратно клиенту
            while ((bytesRead = serverInput.read(buffer)) != -1) {
                clientOutput.write(buffer, 0, bytesRead);
                clientOutput.flush();
            }
        } finally {
            clientSocket.close();
        }
    }
}
