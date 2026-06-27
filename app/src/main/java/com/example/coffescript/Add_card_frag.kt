package com.example.coffescript

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.coffescript.Model.Card

class Add_card_frag : Fragment() {

    val dataModel: DataModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflatere = inflater.inflate(R.layout.fragment_add_card_frag, container, false)

        var edit_name_card: EditText = inflatere.findViewById(R.id.editText_name_card)
        var edit_number_card: EditText = inflatere.findViewById(R.id.editText_number_card)
        var editText_expiry: EditText = inflatere.findViewById(R.id.editText_expiry)

        var number_card: TextView = inflatere.findViewById(R.id.number_card)
        var here_card: TextView = inflatere.findViewById(R.id.here_card)
        var date_card: TextView = inflatere.findViewById(R.id.date_card)

        var back_button_add_card: ImageView = inflatere.findViewById(R.id.back_button_add_card)
        var button_add_card_card: Button = inflatere.findViewById(R.id.button_add_card_card)

        back_button_add_card.setOnClickListener{
            dataModel.message_order.value = true
        }

        edit_name_card.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                here_card.text = edit_name_card.text.toString()

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                here_card.text = edit_name_card.text.toString()

            }
            override fun afterTextChanged(s: Editable?) {
                Log.i("jkfldsa;", "fd")
            }
        })

        edit_number_card.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                number_card.text = edit_number_card.text.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                number_card.text = edit_number_card.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {
                Log.i("jkfldsa;", "fd")
            }
        })

        editText_expiry.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                date_card.text = editText_expiry.text.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                date_card.text = editText_expiry.text.toString()
            }
            override fun afterTextChanged(s: Editable?) {
                Log.i("jkfldsa;", "fd")
            }
        })

        button_add_card_card.setOnClickListener{
            dataModel.list_add_card.value = arrayListOf()
            dataModel.list_add_card.value = arrayListOf(edit_name_card.text.toString(), edit_name_card.text.toString(), editText_expiry.text.toString())
        }

        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = Add_card_frag()
    }
}