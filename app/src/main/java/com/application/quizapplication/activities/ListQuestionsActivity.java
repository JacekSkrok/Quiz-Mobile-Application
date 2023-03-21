package com.application.quizapplication.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.quizapplication.R;
import com.application.quizapplication.Utils.FirebaseUtil;
import com.application.quizapplication.classes.QuestionAdapter;
import com.application.quizapplication.classes.QuizQuestion;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import com.application.quizapplication.Utils.FirebaseUtil;

public class ListQuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_questions);

        RecyclerView rvQuestions = findViewById(R.id.rvQuestions);
        final QuestionAdapter adapter = new QuestionAdapter();
        rvQuestions.setAdapter(adapter);
        LinearLayoutManager questionLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvQuestions.setLayoutManager(questionLayoutManager);
    }
}