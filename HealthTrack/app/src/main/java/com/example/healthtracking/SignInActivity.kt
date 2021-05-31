package com.example.healthtracking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.example.healthtracking.databinding.ActivitySignInBinding
import com.example.healthtracking.utils.FIrebaseUtil.firebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding

    lateinit var signInEmail: String
    lateinit var signInPassword: String
    lateinit var signInInputsArray: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        //setContentView(R.layout.activity_sign_in)
        setContentView(view)
        signInInputsArray = arrayOf(binding.etSignInEmail, binding.etSignInPassword)
        binding.btnCreateAccount2.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
            finish()
        }

        binding.btnSignIn.setOnClickListener {
            signInUser()
        }
    }

    private fun notEmpty(): Boolean = signInEmail.isNotEmpty() && signInPassword.isNotEmpty()

    private fun signInUser() {
        signInEmail = binding.etSignInEmail.text.toString().trim()
        signInPassword = binding.etSignInPassword.text.toString().trim()
        Log.d("coba",signInEmail+", "+signInPassword)

        if (notEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                        //toast("signed in successfully")
                        Log.d("sign in","sign in success")
                        finish()
                    } else {
                        Log.e("sign in", "sign in failed")
                        //toast("sign in failed")
                    }
                }
        } else {
            signInInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        }
    }
}