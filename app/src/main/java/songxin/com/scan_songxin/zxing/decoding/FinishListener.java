package songxin.com.scan_songxin.zxing.decoding;

import android.app.Activity;
import android.content.DialogInterface;

/**
 * 类描述：Simple listener used to exit the app in a few cases.
 * Created by 殴打小熊猫 on 2017/5/19.
 */

public class FinishListener implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener, Runnable {

    private final Activity activityToFinish;

    public FinishListener(Activity activityToFinish) {
        this.activityToFinish = activityToFinish;
    }

    public void onCancel(DialogInterface dialogInterface) {
        run();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        run();
    }

    public void run() {
        activityToFinish.finish();
    }

}
