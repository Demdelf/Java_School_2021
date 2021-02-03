package shop.config;

import java.net.URI;
import java.util.Properties;
import javax.jms.Destination;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MessagingConfiguration {
    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
    private static final String TOP_PRODUCTS_QUEUE = "TopProductsQueue";

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(connectionFactory());
        template.setDefaultDestination(getDestination());
        return template;
    }

    @Bean
    public Destination getDestination(){
        final Properties env = new Properties();
        Context namingContext;
        Destination destination = null;
        env.put(
                Context.INITIAL_CONTEXT_FACTORY,
                "org.jboss.naming.remote.client.InitialContextFactory");
        env.put(Context.PROVIDER_URL, DEFAULT_BROKER_URL);
        env.put(Context.SECURITY_PRINCIPAL, "jmsuser");
        env.put(Context.SECURITY_CREDENTIALS, "Password1!");
        try {
            namingContext = new InitialContext(env);
            destination = (Destination) namingContext
                    .lookup(TOP_PRODUCTS_QUEUE);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return destination;
    }
}
