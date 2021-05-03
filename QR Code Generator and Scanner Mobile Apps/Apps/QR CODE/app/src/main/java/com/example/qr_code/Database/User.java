package com.example.qr_code.Database;

public class User {
	// the class is defined for temporarily storing User's data in the data base.
	/// <summary>
	/// the class consists of six attributes.
	/// </summary>
    String id;
    String name;
    String username;
    String password;
    String status;
    String date;

	// User() is a constructor defined to initialize the objects 'id','name','username','password','status', and 'date'.
    public User() {
    }

    public User(String id, String name, String username, String password, String status, String date) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.status = status;
        this.date = date;
    }

	// getId() is a user defined function to get the value of object 'id'.
    public String getId() {
        return id;
    }

	// getName() is a user defined function to get the value of object 'name'.
    public String getName(){
        return name;
    }

	// getUsername() is a user defined function to get the value of the object 'username'.
    public String getUsername() {
        return username;
    }

	// getPassword() is a user defined function to get the value of the object 'password'.
    public String getPassword() {
        return password;
    }

	// getStatus() is a user defined function to get the value of the object 'status'.
    public String getStatus() {
        return status;
    }

	// getDate() is a user defined function to get the value of the object 'date'.
    public String getDate() {
        return date;
    }

	// setId() is a user defined function to get the value of the object 'id'.
    public void setId(String id) {
        this.id = id;
    }

	// setName() is a user defined function to get the value of the object 'name'.
    public void setName(String name) {
        this.name = name;
    }

	// setUsername() is a user defined function to set the value of the object 'username'.
    public void setUsername(String username) {
        this.username = username;
    }

	// setPassword() is a user defined function to set the value of the object 'password'.
    public void setPassword(String password) {
        this.password = password;
    }

	// setStatus() is a user defined function to set the value of the object 'status'.
    public void setStatus(String status) {
        this.status = status;
    }

	// setDate() is a user defined function to get the value of the object 'date'.
    public void setDate(String date) {
        this.date = date;
    }

}
