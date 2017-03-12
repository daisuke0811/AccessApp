package access.accessapp.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import access.accessapp.R;

public class StartActivity extends BaseActivity implements LocationListener{

    private ImageButton mImageButton;

    private LocationManager locationManager;
    private String mLatitude;
    private String mLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        mImageButton = (ImageButton) findViewById(R.id.imageButton);
        mImageButton.setOnClickListener(new OnCustomClickListener());

        // 位置情報取得 権限チェック
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }
    }

    private void locationStart() {
        Log.d("debug","locationStart()");

        // LocationManager インスタンス生成
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            // GPSを設定するように促す
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
            Log.d("debug", "gpsEnable, startActivity");
        } else {
            Log.d("debug", "gpsEnabled");
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);

            Log.d("debug", "checkSelfPermission false");
            return;
        }

        // 位置情報の通知するための最小時間間隔（ミリ秒）
        final long minTime = 1000;
        // 位置情報を通知するための最小距離間隔（メートル）
        final long minDistance = 50;

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance, this);

        // 最新情報取得
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            // 緯度の表示
            mLatitude = String.valueOf(location.getLatitude());
            Toast.makeText(this, "Latitude:"+ String.valueOf(location.getLatitude()), Toast.LENGTH_SHORT).show();

            // 経度の表示
            mLongitude = String.valueOf(location.getLongitude());
            Toast.makeText(this, "Latitude:"+ String.valueOf(location.getLongitude()), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("debug","checkSelfPermission true");

                locationStart();
                return;

            } else {
                // それでも拒否された時の対応
                Toast toast = Toast.makeText(this, "位置情報の取得を許可してください。", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status) {
            case LocationProvider.AVAILABLE:
                Log.d("debug", "LocationProvider.AVAILABLE");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //位置情報が通知されるたびにコールバックされる

        // 緯度の表示
        mLatitude = String.valueOf(location.getLatitude());
        Toast.makeText(this, "Latitude:"+ String.valueOf(location.getLatitude()), Toast.LENGTH_SHORT).show();

        // 経度の表示
        mLongitude = String.valueOf(location.getLongitude());
        Toast.makeText(this, "Latitude:"+ String.valueOf(location.getLongitude()), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String s) {
        // 引数にとったロケーションプロバイダが利用可能かどうか調べる
    }

    @Override
    public void onProviderDisabled(String s) {
        //ロケーションプロバイダが利用不可能になるとコールバックされる
    }

    private class OnCustomClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            Intent intent = new Intent();
            intent.setClassName("access.accessapp", "access.accessapp.ui.MainActivity");
            intent.putExtra("LATITUDE", mLatitude);
            intent.putExtra("LONGITUDE", mLongitude);
            startActivity(intent);

            finish();
        }
    }
}
