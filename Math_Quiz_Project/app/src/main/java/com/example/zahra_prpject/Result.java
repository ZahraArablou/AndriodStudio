package com.example.zahra_prpject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.zahra_prpject.model.Math;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Result extends AppCompatActivity implements View.OnClickListener {

    RadioGroup radioGroupRate;
    ListView listViewResultShow;
    EditText editTextRegister;
    TextView textViewScore;
    Button btnShow, btnBack;
    RadioButton radioAll,radioRight,radioWrong,radioSortA,radioSortD;

    ArrayList<Math> listofMathQuiz;
    ArrayList<Math> rightAnswerlist;
    ArrayList<Math> wrongAnswerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initialize();
    }

    private void initialize() {
        rightAnswerlist = new ArrayList<>();
        wrongAnswerList = new ArrayList<>();
        radioGroupRate = findViewById(R.id.radioGroupRate);

        listViewResultShow = findViewById(R.id.listViewResultShow);

        editTextRegister = findViewById(R.id.register);
        textViewScore = findViewById(R.id.score);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(this);

        radioAll=findViewById(R.id.radioButtonAll);
        radioRight=findViewById(R.id.radioButtonRight);
        radioWrong=findViewById(R.id.radioButtonWrong);
        radioSortA=findViewById(R.id.radioButtonSortA);
        radioSortD=findViewById(R.id.radioButtonSortD);

        //-----------------------------------
        Bundle bundle = getIntent().getBundleExtra("intent");
        Serializable bundledListOfMathQuiz = bundle.getSerializable("bundle");

        listofMathQuiz = (ArrayList<Math>) bundledListOfMathQuiz;

        Intent intent = getIntent();
        int percentage = intent.getIntExtra("percentage", 0);
        textViewScore.setText(percentage + "%");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnBack:
                gotoMainPage();
                break;
            case R.id.btnShow:
                show();
                break;
        }
    }

    private void gotoMainPage() {

        String Name = editTextRegister.getText().toString();
        String Score = textViewScore.getText().toString();

        String message = Name + " your score is " + Score;

        Intent intent = new Intent();
        intent.putExtra("message", message);

        setResult(RESULT_OK, intent);
        finish();
    }

    private void show() {
        showList(listofMathQuiz);
        radioAll.setChecked(true);
        radioRight.setChecked(false);
        radioWrong.setChecked(false);
        radioSortA.setChecked(false);
        radioSortD.setChecked(false);
    }

    private void showList(ArrayList<Math> listofMathQuiz) {

        ListView Result = findViewById(R.id.listViewResultShow);
        ArrayAdapter<Math> AdaptorList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listofMathQuiz);
        Result.setAdapter(AdaptorList);
    }

    public void runMe(View v) {
        int radioGroup = radioGroupRate.getCheckedRadioButtonId();

        switch (radioGroup){
            case R.id.radioButtonAll:
                showList(listofMathQuiz);
                break;
            case R.id.radioButtonRight:
                RightanswerList(listofMathQuiz);
                break;
            case R.id.radioButtonWrong:
                WronganswerList(listofMathQuiz);
                break;
            case R.id.radioButtonSortA:
                ascendingSort();
                break;
            case R.id.radioButtonSortD:
                  descendingSort();
                break;
        }
    }

    private void RightanswerList(ArrayList<Math> listofMathQuiz) {

        Iterator<Math> iterator = listofMathQuiz.iterator();
        rightAnswerlist.clear();
        while (iterator.hasNext()) {
            Math operation = iterator.next();
            if (operation.getValidation().equals("RIGHT")) {
                rightAnswerlist.add(operation);
            }
        }
        ListView ResultList = findViewById(R.id.listViewResultShow);
        ArrayAdapter<Math> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rightAnswerlist);
        ResultList.setAdapter(listAdapter);
    }

    private void WronganswerList(ArrayList<Math> listofMathQuiz) {

        Iterator<Math> iterator = listofMathQuiz.iterator();
        wrongAnswerList.clear();
        while (iterator.hasNext()) {

            Math operation = iterator.next();

            if (operation.getValidation().equals("WRONG")) {

                wrongAnswerList.add(operation);
            }
        }
        ListView ResultList = findViewById(R.id.listViewResultShow);
        ArrayAdapter<Math> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, wrongAnswerList);
        ResultList.setAdapter(listAdapter);
    }

    private void ascendingSort() {

        Collections.sort(listofMathQuiz);
        ArrayAdapter<Math> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listofMathQuiz);
        listViewResultShow.setAdapter(listAdapter);
    }

    private void descendingSort() {

        Collections.sort(listofMathQuiz, Collections.reverseOrder());
        ArrayAdapter<Math> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listofMathQuiz);
        listViewResultShow.setAdapter(listAdapter);

    }
}