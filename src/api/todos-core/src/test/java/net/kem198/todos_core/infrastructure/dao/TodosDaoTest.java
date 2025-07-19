package net.kem198.todos_core.infrastructure.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import org.seasar.doma.jdbc.NoCacheSqlFileRepository;
import org.seasar.doma.jdbc.SqlFile;
import org.seasar.doma.jdbc.SqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;

/**
 * 
 */
public class TodosDaoTest {

    /** */
    protected SqlFileRepository repository;

    /** */
    protected Dialect dialect;

    /** */
    protected Driver driver;

    /** */
    protected String url;

    /** */
    protected String user;

    /** */
    protected String password;

    @BeforeEach
    protected void setUp() throws Exception {
        repository = new NoCacheSqlFileRepository();
        dialect = new org.seasar.doma.jdbc.dialect.PostgresDialect();
        url = "jdbc:postgresql://localhost:5432/todos_db";
        user = "postgres";
        password = "__ENTER_POSTGRES_PASSWORD_HERE__";
    }

    /**
     * 
     * @param sqlFile
     * @throws Exception
     */
    protected void execute(SqlFile sqlFile) throws Exception {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            try {
                statement.execute(sqlFile.getSql());
            } finally {
                statement.close();
            }
        } finally {
            try {
                connection.rollback();
            } finally {
                connection.close();
            }
        }
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    protected Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testCountByFinished(TestInfo testInfo) throws Exception {
        SqlFile sqlFile = repository.getSqlFile(testInfo.getTestMethod().get(), "META-INF/net/kem198/todos_core/infrastructure/dao/TodosDao/countByFinished.sql", dialect);
        execute(sqlFile);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testSelectAll(TestInfo testInfo) throws Exception {
        SqlFile sqlFile = repository.getSqlFile(testInfo.getTestMethod().get(), "META-INF/net/kem198/todos_core/infrastructure/dao/TodosDao/selectAll.sql", dialect);
        execute(sqlFile);
    }

    /**
     * 
     * @throws Exception
     */
    @Test
    public void testSelectById(TestInfo testInfo) throws Exception {
        SqlFile sqlFile = repository.getSqlFile(testInfo.getTestMethod().get(), "META-INF/net/kem198/todos_core/infrastructure/dao/TodosDao/selectById.sql", dialect);
        execute(sqlFile);
    }

}
