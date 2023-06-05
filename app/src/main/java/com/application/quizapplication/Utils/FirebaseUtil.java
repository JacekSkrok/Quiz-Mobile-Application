package com.application.quizapplication.Utils;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.application.quizapplication.classes.QuizQuestion;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirebaseUtil {
    public static FirebaseDatabase aFirebaseDatabase;
    public static DatabaseReference aDatabaseReference;
    private static FirebaseUtil aFirebaseUtil;
    public static FirebaseAuth aFirebaseAuth;
    public static FirebaseAuth.AuthStateListener aAuthListener;
    public static ArrayList<QuizQuestion> aQuestions;

    private FirebaseUtil() {};

    public static void opnFbReference(String ref) {
        if(aFirebaseUtil == null) {
            aFirebaseUtil = new FirebaseUtil();
            aFirebaseDatabase = FirebaseDatabase.getInstance();
            aFirebaseAuth = FirebaseAuth.getInstance();
            aAuthListener = new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                    // Choose authentication providers
                    List<AuthUI.IdpConfig> providers = Arrays.asList(
                            new AuthUI.IdpConfig.EmailBuilder().build(),
                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                            new AuthUI.IdpConfig.FacebookBuilder().build());

                    // Create and launch sign-in intent
                    /*Intent signInIntent = AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build();
                    //signInLauncher.launch(signInIntent);

*/
                }
            };



        }
        aQuestions = new ArrayList<QuizQuestion>();
        aDatabaseReference = aFirebaseDatabase.getReference().child(ref);
    }



    public static void attachListener() {
        aFirebaseAuth.addAuthStateListener(aAuthListener);
    }

    public static void detachListener() {
        aFirebaseAuth.removeAuthStateListener(aAuthListener);
    }


}
