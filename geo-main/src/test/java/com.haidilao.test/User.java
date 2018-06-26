package com.icongtai.test;

import java.util.List;

/**
 * Author: DELL
 * Date: 2018/5/23 14:17
 * Description: ...
 */
public class User {

    private String name;
    private int age;
    private List<Address> addressList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
