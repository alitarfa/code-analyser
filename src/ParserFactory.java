import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.util.Map;

public class ParserFactory {

    public static CompilationUnit getInstance(String content) {
        ASTParser parser = ASTParser.newParser(AST.JLS10);
        parser.setSource(content.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setResolveBindings(true);
        parser.setBindingsRecovery(true);
        Map options = JavaCore.getOptions();
        parser.setCompilerOptions(options);
        return (CompilationUnit) parser.createAST(null);
    }
}
