package com.example.coffescript

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels

class Policy_frag : Fragment() {

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
        val inflatere = inflater.inflate(R.layout.fragment_policy_frag, container, false)
        val back_button_policy: ImageView = inflatere.findViewById(R.id.back_button_policy)

        back_button_policy.setOnClickListener{
            dataModel.message_profile.value = true
        }

        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = Policy_frag()
    }
}