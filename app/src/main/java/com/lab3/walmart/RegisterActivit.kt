package com.lab3.walmart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }

    fun createAccount(view:View){
        var fName:String=firstName.text.toString().trim()
        var lName:String=lastName.text.toString().trim()
        var email:String=email.text.toString().trim()
        var pass:String=password.text.toString().trim()

        if(fName.equals("") || lName.equals("") || email.equals("") || pass.equals("")){
            Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_LONG)
                .show()

        }

        else {
            var userData:Users= Users(fName,lName,email,pass)

            val userdataIntent = Intent()
            userdataIntent.putExtra("userData", userData)
            setResult(RESULT_OK, userdataIntent)

            Toast.makeText(this, "Account is succfully Created", Toast.LENGTH_LONG).show()

             finish();
        }
    }
}
