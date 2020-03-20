package spain.barcelona.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.applinks.AppLinkData;

import bolts.AppLinks;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
        //FacebookSdk.sdkInitialize(this);

        //case with deffered deep link
        // Version 4
        //FacebookSdk.setAutoInitEnabled(true);
        //FacebookSdk.fullyInitialize();
/*        AppLinkData.fetchDeferredAppLinkData(this,
                new AppLinkData.CompletionHandler() {
                    @Override
                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                        if (appLinkData == null){
                            try {
                                Uri data = appLinkData.getTargetUri();
                                String stringUri;
                                stringUri = data.toString();
                                Toast.makeText(getApplicationContext(), stringUri, Toast.LENGTH_SHORT).show();
                                String promo = appLinkData.getPromotionCode();
                                Toast.makeText(getApplicationContext(), promo, Toast.LENGTH_SHORT).show();
                                onDeepLink();
                            } catch (NullPointerException e) {
                                Log.e("Deep", "Incorrect deeplink!");
                            }
                        } else {
                            try {
                                Uri data = appLinkData.getTargetUri();
                                String stringUri;
                                stringUri = data.toString();
                                Toast.makeText(getApplicationContext(), stringUri, Toast.LENGTH_SHORT).show();
                                String promo = appLinkData.getPromotionCode();
                                Toast.makeText(getApplicationContext(), promo, Toast.LENGTH_SHORT).show();
                                onDeepLink();
                            } catch (NullPointerException e) {
                                Log.e("Deep", "Incorrect deep!");
                            }

                        }
                    }
                }
        );*/

        // Version 3
        //case with deep link
        Intent intent = getIntent();
        Uri data = intent.getData();
        if (data != null) {
            String stringUri = data.toString();
            Toast.makeText(getApplicationContext(), stringUri, Toast.LENGTH_SHORT).show();
            //Log.d("TAG", "onDeferredAppLinkDataFetched: " + data.toString());
            //ecalcapp://user?offer=abcde
            //String[] arrBefore = data.toString().split("&");
            //String clearData = arrBefore[0].replace("ecalcapp://user?","");
            //String[] arr  = clearData.split("=");
            //DeepLinkActivity.start(MainActivity.this, arr[1]);
        } else {
            Toast.makeText(getApplicationContext(), "data == null", Toast.LENGTH_SHORT).show();
        }

        // Version 2 (intent work. Open -> MainActivity -> DeepLinkActivity)
        /*FacebookSdk.setAutoInitEnabled(true);
        FacebookSdk.fullyInitialize();
        AppLinkData.fetchDeferredAppLinkData(this,
                new AppLinkData.CompletionHandler() {
                    @Override
                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                        onDeepLink(); // i think it works according this method
                        if (appLinkData == null){
                            try {
                                Uri data = appLinkData.getTargetUri();
                                String stringUri;
                                stringUri = data.toString();
                                Toast.makeText(getApplicationContext(), stringUri, Toast.LENGTH_SHORT).show();
                                String promo = appLinkData.getPromotionCode();
                                Toast.makeText(getApplicationContext(), promo, Toast.LENGTH_SHORT).show();
                                onDeepLink();
                            } catch (NullPointerException e) {
                                Log.e("Deep", "Incorrect deeplink!");
                            }
                        } else {
                            try {
                                Uri data = appLinkData.getTargetUri();
                                String stringUri;
                                stringUri = data.toString();
                                Toast.makeText(getApplicationContext(), stringUri, Toast.LENGTH_SHORT).show();
                                String promo = appLinkData.getPromotionCode();
                                Toast.makeText(getApplicationContext(), promo, Toast.LENGTH_SHORT).show();
                                onDeepLink();
                            } catch (NullPointerException e) {
                                Log.e("Deep", "Incorrect deep!");
                            }

                        }
                    }
                }
        );*/


        // Version 1
        //FacebookSdk.setAutoInitEnabled(true);
        //FacebookSdk.fullyInitialize();
        /*AppLinkData.fetchDeferredAppLinkData(this,
                new AppLinkData.CompletionHandler() {
                    @Override
                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                        if(appLinkData != null){
                            onDeepLink();
                        }
                    }
                }
        );*/

        // Current Version
/*      FacebookSdk.setAutoInitEnabled(true);
        FacebookSdk.fullyInitialize();
        AppLinkData.fetchDeferredAppLinkData(this,
                new AppLinkData.CompletionHandler() {
                    @Override
                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                        //Log.d("TAG", "onDeferredAppLinkDataFetched: "+"event");
                        // случай когда не важен источник установки, но запуск прилы с рекламы
                        if (appLinkData != null && appLinkData.getTargetUri() != null) {
                            Toast.makeText(getApplicationContext(), "Empty deep link", Toast.LENGTH_SHORT).show();
                            //Log.d("TAG", "onDeferredAppLinkDataFetched: "+appLinkData.getTargetUri().toString());
                            onDeepLink();
                        }
                    }
                }
        );*/
    }

    public void onClickGraphicHint(View view) {
        Intent intent = new Intent(this, GraphicHintActivity.class);
        startActivity(intent);
    }

    //Вызвать onSendMessage() при щелчке на кнопке
    public void onCalculate(View view) {
        EditText parameter1 = findViewById(R.id.parameter_1);
        EditText parameter2 = findViewById(R.id.parameter_2);
        EditText parameter3 = findViewById(R.id.parameter_3);
        EditText parameter4 = findViewById(R.id.parameter_4);

        try {
            float chair = Float.parseFloat(parameter1.getText().toString());
            float eyes = Float.parseFloat(parameter2.getText().toString());
            float table = Float.parseFloat(parameter3.getText().toString());
            float monitor = Float.parseFloat(parameter4.getText().toString());

            float usersEyesHight = chair + eyes;
            float estimatedMonitorCenter = usersEyesHight - 19;

            float resultStandHight = 0;


            if (monitor != 0) {
                float currentMonitorCenter = table + monitor;
                resultStandHight = estimatedMonitorCenter - currentMonitorCenter;
            }
            float input = resultStandHight;

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("INPUT_DATA", input);
            startActivity(intent);


            if (chair > 150 | eyes > 150 | table > 150 | monitor > 150) {
                intent = new Intent(this, ErrorActivity.class);
                startActivity(intent);
            }

        } catch (Exception e) {
            Intent intent = new Intent(this, ErrorActivity.class);
            startActivity(intent);
        }
    }

    public void onDeepLink() {
        Intent intent = new Intent(this, DeepLinkActivity.class);
        startActivity(intent);
    }

}
