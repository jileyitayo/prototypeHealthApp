package com.example.jil.myproject_prototype2sqlite;

/**
 * Created by JIL on 02/02/16.
 */
public class user {

    public user(long id, String first_Name, String username, String password, String email)
    {
        getID(id);
        getFirst_Name(first_Name);
        getUsername(username);
        getPassword(password);
        getEmail(email);
    }

    private long id;
    private String first_Name, last_Name, username, email, password;

    public long getID(long id)
    {
        this.id = id;
        return this.id;
    }

    public String getFirst_Name(String first_Name)
    {
        this.first_Name = first_Name;
        return this.first_Name;
    }

    public String getLast_Name(String last_Name)
    {
        this.last_Name = last_Name;
        return this.last_Name;
    }

    public String getUsername(String username)
    {
        this.username = username;
        return this.username;
    }
    public String getEmail(String email)
    {
        this.email = email;
        return this.email;
    }

    public String getPassword(String password)
    {
        this.password = password;
        return this.password;
    }
}
