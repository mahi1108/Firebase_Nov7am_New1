package cubex.mahesh.firebase_nov7am

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fbfcm.view.*
import kotlinx.android.synthetic.main.fbusers.*
import kotlinx.android.synthetic.main.fbusers.view.*

var fcm_tokens_list = mutableListOf<String>()
var users_uuids = mutableListOf<String>()
var selected_user_uid = ""

class FbRegUsers : Fragment( ) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var v = inflater.inflate(R.layout.fbusers,container,false)

        var dBase = FirebaseDatabase.getInstance()
        var dRef =  dBase.getReference("users")
        dRef.addValueEventListener(object:ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {

            var children_users =     p0.children
                var list = mutableListOf<String>()
            children_users.forEach {
                        users_uuids.add(it.key!!)
                var  children_uid = it.children
                children_uid.forEach {
                    when(it.key){
                        "name"->{ list.add(it.value.toString()) }
                        "fcm_token"->{ fcm_tokens_list.add(it.value.toString())}
                    }
                }

            }

           var adapter = ArrayAdapter<String>(activity,
     android.R.layout.simple_list_item_single_choice,list)
                lview.adapter = adapter

                lview.setOnItemClickListener { adapterView, view, i, l ->

                    selected_user_uid = users_uuids.get(i)

                    var fManager = fragmentManager
                    var tx = fManager!!.beginTransaction()
                    tx.replace(R.id.frame1,FbFcm())
                    tx.commit()

                }

            }  // onDataChange
        })




        return v
    }
}
