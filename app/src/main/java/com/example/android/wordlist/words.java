package com.example.android.wordlist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="wordTable")
public class words {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String wordName;
    private String wordMeaning;
    private String wordType;

    public words(String wordName, String wordMeaning, String wordType) {
        this.wordName = wordName;
        this.wordMeaning = wordMeaning;
        this.wordType = wordType;
    }

    public int getId() {
        return id;
    }

    public String getWordName() {
        return wordName;
    }

    public String getWordMeaning() {
        return wordMeaning;
    }

    public String getWordType() {
        return wordType;
    }

    public void setId(int id) {
        this.id = id;
    }
}
