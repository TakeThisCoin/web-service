package migrations;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.DirectoryResourceAccessor;
import lombok.SneakyThrows;

import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class IntegrationEnvironment extends AbstractPostgreSqlContainer{

    static {
        init();
    }

    @SneakyThrows
    private static void init(){
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation((new JdbcConnection(getConnection())));
        Liquibase liquibase = new liquibase.Liquibase("master.xml", new DirectoryResourceAccessor(getMigrationsPath()), database);
        liquibase.update();
    }

    @SneakyThrows
    public static Connection getConnection() {
        return DriverManager.getConnection(
                postgreSQLContainer.getJdbcUrl(),
                postgreSQLContainer.getUsername(),
                postgreSQLContainer.getPassword()
        );
    }


    private static Path getMigrationsPath(){
        return new File(".").toPath().toAbsolutePath().getParent().resolve("migrations");
    }
}
