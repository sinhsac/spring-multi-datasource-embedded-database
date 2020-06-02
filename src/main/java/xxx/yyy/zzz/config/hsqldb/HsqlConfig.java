package xxx.yyy.zzz.config.hsqldb;

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
        basePackages = "xxx.yyy.zzz.components.hsql",
        entityManagerFactoryRef = "getHSQLLocalContainerEntityManagerFactoryBean",
        transactionManagerRef = "getHSQLPlatformTransactionManager"
)
public class HsqlConfig {

    @Bean
    public DataSource getHSQLDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setJdbcUrl("jdbc:hsqldb:file:db/hsqldb.db/foo-test");
        return new HikariDataSource(hikariConfig);
    }
    //hsqldb.default_table_type=cached

    @Bean
    public JdbcTemplate getHSQLJdbcTemplate() {
        return new JdbcTemplate(getHSQLDataSource());
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getHSQLLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getHSQLDataSource());
        em.setPackagesToScan("xxx.yyy.zzz.components.hsql");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager getHSQLPlatformTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getHSQLLocalContainerEntityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
