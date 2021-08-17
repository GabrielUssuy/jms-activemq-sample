package br.com.gabrielussuy.jms.classic.topic;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TopicProducerSample {

    private static final String TOPIC_NAME = "store";
    private static final String MESSAGE = "<order><id>123</id></order>";

    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination topic = (Destination) context.lookup(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);

        Message message = session.createTextMessage(MESSAGE);
        message.setBooleanProperty("ebook", false);
        producer.send(message);

        session.close();
        connection.close();
        context.close();
    }
}
