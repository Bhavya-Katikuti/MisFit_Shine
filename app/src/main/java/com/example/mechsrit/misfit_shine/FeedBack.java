package com.example.mechsrit.misfit_shine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.firebase.client.Firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedBack extends Fragment {
    public static final String server_url = "https://misfitshine-d4637.firebaseio.com/";
    DatabaseReference databaseReference;
    public static final String Database_path = "user_feedback";
    Firebase firebase;
    EditText feedbacktext;
    RatingBar ratingBar;
   Button submitfeedback;
    public FeedBack() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_feed_back, container, false);
        feedbacktext=view.findViewById(R.id.feedback_edit);
        ratingBar=view.findViewById(R.id.ratingbar);
        submitfeedback=view.findViewById(R.id.submitfeedback);

        Firebase.setAndroidContext(getActivity());
        firebase = new Firebase(server_url);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_path);
        submitfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback = feedbacktext.getText().toString();
                String rate= String.valueOf(ratingBar.getRating());
        String feedbackRecord = databaseReference.push().getKey();
        databaseReference.child(feedbackRecord).setValue(feedback);
        Toast.makeText(getActivity(), R.string.feedbackToast, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), feedback+"\n"+rate, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
