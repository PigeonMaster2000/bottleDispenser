package com.example.bottledispensergame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.TextView;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static java.lang.Math.round;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Context context = null, contextFile;
    int choice, seekBarValue = 0;
    double currentSize = 0.5;
    TextView creditAmountTemp, moneyAmountText, console;
    BottleDispenser machine = BottleDispenser.getInstance();
    Spinner spinnerSizes;
    String[] bottleSizes = { "1.5", "0.5"};
    String fileName = "kuitit.txt";
    public SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contextFile = MainActivity.this;


        creditAmountTemp = findViewById(R.id.tempCreditValue);
        moneyAmountText = findViewById(R.id.moneyAmountView);
        console = findViewById(R.id.consoleView);

        //SeekBar
        seekBar =(SeekBar)findViewById(R.id.seekBar);

        spinnerSizes = (Spinner)findViewById(R.id.spinner_sizes);
        spinnerSizes.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bottleSizes);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSizes.setAdapter(aa);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                seekBarValue = round(progressChangedValue/10);
                creditAmountTemp.setText(Integer.toString(seekBarValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
    });
    }

    public void depositMoney(View v){
        machine.addMoney(seekBarValue);
        seekBar.setProgress(0);
        creditAmountTemp.setText("0");
        seekBarValue = 0;
        moneyAmountText.setText(Double.toString(machine.getMoney()));
        console.setText("Klink! Added more money!");
    }

    public void buyPepsiMax (View v){
        choice = 1;
        console.setText(machine.buyBottle(choice, currentSize));
        moneyAmountText.setText(Double.toString(machine.getMoney()));
    }

    public void buyCocaColaZero (View v){
        choice = 2;
        console.setText(machine.buyBottle(choice, currentSize));
        moneyAmountText.setText(Double.toString(machine.getMoney()));
    }

    public void BuyCocaCola (View v){
        choice = 3;
        console.setText(machine.buyBottle(choice, currentSize));
        moneyAmountText.setText(Double.toString(machine.getMoney()));
    }

    public void buyFantaZero (View v){
        choice = 4;
        console.setText(machine.buyBottle(choice, currentSize));
        moneyAmountText.setText(Double.toString(machine.getMoney()));
    }

    public void returnMoney(View v){
        console.setText(machine.returnMoney());
        moneyAmountText.setText("0");
    }

    public void printReceipt(View v){
        String receiptString;
        receiptString = ("************RECEIPT************ \n\nBottle: "+machine.getLastBottleName()+" \nPrice: "+Double.toString(machine.getLastBottlePrice())+" \n\n***********THANK YOU***********\n\n\n");
        System.out.println(receiptString);
        try {
            OutputStreamWriter ows = new OutputStreamWriter(contextFile.openFileOutput(fileName, Context.MODE_PRIVATE));
            ows.write(receiptString);
            ows.close();
        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            console.setText("Your receipt is in a '"+fileName+"' file");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (id == 0){
            currentSize = 1.5;
        } else if (id == 1){
            currentSize = 0.5;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}