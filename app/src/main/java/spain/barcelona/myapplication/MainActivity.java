package spain.barcelona.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.facebook.applinks.AppLinkData;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppLinkData.fetchDeferredAppLinkData(this,
                new AppLinkData.CompletionHandler() {
                    @Override
                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                        // случай когда не важен источник установки, но запуск прилы с рекламы
                        if (appLinkData.getTargetUri() != null) {
                            onDeepLink();
                        } else {
                            Toast.makeText(getApplicationContext(), "Empty deep link", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
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

    public void onDeepLink(){
        Intent intent = new Intent(this, DeepLinkActivity.class);
        startActivity(intent);
    }
}
