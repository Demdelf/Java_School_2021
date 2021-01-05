package shop.config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DBconfig {

//    @Bean
//    public EntityManagerFactory getEntityManagerFactory(){
//        return Persistence.createEntityManagerFactory("shop-persistence-unit");
//    }
//
//    @Bean
//    public EntityManager getEntityManager(){
//        return getEntityManagerFactory().createEntityManager();
//    }
//
//    @Bean
//    public PlatformTransactionManager platformTransactionManager(){
//        return new JpaTransactionManager();
//    }

    @Bean
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("shop-persistence-unit");
        factoryBean.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        return factoryBean;
    }

    @Bean
    public EntityManager entityManager(LocalContainerEntityManagerFactoryBean entityManagerFactory){
        return getEntityManagerFactory().getObject().createEntityManager();
    }

    @Bean
    public JpaTransactionManager jpaTransactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());
        return transactionManager;
    }
}
