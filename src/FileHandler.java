import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileHandler {

    public static String read(String filePath) throws Exception {
        Path path = Paths.get("/home/tarfa/MySpace/Tekit/soon-back/target/generated-sources/annotations/soon/io/soon/DTO/catergory/CategoryMapperImpl.java");
        try {
            return Files.readString(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new Exception("error.file.read.failed");
        }
    }

    public static List<File> readJavaFiles(File folder) {
        List<File> javaFiles = new ArrayList<>();
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isDirectory()) {
                javaFiles.addAll(readJavaFiles(file));
            } else if (file.getName().endsWith(".java")) {
                javaFiles.add(file);
            }
        }
        return javaFiles;
    }
}
