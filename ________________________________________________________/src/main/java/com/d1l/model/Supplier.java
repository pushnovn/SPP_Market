package com.d1l.model;

import javax.persistence.*;

@Entity
@Table(name = "marketservice.supplier")
public class Supplier {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "company_name")
    private String companyName;
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
