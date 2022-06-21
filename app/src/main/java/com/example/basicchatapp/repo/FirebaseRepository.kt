package com.example.basicchatapp.repo

import android.view.View
import androidx.navigation.findNavController
import com.example.basicchatapp.R
import com.example.basicchatapp.databinding.FragmentChatBinding
import com.example.basicchatapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

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

    fun sendMessage(auth: FirebaseAuth,binding: FragmentChatBinding,db:FirebaseFirestore){
        auth.currentUser.let {
            var sender = auth.currentUser!!.email
            var message = binding.chatText.text.toString()
            var date = FieldValue.serverTimestamp()

            val messageMap = HashMap<String,Any>()
            messageMap.put("sender",sender!!)
            messageMap.put("message",message)
            messageMap.put("date",date)

            db.collection("Chats").add(messageMap)
                .addOnSuccessListener {
                    println("succes")
                }
                .addOnFailureListener {
                    println(it.localizedMessage)
                }
        }
    }
}