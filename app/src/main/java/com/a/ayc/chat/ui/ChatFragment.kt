package com.a.ayc.chat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.a.ayc.R
import com.a.ayc.databinding.FragmentChatBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var messageReceiver: String
    private lateinit var messageSender: String

    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        messageReceiver =
            arguments?.let { ChatFragmentArgs.fromBundle(it).messageReceiver }.toString()

        messageSender = chatViewModel.currentUser()?.uid.toString()

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(ChatFragmentDirections.actionChatFragmentToHomeFragment())
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatViewModel.receiverUsername.observe(viewLifecycleOwner, { name ->
            if (name != null) {
                binding.username = name
            }
        })

        chatViewModel.isInDirect.observe(viewLifecycleOwner, {
            if (it != null) {
                val chatId = chatViewModel.chatIdDecide(messageReceiver)
                if (it) {
                    // open existing chat
                } else {
                    chatViewModel.createChatRoom(chatId)
                    chatViewModel.putChatInDirect(messageReceiver, messageSender)
                    chatViewModel.putChatInDirect(messageSender, messageReceiver)
                }
            }
        })

        chatViewModel.usernameFromUid(messageReceiver)
        chatViewModel.isUserInDirect(messageReceiver)

    }
}