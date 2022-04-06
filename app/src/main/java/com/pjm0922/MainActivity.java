package com.pjm0922;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar seek_kg;
    ImageButton btn_plus, btn_minus;
    Button btn_bmi;
    TextView str_cm, str_kg, str_bmi, str_result;

    float data_cm = 0.0f;
    float data_kg = 0.0f;
    float result_bmi = 0.0f;
    int cmcm = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seek_kg = findViewById(R.id.seek_kg);
        btn_plus = findViewById(R.id.plus_btn);
        btn_minus = findViewById(R.id.minus_btn);
        btn_bmi = findViewById(R.id.bmi_btn);
        str_cm = findViewById(R.id.cm_str);
        str_kg = findViewById(R.id.kg_str);
        str_bmi = findViewById(R.id.bmi_str);
        str_result = findViewById(R.id.result_str);

        setTitle("BMI");

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cmcm >= 210) {
                    cmcm = 210;
                    Toast.makeText(getBaseContext(), "더이상 증가할 수 없음", Toast.LENGTH_SHORT).show();
                }
                else { cmcm ++;
                    str_cm.setText("신장(키) : " + cmcm + " cm"); }
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cmcm <= 120) {
                    cmcm = 120;
                    Toast.makeText(getBaseContext(), "더이상 감소할 수 없음", Toast.LENGTH_SHORT).show();
                }
                else { cmcm --;
                    str_cm.setText("신장(키) : " + cmcm + " cm"); }
            }
        });

        seek_kg.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int kgkg, boolean fromUser) {
                data_kg = (kgkg * 0.5f / 0.5f);
                str_kg.setText(String.format("몸무게: %.0f kg", data_kg));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btn_bmi.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                data_cm = (cmcm * 0.5f / 0.5f);
                bmi_calculator(data_cm / 100, data_kg);
            }
        });
    }

    private void bmi_calculator(float cm, float kg) {
        result_bmi = kg / (cm * cm);
        str_bmi.setText(String.format("BMI : %.2f", result_bmi));

        if (result_bmi <= 18.5f) {
            str_result.setText("결과 : 저체중");
        } else if (result_bmi > 18.5f && result_bmi < 23.0f) {
            str_result.setText("결과 : 정상체중");
        } else if (result_bmi >= 23.0f && result_bmi < 25.0f) {
            str_result.setText("결과 : 과체중");
        } else if (result_bmi >= 25.0f) {
            str_result.setText("결과 : 비만");
        }
    }
}