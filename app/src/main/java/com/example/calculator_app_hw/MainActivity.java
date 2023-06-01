package com.example.calculator_app_hw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    private TextView binary_label,octal_label,decimal_label,hexadecimal_label;
    private EditText number_field;
    private RadioButton binary,octal,decimal,hexadecimal;
    private RadioGroup radioGroup;
    private Button clear_button;

    public static final String DO_DECIMAL_SERVICE_REQ = "THIS_IS_DECIMAL";
    public static final String RESULT_BACK_DECIMAL = "DECIMAL_RESULT";
    public static final String DO_OCTAL_SERVICE_REQ = "THIS_IS_OCTAL";
    public static final String RESULT_BACK_OCTAL = "OCTAL_RESULT";
    public static final String DO_HEXA_SERVICE_REQ = "THIS_IS_HEXADECIMAL";
    public static final String RESULT_BACK_HEXA = "HEXADECIMAL_RESULT";
    public static final String DO_BIN_SERVICE_REQ = "THIS_IS_BINARY";
    public static final String RESULT_BACK_BIN = "BINARY_RESULT";
    public static final String VALUE = "NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binary_label=findViewById(R.id.binary_label);
        octal_label=findViewById(R.id.octal_label);
        decimal_label=findViewById(R.id.decimal_label);
        hexadecimal_label=findViewById(R.id.hexadecimal_label);
        number_field=findViewById(R.id.numberfield);
        binary=findViewById(R.id.binary_radio);
        octal=findViewById(R.id.octal_radio);
        decimal=findViewById(R.id.decimal_radio);
        hexadecimal=findViewById(R.id.hexadecimal_radio);
        clear_button=findViewById(R.id.clear_button);
        radioGroup=findViewById(R.id.radioGroup);


        BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int decimalNumber;
                String octalNumber,hexadecimalNumber,binaryNumber;

                if(intent.getAction().equals(RESULT_BACK_DECIMAL)){
                    decimalNumber=intent.getIntExtra("dec",99);
                    octalNumber=intent.getStringExtra("oct");
                    hexadecimalNumber=intent.getStringExtra("hex");
                    binaryNumber=intent.getStringExtra("bin");

                    updateLabels(decimalNumber, octalNumber, hexadecimalNumber, binaryNumber);

                }
                else if(intent.getAction().equals(RESULT_BACK_OCTAL)){
                    decimalNumber=intent.getIntExtra("dec",99);
                    octalNumber=intent.getStringExtra("oct");
                    hexadecimalNumber=intent.getStringExtra("hex");
                    binaryNumber=intent.getStringExtra("bin");

                    updateLabels(decimalNumber, octalNumber, hexadecimalNumber, binaryNumber);


                }
                else if(intent.getAction().equals(RESULT_BACK_HEXA)){
                    decimalNumber=intent.getIntExtra("dec",99);
                    octalNumber=intent.getStringExtra("oct");
                    hexadecimalNumber=intent.getStringExtra("hex");
                    binaryNumber=intent.getStringExtra("bin");

                    updateLabels(decimalNumber, octalNumber, hexadecimalNumber, binaryNumber);


                }
                else if(intent.getAction().equals(RESULT_BACK_BIN)){
                    decimalNumber=intent.getIntExtra("dec",99);
                    octalNumber=intent.getStringExtra("oct");
                    hexadecimalNumber=intent.getStringExtra("hex");
                    binaryNumber=intent.getStringExtra("bin");

                    updateLabels(decimalNumber, octalNumber, hexadecimalNumber, binaryNumber);

                }

            }
        };

        IntentFilter filter=new IntentFilter();
        filter.addAction(RESULT_BACK_DECIMAL);
        filter.addAction(RESULT_BACK_OCTAL);
        filter.addAction(RESULT_BACK_HEXA);
        filter.addAction(RESULT_BACK_BIN);
        registerReceiver(broadcastReceiver,filter);


        number_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String inputText = number_field.getText().toString();
                if (inputText.isEmpty()) {
                    clear();
                } else {
                    String numericPattern;
                       if (decimal.isChecked()) {
                        numericPattern = "^[0-9]*$";
                        if (!inputText.matches(numericPattern)) {
                            String filteredText = inputText.replaceAll("[^0-9]", "");
                            number_field.setText(filteredText);
                            Snackbar.make(number_field, "Please enter a valid number", Snackbar.LENGTH_LONG).show();
                            return;
                        }else {
                            performConversion(MainActivity.DO_DECIMAL_SERVICE_REQ,inputText);
                        }
                    }
                    else if (octal.isChecked()) {
                        numericPattern = "^[0-7]*$";
                        if (!inputText.matches(numericPattern)) {
                            String filteredText = inputText.replaceAll("[^0-7]", "");
                            number_field.setText(filteredText);
                            Snackbar.make(number_field, "Please enter a valid number", Snackbar.LENGTH_LONG).show();
                            return;
                        }else {
                            performConversion(MainActivity.DO_OCTAL_SERVICE_REQ,inputText);
                        }
                    }
                    else if (hexadecimal.isChecked()) {
                        numericPattern = "^[0-9a-fA-F]*$";
                        if (!inputText.matches(numericPattern)) {
                            String filteredText = inputText.replaceAll("[^0-9a-fA-F]", "");
                            number_field.setText(filteredText);
                            Snackbar.make(number_field, "Please enter a valid number", Snackbar.LENGTH_LONG).show();
                            return;
                        }else {
                            performConversion(MainActivity.DO_HEXA_SERVICE_REQ,inputText);
                        }
                    }
                    else if (binary.isChecked()) {
                        numericPattern = "^[01]*$";
                        if (!inputText.matches(numericPattern)) {
                            String filteredText = inputText.replaceAll("[^01]", "");
                            number_field.setText(filteredText);
                            Snackbar.make(number_field, "Please enter a valid number", Snackbar.LENGTH_LONG).show();
                            return;
                        } else {
                            performConversion(MainActivity.DO_BIN_SERVICE_REQ,inputText);
                        }
                    }
                }
            }
        });

        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
                number_field.setText("");
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Clear the contents of number_field when a different radio button is selected
                clear();
                number_field.setText("");
            }
        });

    }

    private void updateLabels(int decimal, String octal, String hexadecimal, String binary) {
        decimal_label.setText("Decimal: " + decimal);
        binary_label.setText("Binary: " + binary);
        octal_label.setText("Octal: " + octal);
        hexadecimal_label.setText("HexaDecimal: " + hexadecimal);
    }

    public void clear(){
        decimal_label.setText("Decimal:" );
        binary_label.setText("Binary:");
        octal_label.setText("Octal:" );
        hexadecimal_label.setText("HexaDecimal:");
        Snackbar.make(number_field, "Please Enter a Number", Snackbar.LENGTH_LONG).show();
    }

    private void performConversion(String action, String value) {
        Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
        intent.setAction(action);
        intent.putExtra(VALUE, value);
        startService(intent);
    }
}