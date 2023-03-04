package com.application.quizapplication.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.application.quizapplication.R;
import com.application.quizapplication.classes.QuizQuestion;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertQuestionActivity extends AppCompatActivity {
    private FirebaseDatabase aFirebaseDatabase;
    private DatabaseReference aDatabaseReference;

    EditText txtQuestiontext;
    EditText txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_question);

        aFirebaseDatabase = FirebaseDatabase.getInstance();
        aDatabaseReference = aFirebaseDatabase.getReference().child("quizquestions");
        txtQuestiontext = findViewById(R.id.questionName);
        txtAnswerA = findViewById(R.id.answerA);
        txtAnswerB = findViewById(R.id.answerB);
        txtAnswerC = findViewById(R.id.answerC);
        txtAnswerD = findViewById(R.id.answerD);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_menu:
                addQuestion();
                Toast.makeText(this, "Question added", Toast.LENGTH_LONG).show();
                clean();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void clean() {
        txtQuestiontext.setText("");
        txtAnswerA.setText("");
        txtAnswerB.setText("");
        txtAnswerC.setText("");
        txtAnswerD.setText("");
        txtQuestiontext.requestFocus();
    }

    private void addQuestion() {
        String questiontxt = txtQuestiontext.getText().toString();
        String answerA = txtAnswerA.getText().toString();
        String answerB = txtAnswerB.getText().toString();
        String answerC = txtAnswerC.getText().toString();
        String answerD = txtAnswerD.getText().toString();

        QuizQuestion question = new QuizQuestion(questiontxt, answerA, answerB, answerC, answerD, "");

        aDatabaseReference.push().setValue(question);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }
}