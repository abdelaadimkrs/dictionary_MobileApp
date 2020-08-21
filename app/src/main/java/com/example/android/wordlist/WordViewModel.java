package com.example.android.wordlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private wordsRepository mRepository;
    private LiveData<List<words>> mAllWords;
    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new wordsRepository(application);
        mAllWords= mRepository.getAllWords();
    }

    public void insert(words word)
    {
        mRepository.insert(word);
    }
    public void delete(words word)
    {
        mRepository.delete(word);
    }
    public void update(words word)
    {
        mRepository.update(word);
    }
    public void deleteAllWords(words word)
    {
        mRepository.deleteAllWord();
    }

    public  LiveData<List<words>> getmAllWords()
    {
        return  mAllWords;
    }
}
