package controller;

import android.view.MotionEvent;
import android.view.View;

public interface GameActivityInterface {
    boolean dispatchTouchEvent(MotionEvent ev);

    void onClick(View v);
}
