package vn.foo.core.spring.config.h2;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(basePackages = "vn.foo.core.spring.components.h2",
        entityManagerFactoryRef = "getH2LocalContainerEntityManagerFactoryBean",
        transactionManagerRef = "getH2PlatformTransactionManager"
)
public class H2Config {

    @Value("${h2.hibernate.hbm2ddl.auto:}")
    String hbm2ddlAuto;

    @Bean
    public DataSource getH2DataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("root");
        hikariConfig.setJdbcUrl("jdbc:h2:file:./db/h2db/db;MV_STORE=false");
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public JdbcTemplate getH2JdbcTemplate() {
        return new JdbcTemplate(getH2DataSource());
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean getH2LocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getH2DataSource());
        em.setPackagesToScan("vn.foo.core.spring.components.h2");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        if (!StringUtils.isEmpty(hbm2ddlAuto)) {
            properties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        }
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Bean
    public PlatformTransactionManager getH2PlatformTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getH2LocalContainerEntityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
