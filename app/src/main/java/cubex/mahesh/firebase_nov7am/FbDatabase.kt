package cubex.mahesh.firebase_nov7am

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fb_db.view.*

class FbDatabase:Fragment( ) {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.fb_db,
                          container,false)

        v.submit.setOnClickListener {


        }


        return v
    }


}