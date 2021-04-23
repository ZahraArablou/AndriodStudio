package com.example.zahra_prpject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import com.example.zahra_prpject.model.Math;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher  {
    EditText editTextAnswer;
    TextView textViewMathquiz,textViewOperation,textViewValidation;
    Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnDot,btn0,btnDash,btnGenerate,btnValidate,btnClear,btnScore,btnFinish;

    double rightResult;
    String operation = null;
    String validation;
    final static int REQUEST_CODE1 = 1;
    boolean flag=true;
    ArrayList<Math> listofMathQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {

        listofMathQuiz = new ArrayList<>();

        textViewMathquiz = findViewById(R.id.tvMathQuiz);
        editTextAnswer = findViewById(R.id.etAnswer);
        textViewOperation = findViewById(R.id.tvOperation);
        textViewValidation=findViewById(R.id.tvValidation);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);

        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);

        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);

        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);

        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);

        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);

        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(this);

        btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(this);

        btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(this);

        btnDot = findViewById(R.id.btnDot);
        btnDot.setOnClickListener(this);

        btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(this);

        btnDash = findViewById(R.id.btnDash);
        btnDash.setOnClickListener(this);

        btnGenerate = findViewById(R.id.btnGenerate);
        btnGenerate.setOnClickListener(this);

        btnValidate = findViewById(R.id.btnValidate);
        btnValidate.setOnClickListener(this);

        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        btnScore = findViewById(R.id.btnScore);
        btnScore.setOnClickListener(this);

        btnFinish = findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(this);

        editTextAnswer.addTextChangedListener(this);
        btnValidate.setEnabled(false);

    }

    @Override
    public void onClick(View v) {
        String answer;
        switch (v.getId()) {
              case R.id.btnDash:
                String str=editTextAnswer.getText().toString();
                int lenght=str.length();
                if (editTextAnswer.getText().toString().contains("-")||lenght>=1) {
                    Toast toastException=Toast.makeText(this,"Invalid Entry",Toast.LENGTH_SHORT);
                    toastException.setGravity(Gravity.TOP|Gravity.CENTER,0,400);
                    toastException.show();
                }else{
                    flag=false; //not to call afterTextChanged method
                    answer = "-";
                    editTextAnswer.setText(answer);
                    flag=true;
                }
                break;

            case R.id.btn1:
                answer = editTextAnswer.getText() + "1";
                editTextAnswer.setText(answer);
                break;
            case R.id.btn2:
                answer = editTextAnswer.getText() + "2";
                editTextAnswer.setText(answer);
                break;
            case R.id.btn3:
                answer = editTextAnswer.getText() + "3";
                editTextAnswer.setText(answer);
                break;
            case R.id.btn4:
                answer = editTextAnswer.getText() + "4";
                editTextAnswer.setText(answer);
                break;
            case R.id.btn5:
                answer = editTextAnswer.getText() + "5";
                editTextAnswer.setText(answer);
                break;
            case R.id.btn6:
                answer = editTextAnswer.getText() + "6";
                editTextAnswer.setText(answer);
                break;
            case R.id.btn7:
                answer = editTextAnswer.getText() + "7";
                editTextAnswer.setText(answer);
                break;
            case R.id.btn8:
                answer = editTextAnswer.getText() + "8";
                editTextAnswer.setText(answer);
                break;
            case R.id.btn9:
                answer = editTextAnswer.getText() + "9";
                editTextAnswer.setText(answer);
                break;
            case R.id.btn0:
                answer = editTextAnswer.getText() + "0";
                editTextAnswer.setText(answer);
                break;
            case R.id.btnDot:
                if (editTextAnswer.getText().toString().contains(".")) {
                    Toast toastException=Toast.makeText(this,"Invalid Entry",Toast.LENGTH_SHORT);
                    toastException.setGravity(Gravity.TOP|Gravity.CENTER,0,400);
                    toastException.show();

                } else{
                    flag=false;// not to call afterChangeText method
                    answer = "";
                    answer = editTextAnswer.getText() + ".";
                    editTextAnswer.setText(answer);
                    flag=true;
                }
                break;

            case R.id.btnGenerate:
                goGenerate();
                break;

            case R.id.btnValidate:
                goValidate();
                break;

            case R.id.btnClear:
                goClear();
                break;

            case R.id.btnScore:
                goResult();
                break;

            case R.id.btnFinish:
                showAlertDialog1(btnFinish);
                break;
        }
    }

    private void goGenerate() {
        flag=false; //not to call afterTextChanged method
        editTextAnswer.setText(null);
        textViewValidation.setText(null);
        flag=true;
        btnValidate.setEnabled(false);

        Random random = new Random();
        int operand1 = random.nextInt(10);
        int operand2 = random.nextInt(10);
        int operator = random.nextInt(4);

        while(operand2==0 && operator==3) { operand2 = random.nextInt(10);} //handle divide 0

        String operatorSwitch;

        switch (operator){

            case 0:
                operatorSwitch= "+";
                rightResult = operand1 + operand2;
                break;
            case 1:
                operatorSwitch= "-";
                rightResult = operand1 - operand2;
                break;
            case 2:
                operatorSwitch= "*";
                rightResult = operand1 * operand2;
                break;
            case 3:
                operatorSwitch= "/";
                rightResult = operand1 / operand2;
                break;
            default:
                operatorSwitch= "";
        }

        operation = String.valueOf(operand1)+" " + operatorSwitch +" "+ String.valueOf(operand2)+ " = ?" ;
        textViewOperation.setText(operation);
    }

    private void goValidate() {
        try{

            double UserAnswer = Double.valueOf(editTextAnswer.getText().toString());
            String strResult;
            if (UserAnswer == rightResult) {
                textViewValidation.setTextColor(Color.parseColor("#049F0A"));
                strResult = "Right Answer!";
                validation = "RIGHT";
             } else {
                textViewValidation.setTextColor(Color.parseColor("#FFBF0F0F"));
                strResult = "Wrong Answer!";
                validation = "WRONG";
              }
            textViewValidation.setText(strResult);

            Math math = new Math(operation,validation,UserAnswer);
            listofMathQuiz.add(math);

        }catch(Exception e){

            Toast.makeText(this, "Please Give Us an Answer! ", Toast.LENGTH_LONG).show();
        }
    }

    private void goClear() {
        flag=false;//not to call afterTextChanged method
        editTextAnswer.setText(null);
        textViewOperation.setText("0 + 0");
        textViewValidation.setText(null);
        btnValidate.setEnabled(false);
        flag=true;
    }

    private void goResult() {
        try{
            int totalScore = 0;
            int count = 0;
            int percentage;

            Iterator<Math> iterator = listofMathQuiz.iterator();

            while(iterator.hasNext()){
                Math math = iterator.next();
                if(math.getValidation().equals("RIGHT")) totalScore+=1;
                count++;
            }

            percentage = (totalScore*100)/count;

            Bundle bundle = new Bundle();
            bundle.putSerializable("bundle" , listofMathQuiz);

            Intent intent = new Intent( this, Result.class);
            intent.putExtra( "intent" , bundle);
            intent.putExtra( "percentage" , percentage);

            startActivityForResult(intent,REQUEST_CODE1);
        }catch(Exception e){
            Toast.makeText(this, "There is no score! ", Toast.LENGTH_LONG).show();
        }
    }

    public void showAlertDialog1(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // type 2
        builder.setTitle("Confirmation")
                .setMessage("Do you want to exit Math Quiz?")
                .setCancelable(true)
                .setIcon(android.R.drawable.ic_dialog_alert)
                // We just define event listener for yes button,
                // but it can be defined for 'No' and 'Cancel' as well
                .setPositiveButton("OK",(dialogInterface, i) -> finish())
                .setNeutralButton("Cancel", null);
        builder.show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {   }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {   }

    @Override
    public void afterTextChanged(Editable s) {
        if(!flag) return;
        try {
            Float userAnswer =Float.valueOf(editTextAnswer.getText().toString());
             if(textViewOperation.getText().toString().equals("0 + 0")){
                Toast toast=Toast.makeText(this,"Generate an operation first!",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER,0,400);
                toast.show();
            }
            if (userAnswer> 81) {
                Toast toastException=Toast.makeText(this,"Total should be less than 81",Toast.LENGTH_SHORT);
                toastException.setGravity(Gravity.TOP|Gravity.CENTER,0,400);
                toastException.show();
                btnValidate.setEnabled(false);
            } else {
                btnValidate.setEnabled(true);
            }
        }catch (Exception e){
            Toast toastException=Toast.makeText(this,"Enter number data type",Toast.LENGTH_SHORT);
            toastException.setGravity(Gravity.TOP|Gravity.CENTER,0,400);
            toastException.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        goClear();
        if(requestCode == REQUEST_CODE1){
            String receiveData =(String) data.getStringExtra("message");
            if(resultCode == RESULT_OK){
               textViewMathquiz.setText(receiveData);
            }
        }
    }
}
