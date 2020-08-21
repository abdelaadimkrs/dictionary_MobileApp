package com.example.android.wordlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewWordActivity extends AppCompatActivity {

    private EditText wordName;
    private EditText wordMeaning;
    private EditText wordType;
    private AddNewWordViewModel mViewModel;

    private boolean editMode;
    private int mID;
    public static String EXTRA_ID= "com.example.android.wordlist.id";
    public static String EXTRA_WORD= "com.example.android.wordlist.word";
    public static String EXTRA_MEANING= "com.example.android.wordlist.meaning";
    public static String EXTRA_TYPE= "com.example.android.wordlist.type";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);

        wordName = findViewById(R.id.edit_text_word);
        wordMeaning = findViewById(R.id.edit_text_meaning);
        wordType = findViewById(R.id.edit_text_type);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);


        Intent i = getIntent();
        if(i.hasExtra(EXTRA_ID)){
            setTitle("Update Word");
            editMode= true;
            mID= i.getIntExtra(EXTRA_ID,-1);
            wordName.setText(i.getStringExtra(EXTRA_WORD));
            wordMeaning.setText(i.getStringExtra(EXTRA_MEANING));
            wordType.setText(i.getStringExtra(EXTRA_TYPE));

        }else{
            setTitle("Add new Word");
            editMode=false;
        }

        mViewModel= new ViewModelProvider(this).get(AddNewWordViewModel.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m =getMenuInflater();
        m.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_word:
                saveWord();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public void saveWord()
    {
        String word = wordName.getText().toString().trim();
        String meaning = wordMeaning.getText().toString().trim();
        String type = wordType.getText().toString().trim();

        words WordObject= new words(word,meaning,type);
        if(word.isEmpty()||meaning.isEmpty()||type.isEmpty())
        {
            Toast.makeText(this,"please fill all fields",Toast.LENGTH_LONG).show();
            return;
        }
        if(editMode)
        {
            WordObject.setId(mID);
            mViewModel.update(WordObject);
        }
        else
            mViewModel.insert(WordObject);


        finish();

    }
}