package com.app.moviltprestamo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Pop extends Activity {

    EditText ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_activity);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Button btn = (Button)findViewById(R.id.btn_send);
        ip = (EditText) findViewById(R.id.editText1);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width * .8) , (int)(height * .6));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pop.this, MainActivity.class);
                //Bundle bundle = new Bundle();
                //bundle.putString("ip", ip.getText().toString());
                intent.putExtra("ip",ip.getText().toString());
                startActivity(intent);
            }
        });
    }
}
