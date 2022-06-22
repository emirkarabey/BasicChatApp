package com.example.basicchatapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.basicchatapp.R
import com.example.basicchatapp.databinding.FragmentLoginBinding
import com.example.basicchatapp.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var auth : FirebaseAuth
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

    }
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser = auth.currentUser
        if(currentUser!=null){
            view.findNavController().navigate(R.id.action_loginFragment_to_chatFragment)

        }
        binding.signUpButton.setOnClickListener {
            viewModel.signUp(auth,binding,view)
        }

        binding.signInButton.setOnClickListener {
            viewModel.signIn(auth,binding,view)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}