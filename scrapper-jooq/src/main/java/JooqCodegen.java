import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

public class JooqCodegen {
    public static void main(String[] args) throws Exception {
        Jdbc jdbc = new Jdbc()
                .withDriver("org.postgresql.Driver")
                .withUrl("jdbc:postgresql://localhost:5051/scrapper")
                .withUser("postgres")
                .withPassword("qwer123");

        Generate options = new Generate()
                .withGeneratedAnnotation(true)
                .withGeneratedAnnotationDate(false)
                .withNullableAnnotation(true)
                .withNullableAnnotationType("org.jetbrains.annotations.Nullable")
                .withNonnullAnnotation(true)
                .withNonnullAnnotationType("org.jetbrains.annotations.NotNull")
                .withJpaAnnotations(false)
                .withValidationAnnotations(true)
                .withSpringAnnotations(true)
                .withConstructorPropertiesAnnotation(true)
                .withConstructorPropertiesAnnotationOnPojos(true)
                .withConstructorPropertiesAnnotationOnRecords(true)
                .withFluentSetters(false)
                .withDaos(false)
                .withPojos(true);

        Target target = new Target()
                .withPackageName("ru.tinkoff.edu.java.scrapper.service.impl.jooq.codegen")
                .withDirectory("scrapper/src/main/java");

        Configuration configuration = new Configuration()
                .withGenerator(
                        new Generator()
                                .withGenerate(options)
                                .withTarget(target)
                )
                .withJdbc(jdbc);

        GenerationTool.generate(configuration);
    }
}