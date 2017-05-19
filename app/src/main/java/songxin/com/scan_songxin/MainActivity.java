package songxin.com.scan_songxin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import songxin.com.scan_songxin.zxing.activity.CaptureActivity;
import songxin.com.scan_songxin.zxing.encoding.EncodingHandler;
import songxin.com.scan_songxin.zxing.utils.CameraUtils;

public class MainActivity extends AppCompatActivity {

    private Button openQrCodeScan;
    private Button CreateQrCode;
    private EditText text;
    private TextView qrCodeText;
    private ImageView QrCode;
    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initListener();
    }


    private void initView() {
        openQrCodeScan = (Button) findViewById(R.id.openQrCodeScan);
        CreateQrCode = (Button) findViewById(R.id.CreateQrCode);
        text = (EditText)findViewById(R.id.text);
        qrCodeText = (TextView) findViewById(R.id.qrCodeText);
        QrCode = (ImageView)findViewById(R.id.QrCode);
    }

    private void initListener() {
        openQrCodeScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开二维码扫描界面
                if (CameraUtils.isCameraCanUse()) {
                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
//                    MainActivity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(MainActivity.this, "请打开此应用的摄像头权限！", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                }
            }
        });
        CreateQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //获取输入的文本信息
                    String str = text.getText().toString().trim();
                    if(str != null && !"".equals(str.trim())){
                        //根据输入的文本生成对应的二维码并且显示出来
                        Bitmap mBitmap = EncodingHandler.createQRCode(text.getText().toString(), 500);
                        if(mBitmap != null){
                            Toast.makeText(MainActivity.this,"二维码生成成功！",Toast.LENGTH_SHORT).show();
                            QrCode.setImageBitmap(mBitmap);
                        }
                    }else{
                        Toast.makeText(MainActivity.this,"文本信息不能为空！",Toast.LENGTH_SHORT).show();
                    }
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (resultCode == RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("qr_scan_result");
            //将扫描出的信息显示出来
            qrCodeText.setText(scanResult);
        }
    }
}
