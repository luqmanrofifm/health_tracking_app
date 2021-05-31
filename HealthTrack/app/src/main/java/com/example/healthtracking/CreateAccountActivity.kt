package com.example.healthtracking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.example.healthtracking.databinding.ActivityCreateAccountBinding
import com.example.healthtracking.utils.FIrebaseUtil.firebaseAuth
import com.example.healthtracking.utils.FIrebaseUtil.firebaseUser
import com.google.firebase.auth.FirebaseUser

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding

    lateinit var userEmail: String
    lateinit var userPassword: String
    lateinit var createAccountInputsArray: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        val view = binding.root
        //setContentView(R.layout.activity_create_account)
        setContentView(view)
        createAccountInputsArray = arrayOf(binding.etEmail, binding.etPassword, binding.etConfirmPassword)
        binding.btnCreateAccount.setOnClickListener {
            signIn()
        }
        binding.btnSignIn2.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            //toast("please sign into your account")
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        //val user: FirebaseUser? = firebaseAuth.currentUser
        /*user?.let {
            startActivity(Intent(this, MainActivity::class.java))
            //toast("welcome back")
        }*/
    }

    private fun notEmpty(): Boolean = binding.etEmail.text.toString().trim().isNotEmpty() &&
            binding.etPassword.text.toString().trim().isNotEmpty() &&
            binding.etConfirmPassword.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        var identical = false
        if (notEmpty() &&
           binding.etPassword.text.toString().trim() == binding.etConfirmPassword.text.toString().trim()
        ) {
            identical = true
        } else if (!notEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        } else {
            //toast("passwords are not matching !")
            Log.e("password","password not matching")
        }
        return identical
    }

    private fun signIn() {
        if (identicalPassword()) {
            // identicalPassword() returns true only  when inputs are not empty and passwords are identical
            userEmail = binding.etEmail.text.toString().trim()
            userPassword = binding.etPassword.text.toString().trim()

            /*create a user*/
            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //toast("created account successfully !")
                        //sendEmailVerification()
                        Log.d("login","login success")
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        //toast("failed to Authenticate !")
                        Log.e("log in","login failed")
                    }
                }
        }
    }

    /* send verification email to the new user. This will only
    *  work if the firebase user is not null.
    */

    private fun sendEmailVerification() {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //toast("email sent to $userEmail")
                    Log.d("send email","email sended")
                }
            }
        }
    }
}