package com.example.noti;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private NotificationCompat.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        btn1 = findViewById(R.id.btn1);

        // 다음 스니펫은 사용자가 알림을 탭하면 활동을 여는 기본 인텐트를 만드는 방법을 보여줍니다.
        Intent intent = new Intent(this, Two.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0); //PendingIntent->앱이 꺼져 있오도 원격으로 킬 수가 있는 거

        // Bitmap으로 바꾼 후 저장
        Bitmap bitimg = BitmapFactory.decodeResource(getResources(),R.drawable.internet);

        // 알림의 콘텐츠와 채널 설정
        builder = new NotificationCompat.Builder(this, "Channel_Id")
                .setSmallIcon(R.drawable.receiveimg)  // 작은 아이콘
                .setContentTitle("제목!!")  // 제목
                .setContentText("본문!! 입니다~~~")  // 본문 텍스트
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitimg)) //이미지 추가!!
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)  // 알림 우선순위
                //밑에는 intent 사용해서 작성한거
                .setContentIntent(pendingIntent) // 사용자가 탭하면 자동으로 알림을 삭제
                .setAutoCancel(true);

        // 알림 진짜 띄우게 하는거 (알림 표시)
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        notificationManager.notify(0, builder.build()); // 0 줌

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 알림 진짜 띄우게 하는거 (알림 표시)
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                notificationManager.notify(0, builder.build()); // 0 줌
            }
        });

    }

    // 채널 만들기
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  //SDK_INT 버전에서 조건에 의해 차단
            CharSequence name = getString(R.string.channel_name); //채널이름
            String description = getString(R.string.channel_description); //채널설명
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Channel_Id", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
