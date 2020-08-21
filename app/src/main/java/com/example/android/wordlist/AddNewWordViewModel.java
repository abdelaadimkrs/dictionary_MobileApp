package com.example.android.wordlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AddNewWordViewModel extends AndroidViewModel {

    private wordsRepository mRepository;

    public AddNewWordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new wordsRepository(application);

    }

    public void insert(words word)
    {
        mRepository.insert(word);
    }

    public void update(words word)
    {
        mRepository.update(word);
    }



}
