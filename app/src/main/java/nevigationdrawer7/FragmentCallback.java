package nevigationdrawer7;

import android.os.Bundle;

public interface FragmentCallback {
    //매개변수(몇번째 프래그먼트가 선택되었는지, 데이터 전달할려고)
    public void onFragmentSelected(int position, Bundle bundle);

}
