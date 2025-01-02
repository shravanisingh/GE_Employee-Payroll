import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String directoryPath = "testDir";
        String filePath = directoryPath + "/testFile.txt";
        String extension = ".txt";

        // 1. Check if File Exists
        checkFileExists(filePath);

        // 2. Create a Directory
        createDirectory(directoryPath);

        // 3. Create an Empty File
        createEmptyFile(filePath);

        // 4. Check if File Exists Again
        checkFileExists(filePath);

        // 5. List Files, Directories, and Files with Specific Extension
        listFilesInDirectory(directoryPath, extension);

        // 6. Delete File
        deleteFile(filePath);

        // 7. Check File Not Exist
        checkFileExists(filePath);
    }

    // Method to check if a file exists
    private static void checkFileExists(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("File exists: " + filePath);
        } else {
            System.out.println("File does not exist: " + filePath);
        }
    }

    // Method to create a directory
    private static void createDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdir()) {
                System.out.println("Directory created: " + directoryPath);
            } else {
                System.out.println("Failed to create directory: " + directoryPath);
            }
        } else {
            System.out.println("Directory already exists: " + directoryPath);
        }
    }

    // Method to create an empty file
    private static void createEmptyFile(String filePath) {
        File file = new File(filePath);
        try {
            if (file.createNewFile()) {
                System.out.println("Empty file created: " + filePath);
            } else {
                System.out.println("File already exists: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    // Method to delete a file
    private static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted: " + filePath);
            } else {
                System.out.println("Failed to delete file: " + filePath);
            }
        } else {
            System.out.println("File does not exist: " + filePath);
        }
    }

    // Method to list files, directories, and files with specific extension
    private static void listFilesInDirectory(String directoryPath, String extension) {
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            System.out.println("\nContents of directory: " + directoryPath);

            // List all files and directories
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.println("Directory: " + file.getName());
                    } else {
                        System.out.println("File: " + file.getName());
                    }
                }

                // List files with specific extension
                System.out.println("\nFiles with extension " + extension + ":");
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(extension)) {
                        System.out.println(file.getName());
                    }
                }
            } else {
                System.out.println("Directory is empty.");
            }
        } else {
            System.out.println("Directory does not exist: " + directoryPath);
        }
    }
}