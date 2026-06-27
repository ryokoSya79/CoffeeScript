package com.example.coffescript

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.activityViewModels

class Payment_done_frag : Fragment() {

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
        val inflatere = inflater.inflate(R.layout.fragment_payment_done_frag, container, false)

        val back_button_payment: ImageView = inflatere.findViewById(R.id.back_button_payment)
        val button_track_order: Button = inflatere.findViewById(R.id.button_track_order)

        button_track_order.setOnClickListener{
            dataModel.message_track_order.value = true
            dataModel.message_my_order.value = false
        }

        back_button_payment.setOnClickListener{
            dataModel.message_cart_button.value = true
        }

        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = Payment_done_frag()
    }
}