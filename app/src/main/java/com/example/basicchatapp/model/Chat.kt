package com.example.basicchatapp.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue

data class Chat(val sender:String,val message:String,val date:Timestamp?)