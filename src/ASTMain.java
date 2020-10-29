import Types.ClassVisitors;

import java.io.File;
import java.util.List;


public class ASTMain {

    public static void main(String args[]) throws Exception {

        List<File> projectFiles = FileHandler.readJavaFiles(new File("/home/tarfa/MySpace/Tekit"));
        Long classes = ClassStatistics.classCounter(projectFiles);
        int methods = ClassStatistics.methodCounter(projectFiles);
        int attributes = ClassStatistics.attributeCounter(projectFiles);
        System.out.println(classes);
        System.out.println(methods);
        System.out.println(attributes);
    }


}
