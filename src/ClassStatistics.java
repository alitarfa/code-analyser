import Types.ClassVisitors;
import Types.MethodVisitors;
import Types.PackageVisitors;
import Types.VariableVisitors;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class ClassStatistics {

    public static Long classCounter(List<File> projectFiles) {
        ClassVisitors classVisitors = new ClassVisitors();
        projectFiles.forEach(file -> {
            try {
                String content = FileHandler.read(file.getAbsolutePath());
                CompilationUnit result = ParserFactory.getInstance(content);
                result.accept(classVisitors);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return classVisitors
                .getClasses()
                .stream()
                .filter(typeDeclaration -> !typeDeclaration.isInterface())
                .count();
    }

    public static int methodCounter(List<File> projectFiles) {
        MethodVisitors methodVisitors = new MethodVisitors();
        projectFiles.forEach(file -> {
            try {
                String content = FileHandler.read(file.getAbsolutePath());
                CompilationUnit result = ParserFactory.getInstance(content);
                result.accept(methodVisitors);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return methodVisitors.getMethods()
                .size();
    }

    public static int attributeCounter(List<File> projectFiles) {
        VariableVisitors variableVisitors = new VariableVisitors();
        projectFiles.forEach(file -> {
            try {
                String content = FileHandler.read(file.getAbsolutePath());
                CompilationUnit result = ParserFactory.getInstance(content);
                result.accept(variableVisitors);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        return variableVisitors.getFields()
                .size();
    }

    public static int packageCounter(List<File> projectFiles) {
        PackageVisitors packageVisitors = new PackageVisitors();
        projectFiles.forEach(file -> {
            try {
                String content = FileHandler.read(file.getAbsolutePath());
                CompilationUnit result = ParserFactory.getInstance(content);
                result.accept(packageVisitors);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return packageVisitors.getPackages()
                .size();
    }

    public static Long lineCounter(List<File> projectFiles) {
        AtomicLong numberLines = new AtomicLong();
        projectFiles.forEach(file -> {
            try {
                String content = FileHandler.read(file.getAbsolutePath());
                numberLines.addAndGet(content.lines().count());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return numberLines.get();
    }
}
