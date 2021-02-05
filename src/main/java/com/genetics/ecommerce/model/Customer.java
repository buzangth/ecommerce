package com.genetics.ecommerce.model;

import org.springframework.data.annotation.Id;

public class Customer  {

    public enum Role{ADMIN,USER;}

    @Id
    private String id;

    private String firstName,LastName;
    private Role role;

    public Role getRole() {
        return role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
