import java.io.File;
import java.util.List;


public class ASTMain {

    public static void main(String args[]) throws Exception {

        List<File> projectFiles = FileHandler.readJavaFiles(new File("/home/tarfa/MySpace/Tekit"));
        int packages = ClassStatistics.packageCounter(projectFiles);
        Long classes = ClassStatistics.classCounter(projectFiles);
        int methods = ClassStatistics.methodCounter(projectFiles);
        int attributes = ClassStatistics.attributeCounter(projectFiles);
        Long lines = ClassStatistics.lineCounter(projectFiles);
        System.out.println("Package Count : " + packages);
        System.out.println("Class Count : " + classes);
        System.out.println("Methods Count : " + methods);
        System.out.println("Attribute Count : " + attributes);
        System.out.println("Line Count : " + lines);
    }

}
