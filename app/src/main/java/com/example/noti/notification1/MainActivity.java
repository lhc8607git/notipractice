package com.example.noti.notification1;


import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noti.R;

import ratingbar4.CommentWriteActivity;

import static android.app.Notification.EXTRA_NOTIFICATION_ID;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private NotificationCompat.Builder builder;

    private RatingBar ratingBar;
    private TextView outputView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1.노티피케이션 연습
//        notipractice();


        // 2.브로드캐스트 수신자(=브로드캐스트 리시버) 연습


        // 3.위험 권한 부여 연습
//        dangerous_permission();


        // 4.별점(RatingBar) 연습
        ratingpractice();

    }





    // 4.별점(RatingBar) 연습
    private void ratingpractice(){
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        outputView = (TextView) findViewById(R.id.outputView);


        Button btn4 = (Button)findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //다른 화면 띄우도록 하나 만듬
                showCommentWriteActivity();
            }
        });
    }

    public void showCommentWriteActivity(){
        float rating = ratingBar.getRating();

        Intent intent = new Intent(getApplicationContext(), CommentWriteActivity.class);
        intent.putExtra("rating",rating);
        startActivityForResult(intent,101);

    }

    // CommentWriteActivity.java에서 응답을 받을 경우
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 101){  // 101로 받으면
            if(intent != null){
                String contents = intent.getStringExtra("contents");
                float ratingbarupdate = intent.getFloatExtra("ratingbarupdate",0.0f);
                outputView.setText(contents);
                ratingBar.setRating(ratingbarupdate);
            }
        }
    }










    // 3.위험 권한 부여 연습
    private void dangerous_permission(){

        // 권한이 이미 부여된 경우가 있을 수가 있어요.. (권한 확인하는 코드)
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if(permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this,"SMS 수신 권한 주어져 있음",Toast.LENGTH_LONG).show();
        } else{
            //else if(permissionCheck == PackageManager.PERMISSION_DENIED){
            Toast.makeText(this,"SMS 수신 권한 없음.",Toast.LENGTH_LONG).show();
        }

    }
    // 여기서 권한이 부여가 됬는지, 안됬는지 확인을 여기서 할 수가 있다.
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode){
//            case 22:
//                if(grantResults.length > 0){
//
//                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){   //PERMISSION_GRANTED 사용자가 수락을 할 경우
//                        Toast.makeText(this,"SMS 수신 권한을 사용자가 승인함.",Toast.LENGTH_LONG).show();
//                    }else if(grantResults[0] == PackageManager.PERMISSION_DENIED){  // 사용자가 거부할 경우
//                        Toast.makeText(this,"SMS 수신 권한을 사용자가 거부함.",Toast.LENGTH_LONG).show();
//                    }
//                }else {
//                    Toast.makeText(this,"SMS 수신 권한을 부여받지 못함.",Toast.LENGTH_LONG).show();
//                }
//        }
//    }






    // 2.브로드캐스트 수신자(=브로드캐스트 리시버) 연습
        // activity_sms.xml
        // SmsReceiver.java      <- 브로드캐스트 수신자, 리시버
        // AndroidManifest.xml   <- receiver태그, intent-filter태그, action태그 추가
        // app                   <- 버전 22 변경
        // SmsActivity.java      <- 브로드캐스트 수신자에서 sms를 받아서(intent로 받음) 화면으로 보여주는 기능
        // activity_sms.xml      <- 보여줄 화면





    // 1.노티피케이션 연습
    private void notipractice(){
        createNotificationChannel();

        btn1 = findViewById(R.id.btn1);

        // 다음 스니펫은 사용자가 알림을 탭하면 활동을 여는 기본 인텐트를 만드는 방법을 보여줍니다.
        Intent intent = new Intent(this, Two.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0); //PendingIntent->앱이 꺼져 있오도 원격으로 킬 수가 있는 거

        // Bitmap으로 바꾼 후 저장
        Bitmap bitimg = BitmapFactory.decodeResource(getResources(),R.drawable.internet);



        // 브로드캐스트 전송 (이거 사용한적 없음)
//        Intent brodcastsend_intent = new Intent();
//        brodcastsend_intent.setAction("com.example.broadcast.MY_NOTIFICATION");
//        brodcastsend_intent.putExtra("data","Notice me senpai!");
//        sendBroadcast(brodcastsend_intent);

        //////////////4번째 커밋(시작)//////////////////////////////////////////
        //작업 버튼 추가 ( + 브로드캐스트 )
        Intent snoozeIntent = new Intent(this, MyBroadcastReceiver.class);
        snoozeIntent.setAction("snooze");
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
        PendingIntent snoozePendingIntent =
                PendingIntent.getBroadcast(this, 0, snoozeIntent, 0);
        //////////////4번째 커밋(끝)/////////////////////////////////////////////////


        // 알림의 콘텐츠와 채널 설정
        builder = new NotificationCompat.Builder(this, "Channel_Id")
                .setSmallIcon(R.drawable.receiveimg)  // 작은 아이콘
                .setContentTitle("제목!!")  // 제목
                .setContentText("본문!! 입니다~~~")  // 본문 텍스트
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitimg)) //이미지 추가!!
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)  // 알림 우선순위
                //밑에는 intent 사용해서 작성한거
                .setContentIntent(pendingIntent) // 사용자가 탭하면 자동으로 알림을 삭제
                .addAction(R.drawable.heart,"스누즈",snoozePendingIntent) // (4번째 커밋 영역)
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
