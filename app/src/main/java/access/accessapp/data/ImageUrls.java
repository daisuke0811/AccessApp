package access.accessapp.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

// 子要素を持つ要素に指定
@Root(strict = false) /* メンバに定義していない要素は単に無視 */
public class ImageUrls {

    @Element(name = "shop_image1")
    private String mShopImage1;

    @Element(name = "shop_image2")
    private String mShopImage2;

    public String getmShopImage1() {
        return mShopImage1;
    }

    public String getmShopImage2() {
        return mShopImage2;
    }
}
