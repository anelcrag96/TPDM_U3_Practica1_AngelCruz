package com.example.tpdm_u3_practica1_angelcruz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DatosAlumnos extends AppCompatActivity {
    EditText nocontrol, nombre, apellidos, carrera, fechaaplicacion;
    Button modificar, eliminar, cancelar;
    ListView lista_alumnos;
    DatabaseReference realtime;
    Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datos_alumnos);

        nocontrol=findViewById(R.id.txtNoControl);
        nombre=findViewById(R.id.txtNombre);
        apellidos=findViewById(R.id.txtApellidos);
        carrera=findViewById(R.id.txtCarrera);
        fechaaplicacion=findViewById(R.id.txtFechaAplicacion);

        modificar=findViewById(R.id.btnModificarAlumno);
        eliminar=findViewById(R.id.btnEliminarAlumno);
        cancelar=findViewById(R.id.btnCancelarAlumno);

        realtime= FirebaseDatabase.getInstance().getReference();
        lista_alumnos=findViewById(R.id.listaAlumno);
        datos=getIntent().getExtras();

        nocontrol.setText(datos.get("NoControl").toString());
        nocontrol.isEnabled();
        nombre.setText(datos.get("Nombre").toString());
        apellidos.setText(datos.get("Apellidos").toString());
        carrera.setText(datos.get("Carrera").toString());
        fechaaplicacion.setText(datos.get("FechaAplicacion").toString());

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> data=new HashMap<>();
                data.put("NoControl",nocontrol.getText().toString());
                data.put("Nombre",nombre.getText().toString());
                data.put("Apellidos",apellidos.getText().toString());
                data.put("Carrera",carrera.getText().toString());
                data.put("FechaAplicacion",fechaaplicacion.getText().toString());

                realtime.child("Alumno").child(datos.get("Id").toString()).updateChildren(data);
                Toast.makeText(DatosAlumnos.this,"Actualizado correctamente",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(DatosAlumnos.this,ListadoAlumnos.class);
                startActivity(i);
                finish();
            }//onClick
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realtime.child("Alumno").child(datos.get("Id").toString()).removeValue();
                Toast.makeText(DatosAlumnos.this, "Se elimino correctamete",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(DatosAlumnos.this,ListadoAlumnos.class);
                startActivity(i);
            }//onClick
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DatosAlumnos.this, ListadoAlumnos.class);
                startActivity(i);
                finish();
            }//onClick
        });
    }//onCreate
}//class
