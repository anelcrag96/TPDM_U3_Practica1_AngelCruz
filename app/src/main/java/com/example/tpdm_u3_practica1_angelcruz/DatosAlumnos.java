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

        realtime= FirebaseDatabase.getInstance().getReference();

        nocontrol=findViewById(R.id.txtNoControlAlumnoModificar);
        nombre=findViewById(R.id.txtNombreAlumnoModificar);
        apellidos=findViewById(R.id.txtApellidosAlumnoModificar);
        carrera=findViewById(R.id.txtCarreraAlumnoModificar);
        fechaaplicacion=findViewById(R.id.txtFechaAplicacionAlumnoModificar);
        lista_alumnos=findViewById(R.id.listaAlumno);
        datos=getIntent().getExtras();

        modificar=findViewById(R.id.btnModificarAlumnoModificar);
        eliminar=findViewById(R.id.btnEliminarAlumnoModificar);
        cancelar=findViewById(R.id.btnRegresarAlumnoModificar);

        nocontrol.setText(datos.get("nocontrol").toString());
        nombre.setText(datos.get("nombre").toString());
        apellidos.setText(datos.get("apellidos").toString());
        carrera.setText(datos.get("carrera").toString());
        fechaaplicacion.setText(datos.get("fechaaplicacion").toString());

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> data=new HashMap<>();
                data.put("nocontrol",nocontrol.getText().toString());
                data.put("nombre",nombre.getText().toString());
                data.put("apellidos",apellidos.getText().toString());
                data.put("carrera",carrera.getText().toString());
                data.put("fechaaplicacion",fechaaplicacion.getText().toString());

                realtime.child("Alumno").child(datos.get("id").toString()).updateChildren(data);
                Toast.makeText(DatosAlumnos.this,nombre.getText().toString()+" actualizado correctamente",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(DatosAlumnos.this,ListadoAlumnos.class);
                startActivity(i);
                finish();
            }//onClick
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realtime.child("Alumno").child(datos.get("id").toString()).removeValue();
                Toast.makeText(DatosAlumnos.this, nombre.getText().toString()+" eliminado correctamete",Toast.LENGTH_SHORT).show();
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
