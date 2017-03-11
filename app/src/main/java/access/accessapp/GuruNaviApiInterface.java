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
    String FREEWORD = "家系";

    /**
     * https://api.gnavi.co.jp/RestSearchAPI/20150630/?keyid=57d4fbeaecbf2fd5e58bb45577ec4b01&format=xml&freeword=家系
     */
    @GET("/RestSearchAPI/20150630/")
    Call<Res> getShop(@Query("keyid") String KEYID, @Query("format") String FORMAT, @Query("freeword") String FREEWORD);
}
