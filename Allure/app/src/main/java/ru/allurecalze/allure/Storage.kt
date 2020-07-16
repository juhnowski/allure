package ru.allurecalze.allure

import android.util.Log
import ru.allurecalze.allure.json.*
import com.google.gson.internal.LinkedTreeMap
import kotlin.collections.ArrayList


object Storage {

    val TAG = "Storage"
    @JvmField
    val CATEGORY: ArrayList<Category> = ArrayList<Category>()
    @JvmField
    val KEYWORDS: ArrayList<Keywords> = ArrayList<Keywords>()
    @JvmField
    val LANGUIGE: ArrayList<Languige> = ArrayList<Languige>()
    @JvmField
    val PAYMENTS: ArrayList<Payments> = ArrayList<Payments>()
    @JvmField
    val ORDERS: ArrayList<Orders> = ArrayList<Orders>()

    init {
        ORDERS.add(Orders(id = 0, name = "По дате"))
        ORDERS.add(Orders(id = 1, name = "По цене"))
        ORDERS.add(Orders(id = 2, name = "По ключевым словам"))

        CATEGORY.add(Category(0,"КОЛГОТКИ \"ALLURE Classic\"",0))
        CATEGORY.add(Category(1,"КОЛГОТКИ \"ALLURE BRILLIANT\"",0))
        CATEGORY.add(Category(2,"БОТФОРТЫ \"ALLURE BRILLIANT\"",0))
        CATEGORY.add(Category(3,"ЖЕНСКИЕ ГОЛЬФЫ \"ALLURE\"",0))
        CATEGORY.add(Category(4,"ALLURE BRILLIANT MICROFIBRA",0))
        CATEGORY.add(Category(5,"КОЛГОТКИ \"ALLURE FASHION\"",0))
        CATEGORY.add(Category(6,"КОЛГОТКИ \"ALLURE NATURAL\"",0))
        CATEGORY.add(Category(7,"КОЛГОТКИ ДЛЯ БЕРЕМЕННЫХ",0))
        CATEGORY.add(Category(8,"ЖЕНСКИЕ НОСКИ \"ALLURE\"",0))
        CATEGORY.add(Category(9,"ЖЕНСКИЕ ЛЕГГИНСЫ \"ALLURE\"",0))
        CATEGORY.add(Category(10,"КОЛГОТКИ с УТЯЖКОЙ \"ALLURE\"",0))

        PAYMENTS.add(Payments(0,"Розница"))
        PAYMENTS.add(Payments(1,"Мелкий опт"))
        PAYMENTS.add(Payments(2,"Курпный опт"))

        LANGUIGE.add(Languige(0,"Caramello"))
        LANGUIGE.add(Languige(1,"Glasse"))
        LANGUIGE.add(Languige(2,"Cappuccino"))
        LANGUIGE.add(Languige(3,"Barolo"))
        LANGUIGE.add(Languige(4,"Nero"))
        LANGUIGE.add(Languige(5,"Fumo"))
        LANGUIGE.add(Languige(6,"Pietra"))
        LANGUIGE.add(Languige(7,"Sigaro"))
        LANGUIGE.add(Languige(8,"Viola Grey"))
        LANGUIGE.add(Languige(9,"Pois Mandoria"))

        KEYWORDS.add(0,Keywords(0,"теплые",0))
        KEYWORDS.add(1,Keywords(1,"тонкие",0))
        KEYWORDS.add(2,Keywords(2,"прозрачные",0))
        KEYWORDS.add(3,Keywords(3,"непрозрачные",0))
        KEYWORDS.add(4,Keywords(4,"матовые",0))
        KEYWORDS.add(5,Keywords(5,"меланж",0))
        KEYWORDS.add(6,Keywords(6,"классические",0))
        KEYWORDS.add(7,Keywords(7,"фантазийные",0))
        KEYWORDS.add(8,Keywords(8,"с поддерживающим эффектом",0))
        KEYWORDS.add(9,Keywords(9,"с усиленной верхней частью",0))
        KEYWORDS.add(10,Keywords(10,"с заниженной талией",0))
        KEYWORDS.add(11,Keywords(11,"с укрепленным мыском",0))
        KEYWORDS.add(12,Keywords(12,"с распределенным давлением по ногем",0))
        KEYWORDS.add(13,Keywords(13,"плоский шов",0))
        KEYWORDS.add(14,Keywords(14,"хлопковая ластовицав",0))
        KEYWORDS.add(15,Keywords(15,"микрофибра",0))
        KEYWORDS.add(16,Keywords(16,"лайкра",0))
        KEYWORDS.add(17,Keywords(17,"3D",0))


    }

    @JvmStatic
    fun parseCategory(list: ArrayList<LinkedTreeMap<String, Any>>) {
        list.forEach { ld ->
            run {
                val catId = ld.get("id").toString().replace(".0", "").toInt()
                val catName = ld.get("name").toString()
                val catParentCategory = ld.get("parent_category").toString()
                var catParentId = 0
                if (!catParentCategory.equals("null")) {
                    catParentId = catParentCategory.replace(".0", "").toInt()
                }

                CATEGORY.add(Category(catId, catName, catParentId))
                if (ld.get("listItems") != null) {
                    val listItems = ld.get("listItems") as ArrayList<LinkedTreeMap<String, Any>>
                    if (listItems != null) {
                        parseCategory(listItems)
                    }
                }
            }
        }
    }

    @JvmStatic
    fun parseKeywords(list: ArrayList<LinkedTreeMap<String, Any>>) {
        list.forEach { ld ->
            run {
                val keyId = ld.get("id").toString().replace(".0", "").toInt()
                val keyName = ld.get("name").toString()
                val keyParentCategory = ld.get("parent_category").toString()
                var keyParentId = 0
                if (!keyParentCategory.equals("null")) {
                    keyParentId = keyParentCategory.replace(".0", "").toInt()
                }
                Log.d(TAG, "Keyword: id=${keyId} name=${keyName} parent_category=${keyParentCategory}")
                KEYWORDS.add(Keywords(keyId, keyName, keyParentId))
            }
        }
    }
}


/*
var arrCategory = ld.get("category") as ArrayList<LinkedTreeMap<String, Any>>
                Log.d(TAG,"arrCategory = ${arrCategory.get(0).toString()}")
                //2018-12-12 14:47:07.065 6605-6605/ru.allurecalze.Allure D/LogInBySession: arrCategory = {id=1.0, name=Your Theme Blog, parent_category=null, listItems=null}

 */


/*
class Category(val id: Int, val name: String, val parent_category: Int, val listItems: List<Category>?)
class Keywords(val id: Int, val name: String, val parent_category: Int)
class Languige(val id: Int, val name: String)
class Payments(val id: Int, val name: String)
 */

/*
val ld = resp.data as LinkedTreeMap<String, Any>
            save(
                true,
                ld.get("session").toString(),
                ld.get("name").toString(),
                ld.get("surname").toString(),
                ld.get("insta_address").toString(),
                ld.get("insta_client_id").toString()
            )
 */