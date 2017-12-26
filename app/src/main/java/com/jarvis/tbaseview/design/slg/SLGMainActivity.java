package com.jarvis.tbaseview.design.slg;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jarvis.tbaseview.design.slg.duck.BlueDuck;
import com.jarvis.tbaseview.design.slg.duck.RedDuck;
import com.jarvis.tbaseview.design.slg.duck.WhiteDuck;
import com.jarvis.tbaseviewlib.ui.common.TActivity;

/**
 * 策略模式：定义了算法族(flyAction,quackAction)，分别封装起来，让他们之间可以互相替换，此模式让算法的变化独立于使用算法的客户
 * 作者: tansheng on 2016/3/25.
 * QQ:717549357
 */
public class SLGMainActivity extends TActivity {
    private LinearLayout view;
    private Button whiteBtn;
    private Button redBtn;
    private Button blueBtn;

    private WhiteDuck whiteDuck;
    private RedDuck redDuck;
    private BlueDuck blueDuck;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        view = new LinearLayout(context);
        whiteBtn = new Button(context);
        whiteBtn.setText("白色鸭子");
        redBtn = new Button(context);
        redBtn.setText("红色鸭子");
        blueBtn = new Button(context);
        blueBtn.setText("蓝色鸭子");

        whiteDuck = new WhiteDuck(context);
        redDuck = new RedDuck(context);
        blueDuck = new BlueDuck(context);


        whiteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                whiteDuck.dispaly();
                whiteDuck.doFlyAction();
                whiteDuck.doQuackAction();
            }
        });
        redBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redDuck.dispaly();
                redDuck.doFlyAction();
                redDuck.doQuackAction();

            }
        });
        blueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blueDuck.dispaly();
                blueDuck.doFlyAction();
                blueDuck.doQuackAction();

            }
        });
        view.addView(whiteBtn);
        view.addView(redBtn);
        view.addView(blueBtn);
        setContentView(view);


        initView(false);
        setData();
    }


    @Override
    public void initView(boolean isStatusBar) {
        super.initView(isStatusBar);
    }

    @Override
    public void setData() {

    }

    @Override
    public void requestData(boolean isShow) {

    }
}
