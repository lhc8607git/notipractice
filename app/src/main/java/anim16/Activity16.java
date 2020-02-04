package anim16;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.noti.R;

import java.util.ArrayList;

public class Activity16 extends AppCompatActivity {
    ArrayList<Drawable> imgList = new ArrayList<Drawable>();
    ImageView imageView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity16_main);

        imageView = findViewById(R.id.imageView);

        Resources res = getResources();
        imgList.add(res.getDrawable(R.drawable.emo_crying));
        imgList.add(res.getDrawable(R.drawable.emo_happy));
        imgList.add(res.getDrawable(R.drawable.emo_sad));
        imgList.add(res.getDrawable(R.drawable.emo_surprised));

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AniThread thread = new AniThread();
                thread.start();
            }
        });
    }

    class AniThread extends Thread{
        public void run(){
            for(int i=0; i<4; i++){
                int currentindex = i % 5;
                final Drawable drawable =imgList.get(currentindex);

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
