package com.example.android.wordlist;

import android.provider.UserDictionary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface wordsDao {
    @Insert
    void insert(words word);

    @Update
    void update(words word);

    @Delete
    void delete(words word);

    @Query("DELETE FROM wordTable")
    void deleteAllWords();

    @Query("SELECT * FROM wordTable")
    LiveData<List<words>> getAllWords();
}
