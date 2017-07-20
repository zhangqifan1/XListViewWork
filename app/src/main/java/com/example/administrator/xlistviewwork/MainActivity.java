package com.example.administrator.xlistviewwork;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

import static com.example.administrator.xlistviewwork.R.id.but3;

public class MainActivity extends FragmentActivity {

    /**
     * 栏目一
     */
    private RadioButton mBut1;
    /**
     * 栏目二
     */
    private RadioButton mBut2;
    /**
     * 栏目三
     */
    private RadioButton mBut3;
    public static Fragment01 fragment01;
    public static Fragment02 fragment02;
    public static Fragment03 fragment03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragment01 = new Fragment01();
        fragment02 = new Fragment02();
        fragment03 = new Fragment03();

    }

    public void onclick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        switch (view.getId()) {
            case R.id.but1:
                mBut1.setChecked(true);
                mBut2.setChecked(false);
                mBut3.setChecked(false);
                manager.beginTransaction().replace(R.id.frame,fragment01).show(fragment01).commit();

                break;
            case R.id.but2:
                mBut2.setChecked(true);
                mBut1.setChecked(false);
                mBut3.setChecked(false);
                manager.beginTransaction().replace(R.id.frame,fragment02).show(fragment02).commit();
                break;
            case but3:
                mBut3.setChecked(true);
                mBut1.setChecked(false);
                mBut2.setChecked(false);
                manager.beginTransaction().replace(R.id.frame,fragment03).show(fragment03).commit();

                break;
            default:
                break;
        }
    }

    private void initView() {
        mBut1 = (RadioButton) findViewById(R.id.but1);
        mBut2 = (RadioButton) findViewById(R.id.but2);
        mBut3 = (RadioButton) findViewById(but3);
    }
}
