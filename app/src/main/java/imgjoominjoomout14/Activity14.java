package imgjoominjoomout14;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.noti.R;
import com.github.chrisbanes.photoview.PhotoView;

public class Activity14  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity14_main);

        PhotoView photoView = findViewById(R.id.photoView);
        photoView.setImageResource(R.drawable.imgimg);
    }
}
