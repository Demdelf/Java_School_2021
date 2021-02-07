package shop.util;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageSender {

    private final JmsTemplate jmsTemplate;

    public void sendMessage() {

        jmsTemplate.send(new MessageCreator(){
            @Override
            public Message createMessage(Session session) throws JMSException{
                Message message = session.createTextMessage("!");
                return message;
            }
        });
    }
}
