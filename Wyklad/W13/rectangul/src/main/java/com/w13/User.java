package com.w13;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private final String USERNAME_PROP_NAME = "username";
    private ReadOnlyStringWrapper userName;
    private final String PASSWORD_PROP_NAME = "password";
    private StringProperty password;

    public User(String userName, String password){
        this.userName = new ReadOnlyStringWrapper(
            this, USERNAME_PROP_NAME, userName
        );
        this.password = new SimpleStringProperty(
            this, PASSWORD_PROP_NAME, password
        );
    }

    public ReadOnlyStringWrapper userNameProperty(){
        return userName;
    }
    public String getUserName(){
        return userName.get();
    }
    public StringProperty passwordProperty(){
        return password;
    }
    public String getPassword(){
        return password.get();
    }
}
