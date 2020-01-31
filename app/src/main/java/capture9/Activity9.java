package capture9;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.noti.R;

public class Activity9 extends AppCompatActivity {

    ImageView imageView;
    CameraSurfaceView surfaceView;   // 내가 만든 클래스

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        surfaceView = (CameraSurfaceView)findViewById(R.id.surfaceView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capture();
            }
        });

    }

    public void capture(){
        // 사진 찍는 기능
        surfaceView.capture(new Camera.PictureCallback() {
            @Override                 // 사진이 찍어지먄  byte[]로 전달이 됩니다. ★파일로 전달 되는게 아닙니다.
            public void onPictureTaken(byte[] data, Camera camera) {
                // 전달받은 byte[]를  bitmap 객체로 만듭니다
                BitmapFactory.Options options = new BitmapFactory.Options();  // 옵션을 줄 수 있다.
                options.inSampleSize = 8; // 8분의 1크기로 만든다.
                Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length); //(데이터, 첫번째부터, 어디까지)

                imageView.setImageBitmap(bitmap);

                // 사진을 찍게 되면, 미리보기가 중지가 됩니다, 그래서 다시 미리보기를 시작을 할려면!!!! (밑에코드)
                camera.startPreview();
            }
        });
    }

}
