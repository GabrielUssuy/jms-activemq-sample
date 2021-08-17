package br.com.gabrielussuy.jms.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

//JAX-B
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private LocalDate orderDate;
    private LocalDate payDay;
    private BigDecimal amount;

    @XmlElementWrapper(name = "itens")
    @XmlElement(name = "item")
    private Set<Item> itens = new LinkedHashSet<>();


    Order() {
    }

    public Order(Integer id, LocalDate orderDate, LocalDate payDay, BigDecimal amount) {
        this.id = id;
        this.orderDate = orderDate;
        this.payDay = payDay;
        this.amount = amount;
    }

    public void addItem(Item item) {
        this.itens.add(item);
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getPayDay() {
        return payDay;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Set<Item> getItens() {
        return itens;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Order [id=%s, orderDate=%s, payDay=%s, amount=%s, itens=%s",
                id, orderDate.toString(), payDay.toString(), amount, itens);
    }


}
