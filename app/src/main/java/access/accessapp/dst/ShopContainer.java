package access.accessapp.dst;


import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * API実行後の戻り値を受けるためのデータクラス
 */
class ShopContainer {
    private static final String TAG = ShopContainer.class.getSimpleName();

    @Expose
    private List<Shop> shops = new ArrayList<Shop>();
    @Expose
    private Integer count;
    @Expose
    private Integer eid;
    @Expose
    private String entryUrl;
    @Expose
    private String screenshot;
    @Expose
    private String title;
    @Expose
    private String url;

    /**
     *
     * @return
     */
    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getEntryUrl() {
        return entryUrl;
    }

    public void setEntryUrl(String entryUrl) {
        this.entryUrl = entryUrl;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
