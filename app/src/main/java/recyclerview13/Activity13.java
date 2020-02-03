package recyclerview13;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.noti.R;

public class Activity13 extends AppCompatActivity {
    RecyclerView recyclerView;
    SingerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity13_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // 세로,가로 방향 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager); // 만든 레이아웃매니저를 넣어줍니다.

        adapter = new SingerAdapter(getApplicationContext());
        adapter.addItem(new SingerItem("히짱아","010-1000-1000"));
        adapter.addItem(new SingerItem("이구아나","010-2000-2000"));
        adapter.addItem(new SingerItem("노라조","010-3000-3000"));
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new SingerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SingerAdapter.ViewHolder holder, View view, int position) {
                SingerItem item = adapter.getItem(position);  // 클릭 된거

                Toast.makeText(getApplicationContext(),"아이템 선택됨 : " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
