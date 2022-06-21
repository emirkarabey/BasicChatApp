package com.example.basicchatapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.basicchatapp.databinding.FragmentChatBinding
import com.example.basicchatapp.repo.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ChatViewModel : ViewModel() {

    var repo = FirebaseRepository()
    fun sendMessage(auth: FirebaseAuth,binding: FragmentChatBinding,db:FirebaseFirestore){
        repo.sendMessage(auth,binding,db)
    }
}