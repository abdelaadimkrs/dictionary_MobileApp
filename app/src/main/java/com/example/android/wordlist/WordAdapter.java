package com.example.android.wordlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.security.AllPermission;
import java.util.ArrayList;
import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.wordViewHolder> {

    private List<words> listWords = new ArrayList<>();
    public OnItemClickListener mListener;
    @NonNull
    @Override
    public wordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_item,parent,false);
        return new  wordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull wordViewHolder holder, int position) {
        words currentWord = listWords.get(position);
        holder.editBook.setText(currentWord.getWordName());
        holder.editMeaning.setText(currentWord.getWordMeaning());
        holder.editType.setText(currentWord.getWordType());

    }

    public void setWord(List<words> word)
    {
        listWords = word;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
            return listWords.size();
    }

    public  class wordViewHolder extends RecyclerView.ViewHolder{

         public TextView editBook;
         public TextView editMeaning;
         public TextView editType;
        public wordViewHolder(@NonNull View itemView) {
            super(itemView);
            editBook = itemView.findViewById(R.id.word_text);
            editMeaning = itemView.findViewById(R.id.meaning_text);
            editType = itemView.findViewById(R.id.type_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    if(mListener!=null && index!=RecyclerView.NO_POSITION)
                    {
                        mListener.onItemClick(listWords.get(index));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener{
        void onItemClick(words word);
    }

    public void OnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }


    public words Words(int position)
    {
        return listWords.get(position);
    }
}
