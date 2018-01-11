package com.zonekey.testanim.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.zonekey.testanim.R;

/**
 * Created by xu.wang
 * Date on  2018/1/10 13:24:20.
 *
 * @Desc
 */

public class LeftPopupWindow extends PopupWindow {
    private final static String TAG = "LeftPopupWindow";
    private Context mContext;
    private ImageView iv_1, iv_2, iv_3, iv_4,
            iv_anim_1, iv_anim_2, iv_anim_3, iv_anim_4;
    private RelativeLayout rl_content;
    private ImageView mIv;

    public LeftPopupWindow(Context context, ImageView iv) {
        super(context);
        this.mContext = context;
        this.mIv = iv;
        initialView();
    }

    private void initialView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_left, null);
        iv_1 = view.findViewById(R.id.iv_left_1);
        iv_2 = view.findViewById(R.id.iv_left_2);
        iv_3 = view.findViewById(R.id.iv_left_3);
        iv_4 = view.findViewById(R.id.iv_left_4);
        rl_content = view.findViewById(R.id.ll_pop_left_content);
        iv_anim_1 = view.findViewById(R.id.iv_left_anim_1);
        iv_anim_2 = view.findViewById(R.id.iv_left_anim_2);
        iv_anim_3 = view.findViewById(R.id.iv_left_anim_3);
        iv_anim_4 = view.findViewById(R.id.iv_left_anim_4);
        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        rl_content.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rl_content.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                startAnimation();
            }
        });
    }

    private void startAnimation() {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        int popWidth = windowManager.getDefaultDisplay().getWidth();
        int imageWidth = iv_anim_4.getWidth();
        int[] tempLocation = new int[2];
        mIv.getLocationOnScreen(tempLocation);
        int startPosY = tempLocation[1];
        TranslateAnimation sa1 = new TranslateAnimation(0, (popWidth - imageWidth) / 2, 0, 0);
        sa1.setDuration(1500);
        sa1.setInterpolator(new BounceInterpolator());
        for (int i = 0; i < 4; i++) {
            final int pos = i;
            int[] location = new int[2];
            switch (i) {
                case 0:
                    iv_1.getLocationOnScreen(location);
                    break;
                case 1:
                    iv_2.getLocationOnScreen(location);
                    break;
                case 2:
                    iv_3.getLocationOnScreen(location);
                    break;
                case 3:
                    iv_4.getLocationOnScreen(location);
                    break;
            }
            Log.e(TAG, "popWidth = " + popWidth + "imagePosY = " + location[1]);
            TranslateAnimation sa = new TranslateAnimation(0, (popWidth - imageWidth) / 2, startPosY, location[1]);
            sa.setDuration(600);
//            sa.setInterpolator(new BounceInterpolator());
            switch (i) {
                case 0:
                    iv_anim_1.startAnimation(sa);
                    break;
                case 1:
                    iv_anim_2.startAnimation(sa);
                    break;
                case 2:
                    iv_anim_3.startAnimation(sa);
                    break;
                case 3:
                    iv_anim_4.startAnimation(sa);
                    break;
            }
            sa.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    animStart(pos);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    animEnd(pos);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }

    }

    private void animStart(int index) {
        Log.e(TAG, "anim start index = " + index);
        switch (index) {
            case 0:
                iv_anim_1.setVisibility(View.VISIBLE);
                iv_1.setVisibility(View.GONE);
                break;
            case 1:
                iv_anim_2.setVisibility(View.VISIBLE);
                iv_2.setVisibility(View.GONE);
                break;
            case 2:
                iv_anim_3.setVisibility(View.VISIBLE);
                iv_3.setVisibility(View.GONE);
                break;
            case 3:
                iv_anim_4.setVisibility(View.VISIBLE);
                iv_4.setVisibility(View.GONE);
                break;
        }
    }

    private void animEnd(int index) {
        Log.e(TAG, "anim end index = " + index);
        switch (index) {
            case 0:
                iv_1.setVisibility(View.VISIBLE);
                iv_anim_1.setVisibility(View.GONE);
                break;
            case 1:
                iv_2.setVisibility(View.VISIBLE);
                iv_anim_2.setVisibility(View.GONE);
                break;
            case 2:
                iv_3.setVisibility(View.VISIBLE);
                iv_anim_3.setVisibility(View.GONE);
                break;
            case 3:
                iv_4.setVisibility(View.VISIBLE);
                iv_anim_4.setVisibility(View.GONE);
                break;
        }
    }

    /*
     * getWindow().getDecorView()得到的View是Window中的最顶层View，可以从Window中获取到该View，
     * 然后该View有个getWindowVisibleDisplayFrame()方法可以获取到程序显示的区域，
     * 包括标题栏，但不包括状态栏。
     */
    public void show(AppCompatActivity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int winHeight = activity.getWindow().getDecorView().getHeight();
        this.showAtLocation(activity.getWindow().getDecorView(), Gravity.BOTTOM, 0, winHeight - rect.bottom);
    }

}
