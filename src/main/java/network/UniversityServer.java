package network;

import data.DataService;
import domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.*;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class UniversityServer {

    private static final Logger logger = LoggerFactory.getLogger(UniversityServer.class);

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Сервер NAUKMA запущено.");
            logger.info("Сервер запущено успішно.");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                     ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

                    // отримуємо команду від клієнта
                    String request = (String) in.readObject();

                    // обробка через існуючі сервіси
                    if (request.equals("STUDENT_getAll")) {
                        DataService.loadData();
                        List<Student> students = StudentService.getAll();
                        out.writeObject(students); // Відправка даних
                    } else if (request.equals("TEACHER_getAll")){
                        DataService.loadData();
                        List<Teacher> teachers = TeacherService.getAll();
                        out.writeObject(teachers);
                    }
                    logger.info("Запит {} успішно оброблено", request);
                } catch (Exception e){
                    logger.error("Помилка при обробці запиту від клієнта: {}", e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.error("Помилка при запуску сервера: {}", e.getMessage());
        }
    }

}
