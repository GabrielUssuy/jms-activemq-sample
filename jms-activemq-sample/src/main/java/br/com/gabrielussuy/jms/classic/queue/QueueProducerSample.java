package br.com.gabrielussuy.jms.classic.queue;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueProducerSample {

    private static final String QUEUE_NAME = "financeiro";
    private static final String MESSAGE = "<pedido><id>123</id></pedido>";

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
