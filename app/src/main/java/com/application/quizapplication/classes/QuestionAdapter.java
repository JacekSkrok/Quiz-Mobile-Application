package com.application.quizapplication.classes;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.application.quizapplication.R;
import com.application.quizapplication.Utils.FirebaseUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    ArrayList<QuizQuestion> quizQuestions;
    private FirebaseDatabase aFirebaseDatabase;
    private DatabaseReference aDatabaseReference;
    private ChildEventListener aChildListener;

    public QuestionAdapter() {
        FirebaseUtil.opnFbReference("quizquestions");
        aFirebaseDatabase = FirebaseUtil.aFirebaseDatabase;
        aDatabaseReference = FirebaseUtil.aDatabaseReference;
        quizQuestions = FirebaseUtil.aQuestions;
        aChildListener = new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                QuizQuestion qq = snapshot.getValue(QuizQuestion.class);
                Log.d("Question: ", qq.getQuestionText());
                qq.setId(snapshot.getKey());
                quizQuestions.add(qq);
                notifyItemInserted(quizQuestions.size()-1);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        aDatabaseReference.addChildEventListener(aChildListener);
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rv_row, parent, false);
        return new QuestionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        QuizQuestion question = quizQuestions.get(position);
        holder.bind(question);
    }

    @Override
    public int getItemCount() {
        return quizQuestions.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }

        public void bind (QuizQuestion question) {
            tvTitle.setText(question.getQuestionText());
        }
    }
}
