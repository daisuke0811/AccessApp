package access.accessapp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import access.accessapp.GuruNaviApiInterface;
import access.accessapp.R;
import access.accessapp.data.Res;
import access.accessapp.data.Rest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends BaseActivity {

    private GuruNaviApiInterface mApiInterface;
    private ListView mListView;

    private String mLatitude;
    private String mLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if(intent != null){
            mLatitude = intent.getStringExtra("LATITUDE");
            mLongitude = intent.getStringExtra("LONGITUDE");
        } else {
            mLatitude ="35.446876";
            mLatitude ="139.638032";
        }

        mListView = (ListView) findViewById(R.id.listView);

        // SimpleXmlConverter: APIレスポンスを作成したPOJOクラスにパースすることができる
        // Retrofitインスタンス作成
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GuruNaviApiInterface.END_POINT)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        // Web APIアクセス用インタフェース作成
        mApiInterface = retrofit.create(GuruNaviApiInterface.class);
        execGetGuruNaviApi();
    }

    /**
     * Web API実行用
     */
    private void execGetGuruNaviApi() {
        // Web API実行用インスタンス(インタフェース)を取得します。
        Call<Res> call = mApiInterface.getShop(
                GuruNaviApiInterface.KEYID,
                GuruNaviApiInterface.FORMAT,
                GuruNaviApiInterface.FREEWORD,
            //  GuruNaviApiInterface.LATITUDE,
                mLatitude,
            //  GuruNaviApiInterface.LONGITUDE,
                mLongitude,
                GuruNaviApiInterface.RANGE
                );

        // Web APIを実行します
        // コールバック呼び出しで処理結果が通知されます
        call.enqueue(new Callback<Res>() {

            // Web APIアクセスが成功した場合の処理を記述します
            @Override
            public void onResponse(Call<Res> call, Response<Res> response) {
                Log.d("AAA", "Successed to request");

                Res res = response.body();

                List<Rest> restList = res.getRestList();

                RestAdapter adapter = new RestAdapter(MainActivity.this, R.layout.list_item, restList);
                mListView.setAdapter(adapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Rest item = (Rest) parent.getItemAtPosition(position);

                        // リストをクリックするとブラウザへ遷移させる
                        Uri uri = Uri.parse(item.getUrl());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });
            }

            // Web APIアクセスが失敗した場合の処理を記述します
            @Override
            public void onFailure(Call<Res> call, Throwable t) {
                Log.d("AAA", "Failed to request : " + t.getCause() + ", " + t.getMessage());
                t.printStackTrace();

                // 暫定対応
                Toast.makeText(MainActivity.this, "Sorr Request Empty...", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private class RestAdapter extends ArrayAdapter<Rest> {
        RestAdapter(Context context, int resource, List<Rest> restList) {
            super(context, resource, restList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.list_item, null);
            }

            Rest rest = this.getItem(position);

            // 店名セット
            TextView shopName = (TextView) convertView.findViewById(R.id.shopName);
            shopName.setText(String.valueOf(rest.getShopName()));

            // 画像
            ImageView imageView = (ImageView) convertView.findViewById(R.id.shopImage);
            Glide.with(this.getContext())
                    .load(rest.getImageUrlList().getShopImage1())
                    // エラー画像
                    .error(android.R.drawable.ic_menu_gallery)
                    .into(imageView);

            // XMLで定義したアニメーションを読み込む
            Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.item_motion);
            // リストアイテムのアニメーションを開始
            convertView.startAnimation(anim);

            return convertView;
        }
    }
}

