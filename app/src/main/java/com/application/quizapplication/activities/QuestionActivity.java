package com.application.quizapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.application.quizapplication.R;
import com.application.quizapplication.classes.QuizQuestion;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.application.quizapplication.Utils.FirebaseUtil;

public class QuestionActivity extends AppCompatActivity {
    private FirebaseDatabase aFirebaseDatabase;
    private DatabaseReference aDatabaseReference;

    EditText txtQuestionText;
    EditText txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD;
    QuizQuestion question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_question);
        FirebaseUtil.opnFbReference("quizquestions");
        aFirebaseDatabase = FirebaseUtil.aFirebaseDatabase;
        aDatabaseReference = FirebaseUtil.aDatabaseReference;
        txtQuestionText = (EditText) findViewById(R.id.questionName);
        txtAnswerA = findViewById(R.id.answerA);
        txtAnswerB = findViewById(R.id.answerB);
        txtAnswerC = findViewById(R.id.answerC);
        txtAnswerD = findViewById(R.id.answerD);

        Intent intent = getIntent();
        QuizQuestion question = (QuizQuestion) intent.getSerializableExtra("QuizQuestion");
        if (question == null)
        {
            question = new QuizQuestion();
        }
        this.question = question;
        txtQuestionText.setText(question.getQuestionText());
        txtAnswerA.setText(question.getAnswerA());
        txtAnswerB.setText(question.getAnswerB());
        txtAnswerC.setText(question.getAnswerC());
        txtAnswerD.setText(question.getAnswerD());

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_menu:
                addQuestion();
                Toast.makeText(this, "Question added", Toast.LENGTH_LONG).show();
                backToList();
                clean();
                return true;
            case R.id.delete_menu:
                deleteQuestion();
                Toast.makeText(this, "Question deleted", Toast.LENGTH_LONG).show();
                backToList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void clean() {
        txtQuestionText.setText("");
        txtAnswerA.setText("");
        txtAnswerB.setText("");
        txtAnswerC.setText("");
        txtAnswerD.setText("");
        txtQuestionText.requestFocus();
    }

    private void addQuestion() {
        question.setQuestionText(txtQuestionText.getText().toString());
        question.setAnswerA(txtAnswerA.getText().toString());
        question.setAnswerB(txtAnswerB.getText().toString());
        question.setAnswerC(txtAnswerC.getText().toString());
        question.setAnswerD(txtAnswerD.getText().toString());

        if(question.getId() == null) {
            aDatabaseReference.push().setValue(question);
        }
        else {
            aDatabaseReference.child(question.getId()).setValue(question);
        }
    }

    private void deleteQuestion() {
        if(question.getId() == null) {
            Toast.makeText(this, "Question doesn't exists", Toast.LENGTH_SHORT).show();
        }
        aDatabaseReference.child(question.getId()).removeValue();
    }

    private void backToList() {
        Intent intent = new Intent(this, ListQuestionsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }
}