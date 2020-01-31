package capture9;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback{   // "껍데기"
    SurfaceHolder holder;  // "실제 제어"
    Camera camera = null;  // "이안에서 카메라를 다룹니다."


    public CameraSurfaceView(Context context) {
        super(context);

        init(context);
    }

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){   // 초기화
        holder = getHolder();  // SurfaceView 안에 있는 holder 참조해야 합니다.
        holder.addCallback(this);
    }

    // SurfaceHolder.Callback : 콜백 메소드들 (총 3개)////////////////////////////////////////////////////////////////
    @Override
    public void surfaceCreated(SurfaceHolder holder) {  // 1-메모리 만들어지는 시점에 호출합니다.
        camera = Camera.open();  // 카메라 객체 참조

        try {
            camera.setPreviewDisplay(holder); // 미리보기로 쓴다는 걸 설정합니다.
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { // 2-변경되는시점(크기같은거)
        camera.startPreview(); // "미리보기" 화면에 픽셀을 막 뿌리기 시작 합니다.

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) { // 3-없어질 때 호출
        camera.stopPreview(); // "미리보기" 중지 시킵니다.
        camera.release(); // 리소스도 없어지게 됩니다.
        camera = null;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // SurfaceView에서 사진을 찍을 수 있도록 하는 메소드 선언 (만듬)
    public boolean capture(Camera.PictureCallback callback){
        if(camera != null){
            camera.takePicture(null,null,callback); // 사진을 찍게 하는거
            return true;
        }else {
            return false;
        }
    }

}
