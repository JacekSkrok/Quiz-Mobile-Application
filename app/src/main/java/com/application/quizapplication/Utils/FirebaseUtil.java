package com.application.quizapplication.Utils;

import com.application.quizapplication.classes.QuizQuestion;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase aFirebaseDatabase;
    public static DatabaseReference aDatabaseReference;
    private static FirebaseUtil aFirebaseUtil;
    public static ArrayList<QuizQuestion> aQuestions;

    private FirebaseUtil() {};

    public static void opnFbReference(String ref) {
        if(aFirebaseUtil == null) {
            aFirebaseUtil = new FirebaseUtil();
            aFirebaseDatabase = FirebaseDatabase.getInstance();
            aQuestions = new ArrayList<QuizQuestion>();
        }
        aDatabaseReference = aFirebaseDatabase.getReference().child(ref);
    }


}
