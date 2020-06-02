package xxx.yyy.zzz.config.h2;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class H2Config {

    @Bean("h2DataSource")
    //@Primary
    public DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setJdbcUrl("jdbc:h2:file:./db/h2db/db;MV_STORE=false");
        return new HikariDataSource(hikariConfig);
    }

    @Bean("h2JdbcTemplate")
    public JdbcTemplate jdbc() {
        return new JdbcTemplate(getDataSource());
    }
}
