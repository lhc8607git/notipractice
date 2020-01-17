package broadcasting2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    private static SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 포맷

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("SmsReceiver","onReceive() 호출됨");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages =parseSmsMessage(bundle);

        if(messages.length > 0){
            String sender = messages[0].getOriginatingAddress(); // 상대방 번호 = 발신 번호
            Log.d("SmsReceiver","sender : " +sender);

            String contents = messages[0].getMessageBody().toString(); // 메시지내용
            Log.d("SmsReceiver","contents : " +contents);

            Date receivedDate = new Date(messages[0].getTimestampMillis()); // 발신한 시각
            Log.d("SmsReceiver","receivedDate : " +receivedDate);

            // 보내는 방법
            sendToActivity(context,sender,contents,receivedDate);
        }
    }

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate){
        Intent intent = new Intent(context, SmsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                |Intent.FLAG_ACTIVITY_SINGLE_TOP
                |Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender",sender);
        intent.putExtra("contents",contents);
        intent.putExtra("receivedDate",fm.format(receivedDate));


        context.startActivity(intent);
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs = (Object[])bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        for(int i = 0; i<objs.length; i++){ //sms로 복원을 해볼거다.
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i],format);
            }else { // 단말이 이전 버전 이면
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
            }
        }
        return messages;

    }
}
