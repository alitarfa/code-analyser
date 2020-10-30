import Types.ClassVisitors;
import Types.MethodInvocationVisitors;
import Types.MethodVisitors;
import org.eclipse.jdt.core.dom.*;

import java.util.Map;

public class ASTMain2 {

    public static void main(String args[]) throws Exception {
        String string = "class AA {\n" +
                "\t\n" +
                "\tpublic BB atta1;\n" +
                "\tpublic CC atta2;\n" +
                "\tpublic int atta3;\n" +
                "\t\n" +
                "\tpublic void ma1() {\n" +
                "\t\tma2();\n" +
                "\t}\n" +
                "\t\n" +
                "\tpublic void ma2() {atta2.mc1();}\n" +
                "\tpublic void ma3() {atta1.mb2();}\n" +
                "}\n" +
                "\n" +
                "class BB {\n" +
                "\n" +
                "\tpublic AA attb1;\n" +
                "\tpublic CC attb2;\n" +
                "\t\n" +
                "\tpublic void mb1() {mb2();}\n" +
                "\t\n" +
                "\tpublic void mb2() {\n" +
                "\t\tattb2.mc2();\n" +
                "\t\tattb1.ma1();\n" +
                "\t}\n" +
                "\t\n" +
                "\tpublic void mb3() {attb1.ma3();}\n" +
                "}\n" +
                "\n" +
                "class CC {\n" +
                "\n" +
                "\tpublic void mc1() {\n" +
                "\t\tBB b = new BB();\n" +
                "\t\tb.mb1();\n" +
                "\t}\n" +
                "\n" +
                "\tpublic void mc2() {\n" +
                "\t\tSystem.out.println(\"fin\");\n" +
                "\t}\n" +
                "}";
        CompilationUnit instance = ParserFactory.getInstance(string);
        ClassVisitors classVisitors = new ClassVisitors();
        instance.accept(classVisitors);
        CallingGraph callingGraph = new CallingGraph();
        Map<String, String> stringStringMap = callingGraph.generateGraph(classVisitors);
        stringStringMap.forEach((s, s2) -> {
            System.out.println(s + " -----> " + s2);
        });
    }
}
