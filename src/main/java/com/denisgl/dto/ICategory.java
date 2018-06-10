package com.denisgl.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public interface ICategory {
    int getId();

    String getName();

    String getDescription();

    String getImageURL();

    boolean getActive();
}
