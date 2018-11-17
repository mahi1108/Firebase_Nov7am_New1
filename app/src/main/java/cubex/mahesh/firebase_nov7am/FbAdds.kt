package cubex.mahesh.firebase_nov7am

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
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

            var mInterstitialAd = InterstitialAd(activity)
           mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
       /*   mInterstitialAd.adUnitId =
                  resources.getString(R.string.myinterestial) */
            mInterstitialAd.loadAd(AdRequest.Builder().build())

        }

        v.video.setOnClickListener {
           var  mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity)
           mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                AdRequest.Builder().build())
        /*    mRewardedVideoAd.loadAd(resources.getString(R.string.myvideoadd),
                AdRequest.Builder().build())*/
        }

        return v
    }

}