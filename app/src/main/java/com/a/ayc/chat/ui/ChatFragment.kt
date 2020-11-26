package com.a.ayc.chat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.a.ayc.R
import com.a.ayc.databinding.FragmentChatBinding
import com.a.ayc.general.GeneralStrings
import com.a.ayc.general.MessageType
import com.a.ayc.model.MessageModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var messageReceiver: String
    private lateinit var messageSender: String
    private lateinit var myAdapter: ChatAdapter

    private val chatViewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        messageReceiver =
            arguments?.let { ChatFragmentArgs.fromBundle(it).messageReceiver }.toString()

        messageSender = chatViewModel.currentUser()?.uid.toString()

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(
                        ChatFragmentDirections.actionChatFragmentToHomeFragment(
                            GeneralStrings.keyChat
                        )
                    )
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            chatViewModel.receiverUsername.observe(viewLifecycleOwner, { name ->
                if (name != null) {
                    binding.username = name
                }
            })

            chatViewModel.isInDirect.observe(viewLifecycleOwner, { isInDirect ->
                if (isInDirect != null) {
                    val chatId = chatViewModel.chatIdDecide(messageReceiver)
                    if (isInDirect) {
                        // open existing chat
                    } else {
                        chatViewModel.createChatRoom(chatId)
                        chatViewModel.putChatInDirect(messageReceiver, messageSender)
                        chatViewModel.putChatInDirect(messageSender, messageReceiver)
                    }
                }
            })

            chatViewModel.usernameFromUid(messageReceiver)
            chatViewModel.isUserInDirect(
                chatViewModel.currentUser()?.uid.toString(),
                messageReceiver
            )

            val list = mutableListOf<MessageModel>()

            myAdapter = ChatAdapter(list)

            chat_recycler.apply {
                adapter = myAdapter
            }

            send_cv.setOnClickListener {

                if (chat_type_et.editText?.text?.isNotBlank() == true) {

                    list.add(
                        MessageModel(
                            "",
                            chat_type_et.editText?.text.toString(),
                            MessageType.SENT
                        )
                    )

                    // here it should check that if it is a sent message gone the seen icon
                    // and if it is a received message visible the icon

                    chat_type_et.editText?.setText("")

                    myAdapter.list = list
                    myAdapter.notifyItemInserted(list.size)

                    chat_recycler.scrollToPosition(list.size - 1)
                }
            }

        }

    }

    override fun onPause() {
        super.onPause()
        // disable seen icon
    }
}