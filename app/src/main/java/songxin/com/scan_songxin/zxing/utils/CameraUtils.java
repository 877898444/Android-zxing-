package songxin.com.scan_songxin.zxing.utils;

import android.hardware.Camera;

/**
 * 类描述：
 * Created by 殴打小熊猫 on 2017/5/19.
 */

public class CameraUtils {
    public static boolean isCameraCanUse() {
        boolean canUse = true;
        Camera mCamera = null;
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            canUse = false;
        }
        if (canUse) {
            if (mCamera != null)
                mCamera.release();
            mCamera = null;
        }
        return canUse;
    }
}
