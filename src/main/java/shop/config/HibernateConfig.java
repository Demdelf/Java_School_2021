package shop.config;

//@Configuration
//@EnableTransactionManagement
//@ComponentScans(value = { @ComponentScan("shop")})
public class HibernateConfig {

//    @Autowired
//    private ApplicationContext context;
//
//    @Bean
//    public LocalSessionFactoryBean getSessionFactory() {
//        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//        factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
//        factoryBean.setAnnotatedClasses(Customer.class);
//        return factoryBean;
//    }
//
//    @Bean
//    public HibernateTransactionManager getTransactionManager() {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(getSessionFactory().getObject());
//        return transactionManager;
//    }
}
