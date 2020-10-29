import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {

    public static String read(String fileName) throws Exception {
        String filePath = "/home/tarfa/projects/" + fileName;
        Path path = Paths.get(filePath);
        try {
            return Files.readAllLines(path).get(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new Exception("error.file.read.failed");
        }
    }
}
