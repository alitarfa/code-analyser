import Types.ClassVisitors;
import Types.MethodVisitors;
import Types.VariableVisitors;
import org.eclipse.jdt.core.dom.*;

public class InfoHelper {

    private static void className(ClassVisitors astVisitor) {

        astVisitor.getClasses()
                .forEach(typeDeclaration -> {
                    System.out.println("Class " + typeDeclaration.getName());
                    System.out.println("Parent Class" + typeDeclaration.getSuperclassType());
                });

        System.out.println("******* Methods *******");
        astVisitor.getClasses()
                .forEach(typeDeclaration -> {
                    MethodVisitors methodVisitors = new MethodVisitors();
                    typeDeclaration.accept(methodVisitors);
                    methodVisitors
                            .getMethods()
                            .forEach(methodDeclaration -> {
                                Type returnType = methodDeclaration.getReturnType2();
                                SimpleName name = methodDeclaration.getName();
                                System.out.println(returnType + " " + name);
                            });
                });

        System.out.println("******* Variables *******");
        astVisitor.getClasses()
                .forEach(typeDeclaration -> {
                    VariableVisitors variableVisitors = new VariableVisitors();
                    typeDeclaration.accept(variableVisitors);
                    variableVisitors
                            .getFields()
                            .stream()
                            .map(VariableDeclaration::getName)
                            .forEach(System.out::println);
                });
    }

    public static void show(ClassVisitors classVisitors) {
        className(classVisitors);
    }
}
