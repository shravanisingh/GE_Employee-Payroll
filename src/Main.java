import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;

public class Main {

    public static void main(String[] args) {
        Path directoryToWatch = Paths.get("watchedDir"); // Specify the directory to watch

        // Ensure the directory exists
        File directory = new File("watchedDir");
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Directory created: " + directoryToWatch);
        }

        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            // Register the directory and its subdirectories with the WatchService
            registerDirectoryRecursively(directoryToWatch, watchService);

            System.out.println("Watching directory: " + directoryToWatch);

            // Monitor the directory for events
            while (true) {
                WatchKey watchKey = watchService.take();

                for (WatchEvent<?> event : watchKey.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    if (kind == OVERFLOW) {
                        continue;
                    }

                    // Retrieve the file path
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                    Path filePath = directoryToWatch.resolve(fileName);

                    System.out.println("Event detected: " + kind.name() + " on file: " + filePath);

                    // If a file is modified, count the lines
                    if (kind == ENTRY_MODIFY && Files.isRegularFile(filePath)) {
                        int lineCount = countLinesInFile(filePath.toFile());
                        System.out.println("File: " + filePath + " has " + lineCount + " lines.");
                    }
                }

                // Reset the key and exit if the directory is no longer accessible
                boolean valid = watchKey.reset();
                if (!valid) {
                    System.out.println("WatchKey is no longer valid. Exiting.");
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Method to recursively register a directory and its subdirectories
    private static void registerDirectoryRecursively(Path directory, WatchService watchService) throws IOException {
        Files.walk(directory)
                .filter(Files::isDirectory)
                .forEach(dir -> {
                    try {
                        dir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
                        System.out.println("Registered directory: " + dir);
                    } catch (IOException e) {
                        System.err.println("Failed to register directory: " + dir);
                    }
                });
    }

    // Method to count the number of lines in a file
    private static int countLinesInFile(File file) {
        int lineCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + file.getAbsolutePath());
        }

        return lineCount;
    }
}