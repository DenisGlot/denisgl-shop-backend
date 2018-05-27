package com.denisgl.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public interface ICategory {
    @Id
    @SequenceGenerator(name="nextIdCategory",sequenceName="category_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="nextIdCategory")
    int getId();

    @Column(name = "name")
    String getName();

    @Column(name = "description")
    String getDescription();

    @Column(name = "image_url")
    String getImageURL();

    @Column(name = "active")
    boolean isActive();
}
