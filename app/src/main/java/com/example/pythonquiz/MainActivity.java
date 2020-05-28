package com.example.pythonquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean q1IsCorrect = true;
    private boolean q2IsCorrect = true;
    private boolean q3IsCorrect = true;
    private boolean q4IsCorrect = true;
    private int score = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit(View view){
        //get score
        calcScore();
        // get summary
        String summary = getSummary();
        // make a toast
        Toast toast = Toast.makeText(this, "Total score is: "+score, Toast.LENGTH_LONG);
        toast.show();
        // send the score w/ email
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:test123@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name+" Score");
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    private void calcScore(){
        TextView ans1 = findViewById(R.id.ans1);
        if(ans1.getText().toString().toLowerCase()!="random.shuffle()"){
            q1IsCorrect=false;
            score-=25;
        }

        RadioButton ans2 = findViewById(R.id.ans2);
        if(ans2.isChecked()!=true){
            q2IsCorrect=false;
            score-=25;
        }

        RadioButton ans3 = findViewById(R.id.ans3);
        if(ans3.isChecked()!=true){
            q3IsCorrect=false;
            score-=25;
        }

        CheckBox cb1 = findViewById(R.id.str1);
        if(!cb1.isChecked()){
            q4IsCorrect=false;
            score-=5;
        }
        CheckBox cb2 = findViewById(R.id.str2);
        if(!cb2.isChecked()){
            q4IsCorrect=false;
            score-=5;
        }
        CheckBox cb3 = findViewById(R.id.str3);
        if(!cb3.isChecked()){
            q4IsCorrect=false;
            score-=5;
        }
        CheckBox cb4 = findViewById(R.id.str4);
        if(!cb4.isChecked()){
            q4IsCorrect=false;
            score-=5;
        }
        // if cb5 is checked, it is not considered as correct answer
        CheckBox cb5 = findViewById(R.id.str5);
        if(cb5.isChecked()){
            q4IsCorrect=false;
            score-=5;
        }
    }

    private String getSummary(){
        String summary="Total score is: "+score;
        summary+="\n1st question is correct: "+q1IsCorrect;
        summary+="\n2nd question is correct: "+q2IsCorrect;
        summary+="\n3rd question is correct: "+q3IsCorrect;
        summary+="\n4th question is correct: "+q4IsCorrect;
        return summary;
    }
}
