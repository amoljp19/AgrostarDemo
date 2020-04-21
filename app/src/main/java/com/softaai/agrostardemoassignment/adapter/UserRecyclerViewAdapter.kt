package com.softaai.agrostardemoassignment.adapter

import android.app.Activity
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.softaai.agrostardemoassignment.R
import com.softaai.agrostardemoassignment.databinding.UserListItemBinding
import com.softaai.agrostardemoassignment.model.User


class UserRecyclerViewAdapter(var context: Activity, var userArrayList: ArrayList<User>) :
    RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: UserListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_list_item,
            parent,
            false
        )
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userArrayList[position])
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val builder = AlertDialog.Builder(context)
                with(builder)
                {
                    setTitle("Remove List Item : " + userArrayList[holder.adapterPosition].name)
                    setMessage("To remove item from list, please click on REMOVE button.")
                    setPositiveButton(
                        "REMOVE",
                        DialogInterface.OnClickListener({ dialog: DialogInterface, which: Int ->
                            removeListItem(holder.adapterPosition)
                        })
                    )
                    show()
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return userArrayList.size
    }

    fun removeListItem(position: Int) { //removes the row
        userArrayList.removeAt(position)
        notifyItemRemoved(position)
    }

    class ViewHolder(val binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: User) {
            binding.userModel = item
        }
    }
}