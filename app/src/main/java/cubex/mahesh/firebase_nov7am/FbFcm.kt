package cubex.mahesh.firebase_nov7am

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fbfcm.view.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class FbFcm : Fragment( ) {
    var fcm_token = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater.inflate(R.layout.fbfcm,container,false)

        var dBase = FirebaseDatabase.getInstance()
        var dRef = dBase.getReference("users/${selected_user_uid}")
        dRef.addValueEventListener(
            object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {

                    var children_uid = p0.children
                    children_uid.forEach {
                       when(it.key)
                       {
                           "name" ->{v.uname.text = it.value.toString() }
                           "fcm_token"->{
                                fcm_token = it.value.toString()
                           }
                           "profile_pic" -> {

                               Glide.with(activity!!).load(it.value.toString()).into(v.cview)

                           }
                       }
                    }


                }
            })







        v.sendNotification.setOnClickListener {

            activity!!.runOnUiThread {


            var jsonObjec: JSONObject? = null
            var bodydata:String = v.et1.text.toString()

            jsonObjec =  JSONObject()
            var list = mutableListOf<String>()
            list.add(fcm_token)

            var   jsonArray: JSONArray = JSONArray(list)
            jsonObjec.put("registration_ids", jsonArray);
            var jsonObjec2: JSONObject = JSONObject()
            jsonObjec2.put("body", bodydata);
            jsonObjec2.put("title", "Text Message from Android 7AM")
            jsonObjec2.put("fcm_type", "text")
            jsonObjec.put("data", jsonObjec2);

            jsonObjec.put("time_to_live", 172800);
            jsonObjec.put("priority", "HIGH");

            println("*************")
            print(jsonObjec)
            println("*************")


            val client = OkHttpClient()
            val JSON = MediaType.parse("application/json; charset=utf-8")
            val body = RequestBody.create(JSON, jsonObjec.toString())
            val request = Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "key=AAAACRZA4-0:APA91bGmzKM2joCYGvKUQMAK6uNpy7_oP_S325mwqANkJnFh3PKeKd9ka3yhIpcOE0XEPoZihhd4qr7HLmZ7XQrsPftTNJwxD_GaVXpP7yRiMm7yDlamAHLXOf0atioz3z3DdMACFJQX")
                .url("https://fcm.googleapis.com/fcm/send")
                .post(body)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onResponse(call: Call?, response: Response?) {

                            Toast.makeText(activity,
                                    "Message Sending Success",
                                    Toast.LENGTH_LONG).show()

                }
                override fun onFailure(call: Call?, e: IOException?) {
                            Toast.makeText(activity,
                                    "Message Sending Failure",
                                    Toast.LENGTH_LONG).show()
                }
            })



            }
        }

        v.sendNotificationAll.setOnClickListener {

            activity!!.runOnUiThread {

            //var jsonObjec: JSONObject? = null
            var bodydata:String = v.et1.text.toString()

           var  jsonObjec =  JSONObject()

            var   jsonArray: JSONArray = JSONArray(fcm_tokens_list)
            jsonObjec.put("registration_ids", jsonArray);
            var jsonObjec2: JSONObject = JSONObject()
            jsonObjec2.put("body", bodydata);
            jsonObjec2.put("title", "Text Message from FbNov7AM ")
            jsonObjec2.put("fcm_type", "text")
            jsonObjec.put("data", jsonObjec2);

            jsonObjec.put("time_to_live", 172800);
            jsonObjec.put("priority", "HIGH");

            println("*************")
            print(jsonObjec)
            println("*************")


            val client = OkHttpClient()
            val JSON = MediaType.parse("application/json; charset=utf-8")
            val body = RequestBody.create(JSON, jsonObjec.toString())
            val request = Request.Builder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "key=AAAACRZA4-0:APA91bGmzKM2joCYGvKUQMAK6uNpy7_oP_S325mwqANkJnFh3PKeKd9ka3yhIpcOE0XEPoZihhd4qr7HLmZ7XQrsPftTNJwxD_GaVXpP7yRiMm7yDlamAHLXOf0atioz3z3DdMACFJQX")
                .url("https://fcm.googleapis.com/fcm/send")
                .post(body)
                .build()
            val call = client.newCall(request)
            call.enqueue(object : Callback {
                override fun onResponse(call: Call?, response: Response?) {

                    Toast.makeText(activity,
                        "Message Sending Success",
                        Toast.LENGTH_LONG).show()

                }
                override fun onFailure(call: Call?, e: IOException?) {
                    Toast.makeText(activity,
                        "Message Sending Failure",
                        Toast.LENGTH_LONG).show()
                }
            })


            }

        }

        return v
    }


}