package com.example.coffescript

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract.Data
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffescript.Model.Product_cart
import org.w3c.dom.Text

class Cart_frag : Fragment(), Adapter_Cart.Listener {

    val dataModel: DataModel by activityViewModels()
    private var sub_text: TextView? = null
    private var rc_item_product_cart: RecyclerView? = null
    private var text_total: TextView? = null
    private var shipping_text: TextView? = null
    private var title_kol_items: TextView? = null

    var global_price = 0

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inflatere = inflater.inflate(R.layout.fragment_cart_frag, container, false)

        rc_item_product_cart = inflatere.findViewById(R.id.rc_item_products_cart)
        var adapter_rc_cart = Adapter_Cart(this, requireContext())
        title_kol_items = inflatere.findViewById(R.id.title_have_item_2)
        sub_text = inflatere.findViewById(R.id.text_sub_total)
        shipping_text = inflatere.findViewById(R.id.text_shipping)
        text_total = inflatere.findViewById(R.id.text_total)
        var button_order: Button = inflatere.findViewById(R.id.button_finalize_order)

        var edit_promokod: EditText = inflatere.findViewById(R.id.editText_promokod)
        var button_apply: Button = inflatere.findViewById(R.id.button_apply_promokod)
        var none_image: ImageView = inflatere.findViewById(R.id.icon_none_order)
        var text_non_order: ImageView = inflatere.findViewById(R.id.title_none_order)


        rc_item_product_cart?.layoutManager = LinearLayoutManager(context)
        rc_item_product_cart?.adapter = adapter_rc_cart

        button_apply.setOnClickListener{
            if (edit_promokod.text.toString() == "Karamelka"){
                sub_text?.text = "$0"
                shipping_text?.text = "$0"
                text_total?.text = "$0"
                Log.d("DB_CART",
                    "Сработало !!!"
                )
            }
        }

        button_order.setOnClickListener{
            dataModel.message_order.value = true
        }


        rc_item_product_cart?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    adapter_rc_cart.closeAllItems()
                }
            }
        })



        val db = DataBase_Cart.getDb(requireContext())

        Thread {
            val items = db.getDao().getAllItems()
//            db.getDao().deleteAllItems()
//            db.getDao().resetAutoIncrement()
            if(adapter_rc_cart.productList.isNotEmpty()){
                text_non_order.isGone = true
                none_image.isGone = true
            }else{
                text_non_order.isGone = false
                none_image.isGone = false
            }

            requireActivity().runOnUiThread {
                items.forEach { item ->
                    text_non_order.isGone = true
                    none_image.isGone = true
                    adapter_rc_cart.addProduct(
                        Product_cart(
                            item.id_image.toInt(),
                            item.name_product,
                            item.title_price,
                            item.title_elem,
                            item.title_kol
                        )
                    )

                    global_price += item.title_price.toInt()

                    if (item.id == 1){
                        title_kol_items?.text = "${item.id.toString()} item"
                    }else title_kol_items?.text = "${item.id.toString()} items"
//                    Log.d("DB_CART",
//                        "ID: ${item.id} | " +
//                                "Товар: ${item.name_product} | " +
//                                "Цена: ${item.title_price} | " +
//                                "Состав: ${item.title_elem} | " +
//                                "Кол-во: ${item.title_kol}"
//                    )
//                    Log.d("DB_CART",
//                        "Gloval price: ${global_price} | "
//                    )

                    sub_text?.text = "$${global_price}"
                    shipping_text?.text = "$6"
                    text_total?.text = "$${(global_price+6)}"

                    activity?.runOnUiThread {

                        sub_text?.text = "$${global_price}"
                        shipping_text?.text = "$6"
                        text_total?.text = "$${(global_price+6)}"
                    }

                }

            }
        }.start()

        return inflatere
    }



    companion object {
        @JvmStatic
        fun newInstance() = Cart_frag()
    }

    override fun onClick(product: Product_cart) {
        Handler(Looper.getMainLooper()).postDelayed({

            dataModel.price_predator.value = product.title_price
            sub_text?.text = "$${global_price}"

            val db = DataBase_Cart.getDb(requireContext())
//        var adapter_rc_cart = Adapter_Cart(this, requireContext())
//        rc_item_product_cart?.layoutManager = LinearLayoutManager(context)
//        rc_item_product_cart?.adapter = adapter_rc_cart

            global_price = 0

            Thread {

                val items = db.getDao().getAllItems()

                activity?.runOnUiThread {
                    if (items.isEmpty()){
                        sub_text?.text = "$0"
                        shipping_text?.text = "$0"
                        text_total?.text = "$0"
                        title_kol_items?.text = "0 item"

                        Log.d("DB_CART",
                            "Что-то тип сработала ${sub_text?.text}, ${shipping_text?.text}, ${text_total?.text}"
                        )
                    }
                }

                requireActivity().runOnUiThread {
                    items.forEach { item ->

                        Log.d("DB_CART",
                            "Пустой лист ${items}"
                        )

                        global_price += item.title_price.toInt()

                        if (item.id == 1){
                            title_kol_items?.text = "${item.id.toString()} item"
                        }else title_kol_items?.text = "${item.id.toString()} items"
//                    Log.d("DB_CART",
//                        "ID: ${item.id} | " +
//                                "Товар: ${item.name_product} | " +
//                                "Цена: ${item.title_price} | " +
//                                "Состав: ${item.title_elem} | " +
//                                "Кол-во: ${item.title_kol}"
//                    )
                        Log.d("DB_CART",
                            "Gloval price: ${global_price} | "
                        )

                        sub_text?.text = "$${global_price}"
                        shipping_text?.text = "$6"
                        text_total?.text = "$${(global_price+6)}"

                        activity?.runOnUiThread {

                            sub_text?.text = "$${global_price}"
                            shipping_text?.text = "$6"
                            text_total?.text = "$${(global_price+6)}"
                        }

                    }

                }
            }.start()
        }, 100)


//        rc_item_product_cart?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
//                    adapter_rc_cart.closeAllItems()
//                }
//            }
//        })
    }
}