package com.example.tpdm_u3_practica1_angelcruz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DatosAplicaciones extends AppCompatActivity {
    EditText fechaaplicacion, aplicador, aula, horainicio, horafin;
    Button modificar, eliminar, cancelar;
    DatabaseReference realtime;
    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_aplicaciones);

        fechaaplicacion=findViewById(R.id.txtFechaAplicacion);
        aplicador=findViewById(R.id.txtNombreAplicador);
        aula=findViewById(R.id.txtAula);
        horainicio=findViewById(R.id.txtHoraInicioAplicacion);
        horafin=findViewById(R.id.txtHoraFinAplicacion);

        modificar=findViewById(R.id.btnModificarAplicacion);
        eliminar=findViewById(R.id.btnEliminarAplicacion);
        cancelar=findViewById(R.id.btnCancelarAplicacion);

        realtime= FirebaseDatabase.getInstance().getReference();
        datos=getIntent().getExtras();

        fechaaplicacion.setText(datos.get("FechaAplicacion").toString());
        aplicador.setText(datos.get("Aplicador").toString());
        aula.setText(datos.get("Aula").toString());
        horainicio.setText(datos.get("HoraInicio").toString());
        horafin.setText(datos.get("HoraFin").toString());

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> data=new HashMap<>();
                data.put("FechaAplicacion",fechaaplicacion.getText().toString());
                data.put("Aplicador",aplicador.getText().toString());
                data.put("Aula",aula.getText().toString());
                data.put("HoraInicio",horainicio.getText().toString());
                data.put("HoraFin",horafin.getText().toString());

                realtime.child("Aplicacion").child(datos.get("Id").toString()).updateChildren(data);
                Toast.makeText(DatosAplicaciones.this,"Se modifico correctamente",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(DatosAplicaciones.this, ListadoAplicaciones.class);
                startActivity(i);
                finish();
            }//onClick
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realtime.child("Aplicacion").child(datos.get("Id").toString()).removeValue();
                Toast.makeText(DatosAplicaciones.this,"Se elimino correctamente",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(DatosAplicaciones.this,ListadoAplicaciones.class);
                startActivity(i);
            }//onClick
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DatosAplicaciones.this,ListadoAplicaciones.class);
                startActivity(i);
                finish();
            }//onClick
        });
    }//onCreate
}//class