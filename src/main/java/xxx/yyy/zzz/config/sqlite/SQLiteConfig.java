package xxx.yyy.zzz.config.sqlite;

import ch.qos.logback.core.db.dialect.SQLiteDialect;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "xxx.yyy.zzz.components.sqlite",
        entityManagerFactoryRef = "getSqliteLocalContainerEntityManagerFactoryBean",
        transactionManagerRef = "getSqlitePlatformTransactionManager"
)
public class SQLiteConfig {

    @Bean
    public DataSource getSqliteDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setJdbcUrl("jdbc:sqlite:./db/sqlite.db");
        //hikariConfig.setJdbcUrl("jdbc:sqlite:memory:myDb?cache=shared&database=main");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate getSqliteJdbcTemplate() {
        return new JdbcTemplate(getSqliteDataSource());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getSqliteLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getSqliteDataSource());
        em.setPackagesToScan("xxx.yyy.zzz.components.sqlite");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.dialect", "xxx.yyy.zzz.helper.SQLiteDialect");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager getSqlitePlatformTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getSqliteLocalContainerEntityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
