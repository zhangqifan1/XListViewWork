package com.example.administrator.xlistviewwork;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class Fragment02 extends Fragment implements XListView.IXListViewListener {
    private String path1 = "http://api.expoon.com/AppNews/getNewsList/type/2/p/1";
    private String path2 = "http://api.expoon.com/AppNews/getNewsList/type/2/p/2";
    private String path3 = "http://api.expoon.com/AppNews/getNewsList/type/2/p/3";
    private  int a=0;
    private int b=0;
    private List<Bean1.DataBean> data;
    private XListView lv;
    private MyAdapter adapter;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
            closeXlistView();
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag1, container, false);
        lv = (XListView)view.findViewById(R.id.xListView);

        new Thread() {
            @Override
            public void run() {
                try {
                    HttpURLConnection connection = (HttpURLConnection) new URL(path1).openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(6000);
                    connection.setReadTimeout(6000);
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        InputStream inputStream = connection.getInputStream();
                        String textFromStream = Tools.getTextFromStream(inputStream);
                        Gson gson=new Gson();
                        Bean1 bean1 = gson.fromJson(textFromStream, Bean1.class);
                        data = bean1.getData();
                    }
                    getActivity().runOnUiThread(new Runnable() {



                        @Override
                        public void run() {
                            adapter = new MyAdapter(data, getContext());

                            lv.setPullLoadEnable(true);
                            lv.setPullRefreshEnable(true);
                            lv.setAdapter(adapter);
                            lv.setXListViewListener(MainActivity.fragment02);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return view;
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Toast.makeText(getContext(),"是不是要刷新？？",Toast.LENGTH_SHORT).show();
                a++;
                new Thread() {

                    private List<Bean1.DataBean> data2;

                    @Override
                    public void run() {
                        try {
                            HttpURLConnection connection = (HttpURLConnection) new URL(path2).openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(6000);
                            connection.setReadTimeout(6000);
                            int code = connection.getResponseCode();
                            if (code == 200) {
                                InputStream inputStream = connection.getInputStream();
                                String textFromStream = Tools.getTextFromStream(inputStream);
                                Gson gson=new Gson();
                                Bean1 bean1 = gson.fromJson(textFromStream, Bean1.class);
                                data2 = bean1.getData();
                                data.add(0,data2.get(a));
                                handler.sendEmptyMessage(0);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();



            }

        },2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                b++;
                Toast.makeText(getContext(),"是不是要加载？？",Toast.LENGTH_SHORT).show();
                new Thread() {

                    private List<Bean1.DataBean> data3;

                    @Override
                    public void run() {
                        try {
                            HttpURLConnection connection = (HttpURLConnection) new URL(path3).openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(6000);
                            connection.setReadTimeout(6000);
                            int code = connection.getResponseCode();
                            if (code == 200) {
                                InputStream inputStream = connection.getInputStream();
                                String textFromStream = Tools.getTextFromStream(inputStream);
                                Gson gson=new Gson();
                                Bean1 bean1 = gson.fromJson(textFromStream, Bean1.class);
                                data3 = bean1.getData();
                                data.add(data3.get(b));
                                handler.sendEmptyMessage(0);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        },2000);
    }
    private  void closeXlistView(){
        lv.stopRefresh();
        lv.stopLoadMore();
        lv.setRefreshTime(getCurrentTime());
    }
    private  String getCurrentTime(){
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        return time;
    };
}
