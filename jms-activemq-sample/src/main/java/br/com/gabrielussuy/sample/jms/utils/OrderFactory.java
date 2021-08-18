package br.com.gabrielussuy.sample.jms.utils;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderFactory {

    public static Order createOrderWithValues() {
        Order order = new Order(2459, LocalDate.of(2021, 8, 16), LocalDate.of(2021, 8, 16), new BigDecimal("145.98"));

        Item motoG = createItem(23, "Moto G");
        Item motoX = createItem(51, "Moto X");

        order.addItem(motoX);
        order.addItem(motoG);

        return order;
    }

    private static Item createItem(int id, String nome) {
        return new Item(id, nome);
    }

}
