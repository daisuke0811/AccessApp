package access.accessapp.data;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

// 子要素を持つ要素に指定
@Root(strict = false) /* メンバに定義していない要素は単に無視 */
public class ImageUrl {

    @Element(name = "shop_image1", required = false)
    private String mShopImage1;

    public String getShopImage1() {
        return mShopImage1;
    }
}
