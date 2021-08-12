package br.com.gabrielussuy.jms.classic.queue;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Enumeration;

public class QueueBrowserSample {

    private static final String QUEUE_NAME = "financeiro";

    public static void main(String[] args) throws NamingException, JMSException {
        InitialContext context = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination fila = (Destination) context.lookup(QUEUE_NAME);
        QueueBrowser browser = session.createBrowser((Queue) fila);

        Enumeration msgs = browser.getEnumeration();
        while (msgs.hasMoreElements()) {
            TextMessage msg = (TextMessage) msgs.nextElement();
            System.out.println("Message: " + msg.getText());
        }
    }
}
