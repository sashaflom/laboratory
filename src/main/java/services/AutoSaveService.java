package services;

import data.DataService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AutoSaveService {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public void start() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                DataService.saveData();
            } catch (Exception e) {
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
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}