package access.accessapp.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

// 子要素を持つ要素に指定
@Root(strict = false) /* メンバに定義していない要素は単に無視 */
public class Rest {
    private static final String TAG = Rest.class.getSimpleName();

    @Element(name = "id")
    private String mId;

    @Element(name = "name")
    private String mName;

    @Element(name = "url")
    private String mUrl;

    @ElementList(inline = true, required = false)
    private List<ImageUrls> mImageUrl;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public String getShopName() {
        return mName;
    }

    public void setShopName(String name) {
        this.mName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public List<ImageUrls> getImageUrlList(int i) {
        return mImageUrl;
    }

    public void setImageUrlList(List<ImageUrls> imageUrlList) {
        this.mImageUrl = imageUrlList;
    }
}
