package com.a.ayc.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.a.ayc.R
import com.a.ayc.databinding.ChatsListItemBinding
import com.a.ayc.model.UserModel

class ChatListAdapter(
    var list: MutableList<UserModel>
) :
    RecyclerView.Adapter<ChatListAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: ChatsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {

            itemView.setOnClickListener {
                try {
                    it.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToChatFragment(
                            list[position].id
                        )
                    )
                } catch (e: Exception) {
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ChatsListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.chats_list_item, parent, false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.data = list[position]
    }

}
