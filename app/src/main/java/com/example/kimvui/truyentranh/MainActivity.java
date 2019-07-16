package com.example.kimvui.truyentranh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
//import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyFirebase";
    private NotificationCompat.Builder notBuilder;
    private static final int MY_NOTIFICATION_ID = 12345;

    private static final int MY_REQUEST_CODE = 100;
    private Fragment fragment;

    int demtoback = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragment = new FragmentComic();
        LoadFragment(fragment);

    }

    private void LoadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void comicoffline(View v) {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_dashboard);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:


                    fragment = new FragmentComic();
                    LoadFragment(fragment);

                    return true;
                case R.id.navigation_dashboard:
                    fragment = new DownloadActivity();
                    LoadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                    fragment = new NotificationActivity();
                    LoadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        if (navigation.getSelectedItemId() == R.id.navigation_home) {
            Toast.makeText(MainActivity.this, "Back lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT).show();
            demtoback += 1;
            if (demtoback == 2) {
                finish();
            }
        } else {
            demtoback = 1;
            navigation.setSelectedItemId(R.id.navigation_home);
        }
    }

}
