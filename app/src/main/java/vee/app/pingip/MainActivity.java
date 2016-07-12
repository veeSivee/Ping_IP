package vee.app.pingip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText etIP;
    private Button btnPing;
    private TextView tvRespon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inisialisasi();
    }

    private void inisialisasi(){
        etIP = (EditText)findViewById(R.id.editText);
        btnPing = (Button)findViewById(R.id.button);
        tvRespon = (TextView)findViewById(R.id.textView);

        btnPing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tvRespon.setText(ping(etIP.getText().toString()));
                ping((etIP.getText().toString()));
            }
        });
    }

    private void tulisRespon(String tulis){
        tvRespon.setText(tulis);
    }

    public String ping(String url) {
        String str = "";
        try {
            Process process = Runtime.getRuntime().exec(
                    "/system/bin/ping -c 8 " + url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            int i;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((i = reader.read(buffer)) > 0){
                output.append(buffer, 0, i);
                tulisRespon(output.toString());
            }
            reader.close();

            // body.append(output.toString()+"\n");
            str = output.toString();
            // Log.d(TAG, str);
        } catch (IOException e) {
            // body.append("Error\n");
            //e.printStackTrace();
        }
        return str;
    }
}
