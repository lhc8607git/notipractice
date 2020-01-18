package ratingbar4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.noti.R;

public class CommentWriteActivity extends AppCompatActivity {
    RatingBar ratingBar;
    EditText contentsInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_write);

        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        contentsInput = (EditText)findViewById(R.id.contentsInput);
        Button saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToMain();
            }
        });


        Intent intent = getIntent();
        processIntent(intent);
    }

    // 인텐트를 외부에서 받았으면 rating에다가 받은 값 설정 해주기
    private void processIntent(Intent intent){
        if(intent != null){
            float rating = intent.getFloatExtra("rating", 0.0f);   // 받는게 float이라
            ratingBar.setRating(rating);
        }
    }

    public void returnToMain(){
        String contents = contentsInput.getText().toString();
        float ratingbarupdate = ratingBar.getRating();
        // ★ 인텐트 객체만 전달하면서 데이터만 전달 할 수도 있음
        Intent intent = new Intent();
        intent.putExtra("contents",contents);
        intent.putExtra("ratingbarupdate",ratingbarupdate);

        // 전달하면됨 (이전 액티비티로 인텐트를 전달 됨)
        setResult(RESULT_OK,intent);
        finish();
    }
}
