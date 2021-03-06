package viewpager6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.noti.R;

import java.util.ArrayList;

public class Activity6 extends AppCompatActivity {
    ViewPager pager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_main);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(1);

        MoviePagerAdapter adapter = new MoviePagerAdapter(getSupportFragmentManager());
        Fragment6_1 f1= new Fragment6_1();
        Fragment6_2 f2= new Fragment6_2();
        Fragment6_3 f3= new Fragment6_3();
        adapter.addItem(f1);
        adapter.addItem(f2);
        adapter.addItem(f3);
        pager.setAdapter(adapter);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 두번째 프래그먼트 보기
                pager.setCurrentItem(1);
            }
        });


    }

    class MoviePagerAdapter extends FragmentStatePagerAdapter{
        ArrayList<Fragment> items = new ArrayList<Fragment>();

        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {   // 아이템 리턴
            return items.get(i);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        // 추가
        public void addItem(Fragment item){
            items.add(item);
        }

        // 페이지마다 title붙여서 리턴 해줄수 있는거 이다.
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "페이지 " +position;
        }
    }
}
