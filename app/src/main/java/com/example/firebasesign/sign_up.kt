package com.example.firebasesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebasesign.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class sign_up : AppCompatActivity() {


    private lateinit var binding:ActivitySignUpBinding
    private lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()


       binding.tvAccoount.setOnClickListener {
           val intent=Intent(this,sign_in::class.java)
           startActivity(intent)
       }
        binding.btnSignup.setOnClickListener {
            val email=binding.etEmail.text.toString()
            val pass=binding.etPass.text.toString()
            val confirmPas=binding.etRePass.text.toString()

            if(email.isNotEmpty()&&pass.isNotEmpty()&&confirmPas.isNotEmpty()){
                if(pass==confirmPas){
                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        val intent=Intent(this,sign_in::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
                }
                else{
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                    Toast.makeText(this, "Empty fields are not allowerd ", Toast.LENGTH_SHORT).show()

            }
        }
    }
}