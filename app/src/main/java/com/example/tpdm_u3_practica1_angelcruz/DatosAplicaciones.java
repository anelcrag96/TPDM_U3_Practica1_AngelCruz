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

        realtime= FirebaseDatabase.getInstance().getReference();

        fechaaplicacion=findViewById(R.id.txtFechaAplicacionTOEFL_Modificar);
        aplicador=findViewById(R.id.txtNombreAplicadorTOEFL_Modificar);
        aula=findViewById(R.id.txtAulaAplicacionTOEFL_Modificar);
        horainicio=findViewById(R.id.txtHoraInicioAplicacionTOEFL_Modificar);
        horafin=findViewById(R.id.txtHoraFinAplicacionTOEFL_Modificar);

        modificar=findViewById(R.id.btnModificarAplicacionTOEFL_Modificar);
        eliminar=findViewById(R.id.btnEliminarAplicacionTOEFL_Modificar);
        cancelar=findViewById(R.id.btnRegresarAplicacionTOEFL_Modificar);
        datos=getIntent().getExtras();

        fechaaplicacion.setText(datos.get("fechaaplicacion").toString());
        aplicador.setText(datos.get("aplicador").toString());
        aula.setText(datos.get("aula").toString());
        horainicio.setText(datos.get("horainicio").toString());
        horafin.setText(datos.get("horafin").toString());

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> data=new HashMap<>();
                data.put("fechaaplicacion",fechaaplicacion.getText().toString());
                data.put("aplicador",aplicador.getText().toString());
                data.put("aula",aula.getText().toString());
                data.put("horainicio",horainicio.getText().toString());
                data.put("horafin",horafin.getText().toString());

                realtime.child("Aplicacion").child(datos.get("id").toString()).updateChildren(data);
                Toast.makeText(DatosAplicaciones.this,fechaaplicacion.getText().toString()+" modificada correctamente",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(DatosAplicaciones.this, ListadoAplicaciones.class);
                startActivity(i);
                finish();
            }//onClick
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realtime.child("Aplicacion").child(datos.get("id").toString()).removeValue();
                Toast.makeText(DatosAplicaciones.this,fechaaplicacion.getText().toString()+" eliminada correctamente",Toast.LENGTH_SHORT).show();
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