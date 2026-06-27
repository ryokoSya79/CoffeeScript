package com.example.coffescript

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels

class Navigate_bottom_frag : Fragment() {

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
        val inflatere = inflater.inflate(R.layout.fragment_navigate_bottom_frag, container, false)

        var circ_1: ImageView = inflatere.findViewById(R.id.circ_1)
        var circ_2: ImageView = inflatere.findViewById(R.id.circ_2)
        var circ_3: ImageView = inflatere.findViewById(R.id.circ_3)
        var circ_4: ImageView = inflatere.findViewById(R.id.circ_4)

        var home_button: ImageView = inflatere.findViewById(R.id.home_button)
        var cart_button: ImageView = inflatere.findViewById(R.id.cart_button)
        var like_favorite: ImageView = inflatere.findViewById(R.id.like_favorite)
        var profile_button: ImageView = inflatere.findViewById(R.id.profile_button)

        var back_press_button_1: ImageView = inflatere.findViewById(R.id.back_press_button_1)
        var back_press_button_2: ImageView = inflatere.findViewById(R.id.back_press_button_2)
        var back_press_button_3: ImageView = inflatere.findViewById(R.id.back_press_button_3)
        var back_press_button_4: ImageView = inflatere.findViewById(R.id.back_press_button_4)

        var abs_press_button_1: ImageView = inflatere.findViewById(R.id.abs_press_button_1)
        var abs_press_button_2: ImageView = inflatere.findViewById(R.id.abs_press_button_2)
        var abs_press_button_3: ImageView = inflatere.findViewById(R.id.abs_press_button_3)
        var abs_press_button_4: ImageView = inflatere.findViewById(R.id.abs_press_button_4)

        back_press_button_1.isGone = false
        back_press_button_2.isGone = true
        back_press_button_3.isGone = true
        back_press_button_4.isGone = true

        var list_press = arrayListOf<String>()
        list_press.add("button_1")

        var home_bool = false
        var cart_bool = false
        var like_bool = false
        var profile_bool = false

        cart_button.setOnClickListener{
            back_press_button_2.isGone = false


            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button,  circ_3, back_press_button_3, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_4, back_press_button_4, list_press[0])
            circ_1.animate().translationY(40f).setDuration(200).start()
            back_press_button_1.animate().translationY(50f).setDuration(200).start()

            back_press_button_1.isGone = true
            back_press_button_3.isGone = true
            back_press_button_4.isGone = true

            home_bool = false
            cart_bool = true
            like_bool = false
            profile_bool = false

            list_press = arrayListOf()
            list_press.add("button_2")

            bottom_button_press_up(cart_button, circ_2, back_press_button_2, home_bool, cart_bool, like_bool, profile_bool)

