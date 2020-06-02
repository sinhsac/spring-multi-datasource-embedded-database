package xxx.yyy.zzz.config.derby;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DerbyConfig {

    @Bean("derbyDataSource")
    @Primary
    public DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setJdbcUrl("jdbc:derby:db/derby.dbfile;create=true");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate jdbc() {
        return new JdbcTemplate(getDataSource());
    }
}
