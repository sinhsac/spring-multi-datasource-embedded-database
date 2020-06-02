package xxx.yyy.zzz.config.hsqldb;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class HsqldbConfig {

//    @Bean("hsqldbDataSource")
    public DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setJdbcUrl("jdbc:hsqldb:file:db/hsqldb.db/foo-test");
        return new HikariDataSource(hikariConfig);
    }


}
