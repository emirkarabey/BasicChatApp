package com.example.basicchatapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.basicchatapp.R
import com.example.basicchatapp.model.Chat
import com.example.basicchatapp.utils.Constants.VIEW_TYPE_MESSAGE_RECEIVED
import com.example.basicchatapp.utils.Constants.VIEW_TYPE_MESSAGE_SENT
import com.google.firebase.auth.FirebaseAuth

class ChatAdapter:RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

  class ChatViewHolder(view: View):RecyclerView.ViewHolder(view){

  }
    private val diffUtil = object : DiffUtil.ItemCallback<Chat>(){
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this,diffUtil)

    var chats : List<Chat>
    get() =recyclerListDiffer.currentList
        set(value)= recyclerListDiffer.submitList(value)

    override fun getItemViewType(position: Int): Int {
        val chat = chats.get(position)

        if (chat.sender== FirebaseAuth.getInstance().currentUser?.email.toString()){
            return VIEW_TYPE_MESSAGE_SENT
        }else{
            return VIEW_TYPE_MESSAGE_RECEIVED
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        if (viewType == VIEW_TYPE_MESSAGE_RECEIVED){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
            return ChatViewHolder(view)
        }else{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_right,parent,false)
            return ChatViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.received_message)
        textView.text = "${chats.get(position).sender}: ${chats.get(position).message}"
    }

    override fun getItemCount(): Int {
        return chats.size
    }
}