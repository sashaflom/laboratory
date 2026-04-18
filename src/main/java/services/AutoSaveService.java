package services;

import data.DataService;

import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoSaveService {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final static Path in = Path.of("src", "main", "java", "data", "uniData.json");
    private static final Logger logger = LoggerFactory.getLogger(AutoSaveService.class);

    public void start() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                DataService.saveData();
                logger.info("Автозбереження в файл {} успішнe.", in);
            } catch (Exception e) {
                logger.error("Помилка автозбереження у файл {}: {}.", in, e.getMessage());
                System.out.println("\n[AUTO-SAVE] Помилка у фоновому потоці автозбереження.");
                e.printStackTrace();
            }
        }, 5, 15, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(3, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
            logger.info("Закриття автозбереження успішне.");
        } catch (InterruptedException e) {
            logger.error("Помилка закриття автозбереження: {}", e.getMessage());
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}