package ru.allurecalze.allure

import android.content.Context
import android.widget.LinearLayout
import android.view.ViewGroup
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.support.v4.view.PagerAdapter
import android.util.Base64
import android.view.View
import android.widget.ImageView
import ru.allurecalze.allure.json.Picture


val mResources_0: IntArray = intArrayOf(R.drawable.photo_00, R.drawable.photo_01, R.drawable.photo_02, R.drawable.photo_03, R.drawable.photo_04,
    R.drawable.photo_05,R.drawable.photo_06,R.drawable.photo_07)
val mResources_1: IntArray = intArrayOf(R.drawable.photo_10, R.drawable.photo_11, R.drawable.photo_12, R.drawable.photo_13, R.drawable.photo_14, R.drawable.photo_15, R.drawable.photo_16)
val mResources_2: IntArray = intArrayOf(R.drawable.photo_20)
val mResources_3: IntArray = intArrayOf(R.drawable.photo_30,R.drawable.photo_31,R.drawable.photo_32,R.drawable.photo_33,R.drawable.photo_34,R.drawable.photo_35,R.drawable.photo_36,R.drawable.photo_37,R.drawable.photo_38,R.drawable.photo_39,
    R.drawable.photo_310,R.drawable.photo_311,R.drawable.photo_312)
val mResources_4: IntArray = intArrayOf(R.drawable.photo_40, R.drawable.photo_41, R.drawable.photo_42, R.drawable.photo_43, R.drawable.photo_44,
    R.drawable.photo_45,R.drawable.photo_46)
val mResources_5: IntArray = intArrayOf(R.drawable.photo_51,R.drawable.photo_52,R.drawable.photo_53,R.drawable.photo_54,R.drawable.photo_55,R.drawable.photo_56,R.drawable.photo_57,R.drawable.photo_58,R.drawable.photo_59,
    R.drawable.photo_510,R.drawable.photo_511,R.drawable.photo_512,R.drawable.photo_512)
val mResources_6: IntArray = intArrayOf(R.drawable.photo_60, R.drawable.photo_61, R.drawable.photo_62, R.drawable.photo_63)
val mResources_7: IntArray = intArrayOf(R.drawable.photo_70, R.drawable.photo_71)
val mResources_8: IntArray = intArrayOf(R.drawable.photo_80, R.drawable.photo_81, R.drawable.photo_82, R.drawable.photo_83, R.drawable.photo_84)
val mResources_9: IntArray = intArrayOf(R.drawable.photo_90, R.drawable.photo_63)
val mResources_10: IntArray = intArrayOf(R.drawable.photo_100, R.drawable.photo_101, R.drawable.photo_102)

class CustomPagerAdapter(private val context: Context, private val pictures: ArrayList<Picture>) : PagerAdapter() {

    override fun getCount(): Int {
        return pictures.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        val itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false)
        val itemView = LayoutInflater.from(context).inflate(R.layout.pager_item, null)

        val imageView = itemView.findViewById(R.id.advert_view_pager_image_view) as ImageView
        when (mPosition_advert){
            0 -> imageView.setImageResource(mResources_0[position])
            1 -> imageView.setImageResource(mResources_1[position])
            2 -> imageView.setImageResource(mResources_2[position])
            3 -> imageView.setImageResource(mResources_3[position])
            4 -> imageView.setImageResource(mResources_4[position])
            5 -> imageView.setImageResource(mResources_5[position])
            6 -> imageView.setImageResource(mResources_6[position])
            7 -> imageView.setImageResource(mResources_7[position])
            8 -> imageView.setImageResource(mResources_8[position])
            9 -> imageView.setImageResource(mResources_9[position])
            10 -> imageView.setImageResource(mResources_10[position])
        }

//        val imageAsBytes = Base64.decode(pictures.get(position).base64pic, 0)
//        imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size))

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }

}
