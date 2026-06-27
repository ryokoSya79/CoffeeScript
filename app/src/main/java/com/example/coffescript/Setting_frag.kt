package com.example.coffescript

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Setting_frag : Fragment() {

    val dataModel: DataModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflatere = inflater.inflate(R.layout.fragment_setting_frag, container, false)

        val back_button_setting: ImageView = inflatere.findViewById(R.id.back_button_setting)
        val shadow_setting: ImageView = inflatere.findViewById(R.id.shadow_setting)
        val button_view_password_manager: View = inflatere.findViewById(R.id.button_view_password_manager)
        val strekla_password_manager: ImageView = inflatere.findViewById(R.id.strekla_password_manager)
        val icon_password_manager: ImageView = inflatere.findViewById(R.id.icon_password_manager)
        val title_password_manager: TextView = inflatere.findViewById(R.id.title_password_manager)
        val button_apply_password: Button = inflatere.findViewById(R.id.button_apply_password)
        var edit_old_password: EditText = inflatere.findViewById(R.id.edit_old_password)
        var edit_new_password: EditText = inflatere.findViewById(R.id.edit_new_password)
        var schet_user = 0
        val button_view_delete: View = inflatere.findViewById(R.id.button_view_delete)
        val imageView44: ImageView = inflatere.findViewById(R.id.imageView44)
        val icon_delete_acc: ImageView = inflatere.findViewById(R.id.icon_delete_acc)
        val title_delete_acc: TextView = inflatere.findViewById(R.id.title_delete_acc)
        val button_yes_delete: Button = inflatere.findViewById(R.id.button_yes_delete)
        val button_no_delete: Button = inflatere.findViewById(R.id.button_no_delete)

        val frag_change_password: FrameLayout = inflatere.findViewById(R.id.change_password_fragment)
        val delete_acc_fragment: FrameLayout = inflatere.findViewById(R.id.delete_acc_fragment)

        shadow_setting.isGone = true
        frag_change_password.isGone = true
        delete_acc_fragment.isGone = true
        button_yes_delete.isEnabled = false
        back_button_setting.isEnabled = true

        shadow_setting.setOnClickListener{
            delete_acc_fragment.isGone = true
            frag_change_password.isGone = true
            shadow_setting.isGone = true
            button_yes_delete.isEnabled = false
            back_button_setting.isEnabled = true
        }

        button_no_delete.setOnClickListener{
            delete_acc_fragment.isGone = true
            shadow_setting.isGone = true
            button_yes_delete.isEnabled = false
        }

        button_yes_delete.setOnClickListener{
            val database = FirebaseDatabase.getInstance().getReference("users").child("user_${schet_user}")
            database.removeValue()
            dataModel.message_sing_frag.value = true
        }

        button_view_delete.setOnClickListener{
            shadow_setting.isGone = false
            delete_acc_fragment.isGone = false
            button_yes_delete.isEnabled = true
            back_button_setting.isEnabled = false

        }
        imageView44.setOnClickListener{
            shadow_setting.isGone = false
            delete_acc_fragment.isGone = false
            button_yes_delete.isEnabled = true
            back_button_setting.isEnabled = false


        }
        icon_delete_acc.setOnClickListener{
            shadow_setting.isGone = false
            delete_acc_fragment.isGone = false
            button_yes_delete.isEnabled = true
            back_button_setting.isEnabled = false

        }
        title_delete_acc.setOnClickListener{
            shadow_setting.isGone = false
            delete_acc_fragment.isGone = false
            button_yes_delete.isEnabled = true
            back_button_setting.isEnabled = false

        }

        back_button_setting.setOnClickListener{
            dataModel.message_profile.value = true
        }

        button_view_password_manager.setOnClickListener{
            shadow_setting.isGone = false
            frag_change_password.isGone = false
            edit_old_password.setText("")
            edit_new_password.setText("")
            back_button_setting.isEnabled = false

        }
        strekla_password_manager.setOnClickListener{
            shadow_setting.isGone = false
            frag_change_password.isGone = false
            edit_old_password.setText("")
            edit_new_password.setText("")
            back_button_setting.isEnabled = false

        }
        icon_password_manager.setOnClickListener{
            shadow_setting.isGone = false
            frag_change_password.isGone = false
            edit_old_password.setText("")
            edit_new_password.setText("")
            back_button_setting.isEnabled = false

        }
        title_password_manager.setOnClickListener{
            shadow_setting.isGone = false
            frag_change_password.isGone = false
            edit_old_password.setText("")
            edit_new_password.setText("")
            back_button_setting.isEnabled = false

        }



        dataModel.data_user.observe(this, {
            schet_user = it
        })

        button_apply_password.setOnClickListener{


            val databaseUser = FirebaseDatabase.getInstance().getReference("users").child("user_$schet_user").child("password")
            val valueUser = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val password_data = snapshot.value

                    if(password_data == edit_old_password.text.toString()){
                        val changePassword = FirebaseDatabase.getInstance().getReference("users").child("user_${schet_user}").child("password")
                        changePassword.setValue(edit_new_password.text.toString())
                        shadow_setting.isGone = true
                        frag_change_password.isGone = true
                    }
                    else{
                        edit_old_password.setBackgroundResource(R.drawable.back_edit_text_err_password)
                        dataModel.message_vibrate.value = true
                        Log.d("DB_CART",
                            "Что ввел полльзователь: ${edit_old_password.text.toString()}, С чем сравнил: ${password_data}, Schet: ${schet_user}"
                        )

                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            }
            databaseUser.addListenerForSingleValueEvent(valueUser)
        }

        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = Setting_frag()
    }
}