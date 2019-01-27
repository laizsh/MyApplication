package com.soon.test.myapplication.utils;

import android.content.Context;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Utils {
    public static void saveObject(Object object, String filePath){
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            File file = new File(filePath);
            if (!file.exists()) {
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();

            byteArrayOutputStream.writeTo(fileOutputStream);
        }
        catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }

                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }

                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object readObject(String filePath) throws Exception{
        ObjectInputStream objectInputStream = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        FileInputStream fileInputStream = null;
        try{
            File objFile = new File(filePath);
            if(!objFile.exists()){
                return null;
            }
            fileInputStream = new FileInputStream(objFile);
            byteArrayOutputStream = new ByteArrayOutputStream();
            int len = -1;
            byte[] buffer = new byte[1024];
            while((len = fileInputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return objectInputStream.readObject();
        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        }finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
                if(byteArrayInputStream != null){
                    byteArrayInputStream.close();
                }
                if(byteArrayOutputStream != null){
                    byteArrayOutputStream.close();
                }

                if(fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static void copy(File src, File dst) throws IOException {
        if (dst.exists()) {
            dst.delete();
        }
        InputStream in = new FileInputStream(src);
        try {
            OutputStream out = new FileOutputStream(dst);
            try {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
}
