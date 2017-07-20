package com.example.administrator.xlistviewwork;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/7/19.
 */

public class Tools {
    public static String getTextFromStream(InputStream inputStream){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        int len=0;
        byte[] b=new byte[1024];
        try {
            while((len=inputStream.read(b))!=-1){
                baos.write(b,0,len);
            }
            baos.close();
            inputStream.close();
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
