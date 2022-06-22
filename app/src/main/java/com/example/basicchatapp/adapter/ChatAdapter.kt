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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val textView = holder.itemView.findViewById<TextView>(R.id.received_message)
        textView.text = "${chats.get(position).sender}: ${chats.get(position).message}"
    }

    override fun getItemCount(): Int {
        return chats.size
    }
}