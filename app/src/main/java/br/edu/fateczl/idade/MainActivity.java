/*
 *@author:<Camila Gagleote, 1110482312050>
 */
package br.edu.fateczl.idade;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText etAno;
    private EditText etMes;
    private EditText etDia;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etDia = findViewById(R.id.etDia);
        etDia.setText(TextView.TEXT_ALIGNMENT_CENTER);

        etMes = findViewById(R.id.etMes);
        etMes.setText(TextView.TEXT_ALIGNMENT_CENTER);

        etAno = findViewById(R.id.etAno);
        etAno.setText(TextView.TEXT_ALIGNMENT_CENTER);

        tvResultado = findViewById(R.id.tvResultado);
        tvResultado.setText(TextView.TEXT_ALIGNMENT_CENTER);

        Button btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(op -> calcular());

    }

    private void calcular(){
        int dia = Integer.parseInt(etDia.getText().toString());
        int mes = Integer.parseInt(etMes.getText().toString());
        int ano = Integer.parseInt(etAno.getText().toString());


        Calendar atualDate = Calendar.getInstance();
        int diaAtual = atualDate.get(Calendar.DAY_OF_MONTH);
        int mesAtual = atualDate.get(Calendar.MONTH) + 1;
        int anoAtual = atualDate.get(Calendar.YEAR);

        int idAnos = anoAtual - ano;
        int idMeses = mesAtual - mes;
        int idDias = diaAtual - dia;

        if (idDias < 0) {
            idMeses--;
            idDias += diasNoMes(mesAtual - 1, anoAtual);
        }

        if (idMeses < 0) {
            idAnos--;
            idMeses += 12;
        }

        String res = ("Você tem " + idAnos + " anos, " + idMeses + " meses e " + idDias + " dias.");
        tvResultado.setText(res);
    }

    int diasNoMes(int mes, int ano) {
        if (mes == 0) {
            mes = 12;
            ano--;
        }
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isBissexto(ano)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0;
        }
    }

    boolean isBissexto(int ano) {
        if (ano % 4 != 0) {
            return false;
        } else if (ano % 100 != 0) {
            return true;
        } else if (ano % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }




}