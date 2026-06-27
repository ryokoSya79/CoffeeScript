package com.example.coffescript

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffescript.Model.Product_my_order

class My_Orders_frag : Fragment(), Adapter_my_order.Listener {

    val dataModel: DataModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflatere = inflater.inflate(R.layout.fragment_my__orders_frag, container, false)
        var rc_my_order: RecyclerView = inflatere.findViewById(R.id.rc_my_order)
        var adapter_my_order = Adapter_my_order(this, requireContext())
        var back_button_my_order: ImageView = inflatere.findViewById(R.id.back_button_my_order)
        var non_order_white: ImageView = inflatere.findViewById(R.id.icon_none_order_white)
        var non_text: TextView = inflatere.findViewById(R.id.title_none_order_white)

        back_button_my_order.setOnClickListener{
            dataModel.message_profile.value = true
        }

        dataModel.date_list_order.observe(this, {

            for(items_order in it.reversed()){
                non_text.isGone = true
                non_order_white.isGone = true
                adapter_my_order.addProduct(Product_my_order(it.indexOf(items_order), items_order[0], items_order[5], items_order[6]))
            }

        })

        non_text.isGone = false
        non_order_white.isGone = false

        rc_my_order.layoutManager = LinearLayoutManager(context)
        rc_my_order.adapter = adapter_my_order

        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = My_Orders_frag()
    }

    override fun onClick(product: Product_my_order) {
        dataModel.message_my_order_track.value = true
        dataModel.index_order.value = product.id
    }
}