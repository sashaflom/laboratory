package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataService {

    private final static Path in = Path.of("data", "uniData.json");

    public static void saveData(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        UniversityData data = new UniversityData();
        data.collectData();
        try {
            // Створюємо директорію "data", якщо її немає (фішка NIO.2)
            if (Files.notExists(in)) {
                Files.createDirectories(in);
            }

            // Відкриваємо BufferedWriter через Files (NIO.2)
            try (BufferedWriter writer = Files.newBufferedWriter(in)) {
                mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
            }
            System.out.println("Дані збережено за шляхом: " + in.toAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadData(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        UniversityData data = new UniversityData();

        if (Files.notExists(in)) {
            System.out.println("Збережених даних немає.");
        } else{
            try (BufferedReader reader = Files.newBufferedReader(in)) {
                data = mapper.readValue(reader, UniversityData.class);
                data.putData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
