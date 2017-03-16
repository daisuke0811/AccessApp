package access.accessapp;

import access.accessapp.data.Res;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * ぐるなび レストラン検索API
 * http://api.gnavi.co.jp/api/manual/restsearch/
 */
public interface GuruNaviApiInterface {
    String END_POINT = "https://api.gnavi.co.jp";

    String KEYID = "57d4fbeaecbf2fd5e58bb45577ec4b01";
    String FORMAT = "xml"; // xml/json
    String FREEWORD = "家系,ラーメン";

    // 緯度/経度からの検索範囲(半径) 1:300m、2:500m、3:1000m、4:2000m、5:3000m
    String RANGE = "4";
    // 緯度: 35.446876
    String LATITUDE = "35.446876";
    // 経度: 139.638032
    String LONGITUDE = "139.638032";

    String CATEGRY_L = "RSFST08000";
    String HIT_PAGE = "15";

    /**
     * https://api.gnavi.co.jp/RestSearchAPI/20150630/?keyid=57d4fbeaecbf2fd5e58bb45577ec4b01&format=xml&freeword=家系
     * カスタム
     * https://api.gnavi.co.jp/RestSearchAPI/20150630/?
     *  keyid=57d4fbeaecbf2fd5e58bb45577ec4b01
     * &format=xml
     * &freeword=家系,ラーメン
     * &range=4
     * &latitude=35.446876
     * &longitude=139.638032
     * &hit_per_page=15
     * &category_l=RSFST08000
     */
    @GET("/RestSearchAPI/20150630/")
    Call<Res> getShop(@Query("keyid")        String KEYID,
                      @Query("format")       String FORMAT,
                      @Query("freeword")     String FREEWORD,
                      @Query("latitude")     String LATITUDE,
                      @Query("longitude")    String LONGITUDE,
                      @Query("range")        String RANGE,
                      @Query("category_l")   String CATEGORY_L,
                      @Query("hit_per_page") String HIT_PAGE
                      );
}
