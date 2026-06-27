package com.example.coffescript

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.coffescript.Model.Profile_user
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class Login_frag : Fragment() {

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
        val inflatere = inflater.inflate(R.layout.fragment_login_frag, container, false)

        var edit_text_email: EditText = inflatere.findViewById(R.id.edit_text_email)
        var edit_text_pass: EditText = inflatere.findViewById(R.id.edit_text_pass)
        var button_sign_in: Button = inflatere.findViewById(R.id.button_sign_in)
        val regist_text: TextView = inflatere.findViewById(R.id.regist_text)

        var password = false
        var email = false

        var list_user = mutableListOf<String>()


        button_sign_in.setOnClickListener{
            var schet_user = 0
            val databaseUsers = FirebaseDatabase.getInstance().getReference("users")
            val valueUsers = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(users in snapshot.children){
                        list_user.add(users.key.toString())

                        schet_user++




                        //Проверка Email
                        val databaseUser_Email = FirebaseDatabase.getInstance().getReference("users").child("${users.key}").child("email")
                        val valueUser_Email = object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val user_email = snapshot.value
                                if(user_email == edit_text_email.text.toString()){
                                    Log.i("USER", "Perm")
                                    email = true
                                    edit_text_email.setBackgroundResource(R.drawable.back_edit_text)
                                }else{
                                    Log.i("USER", "NONPerm")
                                    edit_text_email.setBackgroundResource(R.drawable.back_edit_text_err)
                                    dataModel.message_vibrate.value = true
                                    email = false

                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }

                        }
                        databaseUser_Email.addListenerForSingleValueEvent(valueUser_Email)

                        //Проверка пароля
                        val databaseUser_Pass = FirebaseDatabase.getInstance().getReference("users").child("${users.key}").child("password")
                        val valueUser_Pass = object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val user_pass = snapshot.value


                                if(user_pass == edit_text_pass.text.toString()){
                                    Log.i("USER", "Perm")
                                    password = true

                                    edit_text_pass.setBackgroundResource(R.drawable.back_edit_text)
                                    return
                                }else{
                                    Log.i("USER", "NONPerm")
                                    edit_text_email.setBackgroundResource(R.drawable.back_edit_text_err)
                                    edit_text_pass.setBackgroundResource(R.drawable.back_edit_text_err)
                                    dataModel.message_vibrate.value = true
                                    Log.i("USER", "${password}, $email kkkkkkkkkkkkkkkkkkkkoookokkkkkkkkkkkkkkk")
                                    password = false
                                    return
                                }
                            }
                            override fun onCancelled(error: DatabaseError) {
                                TODO("Not yet implemented")
                            }
                        }
                        databaseUser_Pass.addListenerForSingleValueEvent(valueUser_Pass)

                        val databaseUserName = FirebaseDatabase.getInstance().getReference("users").child("${users.key}")
                        val valueUserName = object : ValueEventListener{
                            override fun onDataChange(snapshot: DataSnapshot) {



//                                Log.d("DB_CART",
//                                    "Что ввел полльзователь: ${email}, С чем сравнил: ${password}, ID: ${users.key}, Schet: ${schet_user}"
//                                )



                                val db = DataBase_Profile.getDb_Profile(requireContext())

                                if (email && password){
//                                    Log.d("DB_CART", "----------------Pizdec ${snapshot.children.count()}")



                                    val item_item_user = snapshot.getValue(Profile_user :: class.java)

                                    Thread{
                                        db.getDao_Profile().deleteAllItems()
                                        db.getDao_Profile().resetAutoIncrement()
                                        val item = Entity_Item_profile(null, "${item_item_user!!.name}", "${item_item_user!!.email}", "${item_item_user!!.password}", "${users.key.toString().removePrefix("user_")}")
                                        db.getDao_Profile().insertItem(item)
                                        var items = db.getDao_Profile().getAllItems()
                                        items.forEach { item ->
                                            Log.d("DB_CART", "IDIDIDIDIDIDIDI: ${item.id} | " +
                                                    "Товар: ${item.name} | " +
                                                    "Цена: ${item.email} | " +
                                                    "Состав: ${item.password} | " +
                                                    "Кол-во: ${item.id_user}"
                                            )

                                        }
                                    }.start()

                                    dataModel.message_home_frag.value = true
                                    dataModel.data_user.value = schet_user

                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Log.i("TAG", "loadPost:onCancelled", error.toException())
                            }

                        }
                        databaseUserName.addListenerForSingleValueEvent(valueUserName)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
            databaseUsers.addListenerForSingleValueEvent(valueUsers)

        }




        regist_text.setOnClickListener{
            dataModel.message_register_frag.value = true
        }

        return inflatere
    }

    companion object {

        @JvmStatic
        fun newInstance() = Login_frag()
    }
}