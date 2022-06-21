package com.example.basicchatapp.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.basicchatapp.databinding.FragmentLoginBinding
import com.example.basicchatapp.model.Chat
import com.example.basicchatapp.repo.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel() : ViewModel() {

    var repo = FirebaseRepository()

    fun signIn(auth: FirebaseAuth,binding: FragmentLoginBinding,view: View){
        repo.signIn(auth,binding,view)
    }

    fun signUp(auth: FirebaseAuth,binding:FragmentLoginBinding,view:View){
        repo.signUp(auth,binding,view)
    }

}