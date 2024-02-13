package app.shop.unit;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.UUID;

public interface WorkWithFile {
    static String generateNameFile (MultipartFile file) {
        return UUID.randomUUID().toString()+"."+file.getOriginalFilename().split("\\.")[1];
    }
    static  void createDirectory (String upload, String fileName, MultipartFile file) {
    String systemSeparator = FileSystems.getDefault().getSeparator();
        Path dirPath = Paths.get(upload.replace("/", systemSeparator));
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
            } catch (Error | IOException error) {
                throw new RuntimeException(error);
            }
        } else  {
            System.out.println("Catalog Already");
        }

        Path photo  = dirPath.resolve(fileName);
        try {
            file.transferTo(photo);
        } catch (Error | IOException e) {
            throw new RuntimeException(e);
        }
    }
    static void deleteDirectoryandContent(Path directory) throws IOException {
        Path currentDirectory =  Paths.get(System.getProperty("use.dir")).toAbsolutePath();
        Path targetdirectory = Paths.get(currentDirectory.toString(), String.valueOf(directory));
        if (Files.exists(directory) && Files.isDirectory(targetdirectory)) {
            Files.walk(targetdirectory)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
            System.out.println("Directory ["+targetdirectory+"]");
        } else {
            System.out.println("Directory ["+targetdirectory+"] is not found");
        }
    }
}
