package me.solidev.quickdevlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.solidev.quickdevlib.fragment.subscribe.SubscribeFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        BannerView bannerView = (BannerView) findViewById(R.id.banner_view);
//        bannerView.setBannerList(getBannerList());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_content, new SubscribeFragment())
                .commit();
    }


}
