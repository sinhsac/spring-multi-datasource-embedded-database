package xxx.yyy.zzz.config.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MySqlConfig {

   // @Bean("mysqlDataSource")
    public DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/trackandtrace?useSSL=false&useUnicode=true&characterEncoding=utf-8");
        return new HikariDataSource(hikariConfig);
    }
}