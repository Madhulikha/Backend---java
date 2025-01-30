package com.weclear.backend.model;

import jakarta.persistence.*;

// WorkSunCategory is 4 types

// group column is used for grouping common works. ex: 1 bathroom cleaning, 2 bathroom cleaning come under bathroom group

@Entity
@Table(name = "work")
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category.WorkCategory workCategory;

    @Enumerated(EnumType.STRING)
    private Category.WorkSubCategory workSubCategory;

    @Column(name = "`group`") // Escaping the reserved word
    private String group;

    private String description;

    private String duration;

    private Double price;

    private String imageUrl;

    private Boolean showQuantity;



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

    public Category.WorkCategory getWorkCategory() {
        return workCategory;
    }

    public void setWorkCategory(Category.WorkCategory workCategory) {
        this.workCategory = workCategory;
    }

    public Category.WorkSubCategory getWorkSubCategory() {
        return workSubCategory;
    }

    public void setWorkSubCategory(Category.WorkSubCategory workSubCategory) {
        this.workSubCategory = workSubCategory;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getShowQuantity() {
        return showQuantity;
    }

    public void setShowQuantity(Boolean showQuantity) {
        this.showQuantity = showQuantity;
    }

}
