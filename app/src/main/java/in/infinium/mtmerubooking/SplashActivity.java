package in.infinium.mtmerubooking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import in.infinium.mtmerubooking.activity.LoginActivity;
import in.infinium.mtmerubooking.utils.Common;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        executeSplash();
    }

    private void executeSplash() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent mIntent;
                if (Common.getStringPrefrences(SplashActivity.this, getString(R.string.pref_userName), getString(R.string.app_name)).trim().equals("")) {
                    mIntent = new Intent(SplashActivity.this, LoginActivity.class);
                } else {
                    mIntent = new Intent(SplashActivity.this, LoginActivity.class);
                }
                startActivity(mIntent);
                finish();
            }
        }, 3000);
    }
}
