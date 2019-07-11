package com.shanghai.logistics.util;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

/**
 * @author chensong
 * @date 2019/5/16 16:44
 */
public class DiyView extends View {
    public DiyView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
