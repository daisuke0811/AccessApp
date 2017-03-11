package access.accessapp.data;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class Res {
    private static final String TAG = Res.class.getSimpleName();

    @ElementList(inline = true)
    private List<Rest> mRestList;

    public List<Rest> getRestList() {
        return mRestList;
    }

    public void setRestList(List<Rest> restList) {
        this.mRestList = restList;
    }
}
