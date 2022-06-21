package com.example.basicchatapp.repo

import android.view.View
import androidx.navigation.findNavController
import com.example.basicchatapp.R
import com.example.basicchatapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepository (){

    fun signIn(auth: FirebaseAuth,binding: FragmentLoginBinding,view: View){
        auth.signInWithEmailAndPassword(binding.emailText.text.toString(),binding.passwordText.text.toString())
            .addOnSuccessListener {
                view.findNavController().navigate(R.id.action_loginFragment_to_chatFragment)
            }
            .addOnFailureListener {
                println(it.localizedMessage)
            }

    }

    fun signUp(auth: FirebaseAuth,binding: FragmentLoginBinding,view: View){
        auth.createUserWithEmailAndPassword(binding.emailText.text.toString(),binding.passwordText.text.toString())
            .addOnSuccessListener {
                view.findNavController().navigate(R.id.action_loginFragment_to_chatFragment)
            }
            .addOnFailureListener {
                it.localizedMessage
            }
    }
}