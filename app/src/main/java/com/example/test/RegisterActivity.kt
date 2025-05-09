package com.example.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        val emailEditText = findViewById<EditText>(R.id.editTextEmailRegister)
        val passwordEditText = findViewById<EditText>(R.id.editTextPasswordRegister)
        val confirmPasswordEditText = findViewById<EditText>(R.id.editTextConfirmPasswordRegister)
        val registerButton = findViewById<Button>(R.id.buttonRegister)

        registerButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (validateRegistration(email, password, confirmPassword)) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Registration success, navigate back to LoginActivity
                            Log.d("RegisterActivity", "createUserWithEmail:success")
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("RegisterActivity", "createUserWithEmail:failure", task.exception)
                        }
                    }
            }
        }
    }

    private fun validateRegistration(email: String, password: String, confirmPassword: String): Boolean {
        // Implement your validation logic here based on the instructions.
        // This is a basic example, you'll need to add checks for special characters, uppercase, etc.
        if (!email.contains("@")) {
            Log.e("RegisterActivity", "Email must contain '@'")
            return false
        }
        if (password.length < 8) {
            Log.e("RegisterActivity", "Password must be at least 8 characters long")
            return false
        }
        if (password != confirmPassword) {
            Log.e("RegisterActivity", "Password and confirm password do not match")
            return false
        }
        // Add checks for special characters and uppercase letters here
        if (!password.matches(Regex(".*[!@#\$%^&*()].*"))) {
            Log.e("RegisterActivity", "Password must contain at least one special character")
            return false
        }
        if (!password.matches(Regex(".*[A-Z].*"))) {
            Log.e("RegisterActivity", "Password must contain at least one uppercase letter")
            return false
        }
        return true
    }
}