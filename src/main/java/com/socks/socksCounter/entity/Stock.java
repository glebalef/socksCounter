package com.socks.socksCounter.entity;


import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Socks socks;
    private Integer quantity;

    public Stock() {
    }

    public Stock(Socks socks, Integer quantity) {
        this.socks = socks;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Socks getSocks() {
        return socks;
    }

    public void setSocks(Socks socks) {
        this.socks = socks;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Stock(Integer id) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return socks.equals(stock.socks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socks);
    }
}
