import Types.ClassVisitors;
import org.eclipse.jdt.core.dom.*;


public class ASTMain {

    public static void main(String args[]) throws Exception {
        String content = FileHandler.read("main.java");
        String string = "public class Resaurant {\n" +
                "\n" +
                "private int id;\n" +
                "private String name;\n" +
                "private id owner;\n" +
                "\n" +
                "public void getName(){\n" +
                "isNull();"+
                "return this.name;\n" +
                "}\n" +
                "\n" +
                "public static void isNull() {\n" +
                "\n" +
                "}\n" +
                "\n" +
                "}";
        CompilationUnit instance = ParserFactory.getInstance(string);
        ClassVisitors classVisitors = new ClassVisitors();
        instance.accept(classVisitors);
        InfoHelper.show(classVisitors);
    }


}
