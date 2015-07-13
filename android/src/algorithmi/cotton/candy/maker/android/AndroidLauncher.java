package algorithmi.cotton.candy.maker.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import algorithmi.cotton.candy.maker.CottonCandyMaker;
import algorithmi.cotton.candy.maker.ads.MarketListener;
import algorithmi.cotton.candy.maker.ads.AdListener;
import algorithmi.cotton.candy.maker.scenes.MainMenuScene;
import algorithmi.cotton.candy.maker.scenes.EndGameScene;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.vungle.sdk.VunglePub;
import com.heyzap.sdk.ads.HeyzapAds;
import com.heyzap.sdk.ads.VideoAd;
import com.heyzap.sdk.ads.InterstitialAd;

public class AndroidLauncher extends AndroidApplication implements MarketListener, 
                        AdListener {

    final String appId = "algorithmi.cotton.candy.maker.android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useWakelock = true;
        initialize(new CottonCandyMaker(), config);

        //VUNGLE
        VunglePub.init(this, appId);
        VunglePub.setSoundEnabled(true);
        VunglePub.setEventListener(new VunglePub.EventListener() {
            @Override
            public void onVungleView(double d, double d1) {
                double percent_scene = d / d1;
                if (percent_scene >= 0.0d) {
                    Log.i("VUNGLE_ADVERT", "Vungle would consider this"
                            + " a 'Completed View'");
                }
            }

            @Override
            public void onVungleAdStart() {
                Log.i("VUNGLE+ADVERT", "Just started and advertisment....");
            }

            @Override
            public void onVungleAdEnd() {
                Log.i("VUNGLE_ADVERT", "No longer seeing an advertisment");
            }
        });

        //HEYZAP
        HeyzapAds.start(this);
        VideoAd.fetch();
        InterstitialAd.fetch();
        
        MainMenuScene.addMarketListener(this);
        EndGameScene.addAddListener(this);
    }
    /**
     * end onCreate(Bundle).
     */
    
    @Override
    public void moreFunClicked() {
        try {
            startActivity(new Intent("android.intent.action.VIEW",
                    Uri.parse("market://search?q=pub:Algorithmi")));
        } catch (ActivityNotFoundException e) {
            Log.e("ALGORITHMI", "ActivityNotFoundException");
        }
    }
    
    @Override
    public void rateUsClicked() {
        try {
            startActivity(new Intent("android.intent.action.VIEW",
                    Uri.parse("market://details?id="
                    + "algorithmi.cotton.candy.maker.android")));
        } catch (ActivityNotFoundException e) {
            Log.e("ALGORITHMI", "ActivityNotFoundException");
        }
        //Toast.makeText(this, "Toast from RateUs AndroidLauncher", Toast.LENGTH_LONG).show();
    }

    @Override 
    public void showHeyzapVideo() { 
        VideoAd.display(this);
    }
    
    @Override
    public void showHeyzapInterstital() {
        InterstitialAd.display(this);
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        VunglePub.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        VunglePub.onResume();
    }
}
/**
 * [end class].
 */
