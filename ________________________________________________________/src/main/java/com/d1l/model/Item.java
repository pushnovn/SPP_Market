package com.d1l.model;

import javax.persistence.*;

@Entity
@Table(name = "marketservice.item")
public class Item {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "count_in_market")
    private int countInMarket;
    @ManyToOne(optional = false)
    @JoinColumn(name="category_id")
    private Category category;
    @ManyToOne(optional = false)
    @JoinColumn(name="market_id")
    private Market market;
    @ManyToOne(optional = false)
    @JoinColumn(name="supplier_id")
    private Supplier supplier;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCountInMarket() {
        return countInMarket;
    }

    public void setCountInMarket(int countInMarket) {
        this.countInMarket = countInMarket;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
