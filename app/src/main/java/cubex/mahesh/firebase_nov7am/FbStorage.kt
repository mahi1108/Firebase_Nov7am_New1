package cubex.mahesh.firebase_nov7am

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.storage.*
import kotlinx.android.synthetic.main.storage.view.*
import java.io.ByteArrayOutputStream
import java.io.File


class FbStorage:Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(R.layout.storage,
                                container,false)
        v.cview.setOnClickListener {
            var aDialog = AlertDialog.Builder(activity)
            aDialog.setTitle("Message")
            aDialog.setMessage("select option to add an attachment")
            aDialog.setPositiveButton("Camera",
                object: DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        var i = Intent("android.media.action.IMAGE_CAPTURE")
                        startActivityForResult(i,123)
                    }
                })
            aDialog.setNegativeButton("Gallery",
                object:DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        var i = Intent( )
                        i.setAction(Intent.ACTION_GET_CONTENT)
                        i.setType("image/*")
                        startActivityForResult(i,124)
                    }
                })
            aDialog.setNeutralButton("Cancel",
                object:DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        p0!!.dismiss()
                    }
                })
            aDialog.show()

        }


        return v
    }  // onCreateView

    var uri:Uri? = null
    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==123 && resultCode== Activity.RESULT_OK)
        {
            var  bmp: Bitmap = data!!.extras.get("data") as Bitmap
            uri = getImageUri(activity!!,bmp)
            var sRef = FirebaseStorage.getInstance().getReference("profile_pics")
            var f = File(uri!!.path)
            var uploadTask = sRef.child(f.name).putFile(uri!!)
            uploadTask.addOnSuccessListener {
               var url =  it.downloadUrl.toString()
                val database = FirebaseDatabase.getInstance()
                val usersRef = database.getReference("users")
                val uid = FirebaseAuth.getInstance().uid
                val uidRef=usersRef.child(uid.toString())
                uidRef.child("profile_pic").setValue(url)

                var fManager = fragmentManager
                var tx = fManager!!.beginTransaction()
                tx.replace(R.id.frame1,FbAdds( ))
                tx.commit()

            }
            cview.setImageURI(uri)
        }
        if(requestCode==124 && resultCode== Activity.RESULT_OK)
        {
            uri = data!!.data
            var sRef = FirebaseStorage.getInstance().getReference("profile_pics")
            var f = File(uri!!.path)
            var uploadTask = sRef.child(f.name).putFile(uri!!)
            uploadTask.addOnSuccessListener {
                var url =  it.downloadUrl.toString()
                val database = FirebaseDatabase.getInstance()
                val usersRef = database.getReference("users")
                val uid = FirebaseAuth.getInstance().uid
                val uidRef=usersRef.child(uid.toString())
                uidRef.child("profile_pic").setValue(url)

                var fManager = fragmentManager
                var tx = fManager!!.beginTransaction()
                tx.replace(R.id.frame1,FbAdds( ))
                tx.commit()

            }
            cview.setImageURI(uri)
        }
    } // onActivityResult
    fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null)
        return Uri.parse(path)
    }



}