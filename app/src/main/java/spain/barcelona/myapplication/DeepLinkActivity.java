 package spain.barcelona.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.applinks.AppLinkData;

 public class DeepLinkActivity extends AppCompatActivity {

     public static void start(Context context) {
         Intent starter = new Intent(context, DeepLinkActivity.class);
         context.startActivity(starter);
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_link);
        Toast.makeText(getApplicationContext(), "Empty deep link", Toast.LENGTH_SHORT).show();
//        AppLinkData.fetchDeferredAppLinkData(this,
//                new AppLinkData.CompletionHandler() {
//                    @Override
//                    public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
//                        // случай когда не важен источник установки, но запуск прилы с рекламы
//                        if (appLinkData.getTargetUri() != null) {
//                            Toast.makeText(getApplicationContext(), "Empty deep link", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );
    }
}
