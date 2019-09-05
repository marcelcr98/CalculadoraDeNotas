package com.mcuellar.calculadoradenotas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    TextView prom;
    EditText et_pt1;
    EditText et_pt2;
    TextView proml;
    EditText et_pl1;
    EditText et_pl2;
    EditText et_pl3;
    EditText et_pl4;
    TextView promedio;
    TextView resultado;
    Button calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] arraySpinner = new String[] {
                "A: 30% T + 70% L",
                "B: 20% T + 80% L",
                "C: 10% T + 90% L"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);

        calcular = findViewById(R.id.resultado2);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_pt1 = findViewById(R.id.prom1a);
                et_pt2 = findViewById(R.id.prom1b);
                et_pl1 = findViewById(R.id.prom1c);
                et_pl2 = findViewById(R.id.prom1d);
                et_pl3 = findViewById(R.id.prom1e);
                et_pl4 = findViewById(R.id.prom1f);

                if(et_pt1.getText().length() == 0 || et_pt2.getText().length() == 0 || et_pl1.getText().length() == 0 || et_pl2.getText().length() == 0 || et_pl3.getText().length() == 0 || et_pl4.getText().length() == 0){
                    Toast.makeText(MainActivity.this, "Falta completar los campos!!", Toast.LENGTH_SHORT).show();
                }else{
                    calculate();
                }

            }
        });
    }

    public void calculate(){
        prom = findViewById(R.id.prom1);
        et_pt1 = findViewById(R.id.prom1a);
        et_pt2 = findViewById(R.id.prom1b);
        double notaT;
        double notaT1 = Double.parseDouble(et_pt1.getText().toString());
        double notaT2 = Double.parseDouble(et_pt2.getText().toString());

        if(notaT1 > notaT2){
            notaT = notaT1;
        }else if(notaT2 > notaT1){
            notaT = notaT2;
        }else{
            notaT = (notaT1+notaT2)/2;
        }

        prom.setText("Prom: " + String.valueOf(notaT));

        proml = findViewById(R.id.prom2);
        et_pl1 = findViewById(R.id.prom1c);
        et_pl2 = findViewById(R.id.prom1d);
        et_pl3 = findViewById(R.id.prom1e);
        et_pl4 = findViewById(R.id.prom1f);
        double notaL;
        double notaL1 = Double.parseDouble(et_pl1.getText().toString());
        double notaL2 = Double.parseDouble(et_pl2.getText().toString());
        double notaL3 = Double.parseDouble(et_pl3.getText().toString());
        double notaL4 = Double.parseDouble(et_pl4.getText().toString());

        notaL = (notaL1 + notaL2 + notaL3 + notaL4)/4;

        proml.setText("Prom: " + String.valueOf(notaL));

        String p = "";
        int pos = spinner.getSelectedItemPosition();

        if(pos == 0){
            p = String.valueOf(promedio(notaT, notaL, 30.0, 70.0));
        }else if(pos == 1){
            p = String.valueOf(promedio(notaT, notaL, 20.0, 80.0));
        }else if(pos == 2){
            p = String.valueOf(promedio(notaT, notaL, 10.0, 90.0));
        }
        promedio = findViewById(R.id.promedio);
        resultado = findViewById(R.id.resultado);
        promedio.setText("Promedio: " + p);

        if(Double.parseDouble(p) >= 13){
            resultado.setText("Aprobado");
        }else{
            resultado.setText("Desaprobado");
        }

    }

    public Double promedio(Double nt, Double nl, Double t, Double l){
        return (((t/100) * nt) + ((l/100) * nl));
    }
}
