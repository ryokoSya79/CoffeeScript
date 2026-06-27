package com.example.coffescript

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {

    val dataModel: DataModel by viewModels()
    private val tf = supportFragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frag_navigate: FragmentContainerView = findViewById(R.id.fragment_navigator_buttom)
        frag_navigate.visibility = View.GONE

//        val item = Entity_Item(null, "Capuchino", "Milk", "321432", "5")
//        val db = DataBase_Cart.getDb(this)


        tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Get_start_frag.newInstance()).commit()

        dataModel.message_start_frag.observe(this, {
            if (it){
                tf.beginTransaction().setCustomAnimations(R.anim.frag_in, R.anim.frag_in).replace(R.id.fragment_get_start, Skip_frag_one.newInstance()).commit()

            }
        })

        dataModel.message_sing_frag.observe(this, {
            if (it){
                frag_navigate.visibility = View.GONE
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Login_frag.newInstance()).commit()
            }
        })

        //Вибрация
        dataModel.message_vibrate.observe(this, {
            if (it){
                val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                val milliseconds = 200L
                vibrator.vibrate(milliseconds)
            }
        })

        dataModel.message_register_frag.observe(this, {
            if (it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Register_frag.newInstance()).commit()
            }
        })

        dataModel.message_home_frag.observe(this, {
            if (it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Home_frag.newInstance()).commit()
            }
        })

        dataModel.message_open_product.observe(this, {
            if (it){
                frag_navigate.visibility = View.GONE
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Open_product.newInstance()).commit()
            }
        })

        dataModel.message_cart_button.observe(this, {
            if (it){
                frag_navigate.visibility = View.VISIBLE
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Cart_frag.newInstance()).commit()
            }
        })

        dataModel.message_home_frag.observe(this, {
            if (it){
                frag_navigate.visibility = View.VISIBLE
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_navigator_buttom, Navigate_bottom_frag()).commit()
            }
        })

        dataModel.message_order.observe(this, {
            if (it){
                frag_navigate.visibility = View.GONE
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Checkout_frag()).commit()
            }
        })

        dataModel.message_add_card.observe(this, {
            if (it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Add_card_frag()).commit()
            }
        })

        dataModel.message_payment_done.observe(this, {
            if (it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Payment_done_frag()).commit()
            }
        })

        dataModel.message_track_order.observe(this, {
            if (it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Track_order_frag()).commit()
            }
        })

        dataModel.message_my_order_track.observe(this, {
            if (it){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Track_order_frag()).commit()
            }
        })

        dataModel.message_profile.observe(this, {
            if (it){
                frag_navigate.visibility = View.VISIBLE
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Profile_frag()).commit()
            }
        })

        dataModel.message_setting.observe(this, {
            if (it){
                frag_navigate.visibility = View.GONE
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Setting_frag()).commit()
            }
        })

        dataModel.message_policy.observe(this, {
            if (it){
                frag_navigate.visibility = View.GONE
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Policy_frag()).commit()
            }
        })

        dataModel.message_order_frag.observe(this, {
            if (it){
                frag_navigate.visibility = View.GONE
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Policy_frag()).commit()
            }
        })
        dataModel.message_my_order.observe(this, {
            if (it){
                frag_navigate.visibility = View.GONE
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, My_Orders_frag()).commit()
            }
        })
        dataModel.message_favorite.observe(this, {
            if (it){
                frag_navigate.visibility = View.VISIBLE
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Favorite_frag()).commit()
            }
        })

//        dataModel.message_favorite_track.observe(this, {
//            if (it){
//                frag_navigate.visibility = View.VISIBLE
//                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Favorite_frag()).commit()
//            }
//        })

        //Полноэкранный режим
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        if (Build.VERSION.SDK_INT < 19) {
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else {
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            decorView.systemUiVisibility = uiOptions
        }


        Thread{
            val db_profile = DataBase_Profile.getDb_Profile(this)
//            db_profile.getDao_Profile().deleteAllItems()
//            db_profile.getDao_Profile().resetAutoIncrement()
            var items = db_profile.getDao_Profile().getAllItems()

            if(items.isEmpty()){
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Get_start_frag.newInstance()).commit()
            }else{
                frag_navigate.visibility = View.VISIBLE
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_get_start, Home_frag.newInstance()).commit()
                tf.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).replace(R.id.fragment_navigator_buttom, Navigate_bottom_frag()).commit()
            }
            Log.d("DB_CART", "--------------------------------------")
            items.forEach { item ->
                Log.d("DB_CART", "ID: ${item.id} | " +
                        "Товар: ${item.name} | " +
                        "Цена: ${item.email} | " +
                        "Состав: ${item.password} | " +
                        "Кол-во: ${item.id_user}"
                )
            }
        }.start()


    }


}