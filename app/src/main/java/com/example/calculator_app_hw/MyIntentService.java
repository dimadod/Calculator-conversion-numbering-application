package com.example.calculator_app_hw;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        String action = intent.getAction();
        String getUserValue;
        int decimal;
        String binary, octal, hexadecimal;

        if (action != null) {
            getUserValue = intent.getStringExtra(MainActivity.VALUE);
            switch (action) {
                case MainActivity.DO_DECIMAL_SERVICE_REQ:
                    decimal = Integer.parseInt(getUserValue, 10);
                    binary = Integer.toBinaryString(decimal);
                    octal = Integer.toOctalString(decimal);
                    hexadecimal = Integer.toHexString(decimal);
                    sendResultBroadcast(decimal, binary, octal, hexadecimal,MainActivity.RESULT_BACK_DECIMAL);
                    break;
                case MainActivity.DO_OCTAL_SERVICE_REQ:
                    int oct = Integer.parseInt(getUserValue, 8);
                    decimal = Integer.parseInt(getUserValue, 8);
                    binary = Integer.toBinaryString(oct);
                    octal = getUserValue;
                    hexadecimal = Integer.toHexString(oct);
                    sendResultBroadcast(decimal, binary, octal, hexadecimal,MainActivity.RESULT_BACK_OCTAL);
                    break;
                case MainActivity.DO_HEXA_SERVICE_REQ:
                    int hex = Integer.parseInt(getUserValue, 16);
                    decimal = Integer.parseInt(getUserValue, 16);
                    binary = Integer.toBinaryString(hex);
                    octal = Integer.toOctalString(hex);
                    hexadecimal = getUserValue;
                    sendResultBroadcast(decimal, binary, octal, hexadecimal,MainActivity.RESULT_BACK_HEXA);
                    break;
                case MainActivity.DO_BIN_SERVICE_REQ:
                    int bin = Integer.parseInt(getUserValue, 2);
                    decimal = Integer.parseInt(getUserValue, 2);
                    binary = getUserValue;
                    octal = Integer.toOctalString(bin);
                    hexadecimal = Integer.toHexString(bin);
                    sendResultBroadcast(decimal, binary, octal, hexadecimal,MainActivity.RESULT_BACK_BIN);
                    break;
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void sendResultBroadcast(int decimal, String binary, String octal, String hexadecimal,String action) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("dec", decimal);
        resultIntent.putExtra("bin", binary);
        resultIntent.putExtra("oct", octal);
        resultIntent.putExtra("hex", hexadecimal);

        resultIntent.setAction(action);

        sendBroadcast(resultIntent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {

    }

}