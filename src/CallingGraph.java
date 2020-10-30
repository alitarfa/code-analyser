import Types.ClassVisitors;
import Types.MethodInvocationVisitors;
import Types.MethodVisitors;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.SimpleName;

import java.util.*;

public class CallingGraph {

    private Set<Map.Entry<String, String>> entries = new HashSet<>();
    private Map<String, String> map = new HashMap<>();
    private Set<String> nodes = new TreeSet<>();

    public Set<Map.Entry<String, String>> generateGraph(ClassVisitors classVisitors) {
        classVisitors.getClasses()
                .forEach(typeDeclaration -> {
                    MethodVisitors methodVisitors = new MethodVisitors();
                    typeDeclaration.accept(methodVisitors);
                    methodVisitors.getMethods()
                            .forEach(methodDeclaration -> {
                                SimpleName nameMethod = methodDeclaration.getName();
                                MethodInvocationVisitors methodInvocationVisitors = new MethodInvocationVisitors();
                                methodDeclaration.accept(methodInvocationVisitors);
                                methodInvocationVisitors.getMethods()
                                        .forEach(methodInvocation -> {
                                            Expression expr = methodInvocation.getExpression();
                                            String invoked = "";
                                            if (expr != null) {
                                                invoked = expr + "::" + methodInvocation.getName().toString();
                                            } else {
                                                invoked = typeDeclaration.getName() + "::" + methodInvocation.getName().toString();
                                            }

                                            entries.add(Map.entry(typeDeclaration.getName() + "::" + nameMethod.toString(),invoked));
                                        });
                            });

                });
        return entries;
    }
}
