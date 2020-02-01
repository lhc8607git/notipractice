package mediaplayer10;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.noti.R;

import java.io.IOException;

public class Activity10 extends AppCompatActivity {
    // 웹서버에 오려둔 "MP3 파일" 사용합니다.
    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";

    MediaPlayer player;
    int position = 0; // 현재 재생한 위치

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity10_main);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });
        // 일시정지
        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseAudio();
            }
        });
        // 재시작
        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resumeAudio();
            }
        });
        // 끝
        Button btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopAudio();
            }
        });
    }

    public void playAudio(){
        try {
            closePlayer(); // play를 여러번 누를 수도 있으니깐!!

            player = new MediaPlayer(); // 초기화
            player.setDataSource(url);  // 웹(인터넷)에있는 것을 가져와서 출력을 하게 됩니다,
            player.prepare();  //★ 꼭 start하기전에 준비 해줘라!!!적어줘!
            player.start();

            Toast.makeText(getApplicationContext(),"재생 시작했습니다",Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 일시정지
    public void pauseAudio(){
        if (player != null) {
            position = player.getCurrentPosition();  //★ 어디까지 플레이가 됬는지를 확인 할 수 있습니다!
            player.pause(); // 일시정지

            Toast.makeText(getApplicationContext(),"일시 정지했습니다",Toast.LENGTH_LONG).show();
        }
    }

    // 재시작
    public void resumeAudio(){
        if(player != null){
            player.seekTo(position);
            player.start();

            Toast.makeText(getApplicationContext(),"재시작 했습니다",Toast.LENGTH_LONG).show();
        }
    }

    // 끝
    public void stopAudio(){
        if(player != null && player.isPlaying()) {  // 플레이가 재생 중인지? 멈춘거지? 알아야 합니다.
            player.stop();
            closePlayer();
            Toast.makeText(getApplicationContext(),"끝!",Toast.LENGTH_LONG).show();
        }
    }

    // 리소스 해제해주는 과정
    public void closePlayer(){
        if(player != null){
            player.release();
            player = null;
        }
    }

}
