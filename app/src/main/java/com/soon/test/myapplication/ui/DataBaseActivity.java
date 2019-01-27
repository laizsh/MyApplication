package com.soon.test.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.soon.test.myapplication.R;
import com.soon.test.myapplication.database.Item;
import com.soon.test.myapplication.database.ItemDBHelper;
import com.soon.test.myapplication.utils.Utils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class DataBaseActivity extends Activity implements View.OnClickListener{
    Item mItem;
    String mObjFileSavePath = new File(Environment.getExternalStorageDirectory().getPath(), "/soontest/database/obj").toString();
    List<Item> mItems = new LinkedList<>();
    String mObjsFileSavePath = new File(Environment.getExternalStorageDirectory().getPath(), "/soontest/database/objs").toString();

    ItemDBHelper mItemDBhelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("DataBaseActivity", "onCreate");

        setContentView(R.layout.database_activity);

        mItemDBhelper = new ItemDBHelper(this);

        mItem = constructItem();
        mItems = constructItems(1000);

        findViewById(R.id.btnSaveObjFile).setOnClickListener(this);
        findViewById(R.id.btnReadObjFile).setOnClickListener(this);
        findViewById(R.id.btnSaveObjsFile).setOnClickListener(this);
        findViewById(R.id.btnReadObjsFile).setOnClickListener(this);

        findViewById(R.id.btnSaveObjDB).setOnClickListener(this);
        findViewById(R.id.btnReadObjDB).setOnClickListener(this);
        findViewById(R.id.btnSaveObjsDB).setOnClickListener(this);
        findViewById(R.id.btnReadObjsDB).setOnClickListener(this);
        findViewById(R.id.btnClearDB).setOnClickListener(this);
    }

    private Item constructItem(){
        Item item = new Item();
        item.str1 = "test";
        item.str2 = "https://img-blog.csdn.net/20180709075058726?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21pZG5pZ2h0X3RpbWU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70";
        item.str3 = "xxxxxadsada";
        item.str4 = "22020202001";
        item.str5 = "xxadaskjdyakjyd";
        item.str6 = "xxxx";
        item.str7 = "1111";
        item.int1 = 112313123;
        item.int2 = 2222;
        item.boolean1 = true;

        return item;
    }

    private List<Item> constructItems(int count){
        List<Item> items = new LinkedList<>();
        for(int i = 0;i<count;++i){
            Item item = new Item();
            item.str1 = "xxxxx" + i;
            item.str2 = "https://img-blog.csdn.net/20180709075058726?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L21pZG5pZ2h0X3RpbWU=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70";
            item.str3 = "xxxxxadsada";
            item.str4 = "22020202002";
            item.str5 = "xxadaskjdyakjyd";
            item.str6 = "xxxx";
            item.str7 = "1111";
            item.int1 = 112313123;
            item.int2 = 2222;
            item.boolean1 = true;
            items.add(item);
        }

        return items;
    }

    //execute 10 times and return cost time in million seconds
    private double executeTimes(Runnable task, int exetime){
        long startTime = System.nanoTime();
        for(int i = 0;i < exetime;++i){
            task.run();
        }
        double ret = ((double)(System.nanoTime() - startTime))/(1000000*exetime);
        return ret;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSaveObjFile:
                double exeTime = executeTimes(new Runnable() {
                    @Override
                    public void run() {
                        Utils.saveObject(mItem, mObjFileSavePath);
                    }
                },10);
                System.out.println("save Obj by file time =" + exeTime + "ms");
                break;
            case R.id.btnReadObjFile:
                exeTime = executeTimes(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            Item item = (Item) Utils.readObject(mObjFileSavePath);
                        }catch (Throwable e){
                            e.printStackTrace();
                        }
                    }
                },10);

                System.out.println("read Obj by file time =" + exeTime + "ms");

                break;
            case R.id.btnSaveObjsFile:
                exeTime = executeTimes(new Runnable() {
                    @Override
                    public void run() {
                        Utils.saveObject(mItems, mObjsFileSavePath);
                    }
                },10);
                System.out.println("save Objs by file time =" + exeTime + "ms");
                break;
            case R.id.btnReadObjsFile:
                exeTime = executeTimes(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            mItems = (List<Item>) Utils.readObject(mObjsFileSavePath);
                        }catch (Throwable e){
                            e.printStackTrace();
                        }
                    }
                },10);
                System.out.println("read Objs by file time =" + exeTime + "ms");
                break;

            case R.id.btnSaveObjDB:
                exeTime = executeTimes(new Runnable() {
                    @Override
                    public void run() {
                        mItemDBhelper.saveObj(mItem);
                    }
                },1);
                System.out.println("save Obj by db time =" + exeTime + "ms");
                break;
            case R.id.btnReadObjDB:
                exeTime = executeTimes(new Runnable() {
                    @Override
                    public void run() {
                        mItem = mItemDBhelper.readItem("test");
                    }
                },1);

                System.out.println("read Obj by db time =" + exeTime + "ms");

                break;
            case R.id.btnSaveObjsDB:
                exeTime = executeTimes(new Runnable() {
                    @Override
                    public void run() {
                       mItemDBhelper.saveObjs(mItems);
                    }
                },1);
                System.out.println("save Objs by db time =" + exeTime + "ms");
                break;
            case R.id.btnReadObjsDB:
                exeTime = executeTimes(new Runnable() {
                    @Override
                    public void run() {
                        mItems = mItemDBhelper.readItems();
                    }
                },1);
                System.out.println("read Objs by db time =" + exeTime + "ms");
                break;
            case R.id.btnClearDB:
                exeTime = executeTimes(new Runnable() {
                    @Override
                    public void run() {
                        mItemDBhelper.clearItems();
                    }
                },1);
                System.out.println("clear db cost time =" + exeTime + "ms");
                break;
            default:break;
        }
    }
}
