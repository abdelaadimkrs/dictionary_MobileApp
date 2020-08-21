package com.example.android.wordlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;

    private RecyclerView mRecycle;
    private WordAdapter mWordAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddNewWordActivity.class);
                startActivityForResult(intent,1);
            }
        });
        mRecycle = findViewById(R.id.recyclerView);
        mRecycle.setLayoutManager(new LinearLayoutManager(this));
        mRecycle.setHasFixedSize(true);

        mWordAdapter = new WordAdapter();
        mRecycle.setAdapter(mWordAdapter);

        mWordViewModel= new ViewModelProvider(this).get(WordViewModel.class);

        mWordViewModel.getmAllWords().observe(this, new Observer<List<words>>() {
            @Override
            public void onChanged(List<words> words) {
                mWordAdapter.setWord(words);
            }
        });



        mWordAdapter.OnItemClickListener(new WordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(words word) {
                Intent i = new Intent(MainActivity.this,AddNewWordActivity.class);
                i.putExtra(AddNewWordActivity.EXTRA_ID,word.getId());
                i.putExtra(AddNewWordActivity.EXTRA_WORD,word.getWordName());
                i.putExtra(AddNewWordActivity.EXTRA_MEANING,word.getWordMeaning());
                i.putExtra(AddNewWordActivity.EXTRA_TYPE,word.getWordType());
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return 0;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    int position= viewHolder.getAdapterPosition();
                   // mWordViewModel.delete(mWordAdapter.d);
            }
        }).attachToRecyclerView(mRecycle);


    }
}