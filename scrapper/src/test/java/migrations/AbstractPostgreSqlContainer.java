package migrations;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractPostgreSqlContainer {

    public static final PostgreSQLContainer postgreSQLContainer;

    static {
        postgreSQLContainer = new PostgreSQLContainer("postgres:15")
                .withDatabaseName("scrapper")
                .withUsername("postgres")
                .withPassword("qwer123");
        postgreSQLContainer.start();
    }
}
