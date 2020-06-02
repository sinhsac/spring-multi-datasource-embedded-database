package xxx.yyy.zzz.config.derby;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(basePackages = "xxx.yyy.zzz.components.derby", entityManagerFactoryRef = "getDerbyEntityManagerFactory", transactionManagerRef = "getDerbyTransactionManager")
public class DerbyConfig {

    @Bean
    @Primary
    public DataSource getDerbyDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setJdbcUrl("jdbc:derby:db/derby.dbfile;create=true");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate getDerbyJdbcTemplate() {
        return new JdbcTemplate(getDerbyDataSource());
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean getDerbyEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDerbyDataSource());
        em.setPackagesToScan("xxx.yyy.zzz.components.derby");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("hibernate.dialect", "org.hibernate.dialect.DerbyTenSevenDialect");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    @Primary
    @Autowired
    public JpaTransactionManager getDerbyTransactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
