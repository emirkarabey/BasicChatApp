package com.example.basicchatapp.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicchatapp.R
import com.example.basicchatapp.adapter.ChatAdapter
import com.example.basicchatapp.databinding.FragmentChatBinding
import com.example.basicchatapp.model.Chat
import com.example.basicchatapp.viewmodel.ChatViewModel
import com.example.basicchatapp.viewmodel.LoginViewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.time.TimedValue

class ChatFragment : Fragment() {
    private val viewModel: ChatViewModel by activityViewModels()
    private lateinit var db:FirebaseFirestore
    private lateinit var auth:FirebaseAuth
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var chatAdapter : ChatAdapter
    private val list = arrayListOf<Chat>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        db = Firebase.firestore
        auth = Firebase.auth
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chatAdapter = ChatAdapter()
        binding.recyclerView.adapter = chatAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.sendButton.setOnClickListener {
            viewModel.sendMessage(auth,binding,db)
            binding.chatText.setText("")
        }

        viewModel.getAllMessage(db)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner,object : Observer<List<Chat>>{

            override fun onChanged(t: List<Chat>?) {
                if (t!=null){
                    viewModel.messageList.postValue(t)
                    chatAdapter.chats = viewModel.messageList.value!!
                    chatAdapter.notifyDataSetChanged()
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_chat,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.log_out){
            auth.signOut()
            view!!.findNavController().navigate(R.id.action_chatFragment_to_loginFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}