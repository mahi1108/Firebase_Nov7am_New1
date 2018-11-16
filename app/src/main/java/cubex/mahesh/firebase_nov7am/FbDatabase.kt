package cubex.mahesh.firebase_nov7am

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fb_db.view.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.fb_db.*


class FbDatabase:Fragment( ) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fb_db,
                          container,false)

        v.submit.setOnClickListener {

            val database = FirebaseDatabase.getInstance()
            val usersRef = database.getReference("users")
            val uid = FirebaseAuth.getInstance().uid
            val uidRef=usersRef.child(uid.toString())
            uidRef.child("name").
                                        setValue(name.text.toString())
            uidRef.child("age").
                setValue(age.text.toString())
            uidRef.child("gender").
                setValue(gender.text.toString())
            uidRef.child("mno").
                setValue(mno.text.toString())
            uidRef.child("qual").
                setValue(qual.text.toString())
            uidRef.child("dob").
                setValue(dob.text.toString())
            val fcm_token =
                FirebaseInstanceId.getInstance().getToken()
            uidRef.child("fcm_token").
                    setValue(fcm_token)

            var fManager = fragmentManager
            var tx = fManager!!.beginTransaction()
            tx.replace(R.id.frame1,FbStorage())
            tx.commit()


        }


        return v
    }


}