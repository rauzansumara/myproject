package com.example.qr_code.Database;

public class Assets {
	
	// the class is defined for temporarily storing Assets data in the data base.
	/// <summary>
	/// the class consists of five attributes.
	/// </summary>

    String id;
    String name;
    String description;
    String amount;
    String location;

    public Assets() {
    }
	
	// Assets() is a constructor defined to initialize the objects 'id','name','description','amount' and 'location'.
    public Assets(String id, String name, String description, String amount, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.location = location;
    }

	// getId() is a user defined function to get the value of object 'id'.
    public String getId() {
        return id;
    }

	// getName() is a user defined function to get the value of object 'name'.
    public String getName() {
        return name;
    }

	// getDescription() is a user defined function to get the value of object 'description'.
    public String getDescription() {
        return description;
    }
	
	// getAmount() is a user defined function to get the value of object 'amount'.
    public String getAmount() {
        return amount;
    }

	// getLocation() is a user defined function to get the value of object 'location'.
    public String getLocation() {
        return location;
    }

	// setId() is a user defined function to get the value of object 'id'.
    public void setId(String id) {
        this.id = id;
    }

	// setName() is a user defined function to get the value of object 'name'.
    public void setName(String name) {
        this.name = name;
    }

	// setDescription() is a user defined function to get the value of object 'description'.
    public void setDescription(String description) {
        this.description = description;
    }

	// setAmount() is a user defined function to get the value of object 'amount'.
    public void setAmount(String amount) {
        this.amount = amount;
    }

	// setLocation() is a user defined function to get the value of object 'location'.
    public void setLocation(String location) {
        this.location = location;
    }
}
