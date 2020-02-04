package pagesliding18;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.noti.R;

public class Activity18 extends AppCompatActivity {
    // 옆에서 슬라이드
    LinearLayout page;
    Animation translateLeft;
    Animation translateRight;

    Button button;
    boolean isPageOpen =false;  // 닫혀있는 상태


    //아래에서 슬라이드
    LinearLayout page2;
    Animation translateBottom;
    Animation translateTop;

    Button button2;
    boolean isPageOpen2 =false;   // 닫혀있는 상태



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity18_main);

        // 옆에서 슬라이드------------------------------------------------------------------
        page = findViewById(R.id.page);

        translateLeft = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this,R.anim.translate_right);

        SlidingAnimationListener listener = new SlidingAnimationListener();
        translateLeft.setAnimationListener(listener);
        translateRight.setAnimationListener(listener);

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPageOpen){
                    page.startAnimation(translateRight);
                }else{
                    page.setVisibility(View.VISIBLE);
                    page.startAnimation(translateLeft);
                }
            }
        });
        // 옆에서 슬라이드------------------------------------------------------------------



        //아래에서 슬라이드----------------------------------------------------------------
        page2 = findViewById(R.id.page2);

        translateBottom = AnimationUtils.loadAnimation(this,R.anim.translate_bottom);
        translateTop = AnimationUtils.loadAnimation(this,R.anim.translate_top);

        SlidingAnimationListener2 listener2 = new SlidingAnimationListener2();
        translateBottom.setAnimationListener(listener2);
        translateTop.setAnimationListener(listener2);

        button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isPageOpen2){
                    page2.startAnimation(translateBottom);
                }else{
                    page2.setVisibility(View.VISIBLE);
                    page2.startAnimation(translateTop);
                }
            }
        });
        //아래에서 슬라이드----------------------------------------------------------------


    }

    class SlidingAnimationListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {
            // 애니메이션 시작 될떄
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // 애니메이션 끝나는 시점
            if(isPageOpen) {
                page.setVisibility(View.INVISIBLE);

                button.setText("열기");
                isPageOpen=false;
            }else{
                button.setText("닫기");
                isPageOpen =true;
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // 반복되는 시점
        }
    }



    class SlidingAnimationListener2 implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {
            // 애니메이션 시작 될떄
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            // 애니메이션 끝나는 시점
            if(isPageOpen2) {
                page2.setVisibility(View.INVISIBLE);

                button2.setText("열기");
                isPageOpen2=false;
            }else{
                button2.setText("닫기");
                isPageOpen2=true;
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            // 반복되는 시점
        }
    }
}
