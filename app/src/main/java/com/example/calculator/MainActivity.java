package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.buttonCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et1 = findViewById(R.id.editText1);
                EditText et2 = findViewById(R.id.editText2);
                String num1 = et1.getText().toString();
                String num2 = et2.getText().toString();
                if (!num1.isEmpty() && !num2.isEmpty()) {
                    String result = calculateAndStringify(Double.valueOf(num1), Double.valueOf(num2));
                    TextView resultView = findViewById(R.id.textViewResult);
                    resultView.setText(result);
                    saveToFile(result);
                }
            }
        });
    }

    String calculateAndStringify(Double n1, Double n2) {
        double addition = n1 + n2;
        double subtraction = n1 - n2;
        double multiplication = n1 * n2;
        double division = n1 / n2;
        return "Result:\n Addition: " + addition + " \nSubtraction: " + subtraction + " \nMultiplication: " + multiplication + " \nDivision: " + division;
    }
    
    void saveToFile (String sBody){
        FileOutputStream resultFileStream = null ;
        try {

            resultFileStream = openFileOutput( System.currentTimeMillis()+".txt", Context.MODE_APPEND);

           resultFileStream.write(sBody.getBytes());
            Toast.makeText(this , "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (resultFileStream != null){
                try {
                    resultFileStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

