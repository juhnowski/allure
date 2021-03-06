package ru.allurecalze.allure

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.TextInputEditText
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import ru.allurecalze.allure.json.*
import com.google.gson.internal.LinkedTreeMap

var mChat_id:Int = -1

class AdvertView : AppCompatActivity(), ResponseProcessable {

    val TAG = "AdvertView"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advert_view)
        val title = findViewById<TextView>(R.id.advert_view_text_view_title)
        val category = findViewById<TextView>(R.id.advert_view_text_view_category)
        val pb = findViewById<ProgressBar>(R.id.pb_advert_view)
        pb.isIndeterminate = true
        pb.visibility = View.INVISIBLE

        val viewPager = findViewById<View>(R.id.pager) as ViewPager

//        val mResources = intArrayOf(
//            R.drawable.winx1,
//            R.drawable.winx2,
//            R.drawable.winx3
//        )

        Log.d(TAG,"mPosition_advert = ${mPosition_advert}")
        val advertInfo = advertList.get(mPosition_advert)

        title.text = advertInfo.title
//        if (advertInfo.category_id!=null) {
////            category.text = Storage.CATEGORY.get(advertInfo.category_id).name
//        } else {
//            category.text = ""
//        }

        val pictureList = ArrayList<Picture>() //advertInfo.pictures
        when(mPosition_advert){
            0 -> {
                pictureList.add(Picture("","","0"))
                pictureList.add(Picture("","","1"))
                pictureList.add(Picture("","","2"))
                pictureList.add(Picture("","","3"))
                pictureList.add(Picture("","","4"))
                pictureList.add(Picture("","","5"))
                pictureList.add(Picture("","","6"))
                pictureList.add(Picture("","","7"))
            }
            1 -> {
                pictureList.add(Picture("","","0"))
                pictureList.add(Picture("","","1"))
                pictureList.add(Picture("","","2"))
                pictureList.add(Picture("","","3"))
                pictureList.add(Picture("","","4"))
                pictureList.add(Picture("","","5"))
                pictureList.add(Picture("","","6"))
            }
            2 -> {
                pictureList.add(Picture("", "", "0"))
            }
            3 -> {
                pictureList.add(Picture("","","0"))
                pictureList.add(Picture("","","1"))
                pictureList.add(Picture("","","2"))
                pictureList.add(Picture("","","3"))
                pictureList.add(Picture("","","4"))
                pictureList.add(Picture("","","5"))
                pictureList.add(Picture("","","6"))
                pictureList.add(Picture("","","7"))
                pictureList.add(Picture("","","8"))
                pictureList.add(Picture("","","9"))
                pictureList.add(Picture("","","10"))
                pictureList.add(Picture("","","11"))
                pictureList.add(Picture("","","12"))
            }
            4 -> {
                pictureList.add(Picture("","","0"))
                pictureList.add(Picture("","","1"))
                pictureList.add(Picture("","","2"))
                pictureList.add(Picture("","","3"))
                pictureList.add(Picture("","","4"))
                pictureList.add(Picture("","","5"))
                pictureList.add(Picture("","","6"))
            }
            5 -> {
                pictureList.add(Picture("","","0"))
                pictureList.add(Picture("","","1"))
                pictureList.add(Picture("","","2"))
                pictureList.add(Picture("","","3"))
                pictureList.add(Picture("","","4"))
                pictureList.add(Picture("","","5"))
                pictureList.add(Picture("","","6"))
                pictureList.add(Picture("","","7"))
                pictureList.add(Picture("","","8"))
                pictureList.add(Picture("","","9"))
                pictureList.add(Picture("","","10"))
                pictureList.add(Picture("","","11"))
                pictureList.add(Picture("","","12"))

            }
            6 -> {
                pictureList.add(Picture("","","0"))
                pictureList.add(Picture("","","1"))
                pictureList.add(Picture("","","2"))
                pictureList.add(Picture("","","3"))
            }
            7 -> {
                pictureList.add(Picture("","","0"))
                pictureList.add(Picture("","","1"))
            }
            8 -> {
                pictureList.add(Picture("","","0"))
                pictureList.add(Picture("","","1"))
                pictureList.add(Picture("","","2"))
                pictureList.add(Picture("","","3"))
            }
            9 -> {
                pictureList.add(Picture("","","0"))
                pictureList.add(Picture("","","1"))
            }
            10 -> {
                pictureList.add(Picture("","","0"))
                pictureList.add(Picture("","","1"))
                pictureList.add(Picture("","","2"))
            }
        }

        val mCustomPagerAdapter = CustomPagerAdapter(this, pictureList)
        viewPager.setAdapter(mCustomPagerAdapter)
    }

    override fun onBackPressed() {
        Log.d(TAG,"onBackPressed")
        super.onBackPressed()
        overridePendingTransition(R.xml.left_to_right, R.xml.right_to_left)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                super.onBackPressed()
                overridePendingTransition(R.xml.left_to_right, R.xml.right_to_left)
                return true
            }
            R.id.advert_view_menu_chat -> {
               // ServerTask(prepareCreateChat1on1(), this@AdvertView, this).execute()
                startActivity(Intent(this,MessageListActivity::class.java))
                overridePendingTransition( R.xml.right_to_left1, R.xml.left_to_right_1)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_advert_view, menu)
        return true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun prepareCreateChat1on1(): String {

        val pb = findViewById<ProgressBar>(R.id.pb_advert_view)
        pb.visibility = View.VISIBLE
        val textToSearch = findViewById<TextInputEditText>(R.id.search_options_text_to_serch)
        val advert = advertList.get(mPosition_advert)
        if (advert.id == null){
            return ""
        }

        val msg = ServerRequest(
            getInfo(this@AdvertView),
            NAME_CREATE_CHAT_1_ON_1,
            CreateChat1on1_RequestData(
                advert_id = advert.id,
                text = "Hello"
                )
        ).json()

        Log.d(TAG,"msg=$msg")
        return msg
    }

    override fun handleResponse(resp: Response) {
        val pb = findViewById<ProgressBar>(R.id.pb_advert_view)
        pb.visibility = View.INVISIBLE
        Log.d(TAG, "handleResponse CreateChat1on1")
        if (resp?.status.equals("0")) {
            Log.d(TAG, "status = 0")
            Log.d(TAG, "data = ${resp?.data.toString()}")
            val ld = resp.data as LinkedTreeMap<String, Any>
            current_chat_id = ld.get("chat_id").toString().replace(".0", "").toInt()

            startActivity(Intent(this,MessageListActivity::class.java))
            overridePendingTransition( R.xml.right_to_left1, R.xml.left_to_right_1)
        } else {
            if (resp?.status.equals("4")) {
                setLoggedIn(this@AdvertView, false)
                gotoLogin(this@AdvertView)
            }

            Log.d(TAG, "handleResponse error message: ${resp?.message} status = ${resp?.status} error=${resp?.error}")
            Toast.makeText(this@AdvertView, resp?.error, Toast.LENGTH_SHORT).show()

        }
        Log.d(TAG, "handleResponse done")
        super.onBackPressed()
        overridePendingTransition(R.xml.left_to_right, R.xml.right_to_left)
    }

}