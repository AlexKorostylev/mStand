package spain.barcelona.myapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            float standHight = bundle.getFloat("INPUT_DATA");
            String paramFromIntent = Float.toString(standHight);
            String formatParamFromIntent = paramFromIntent.substring(0,4);
            TextView rezParam = findViewById(R.id.res_parameter);
            rezParam.setText(formatParamFromIntent);
        } else {
            Intent intent = new Intent(this, ErrorActivity.class);
            startActivity(intent);
        }
    }
}

