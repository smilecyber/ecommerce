package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {
    private UUID id;
    private String userName;
    private String email;
    private List<Address> addressList;

    public Customer(UUID id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public Customer(UUID id, String userName, String email, List<Address> addressList) {
        this(id, userName, email);
        this.addressList = addressList;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Address> getAddressList() {
        if (addressList == null){
            return new ArrayList<>();
        }
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
