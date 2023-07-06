package ir.comprehensive.database.test.utils;

import ir.comprehensive.database.factory.JooqFactory;
import ir.comprehensive.database.provider.mapper.BaseMapper;
import org.jooq.DSLContext;
import org.junit.jupiter.api.extension.*;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

public class DBExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {
    private final DSLContext dslContext = new JooqFactory().getBean();

    private Optional<String> readString(String resource) {
        try {
            return Optional.of(Files.readString(Path.of(Objects.requireNonNull(getClass().getResource(resource)).toURI())));
        } catch (IOException | URISyntaxException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(DSLContext.class) ||
                BaseMapper.class.isAssignableFrom(parameterContext.getParameter().getType());
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (parameterContext.getParameter().getType().equals(DSLContext.class))
            return this.dslContext;

        return Mappers.getMapper(parameterContext.getParameter().getType());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        readString("/schema-drop.sql").ifPresent(dslContext::execute);
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        readString("/schema-create.sql").ifPresent(dslContext::execute);

        var files = context.getTestMethod()
                .map(m -> m.getAnnotation(Sql.class))
                .map(Sql::value)
                .or(() -> context.getTestClass().map(c -> c.getAnnotation(Sql.class)).map(Sql::value));

        if (files.isPresent())
            for (String f : files.get())
                this.readString(f)
                        .ifPresent(dslContext::execute);

    }
}
