package com.example.android.wordlist;

import android.app.Application;
import android.os.AsyncTask;
import android.provider.UserDictionary;

import androidx.lifecycle.LiveData;

import java.util.List;

public class wordsRepository {

    private wordsDao mWordsDao ;
    private LiveData<List<words>> getAllwords;
    public wordsRepository(Application app){

        WordRoomDb db = WordRoomDb.getInstance(app);
        mWordsDao = db.wordDao();
        getAllwords = mWordsDao.getAllWords();
    }

    //insert
        public void insert (words word)
        {
            new InsertAsyncTask(mWordsDao).execute(word);
        }
    //delete
    public void delete(words word)
    {
        new DeletetAsyncTask(mWordsDao).execute(word);
    }

    //update
    public void update(words word)
    {
        new UpdatetAsyncTask(mWordsDao).execute(word);
    }
    //getAllWords
    public LiveData<List<words>>getAllWords()
    {
        return getAllwords;
    }
    //deleteAllWords

    public void deleteAllWord()
    {
        new UpdatetAsyncTask(mWordsDao).execute();
    }

    private static class InsertAsyncTask extends AsyncTask<words,Void,Void>{
        private  wordsDao mWordsDao;

        public InsertAsyncTask(wordsDao wordsDoa){
            mWordsDao=wordsDoa;
        }

        @Override
        protected Void doInBackground(words... words) {
           mWordsDao.insert(words[0]);
            return null;
        }
    }
    private static class UpdatetAsyncTask extends AsyncTask<words,Void,Void>{
        private  wordsDao mWordsDao;

        public UpdatetAsyncTask(wordsDao wordsDoa){
            mWordsDao=wordsDoa;
        }

        @Override
        protected Void doInBackground(words... words) {
            mWordsDao.update(words[0]);
            return null;
        }
    }

    private static class DeletetAsyncTask extends AsyncTask<words,Void,Void>{
        private  wordsDao mWordsDao;

        public DeletetAsyncTask(wordsDao wordsDoa){
            mWordsDao=wordsDoa;
        }

        @Override
        protected Void doInBackground(words... words) {
            mWordsDao.delete(words[0]);
            return null;
        }
    }
    private static class DeleteAllWordAsyncTask extends AsyncTask<Void,Void,Void>{
        private  wordsDao mWordsDao;

        public DeleteAllWordAsyncTask(wordsDao wordsDoa){
            mWordsDao=wordsDoa;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.deleteAllWords();
            return null;
        }
    }
}
