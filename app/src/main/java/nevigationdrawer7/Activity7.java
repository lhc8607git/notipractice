package nevigationdrawer7;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Toast;

import com.example.noti.R;

public class Activity7 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,FragmentCallback {

    // 프레임레이아웃 안에다가 프래그먼트 추가할려고
    Fragment7_1 f1;
    Fragment7_2 f2;
    Fragment7_3 f3;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity7_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  // 화면에 툴바를 설정 할려면! 꼭 넣주기


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);




        // 프래그먼트 생성
        f1 = new Fragment7_1();
        f2 = new Fragment7_2();
        f3 = new Fragment7_3();
        // 프래임레이아웃에 프래그먼트 추가하는 방법
        getSupportFragmentManager().beginTransaction().add(R.id.container, f1).commit();


    }

    // 이거 인터페이스 만들어서 사용함
    @Override
    public void onFragmentSelected(int position, Bundle bundle) {
        Fragment curenFragment =null;
        if(position == 0){
            curenFragment = f1;
            toolbar.setTitle("첫번째 화면");
        }else if(position ==1){
            curenFragment =f2;
            toolbar.setTitle("두번째 화면");
        }else if(position ==2){
            curenFragment = f3;
            toolbar.setTitle("셋번째 화면");
        }
        // 지정한 걸로 화면 보여줄려고 한다면!!
        getSupportFragmentManager().beginTransaction().replace(R.id.container, curenFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity7, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_0) {
            Toast.makeText(this,"첫번째11",Toast.LENGTH_LONG).show();
            onFragmentSelected(0,null);
        } else if (id == R.id.nav_1) {
            Toast.makeText(this,"두번째22",Toast.LENGTH_LONG).show();
            onFragmentSelected(1,null);
        } else if (id == R.id.nav_2) {
            Toast.makeText(this,"셋번째33",Toast.LENGTH_LONG).show();
            onFragmentSelected(2,null);
        } else if (id == R.id.xxxx1) {
            // 확인 할려고( 어떻게 나오는지 )
        } else if (id == R.id.xxxx2) {
            // 확인 할려고( 어떻게 나오는지 )
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
