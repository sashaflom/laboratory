package network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UniversityClient {

    private static final Logger logger = LoggerFactory.getLogger(UniversityClient.class);

    public static Object sendRequest(String request) {
        try (Socket socket = new Socket("localhost", 8080);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            out.writeObject(request); // надсилаємо запит на сервер
            logger.info("Запит {} успішно надіслано на сервер", request);
            return in.readObject();   // отримуємо відповідь
        } catch (Exception e) {
            logger.error("Помилка при надсиланні запиту на сервер: {}", e.getMessage());
            return null;
        }
    }

}
