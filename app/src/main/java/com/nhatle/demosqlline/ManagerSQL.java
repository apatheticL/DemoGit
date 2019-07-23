package com.nhatle.demosqlline;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ManagerSQL {
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    public static final String DB_NAME = "truyencuoi";
    public ImageID intent;
    public ManagerSQL(Context context){
        this.context = context;
        this.intent=intent;
        copyExApp();
    }
    // copy tư file db sang ex
    private void copyExApp(){
        String path = Environment.getDataDirectory().getPath()+"/data/"+context.getPackageName()+"/db";
        if (new File(path+"/"+DB_NAME).exists()){
            return;
        }
        try {
            // lay inouttream trong access
            InputStream in = context.getAssets().open(DB_NAME);
            //lay duowng dan luu vao  ex app
            new File(path).mkdir();// tao thu muc
            OutputStream out = new FileOutputStream(path+"/"+DB_NAME);

            byte[] b = new byte[1024];
            int le = in.read(b);
            while (le>-1){
                out.write(b,0,le);
                le = in.read(b);
            }
            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openDB(){
        if (sqLiteDatabase==null||sqLiteDatabase.isOpen()==false) {

            String path = Environment.getDataDirectory().getPath() + "/data/"
                    + context.getPackageName() + "/db";
            sqLiteDatabase = SQLiteDatabase.openDatabase(path + "/" + DB_NAME,
                    null, SQLiteDatabase.OPEN_READWRITE);
        }

    }
    public void closeDB(){
        if (sqLiteDatabase==null||sqLiteDatabase.isOpen()==false){
            return;
        }
        sqLiteDatabase.close();
        sqLiteDatabase=null;
    }
    public List<Topic> getAllTopic(){
        List <Topic> listTopic = new ArrayList<>();
        openDB();

        Cursor c = sqLiteDatabase.rawQuery("select * from categories",null);
        c.moveToFirst();
        // lay tri so cac cot
        int indexID = c.getColumnIndex("id");
        int indexName= c.getColumnIndex("name");

        while (!c.isAfterLast()){
            int id = c.getInt(indexID);// lay thong tin cot id
            String name = c.getString(indexName);

            listTopic.add(new Topic(id,name));
            c.moveToNext();// chuyeern xuong hang tiep theo
        }


        closeDB();
        return listTopic;
    }

    public List<Store> getStore(int id) {
        openDB();
        List<Store> stores = new ArrayList<>();
        Cursor c = sqLiteDatabase.rawQuery("select * from stories where cat_id= "+id,null);
        c.moveToFirst();
        int indexID = c.getColumnIndex("id");
        int indexname = c.getColumnIndex("name");
        int indexContent = c.getColumnIndex("content");

        while (!c.isAfterLast()){
            int idStore = c.getInt(indexID);
                String nameStore = c.getString(indexname);
                String content = c.getString(indexContent);
                stores.add(new Store(idStore,nameStore,content));

            c.moveToNext();
        }
        c.close();
        closeDB();
        return stores;
    }
    public Store getData(int id){
        openDB();

        Cursor c = sqLiteDatabase.rawQuery("select * from stories where id= "+id,null);
        c.moveToFirst();
        int indexID = c.getColumnIndex("id");
        int indexname = c.getColumnIndex("name");
        int indexContent = c.getColumnIndex("content");

        Store store =null;
        if(c != null && c.moveToFirst()){
            int idStore = c.getInt(indexID);
            String nameStore = c.getString(indexname);
            String content = c.getString(indexContent);
            store = new Store(idStore,nameStore,content);
            c.close();
        }

        closeDB();
        return store;
    }
}
