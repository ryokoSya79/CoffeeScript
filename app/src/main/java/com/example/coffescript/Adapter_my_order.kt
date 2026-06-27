package com.example.coffescript

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.coffescript.Model.Product
import com.example.coffescript.Model.Product_cart
import com.example.coffescript.Model.Product_my_order
import org.w3c.dom.Text

class Adapter_my_order (val listener: Listener, private val myContext: Context): RecyclerView.Adapter<Adapter_my_order.Holder>() {
    var productList = ArrayList<Product_my_order>()

    var global_price = 0

    private var openedViewHolder: Holder? = null

    class Holder (itemView: View, private val context: Context): RecyclerView.ViewHolder(itemView){
        var data_my_order: TextView = itemView.findViewById(R.id.data_my_order)
        var price: TextView = itemView.findViewById(R.id.price_data_my_order)
        var qty: TextView = itemView.findViewById(R.id.qty_data_my_order)
        var button_to_track_data_my_order: Button = itemView.findViewById(R.id.button_to_track_data_my_order)

        fun bind(listItem: Product_my_order, listener: Listener, myContext: Context, list_data: ArrayList<Product_my_order>, adapterCart: Adapter_my_order, global_price: Int, onSwipeOpened: (Holder) -> Unit){
            data_my_order.text = "Order (${listItem.data})"
//            price.text = "With ${listItem.price_my_order}"
            price.text = "$${listItem.price_my_order}"

            qty.text = "Qty: ${listItem.qty}"

            button_to_track_data_my_order.setOnClickListener{
                listener.onClick(listItem)
            }

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_my_order.Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_prod_my_order, parent, false)
        return Holder(itemView, myContext)
    }

    override fun onBindViewHolder(holder: Adapter_my_order.Holder, position: Int) {

        holder.bind(productList[position], listener, myContext, productList, this, global_price) { openedHolder ->
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun closeAllItems() {
        openedViewHolder = null
    }

    fun addProduct(product: Product_my_order){
        productList.add(product)
        notifyDataSetChanged()
    }

    fun removeProduct(itemIndex: Int){
        productList.removeAt(itemIndex)
        notifyItemRemoved(itemIndex)
    }

    interface Listener{
        fun onClick(product: Product_my_order)
    }

}