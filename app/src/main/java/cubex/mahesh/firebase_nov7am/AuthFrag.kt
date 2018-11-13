package cubex.mahesh.firebase_nov7am

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.auth.view.*

class AuthFrag : Fragment( )
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.auth,
                        container,false)

        var fAuth = FirebaseAuth.getInstance()

        v.register.setOnClickListener {
            fAuth.createUserWithEmailAndPassword(
                v.et1.text.toString(), v.et2.text.toString()).
                addOnCompleteListener {
                    if(it.isSuccessful){
         Toast.makeText(activity,"Auth is Success",
                        Toast.LENGTH_LONG).show()
                    }else{
       Toast.makeText(activity,"Auth is Fail",
                            Toast.LENGTH_LONG).show()
                    }
                }
        }
        v.login.setOnClickListener {
            fAuth.signInWithEmailAndPassword(
                v.et1.text.toString(), v.et2.text.toString()).
                addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(activity,"Auth is Success",
                            Toast.LENGTH_LONG).show()
                        var fManager = fragmentManager
                        var tx = fManager!!.beginTransaction()
                        tx.replace(R.id.frame1,FbDatabase( ))
                        tx.commit()

                    }else{
                        Toast.makeText(activity,"Auth is Fail",
                            Toast.LENGTH_LONG).show()
                    }
                }
        }

        return v
    }
}