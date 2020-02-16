package spain.barcelona.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.facebook.FacebookSdk;
import com.facebook.applinks.AppLinkData;


public class MainActivity extends AppCompatActivity {

    // проверка если нет данных в поле
    // проверка если ввели данные с плавающей запятой

    //
    //
    //
    //

    private final String SCHEME = "calcapp://user=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(this);
        AppLinkData.fetchDeferredAppLinkData(this,
                new AppLinkData.CompletionHandler() {
                    @Override
                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                        // случай когда не важен источник установки, но запуск прилы с рекламы
                        if (appLinkData.getTargetUri() != null &&
                                appLinkData.getTargetUri().toString().equals(SCHEME)) {
                            //todo показать вебвью, перейти на экран с вебью

                        }
                        // случай когда  важен источник установки и запуск прилы с рекламы
                        //calcapp://user=source_install001
                        //calcapp://user=source_install002
                        //calcapp://user=source_install003
                        if (appLinkData.getTargetUri() != null &&
                                appLinkData.getTargetUri().toString().contains(SCHEME)) {
                            //todo определем источник рекламі
                            String source = tryCatchFbSource(appLinkData.getTargetUri().toString());
                            //source  =  source_install003
                            //todo показать вебвью, перейти на экран с вебью в зависимотсти от источника трафика
                            switch (source){
                                case "source_install001":
                                    //todo open url1(visibility) или итнент на вебвью
                                    break;
                                case "source_install002":
                                    //todo open url2(visibility) или итнент на вебвью
                                    break;
                                case "source_install003":
                                    //todo open url3(visibility) или итнент на вебвью
                                    break;
                            }
                        }


                    }
                }
        );
    }


    String tryCatchFbSource(String facebookUri) {
        //получили ссілку calcapp://user=source_install003
        String source = null;
        try {
            //обрезали часть calcapp://user
            String valueWithAp = facebookUri.replace(SCHEME, "");
            source = valueWithAp.trim();
        } catch (Exception e) {
            // Just ignore
        }
        Log.d("clientId: ", source);
        return source;
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
}
