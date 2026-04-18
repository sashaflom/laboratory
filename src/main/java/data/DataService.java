package data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataService {

    private final static Path in = Path.of("src", "main", "java", "data", "uniData.json");
    private static ObjectMapper mapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(DataService.class);

    private static void mapperSetUp(){
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static synchronized void saveData(){
        mapperSetUp();
        UniversityData data = new UniversityData();
        data.collectData();
        try {
            try (BufferedWriter writer = Files.newBufferedWriter(in)) {
                mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
            }
            logger.info("Запис в файл {} успішний.", in);
        } catch (Exception e) {
            logger.error("Помилка при записі у файл {}: {}.", in, e.getMessage());
            System.out.println("\n[AUTO-SAVE] Помилка при збереженні даних.");
            e.printStackTrace();
        }
    }

    public static synchronized void loadData(){
        mapperSetUp();
        UniversityData data = new UniversityData();
        if (Files.notExists(in)) {
            System.out.println("\nЗбережених даних немає.");
        } else{
            try (BufferedReader reader = Files.newBufferedReader(in)) {
                data = mapper.readValue(reader, UniversityData.class);
                data.putData();
                System.out.println("\nДані завантажено з файлу: " + in.toAbsolutePath());
                logger.info("Читання з файлу {} успішне.", in);
            } catch (Exception e) {
                logger.error("Помилка при читанні з файлу {}: {}.", in, e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
