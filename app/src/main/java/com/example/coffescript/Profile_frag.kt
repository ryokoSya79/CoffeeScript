package com.example.coffescript

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.fragment.app.activityViewModels

class Profile_frag : Fragment() {

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
        val inflatere = inflater.inflate(R.layout.fragment_profile_frag, container, false)

        val view_setting: View = inflatere.findViewById(R.id.background_view_setting)
        val strelka_setting: ImageView = inflatere.findViewById(R.id.strelka_setting)
        val icon_setting: ImageView = inflatere.findViewById(R.id.icon_setting)
        val text_setting: TextView = inflatere.findViewById(R.id.title_setting_profile)

        val background_view_policy: View = inflatere.findViewById(R.id.background_view_policy)
        val strelka_policy: ImageView = inflatere.findViewById(R.id.strelka_policy)
        val icon_policy: ImageView = inflatere.findViewById(R.id.icon_policy)
        val title_policy_profile: TextView = inflatere.findViewById(R.id.title_policy_profile)

        val background_view_log_out: View = inflatere.findViewById(R.id.background_view_log_out)
        val strelka_logout: ImageView = inflatere.findViewById(R.id.strelka_logout)
        val icon_logout: ImageView = inflatere.findViewById(R.id.icon_logout)
        val title_log_out: TextView = inflatere.findViewById(R.id.title_log_out)

        val button_no_logout: Button = inflatere.findViewById(R.id.button_no_logout)
        val button_yes_logout: Button = inflatere.findViewById(R.id.button_yes_logout)
        val shadow_logout: ImageView = inflatere.findViewById(R.id.shadow_logout)
        val logout_acc_fragment: FrameLayout = inflatere.findViewById(R.id.logout_acc_fragment)

        val background_view_order: View = inflatere.findViewById(R.id.background_view_order)
        val strelka_order: ImageView = inflatere.findViewById(R.id.strelka_order)
        val icon_order: ImageView = inflatere.findViewById(R.id.icon_order)
        val title_order_profile: TextView = inflatere.findViewById(R.id.title_order_profile)

        val text_name_profile: TextView = inflatere.findViewById(R.id.text_name_profile)


        val db_profile = DataBase_Profile.getDb_Profile(requireContext())
        val db = DataBase_Cart.getDb(requireContext())

        shadow_logout.isGone = true
        logout_acc_fragment.isGone = true
        button_yes_logout.isEnabled = false

        background_view_order.setOnClickListener{
            dataModel.message_my_order.value = true
        }
        strelka_order.setOnClickListener{
            dataModel.message_my_order.value = true
        }
        icon_order.setOnClickListener{
            dataModel.message_my_order.value = true
        }
        title_order_profile.setOnClickListener{
            dataModel.message_my_order.value = true
        }

        Thread{
            var items = db_profile.getDao_Profile().getAllItems()
            Log.d("DB_CART", "--------------------------------------")
            activity?.runOnUiThread {
                items.forEach { item ->
                    text_name_profile.text = item.name
                }
            }

        }.start()

        shadow_logout.setOnClickListener{
            shadow_logout.isGone = true
            logout_acc_fragment.isGone = true
            button_yes_logout.isEnabled = false
        }

        button_no_logout.setOnClickListener{
            shadow_logout.isGone = true
            logout_acc_fragment.isGone = true
            button_yes_logout.isEnabled = false
        }

        button_yes_logout.setOnClickListener{
            Thread{
                db_profile.getDao_Profile().deleteAllItems()
                db_profile.getDao_Profile().resetAutoIncrement()
                db.getDao().deleteAllItems()
                db.getDao().resetAutoIncrement()
            }.start()
            dataModel.message_sing_frag.value = true

        }

        background_view_log_out.setOnClickListener{
            logout_acc_fragment.isGone = false
            shadow_logout.isGone = false
            button_yes_logout.isEnabled = true

        }
        strelka_logout.setOnClickListener{
            logout_acc_fragment.isGone = false
            shadow_logout.isGone = false
            button_yes_logout.isEnabled = true

        }
        icon_logout.setOnClickListener{
            logout_acc_fragment.isGone = false
            shadow_logout.isGone = false
            button_yes_logout.isEnabled = true

        }
        title_log_out.setOnClickListener{
            logout_acc_fragment.isGone = false
            shadow_logout.isGone = false
            button_yes_logout.isEnabled = true

        }

        view_setting.setOnClickListener{
            dataModel.message_setting.value = true
        }
        strelka_setting.setOnClickListener{
            dataModel.message_setting.value = true
        }
        icon_setting.setOnClickListener{
            dataModel.message_setting.value = true
        }
        text_setting.setOnClickListener{
            dataModel.message_setting.value = true
        }

        background_view_policy.setOnClickListener{
            dataModel.message_policy.value = true
        }
        strelka_policy.setOnClickListener{
            dataModel.message_policy.value = true
        }
        icon_policy.setOnClickListener{
            dataModel.message_policy.value = true
        }
        title_policy_profile.setOnClickListener{
            dataModel.message_policy.value = true
        }

        return inflatere
    }

    companion object {
        @JvmStatic
        fun newInstance() = Profile_frag()
    }
}