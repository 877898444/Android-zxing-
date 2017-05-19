package songxin.com.scan_songxin.zxing.view;

import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;

/**
 * 类描述：
 * Created by 殴打小熊猫 on 2017/5/19.
 */

public class ViewfinderResultPointCallback implements ResultPointCallback {
    private final ViewfinderView viewfinderView;

    public ViewfinderResultPointCallback(ViewfinderView viewfinderView) {
        this.viewfinderView = viewfinderView;
    }

    public void foundPossibleResultPoint(ResultPoint point) {
        viewfinderView.addPossibleResultPoint(point);
    }
}
