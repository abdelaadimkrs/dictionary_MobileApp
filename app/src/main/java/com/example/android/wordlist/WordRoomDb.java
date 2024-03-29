package com.example.android.wordlist;

import android.content.Context;
import android.os.AsyncTask;
import android.provider.UserDictionary;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = words.class,version = 1)
public abstract class WordRoomDb  extends RoomDatabase {

    private static WordRoomDb instance;
    public abstract wordsDao wordDao();

    //Singlton
    public static synchronized WordRoomDb getInstance(Context context)
    {
        if(instance==null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
            WordRoomDb.class,"word-Database"
            ).fallbackToDestructiveMigration().build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
           new  PopulateDataAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class PopulateDataAsyncTask extends AsyncTask<Void,Void,Void>{
        private  wordsDao mWordsDao;

        PopulateDataAsyncTask(WordRoomDb db){
            mWordsDao= db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.insert(new words("book","Book","noun"));
            mWordsDao.insert(new words("book","Book","noun"));
            mWordsDao.insert(new words("book","Book","noun"));
            return null;
        }
    }
}
