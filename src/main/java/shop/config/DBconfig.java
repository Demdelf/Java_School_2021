package shop.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "shop")
//@EnableTransactionManagement(proxyTargetClass = true)
public class DBconfig {

    @Bean
    public EntityManagerFactory getEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("shop");
    }

    @Bean
    public EntityManager getEntityManager(){
        return getEntityManagerFactory().createEntityManager();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new JpaTransactionManager();
    }
}
