package broadcasting2;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.noti.R;

public class SmsActivity extends AppCompatActivity {
    EditText editText,editText2,editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        editText = (EditText)findViewById(R.id.editText);
        editText2 =  (EditText)findViewById(R.id.editText2);
        editText3 =  (EditText)findViewById(R.id.editText3);

        Button button =  (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent passedIntent = getIntent(); // 전달 받은 인텐트 확인
        processCommand(passedIntent);
    }

    private void processCommand(Intent intent){
        // 브로드캐스트 수신자에서 sms를 받아서(intent로 받음) 화면으로 보여주는 기능
        if(intent != null){ //intent라고 하는게 null 아니면
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");
            String receivedDate = intent.getStringExtra("receivedDate");

            editText.setText(sender);
            editText3.setText(contents);
            editText2.setText(receivedDate);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processCommand(intent);
        super.onNewIntent(intent);
    }
}

