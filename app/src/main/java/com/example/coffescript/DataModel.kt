package com.example.coffescript

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coffescript.Model.Card

open class DataModel : ViewModel() {

    val message_start_frag: MutableLiveData<Boolean> by lazy{
        MutableLiveData<Boolean>()
    }

    val message_sing_frag: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val message_register_frag: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val message_vibrate: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val message_home_frag: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val message_open_product: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val message_cart_button: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val data_user: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }
    val order_add_in_db: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val list_cart_product = MutableLiveData<List<String>>(emptyList())

    val name_and_price = MutableLiveData<List<String>>(emptyList())
    val list_product_like = MutableLiveData<List<String>>(emptyList())

    val price_predator: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val message_order: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_add_card: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val list_add_card = MutableLiveData<List<String>>(emptyList())

    val message_payment_done: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val message_track_order: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val message_my_order_track: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val date_list_order = MutableLiveData<MutableList<List<String>>>(mutableListOf())

    val data_message_like: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_profile: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_setting: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_policy: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_order_frag: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_my_order: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val index_order: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val message_favorite: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val message_favorite_track: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val message_baker: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

}