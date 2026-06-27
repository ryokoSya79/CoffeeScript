package com.example.coffescript

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.coffescript.R.id.login_text
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Register_frag : Fragment() {

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
        val inflatere = inflater.inflate(R.layout.fragment_register_frag, container, false)

        val login_text: TextView = inflatere.findViewById(login_text)
        val button_register: Button = inflatere.findViewById(R.id.button_register)
        var edit_fullName: EditText = inflatere.findViewById(R.id.edit_text_fullName)
        var edit_email: EditText = inflatere.findViewById(R.id.edit_text_email)
        var edit_pass: EditText = inflatere.findViewById(R.id.edit_text_pass)
        var edit_cons_pass: EditText = inflatere.findViewById(R.id.edit_text_confirm_pass)

        var schetchik = 0
        var chek_name = false
        var chek_email = false
        var chek_pass = false
        var chek_conf_pass = false

        login_text.setOnClickListener{
            dataModel.message_sing_frag.value = true
        }

        val databaseUsers = FirebaseDatabase.getInstance().getReference("users")
        val valueUsers = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(value_users in snapshot.children){
                    schetchik++
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        databaseUsers.addListenerForSingleValueEvent(valueUsers)


        button_register.setOnClickListener{

            if(edit_fullName.text.toString() != ""){
                chek_name = true
                edit_fullName.setBackgroundResource(R.drawable.back_edit_text)
            }else { edit_fullName.setBackgroundResource(R.drawable.back_edit_text_err)}

            if(edit_email.text.toString().contains("@")){
                chek_email = true
                edit_email.setBackgroundResource(R.drawable.back_edit_text)
            }else { edit_email.setBackgroundResource(R.drawable.back_edit_text_err)}

            if(edit_pass.text.toString().length > 3){
                chek_pass = true
                edit_pass.setBackgroundResource(R.drawable.back_edit_text)
            }else { edit_pass.setBackgroundResource(R.drawable.back_edit_text_err)}

            if(edit_cons_pass.text.toString() == edit_pass.text.toString()){
                chek_conf_pass = true
                edit_cons_pass.setBackgroundResource(R.drawable.back_edit_text)
            }else { edit_cons_pass.setBackgroundResource(R.drawable.back_edit_text_err)}


            if(chek_email && chek_name && chek_conf_pass && chek_pass) {
                schetchik++
                val addUser_Name = FirebaseDatabase.getInstance().getReference("users").child("user_${schetchik}").child("name")
                addUser_Name.setValue(edit_fullName.text.toString())
                val addUser_Email = FirebaseDatabase.getInstance().getReference("users").child("user_${schetchik}").child("email")
                addUser_Email.setValue(edit_email.text.toString())
                val addUser_Pass = FirebaseDatabase.getInstance().getReference("users").child("user_${schetchik}").child("password")
                addUser_Pass.setValue(edit_pass.text.toString())

                dataModel.data_user.value = schetchik
                dataModel.message_home_frag.value = true

            }

        }


        return inflatere
    }

    companion object {

        @JvmStatic
        fun newInstance() = Register_frag()
    }
}