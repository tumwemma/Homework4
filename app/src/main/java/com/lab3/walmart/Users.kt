package com.lab3.walmart

import java.io.Serializable

public class Users: Serializable {
    lateinit var firstname: String
    lateinit var lastname: String
    lateinit var username: String
    lateinit var password: String


    constructor(firstname: String,lastname:String,username: String,password: String)
    {
        this.firstname = firstname
        this.lastname= lastname
        this.username= username
         this.password= password

    }
}