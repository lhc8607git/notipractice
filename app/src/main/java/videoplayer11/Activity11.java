package videoplayer11;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.noti.R;

public class Activity11 extends AppCompatActivity {

    VideoView videoView;
    Button button, button2;

    public static String url = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";// 동영상을 재생하기 위한 url

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity11_main);

        videoView = (VideoView)findViewById(R.id.videoView);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller); // 재생, 일시중지 같은 것들을 추가 해주어야 합니다.
        videoView.setVideoURI(Uri.parse(url));
        videoView.requestFocus(); // 파일에 대한 정보를 일부 가지고오게 됩니다.

        // 준비 과정이 끝나고(파일이 확실하게 있고) 이제 우리가 사용 할 수 있는 상태가 됩니다. (★ 기달리면 Toast 창 떠야지 준비 완료 된 상태!!입니다)
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(),"동영상 준비됨.",Toast.LENGTH_LONG).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.seekTo(0); // 위치 이동
                videoView.start(); // 동영상 플레이
            }
        });

        // 볼륨 조절
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager audiomanager = (AudioManager)getSystemService(AUDIO_SERVICE); //조절할려면 사용해야함!!
                int maxvolume = audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,maxvolume,AudioManager.FLAG_SHOW_UI);
            }
        });

    }
}
