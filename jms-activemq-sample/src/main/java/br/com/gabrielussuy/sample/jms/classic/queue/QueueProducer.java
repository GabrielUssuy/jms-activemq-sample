package br.com.gabrielussuy.sample.jms.classic.queue;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueProducer {

    private static final String QUEUE_NAME = "financial";
    private static final String MESSAGE = "<order><id>123</id></order>";

    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = (Destination) context.lookup(QUEUE_NAME);

        MessageProducer producer = session.createProducer(queue);
        Message message = session.createTextMessage(MESSAGE);

        producer.send(message);
    }
}
