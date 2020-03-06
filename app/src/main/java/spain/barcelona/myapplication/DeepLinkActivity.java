 package spain.barcelona.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.facebook.applinks.AppLinkData;

 public class DeepLinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);

        AppLinkData.fetchDeferredAppLinkData(this,
                new AppLinkData.CompletionHandler() {
                    @Override
                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                        // случай когда не важен источник установки, но запуск прилы с рекламы
                        if (appLinkData.getTargetUri() != null) {
                            Toast.makeText(getApplicationContext(), "Empty deep link", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}
