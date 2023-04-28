package com.averywanda.beverageapi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "beveragetypes")
public class BeverageType {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @OneToMany(mappedBy = "beverageType", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Beverage> beverageList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public BeverageType() {
    }

    public BeverageType(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public List<Beverage> getBeverageList() {
        return beverageList;
    }

    public void setBeverageList(List<Beverage> beverageList) {
        this.beverageList = beverageList;
    }

    @Override
    public String toString() {
        return "BeverageType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beverageList=" + beverageList +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
