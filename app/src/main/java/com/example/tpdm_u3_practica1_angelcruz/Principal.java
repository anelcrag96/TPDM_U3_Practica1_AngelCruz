package com.example.tpdm_u3_practica1_angelcruz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Principal extends AppCompatActivity {
    Button alumnos, fechas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        alumnos=findViewById(R.id.btnAlumnos);
        fechas=findViewById(R.id.btnFechaAplicacion);

        alumnos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Principal.this, ListadoAlumnos.class);
                startActivity(i);
                finish();
            }//onClick
        });

        fechas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Principal.this, ListadoAplicaciones.class);
                startActivity(i);
                finish();
            }//onClick
        });
    }//onCreate
}//class
