package cubex.mahesh.firebase_nov7am

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fbmlkit.view.*
import android.R.attr.bitmap
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer





class FbMLKit:Fragment() {
    var v:View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        v = inflater.inflate(R.layout.fbmlkit,
                    container,false)

        v!!.camera.setOnClickListener {
            var i = Intent("android.media.action.IMAGE_CAPTURE")
            startActivityForResult(i,123)
        }

        return v
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123 && resultCode == Activity.RESULT_OK) {
            var bmp: Bitmap = data!!.extras.get("data") as Bitmap
            val visImage = FirebaseVisionImage.fromBitmap(bmp)
            val textRecognizer = FirebaseVision.getInstance()
                .onDeviceTextRecognizer
            textRecognizer.processImage(visImage).
                addOnSuccessListener {

                    var blocks = it.textBlocks  // we will get individual words

                    var msg = ""
                    for(block in blocks)
                    {
                        msg = msg+block.text
                    }
                     v!!.tv1.text =  msg
                }
        }
    }
}