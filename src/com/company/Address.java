package com.company;

import java.util.UUID;

public class Address {
    private String name;
    private String street;
    private String zipCode;
    private String additionalAddressLine;
    private String region;

    public Address(String name, String street, String zipCode, String additionalAddressLine, String region) {
        this.name = name;
        this.street = street;
        this.zipCode = zipCode;
        this.additionalAddressLine = additionalAddressLine;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAdditionalAddressLine() {
        return additionalAddressLine;
    }

    public void setAdditionalAddressLine(String additionalAddressLine) {
        this.additionalAddressLine = additionalAddressLine;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
