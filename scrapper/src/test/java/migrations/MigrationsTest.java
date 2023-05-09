package migrations;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class MigrationsTest extends IntegrationEnvironment{

    @Test
    @SneakyThrows
    public void tablesIsCreatable() {
        Connection dbConnection = getConnection();
        Statement statement = dbConnection.createStatement();
        String sql = "SELECT table_name" +
                    " FROM information_schema.tables" +
                    " WHERE table_schema='public'" +
                    " AND table_type='BASE TABLE';";
        ResultSet resultSet = statement.executeQuery(sql);

        List<String> tableNames = new ArrayList<>();

        while (resultSet.next()) {
            tableNames.add(resultSet.getString("table_name"));
        }

        List<String> expected = List.of(
                "databasechangeloglock",
                "databasechangelog",
                "chat",
                "chat_link",
                "link"
        );

        System.out.println("tableNames: " + Arrays.toString(tableNames.toArray()));

        assertIterableEquals(expected, tableNames);
    }
}
