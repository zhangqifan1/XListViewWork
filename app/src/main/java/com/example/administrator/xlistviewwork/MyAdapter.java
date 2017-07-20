package com.example.administrator.xlistviewwork;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */

public class MyAdapter extends BaseAdapter {
    private List<Bean1.DataBean> list;
    private Context context;

    public MyAdapter(List<Bean1.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if(convertView==null){
                holder=new ViewHolder();
                convertView=convertView.inflate(context,R.layout.item,null);
                holder.textView1= (TextView) convertView.findViewById(R.id.tv1);
                holder.textView2= (TextView) convertView.findViewById(R.id.tv2);
                holder.textView3= (TextView) convertView.findViewById(R.id.tvCount);
                holder.imageView=convertView.findViewById(R.id.iv);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.textView1.setText(list.get(position).getNews_title());
            holder.textView2.setText(list.get(position).getNews_summary());
            holder.textView3.setText(list.get(position).getNews_id());
            ImageLoaderUtils.setImageView(list.get(position).getPic_url(),context,holder.imageView);
            return convertView;
        }
        static class ViewHolder{
            TextView textView1,textView2,textView3;
            ImageView imageView;
        }
}
