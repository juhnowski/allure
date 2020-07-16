package ru.allurecalze.allure

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import ru.allurecalze.allure.json.AdvertInfo

var mPosition_advert = -1
val advResources: IntArray = intArrayOf(R.drawable.ph_0, R.drawable.ph_1,R.drawable.ph_2,R.drawable.ph_3,
        R.drawable.ph_4,R.drawable.ph_5,R.drawable.ph_6,R.drawable.ph_7,R.drawable.ph_8,R.drawable.ph_9,R.drawable.ph_10)

class AdvertHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    override fun onClick(v: View?) {
        context.startActivity(Intent(context, AdvertView::class.java))
        val activity = context as Activity
        activity.overridePendingTransition(R.xml.right_to_left1, R.xml.left_to_right_1)
    }

    var avatar: ImageView
    var name: TextView
    var status: TextView
    var picture: ImageView
    //var isOffer: CheckBox
    var title: TextView
    var description: TextView
    val context = itemView.getContext();
    init {
        avatar = itemView.findViewById(R.id.advert_iv_avatar)
        name = itemView.findViewById(R.id.advert_tv_name)
        status = itemView.findViewById(R.id.advert_tv_publish_status)
        picture = itemView.findViewById(R.id.advert_iv_picture)
        //isOffer = itemView.findViewById(R.id.advert_cb_is_offer)
        title = itemView.findViewById(R.id.advert_tv_title)
        description = itemView.findViewById(R.id.advert_tv_description)
    }

    fun bind(advert: AdvertInfo) {

        val ava = advert.small_avatar
        if ((ava!=null)&&(ava.length > 4)) {
            val avaAsBytes = Base64.decode(ava, 0)
            avatar.setImageBitmap(BitmapFactory.decodeByteArray(avaAsBytes, 0, avaAsBytes.size))
            //avatar.setImageResource(R.drawable.honestgave);
        } else {
            avatar.setImageResource(R.mipmap.logo);
        }
        name?.setText("${advert.name} ${advert.surname}")

//        if (Storage.PAYMENTS.size>0) {
//            if (advert.type_payments_id != null) {
//                status?.setText(Storage.PAYMENTS.get(advert.type_payments_id).name)
//            } else {
//                status?.setText("")
//            }
//        }

        when (advert.id) {
            0 -> status?.setText("КОЛГОТКИ \"ALLURE Classic\"")
            1 -> status?.setText("КОЛГОТКИ \"ALLURE BRILLIANT\"")
            2 -> status?.setText("БОТФОРТЫ \"ALLURE BRILLIANT\"")
            3 -> status?.setText("ЖЕНСКИЕ ГОЛЬФЫ \"ALLURE\"")
            4 -> status?.setText("\"ALLURE BRILLIANT MICROFIBRA\"")
            5 -> status?.setText("КОЛГОТКИ \"ALLURE FASHION\"")
            6 -> status?.setText("КОЛГОТКИ \"ALLURE NATURAL\"")
            7 -> status?.setText("КОЛГОТКИ \"ALLURE BABY\"")
            8 -> status?.setText("ЖЕНСКИЕ НОСКИ \"ALLURE\"")
            9 -> status?.setText("ЖЕНСКИЕ ЛЕГГИНСЫ \"ALLURE\"")
            10 -> status?.setText("КОЛГОТКИ C УТЯЖКОЙ \"ALLURE\"")
        }

        picture.setImageResource(advResources[advert.id!!])

//        if (advert.pictures.size > 0) {
//            if (advert.pictures.get(0).base64pic.length > 4) {
//                picture.setImageResource(mResources[advert.id!!])
//                //val imageAsBytes = Base64.decode(advert.pictures.get(0).base64pic, 0)
//                //picture.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size))
//            }
//        }

//        if (advert.publish_status == 1) {
//            isOffer.isChecked = true
//        } else {
//            isOffer.isChecked = false
//        }
        title.setText(advert.title)
        description.setText(advert.description)
    }
}

class AdvertListAdapter(private val context: Context, private val advertList: List<AdvertInfo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view: View
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_advert, parent, false)
        return AdvertHolder(view)
    }

    override fun getItemCount(): Int {
        return advertList.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, position: Int) {
        val advert = advertList[position]
        val holder: RecyclerView.ViewHolder = p0
        (holder as AdvertHolder).bind(advert)

//        val isOffer = p0.itemView.findViewById<CheckBox>(R.id.advert_cb_is_offer)
//        isOffer.setOnClickListener(View.OnClickListener {
//            Log.d("Advert", "isOffer.setOnClickListener")
//        }
//        )

       p0.itemView.setOnClickListener(View.OnClickListener {
           mPosition_advert = position
           val context = it.getContext();
           Log.d("onBindViewHolder","onClick")
           context.startActivity(Intent(context, AdvertView::class.java))
           val activity = context as Activity
           activity.overridePendingTransition(R.xml.right_to_left1, R.xml.left_to_right_1)
        })
    }
}