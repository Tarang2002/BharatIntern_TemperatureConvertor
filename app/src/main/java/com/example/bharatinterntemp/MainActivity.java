package com.example.bharatinterntemp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
Spinner spinnerInput, spinnerOutput;
TextView outputTemp;
EditText inputTemp;
android.widget.Button Button;
ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputTemp = findViewById(R.id.outputTemp);
        inputTemp = findViewById(R.id.inputTemp);
        spinnerInput = findViewById(R.id.spinnerInput);
        spinnerOutput = findViewById(R.id.spinnerOutput);
        Button = findViewById(R.id.btn);

        arrayList.add("CELSIUS");
        arrayList.add("FAHRENHEIT");
        arrayList.add("KELVIN");
        arrayList.add("RANKINE");
        arrayList.add("REAUMUR");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinnerInput.setAdapter(arrayAdapter);
        spinnerOutput.setAdapter(arrayAdapter);


        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputTemp.getText().toString();
                String change = "";
                if (spinnerInput.getSelectedItemPosition()==0 && spinnerOutput.getSelectedItemPosition()==0){
                    change = input + "+" + "0";

                } else if (spinnerInput.getSelectedItemPosition()==0 && spinnerOutput.getSelectedItemPosition()==1) {
                    change = input + " x " + "1.8" + " + " + "32";

                } else if (spinnerInput.getSelectedItemPosition()==0 && spinnerOutput.getSelectedItemPosition()==2) {
                    change = input + " + " + "273.15";

                } else if (spinnerInput.getSelectedItemPosition()==0 && spinnerOutput.getSelectedItemPosition()==3) {
                    change = input + " x " + "1.8" + " + " + "491.67";

                } else if (spinnerInput.getSelectedItemPosition()==0 && spinnerOutput.getSelectedItemPosition()==4) {
                    change = input + " x " + "4" + " / " + "5";

                } else if (spinnerInput.getSelectedItemPosition()==1 && spinnerOutput.getSelectedItemPosition()==0) {
                    change = input + " x " + "0.5556" + " - " + "32" + " x " + "0.5556";

                } else if (spinnerInput.getSelectedItemPosition()==1 && spinnerOutput.getSelectedItemPosition()==1) {
                    change = input + "+" + "0";

                } else if (spinnerInput.getSelectedItemPosition()==1 && spinnerOutput.getSelectedItemPosition()==2) {
                    change = input + " x " + "0.5556" + " - " + "32" + " x " + "0.5556" + " + " + "273.15";

                } else if (spinnerInput.getSelectedItemPosition()==1 && spinnerOutput.getSelectedItemPosition()==3) {
                    change = input + " + " + "459.67";

                } else if (spinnerInput.getSelectedItemPosition()==1 && spinnerOutput.getSelectedItemPosition()==4) {
                    change = input + " x " + "0.444" + " - " + "32" + " x " + "0.444";

                } else if (spinnerInput.getSelectedItemPosition()==2 && spinnerOutput.getSelectedItemPosition()==0) {
                    change = input + " - " + "273.15";

                } else if (spinnerInput.getSelectedItemPosition()==2 && spinnerOutput.getSelectedItemPosition()==1) {
                    change = input + " x " + "1.8" + " - " + "459.67";

                } else if (spinnerInput.getSelectedItemPosition()==2 && spinnerOutput.getSelectedItemPosition()==2) {
                    change = input + "+" + "0";

                } else if (spinnerInput.getSelectedItemPosition()==2 && spinnerOutput.getSelectedItemPosition()==3) {
                    change = input + " x " + "1.8";

                } else if (spinnerInput.getSelectedItemPosition()==2 && spinnerOutput.getSelectedItemPosition()==4) {
                    change = input + " x " + "0.8" + " - " + "273.15" + " x " + "0.8";

                } else if (spinnerInput.getSelectedItemPosition()==3 && spinnerOutput.getSelectedItemPosition()==0) {
                    change = input + " x " + "0.556" + " - " + "491.67" + " x " + "0.556";

                } else if (spinnerInput.getSelectedItemPosition()==3 && spinnerOutput.getSelectedItemPosition()==1) {
                    change = input + " - " + "459.67";

                } else if (spinnerInput.getSelectedItemPosition()==3 && spinnerOutput.getSelectedItemPosition()==2) {
                    change = input + " x " + "0.556";

                } else if (spinnerInput.getSelectedItemPosition()==3 && spinnerOutput.getSelectedItemPosition()==3) {
                    change = input + "+" + "0";

                } else if (spinnerInput.getSelectedItemPosition()==3 && spinnerOutput.getSelectedItemPosition()==4) {
                    change = input + " / " + "2.25" + " - " + "32" + " / " + "2.25" + " - " + "459.67" + " / " + "2.25";

                } else if (spinnerInput.getSelectedItemPosition()==4 && spinnerOutput.getSelectedItemPosition()==0) {
                    change = input + " / " + "0.80";

                } else if (spinnerInput.getSelectedItemPosition()==4 && spinnerOutput.getSelectedItemPosition()==1) {
                    change = input + " x " + "2.25" + " + " + "32";

                } else if (spinnerInput.getSelectedItemPosition()==4 && spinnerOutput.getSelectedItemPosition()==2) {
                    change = input + " x " + "1.25" + " + " + "273.15";

                } else if (spinnerInput.getSelectedItemPosition()==4 && spinnerOutput.getSelectedItemPosition()==3) {
                    change = input + " x " + "2.25" + " + " + "32" + " + " + "459.67";

                } else if (spinnerInput.getSelectedItemPosition()==4 && spinnerOutput.getSelectedItemPosition()==4) {
                    change = input + "+" + "0";

                }

                change = change.replaceAll(" x ", "*");
                change = change.replaceAll(" / ", "/");
                change = change.replaceAll(" + ", "+");
                change = change.replaceAll(" - ", "-");

                Context rhino = Context.enter();
                rhino.setOptimizationLevel(-1);

                String finalResult = "";

                Scriptable scriptable = rhino.initSafeStandardObjects();
                finalResult = rhino.evaluateString(scriptable, change, "Javascript", 1, null).toString();

                double resultValue = Double.parseDouble(finalResult);

                int numberOfDecimalPlaces = 2; // Change this to the desired number of decimal places
                DecimalFormat decimalFormat = new DecimalFormat("#." + "0".repeat(numberOfDecimalPlaces));
                finalResult = decimalFormat.format(resultValue);

                outputTemp.setText(finalResult);
            }
        });

    }
}