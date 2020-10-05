package com.a.ayc

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val create = FirebaseAuth.getInstance()

        create.createUserWithEmailAndPassword("amir@gmail.com", "12345678")
            .addOnCompleteListener { task ->
                val a = task
                if (task.isSuccessful) {
                    Toast.makeText(this, "acc created successfully", Toast.LENGTH_SHORT).show()
                }
            }
    }
}