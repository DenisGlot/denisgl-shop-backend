package com.denisgl.dto;

public interface IUserDetail {

    int getId();

    String getFirstName();

    String getLastName();

    String getRole();

    boolean isEnabled();

    String getPassword();

    String getEmail();

    String getContactNumber();
}
