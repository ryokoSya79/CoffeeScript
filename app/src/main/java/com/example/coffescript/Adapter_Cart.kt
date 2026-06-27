package com.example.coffescript

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.coffescript.Model.Product
import com.example.coffescript.Model.Product_cart
import org.w3c.dom.Text

class Adapter_Cart (val listener: Listener, private val myContext: Context): RecyclerView.Adapter<Adapter_Cart.Holder>() {
    var productList = ArrayList<Product_cart>()

    var global_price = 0



    private var openedViewHolder: Holder? = null

    class Holder (itemView: View, private val context: Context): RecyclerView.ViewHolder(itemView){
        var name_item: TextView = itemView.findViewById(R.id.name_item)
        var image_item: ImageView = itemView.findViewById(R.id.image_item)
        var title_elem: TextView = itemView.findViewById(R.id.title_elem)
        var title_price: TextView = itemView.findViewById(R.id.title_price)
        var id: Int? = 0
        var title_kol_item: TextView = itemView.findViewById(R.id.title_kol_item)
        var backgroundView: View = itemView.findViewById(R.id.background_view)
        var button_plus_cart: ImageView = itemView.findViewById(R.id.button_plus_cart)
        var button_minus_cart: ImageView = itemView.findViewById(R.id.button_minus_cart)
        var delete_button: ImageView = itemView.findViewById(R.id.delete_button)



        var isOpened = false

