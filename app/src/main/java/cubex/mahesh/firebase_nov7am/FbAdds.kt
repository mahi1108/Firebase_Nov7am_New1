package cubex.mahesh.firebase_nov7am

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.fbadds.*
import kotlinx.android.synthetic.main.fbadds.view.*

class FbAdds:Fragment( ) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var v = inflater.inflate(
            R.layout.fbadds,
            container, false
        )

        v.banner.setOnClickListener {
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
        }

        v.iterestial.setOnClickListener {

        }

        v.video.setOnClickListener {

        }

        return v
    }

}