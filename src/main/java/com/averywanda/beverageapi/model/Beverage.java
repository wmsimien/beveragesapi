package com.averywanda.beverageapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "beverages")
public class Beverage {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String pairing;
    @Column
    private String goodToKnow;
    @Column
    private String proTip;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "beverageType_id")
    private BeverageType beverageType;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    public Beverage() {
    }

    public Beverage(Long id, String name, String description, String pairing, String goodToKnow, String proTip, BeverageType beverageType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pairing = pairing;
        this.goodToKnow = goodToKnow;
        this.proTip = proTip;
        this.beverageType = beverageType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPairing() {
        return pairing;
    }

    public void setPairing(String pairing) {
        this.pairing = pairing;
    }

    public String getGoodToKnow() {
        return goodToKnow;
    }

    public void setGoodToKnow(String goodToKnow) {
        this.goodToKnow = goodToKnow;
    }

    public String getProTip() {
        return proTip;
    }

    public void setProTip(String proTip) {
        this.proTip = proTip;
    }

    public BeverageType getBeverageType() {
        return beverageType;
    }

    public void setBeverageType(BeverageType beverageType) {
        this.beverageType = beverageType;
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pairing='" + pairing + '\'' +
                ", goodToKnow='" + goodToKnow + '\'' +
                ", proTip='" + proTip + '\'' +
                ", beverageType=" + beverageType +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