        fun bind(listItem: Product_cart, listener: Listener, myContext: Context,list_data: ArrayList<Product_cart>, adapterCart: Adapter_Cart, global_price: Int, onSwipeOpened: (Holder) -> Unit){
            name_item.text = listItem.name
            title_elem.text = "With ${listItem.title_elem}"
            title_price.text = "$${listItem.title_price}"
            id = listItem.id
            title_kol_item.text = listItem.title_kol_item

//            var proeb_price =

            val db = DataBase_Cart.getDb(context)

            var const_price = listItem.title_price.toString().toInt()/title_kol_item.text.toString().toInt()

            button_plus_cart.setOnClickListener{
                if(title_kol_item.text.toString().toInt() < 20){
                    var int_kol = title_kol_item.text.toString().toInt()+1
                    title_kol_item.text = int_kol.toString()
                    title_price.text = "$${ (const_price * int_kol).toString() }"

                    var position_int: Int = adapterPosition
                    if(position_int != RecyclerView.NO_POSITION){
                        Thread {
                            val db = DataBase_Cart.getDb(context.applicationContext as Context)
                            db.getDao().updatePriceById(position_int+1, "${ (const_price * int_kol).toString() }")
                            db.getDao().updateKolById(position_int+1, "${ (int_kol).toString() }")
                        }.start()

                        (context as? Activity)?.runOnUiThread {
                            listener.onClick(listItem)
                        }
                    }
                }
            }

            button_minus_cart.setOnClickListener{
                if(title_kol_item.text.toString().toInt() > 1){
                    var int_kol = title_kol_item.text.toString().toInt()-1
                    title_kol_item.text = int_kol.toString()
                    title_price.text = "$${ (const_price * int_kol).toString() }"

                    var position_int: Int = adapterPosition
                    if(position_int != RecyclerView.NO_POSITION){
                        Thread {
                            val db = DataBase_Cart.getDb(context.applicationContext as Context)
                            db.getDao().updatePriceById(position_int+1, "${ (const_price * int_kol).toString() }")
                            db.getDao().updateKolById(position_int+1, "${ (int_kol).toString() }")
                        }.start()
                        listener.onClick(listItem)
                    }

                }
            }

            Log.i("DB_CART", "${global_price}")

            delete_button.setOnClickListener{
                var position_int: Int = adapterPosition
                if(position_int != RecyclerView.NO_POSITION){
                    list_data.removeAt(position_int)
                    Thread {
                        val db = DataBase_Cart.getDb(context.applicationContext as Context)
//                        db.getDao().deleteItemById(position_int+1)
                        db.getDao().deleteAndRebuild(position_int+1)
                    }.start()

                    (context as? Activity)?.runOnUiThread {
                        listener.onClick(listItem)
                    }
                }

                adapterCart.notifyItemRemoved(position)
                adapterCart.notifyItemRangeChanged(position, list_data.size)
            }


            val resourceId = context.resources.getIdentifier("product_$id", "drawable", context.packageName)
            image_item.setImageResource(resourceId)

            itemView.setOnTouchListener(object : OnSwipeTouchListener(context){
                override fun onSwipeLeft(){
                    if (!isOpened) {
                        // Закрываем предыдущий открытый элемент
                        onSwipeOpened(this@Holder)


//                        itemView.animate().translationX(-200f).setDuration(500).start()
                        image_item.animate().translationX(-200f).setDuration(500).start()
                        name_item.animate().translationX(-200f).setDuration(500).start()
                        title_price.animate().translationX(-200f).setDuration(500).start()
                        title_elem.animate().translationX(-200f).setDuration(500).start()
                        title_kol_item.animate().translationX(-200f).setDuration(500).start()
                        backgroundView.animate().translationX(-200f).setDuration(500).start()
                        button_minus_cart.animate().translationX(-200f).setDuration(500).start()
                        button_plus_cart.animate().translationX(-200f).setDuration(500).start()

//                        delete_button.animate().translationX(200f).setDuration(500).start()

                        isOpened = true
                    }
                }

                override fun onSwipeRight() {
//                    itemView.animate().translationX(0f).setDuration(500).start()
                    image_item.animate().translationX(0f).setDuration(500).start()
                    name_item.animate().translationX(0f).setDuration(500).start()
                    title_price.animate().translationX(0f).setDuration(500).start()
                    title_elem.animate().translationX(0f).setDuration(500).start()
                    title_kol_item.animate().translationX(0f).setDuration(500).start()
                    backgroundView.animate().translationX(0f).setDuration(500).start()
                    button_minus_cart.animate().translationX(0f).setDuration(500).start()
                    button_plus_cart.animate().translationX(0f).setDuration(500).start()

//                    delete_button.animate().translationX(0f).setDuration(500).start()


                }
            })
        }
        fun closeItem() {
            if (isOpened) {
//                itemView.animate().translationX(0f).setDuration(500).start()

                image_item.animate().translationX(0f).setDuration(500).start()
                name_item.animate().translationX(0f).setDuration(500).start()
                title_price.animate().translationX(0f).setDuration(500).start()
                title_elem.animate().translationX(0f).setDuration(500).start()
                title_kol_item.animate().translationX(0f).setDuration(500).start()
                backgroundView.animate().translationX(0f).setDuration(500).start()
                button_minus_cart.animate().translationX(0f).setDuration(500).start()
                button_plus_cart.animate().translationX(0f).setDuration(500).start()

//                delete_button.animate().translationX(0f).setDuration(500).start()

                isOpened = false

            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_Cart.Holder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_prod_cart, parent, false)
        return Holder(itemView, myContext)
    }

    override fun onBindViewHolder(holder: Adapter_Cart.Holder, position: Int) {

        holder.bind(productList[position], listener, myContext, productList, this, global_price) { openedHolder ->
            // Закрываем предыдущий открытый элемент, если он есть и это не тот же самый
            openedViewHolder?.let {
                if (it != openedHolder) {
                    it.closeItem()
                }
            }
            // Запоминаем новый открытый элемент
            openedViewHolder = openedHolder
        }

        // Если элемент переиспользуется, убеждаемся что он закрыт
        if (!holder.isOpened) {
            holder.itemView.translationX = 0f
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun closeAllItems() {
        openedViewHolder?.closeItem()
        openedViewHolder = null
    }

    fun addProduct(product: Product_cart){
        productList.add(product)
        notifyDataSetChanged()
    }

    fun removeProduct(itemIndex: Int){
        productList.removeAt(itemIndex)
        notifyItemRemoved(itemIndex)
    }

    interface Listener{
        fun onClick(product: Product_cart)
    }

}