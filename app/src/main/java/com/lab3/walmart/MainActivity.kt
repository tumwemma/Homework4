package com.lab3.walmart

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var userlist= arrayListOf<Users>(Users("Emmanuel","Tumwizere","tumwemma@gmail.com","Emma@123"),
        Users("Jean","Ndayizeye","jean@gmail.com","jean@123"),
        Users("Sarah","UWera","uwera@gmail.com","uwera@123"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun signIn(view:View){
        var name=""
        var exist=false
        var username:String =userID.text.toString()
        var pass:String=userPass.text.toString()
        for (i in userlist){
            if(i.username.equals(username) && i.password.equals(pass))
                exist=true
            name=i.firstname.toString()

        }
        if (exist) {
            val shppingIntent = Intent(this, Shopping_activity::class.java)
            shppingIntent.putExtra("name",name)
            startActivity(shppingIntent);
        }
        else
            Toast.makeText(this, "invalid UserName or Password", Toast.LENGTH_LONG).show()
    }

    fun create(view: View){
        val CreateAccountntent = Intent(this, RegisterActivit::class.java)

        startActivityForResult(CreateAccountntent,1);

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode==1 && resultCode== Activity.RESULT_OK ){
            var returnedUser=data!!.getSerializableExtra("userData") as Users
            userlist.add(returnedUser)
        }

    }

    fun passwordreminder(view: View){
        var passwordforgot:String=""
        var Fname:String=""
        if (userID.text.toString().trim().equals("") ||userID.text.toString()==null ){
            Toast.makeText(this, "Please Enter a valid email ", Toast.LENGTH_SHORT).show()

        }
        else{
            var username= userID.text.toString()
            for(i in userlist) {
                if (i.username.equals(username)) {
                    passwordforgot = i.password
                    Fname=i.firstname
                }
            }
            if(passwordforgot.trim().equals("")){

                Toast.makeText(this, "your user name does not exit please create one ", Toast.LENGTH_LONG).show()
            }
            else {
                sendEmail(username, passwordforgot, Fname)
            }
        }
    }

    private fun sendEmail(username: String, passwordforgot: String,Fname:String) {
        var message:String="Hi $Fname! your password is: $passwordforgot"
        var mailIntent=Intent(Intent.ACTION_SEND)
        mailIntent.data= Uri.parse("mailto")
        mailIntent.type="text/plain"
        mailIntent.putExtra(Intent.EXTRA_EMAIL, arrayListOf(username))
        mailIntent.putExtra(Intent.EXTRA_SUBJECT,"FORGOT PASSWORD")
        mailIntent.putExtra(Intent.EXTRA_TEXT,message)
        //startActivity(mailIntent)

        try {
            startActivity(Intent.createChooser(mailIntent,"Select client"))
        }
        catch (e:Exception){
            Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
        }

    }
}