            dataModel.message_cart_button.value = true


        }

        abs_press_button_1.setOnClickListener{
            back_press_button_1.isGone = false

            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_2, back_press_button_2, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_3, back_press_button_3, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button,  circ_4, back_press_button_4, list_press[0])
            circ_1.animate().translationY(40f).setDuration(200).start()
            back_press_button_1.animate().translationY(50f).setDuration(200).start()


            back_press_button_2.isGone = true
            back_press_button_3.isGone = true
            back_press_button_4.isGone = true

            home_bool = true
            cart_bool = false
            like_bool = false
            profile_bool = false

            list_press = arrayListOf()
            list_press.add("button_1")

            bottom_button_press_up(home_button, circ_1, back_press_button_1, home_bool, cart_bool, like_bool, profile_bool)

            dataModel.message_home_frag.value = true
        }

        abs_press_button_2.setOnClickListener{
            back_press_button_2.isGone = false


            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button,  circ_3, back_press_button_3, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_4, back_press_button_4, list_press[0])
            circ_1.animate().translationY(40f).setDuration(200).start()
            back_press_button_1.animate().translationY(50f).setDuration(200).start()

            back_press_button_1.isGone = true
            back_press_button_3.isGone = true
            back_press_button_4.isGone = true

            home_bool = false
            cart_bool = true
            like_bool = false
            profile_bool = false

            list_press = arrayListOf()
            list_press.add("button_2")

            bottom_button_press_up(cart_button, circ_2, back_press_button_2, home_bool, cart_bool, like_bool, profile_bool)

            dataModel.message_cart_button.value = true
        }

        abs_press_button_4.setOnClickListener{
            back_press_button_4.isGone = false

            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_2, back_press_button_2, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_3, back_press_button_3, list_press[0])
            circ_1.animate().translationY(40f).setDuration(200).start()
            back_press_button_1.animate().translationY(50f).setDuration(200).start()


            back_press_button_1.isGone = true
            back_press_button_2.isGone = true
            back_press_button_3.isGone = true

            home_bool = false
            cart_bool = false
            like_bool = false
            profile_bool = true

            list_press = arrayListOf()
            list_press.add("button_4")

            bottom_button_press_up(profile_button, circ_4, back_press_button_4, home_bool, cart_bool, like_bool, profile_bool)

            dataModel.message_profile.value = true
        }

        abs_press_button_3.setOnClickListener{
            back_press_button_3.isGone = false

            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button,  circ_2, back_press_button_2, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_4, back_press_button_4, list_press[0])

            circ_1.animate().translationY(40f).setDuration(200).start()
            back_press_button_1.animate().translationY(50f).setDuration(200).start()

            back_press_button_4.isGone = true
            back_press_button_1.isGone = true
            back_press_button_2.isGone = true


            home_bool = false
            cart_bool = false
            like_bool = true
            profile_bool = false

            list_press = arrayListOf()
            list_press.add("button_3")

            bottom_button_press_up(like_favorite, circ_3, back_press_button_3, home_bool, cart_bool, like_bool, profile_bool)

            dataModel.message_favorite.value = true
        }

        home_button.setOnClickListener {
            back_press_button_1.isGone = false

            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_2, back_press_button_2, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_3, back_press_button_3, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button,  circ_4, back_press_button_4, list_press[0])
            circ_1.animate().translationY(40f).setDuration(200).start()
            back_press_button_1.animate().translationY(50f).setDuration(200).start()


            back_press_button_2.isGone = true
            back_press_button_3.isGone = true
            back_press_button_4.isGone = true

            home_bool = true
            cart_bool = false
            like_bool = false
            profile_bool = false

            list_press = arrayListOf()
            list_press.add("button_1")

            bottom_button_press_up(home_button, circ_1, back_press_button_1, home_bool, cart_bool, like_bool, profile_bool)

            dataModel.message_home_frag.value = true


        }

        like_favorite.setOnClickListener {

            back_press_button_3.isGone = false

            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button,  circ_2, back_press_button_2, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_4, back_press_button_4, list_press[0])

            circ_1.animate().translationY(40f).setDuration(200).start()
            back_press_button_1.animate().translationY(50f).setDuration(200).start()

            back_press_button_4.isGone = true
            back_press_button_1.isGone = true
            back_press_button_2.isGone = true


            home_bool = false
            cart_bool = false
            like_bool = true
            profile_bool = false

            list_press = arrayListOf()
            list_press.add("button_3")

            bottom_button_press_up(like_favorite, circ_3, back_press_button_3, home_bool, cart_bool, like_bool, profile_bool)

            dataModel.message_favorite.value = true

        }

        profile_button.setOnClickListener {

            back_press_button_4.isGone = false

            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_2, back_press_button_2, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_3, back_press_button_3, list_press[0])
            circ_1.animate().translationY(40f).setDuration(200).start()
            back_press_button_1.animate().translationY(50f).setDuration(200).start()


            back_press_button_1.isGone = true
            back_press_button_2.isGone = true
            back_press_button_3.isGone = true

            home_bool = false
            cart_bool = false
            like_bool = false
            profile_bool = true

            list_press = arrayListOf()
            list_press.add("button_4")

            bottom_button_press_up(profile_button, circ_4, back_press_button_4, home_bool, cart_bool, like_bool, profile_bool)

            dataModel.message_profile.value = true

        }

        circ_2.setOnClickListener{
            back_press_button_2.isGone = false


            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button,  circ_3, back_press_button_3, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_4, back_press_button_4, list_press[0])
            circ_1.animate().translationY(40f).setDuration(200).start()
            back_press_button_1.animate().translationY(50f).setDuration(200).start()

            back_press_button_1.isGone = true
            back_press_button_3.isGone = true
            back_press_button_4.isGone = true

            home_bool = false
            cart_bool = true
            like_bool = false
            profile_bool = false

            list_press = arrayListOf()
            list_press.add("button_2")

            bottom_button_press_up(cart_button, circ_2, back_press_button_2, home_bool, cart_bool, like_bool, profile_bool)



        }

        circ_1.setOnClickListener {
            back_press_button_1.isGone = false

            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_2, back_press_button_2, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_3, back_press_button_3, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button,  circ_4, back_press_button_4, list_press[0])
            circ_1.animate().translationY(40f).setDuration(200).start()
            back_press_button_1.animate().translationY(50f).setDuration(200).start()


            back_press_button_2.isGone = true
            back_press_button_3.isGone = true
            back_press_button_4.isGone = true

            home_bool = true
            cart_bool = false
            like_bool = false
            profile_bool = false

            list_press = arrayListOf()
            list_press.add("button_1")

            bottom_button_press_up(home_button, circ_1, back_press_button_1, home_bool, cart_bool, like_bool, profile_bool)

            dataModel.message_home_frag.value = true



        }

        circ_3.setOnClickListener {

            back_press_button_3.isGone = false

            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button,  circ_2, back_press_button_2, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_4, back_press_button_4, list_press[0])
            circ_1.animate().translationY(40f).setDuration(200).start()
            back_press_button_1.animate().translationY(50f).setDuration(200).start()

            back_press_button_4.isGone = true
            back_press_button_1.isGone = true
            back_press_button_2.isGone = true


            home_bool = false
            cart_bool = false
            like_bool = true
            profile_bool = false

            list_press = arrayListOf()
            list_press.add("button_3")

            bottom_button_press_up(like_favorite, circ_3, back_press_button_3, home_bool, cart_bool, like_bool, profile_bool)

            dataModel.message_favorite.value = true


        }

        circ_4.setOnClickListener {

            back_press_button_4.isGone = false

            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_2, back_press_button_2, list_press[0])
            bottom_button_press_down(home_button, cart_button, like_favorite, profile_button, circ_3, back_press_button_3, list_press[0])
            circ_1.animate().translationY(40f).setDuration(200).start()
            back_press_button_1.animate().translationY(50f).setDuration(200).start()


            back_press_button_1.isGone = true
            back_press_button_2.isGone = true
            back_press_button_3.isGone = true

            home_bool = false
            cart_bool = false
            like_bool = false
            profile_bool = true

            list_press = arrayListOf()
            list_press.add("button_4")

            bottom_button_press_up(profile_button, circ_4, back_press_button_4, home_bool, cart_bool, like_bool, profile_bool)

            dataModel.message_profile.value = true


        }

        return inflatere
    }

    fun bottom_button_press_up(icon_button: ImageView, circ_button: ImageView, back_press_button: ImageView, bool_1: Boolean, bool_2: Boolean, bool_3: Boolean, bool_4: Boolean){
        if(bool_1){
            icon_button.setImageResource(R.drawable.home_button_press)
            circ_button.animate().translationY(0f).setDuration(200).start()
            back_press_button.animate().translationY(0f).setDuration(200).start()
            icon_button.animate().translationY(0f).setDuration(200).start()
        }
        if(bool_2){
            icon_button.setImageResource(R.drawable.cart_button_press)
            circ_button.animate().translationY(-40f).setDuration(200).start()
            back_press_button.animate().translationY(-50f).setDuration(200).start()
            icon_button.animate().translationY(-55f).setDuration(200).start()
        }
        if(bool_3){
            icon_button.setImageResource(R.drawable.like_button_press)
            circ_button.animate().translationY(-40f).setDuration(200).start()
            back_press_button.animate().translationY(-50f).setDuration(200).start()
            icon_button.animate().translationY(-55f).setDuration(200).start()
        }
        if(bool_4){
            icon_button.setImageResource(R.drawable.profile_button_press)
            circ_button.animate().translationY(-40f).setDuration(200).start()
            back_press_button.animate().translationY(-50f).setDuration(200).start()
            icon_button.animate().translationY(-55f).setDuration(200).start()
        }
    }

    fun bottom_button_press_down(icon_button_1: ImageView, icon_button_2: ImageView, icon_button_3: ImageView, icon_button_4: ImageView, circ_button: ImageView, back_press_button: ImageView, list_press: String){
        if(list_press == "button_1"){
            icon_button_1.setImageResource(R.drawable.home_button)
            circ_button.animate().translationY(40f).setDuration(200).start()
            back_press_button.animate().translationY(50f).setDuration(200).start()
            icon_button_1.animate().translationY(55f).setDuration(200).start()
        }
        if(list_press == "button_2"){
            icon_button_2.setImageResource(R.drawable.cart_button)
            circ_button.animate().translationY(0f).setDuration(200).start()
            back_press_button.animate().translationY(0f).setDuration(200).start()
            icon_button_2.animate().translationY(0f).setDuration(200).start()
        }
        if(list_press == "button_3"){
            icon_button_3.setImageResource(R.drawable.like_button)
            circ_button.animate().translationY(0f).setDuration(200).start()
            back_press_button.animate().translationY(0f).setDuration(200).start()
            icon_button_3.animate().translationY(0f).setDuration(200).start()
        }
        if(list_press == "button_4"){
            icon_button_4.setImageResource(R.drawable.profile_button)
            circ_button.animate().translationY(0f).setDuration(200).start()
            back_press_button.animate().translationY(0f).setDuration(200).start()
            icon_button_4.animate().translationY(0f).setDuration(200).start()
        }
    }



    companion object {

        @JvmStatic
        fun newInstance() = Navigate_bottom_frag()
    }
}