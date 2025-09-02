package framework.processor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class ControllerImplementationProcessor extends AbstractProcessor {
    private static final String CONTROLLER_INTERFACE = "framework.router.Controller";
    private final Set<String> implementations = new HashSet<>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver()) {
            writeImplementations();
            return false;
        }
        for (Element root : roundEnv.getRootElements()) {
            if (root.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) root;
                for (TypeMirror iface : typeElement.getInterfaces()) {
                    if (iface.toString().equals(CONTROLLER_INTERFACE)) {
                        implementations.add(typeElement.getQualifiedName().toString());
                    }
                }
            }
        }
        return false;
    }

    private void writeImplementations() {
        if (implementations.isEmpty()) return;
        try {
            FileObject file = processingEnv.getFiler().createResource(
                StandardLocation.CLASS_OUTPUT,
                "",
                "META-INF/controller-implementations.txt"
            );
            try (Writer writer = file.openWriter()) {
                for (String impl : implementations) {
                    writer.write(impl + "\n");
                }
            }
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Failed to write controller implementations: " + e.getMessage());
        }
    }
}

