package com.example.firebasesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasesign.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class sign_in : AppCompatActivity() {

    private lateinit var binding:ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val email=binding.Email.text.toString()
        val pass=binding.pass.text.toString()
        firebaseAuth=FirebaseAuth.getInstance()
        if(firebaseAuth.currentUser!=null){
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
        binding.tvs.setOnClickListener {
            val intent= Intent(this,sign_up::class.java)
            startActivity(intent)
        }
        binding.btnSignIn.setOnClickListener {


            if(email.isNotEmpty()&& pass.isNotEmpty())
            {
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent=Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else{
                Toast.makeText(this, "Emppty Fields are not allowed!!", Toast.LENGTH_SHORT).show()
            }
        }
    }


}