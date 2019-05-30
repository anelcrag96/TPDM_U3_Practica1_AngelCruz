package com.example.tpdm_u3_practica1_angelcruz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroAlumnos extends AppCompatActivity {
    EditText nocontrol, nombre, apellidos, carrera, fechaaplicacion;
    Button insertar, cancelar;
    DatabaseReference realtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_alumnos);

        nocontrol=findViewById(R.id.txtNoControlAlumno);
        nombre=findViewById(R.id.txtNombreAlumno);
        apellidos=findViewById(R.id.txtApellidosAlumno);
        carrera=findViewById(R.id.txtCarreraAlumno);
        fechaaplicacion=findViewById(R.id.txtFechaAplicacionAlumno);

        insertar=findViewById(R.id.btnInsertarAlumno);
        cancelar=findViewById(R.id.btnCancelarAlumno);

        realtime = FirebaseDatabase.getInstance().getReference();

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumnos alumnos=new Alumnos(nocontrol.getText().toString(), nombre.getText().toString(), apellidos.getText().toString(), carrera.getText().toString(), fechaaplicacion.getText().toString());

                realtime.child("Alumno").push().setValue(alumnos)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(RegistroAlumnos.this,"Exito!! "+nombre.getText().toString()+" insertado",Toast.LENGTH_SHORT).show();
                                nocontrol.setText("");
                                nombre.setText("");
                                apellidos.setText("");
                                carrera.setText("");
                                fechaaplicacion.setText("");
                            }//onSuccess
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegistroAlumnos.this,"Error!! "+nombre.getText().toString()+" no se inserto",Toast.LENGTH_SHORT).show();
                            }//onFailure
                        });
            }//onClick
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroAlumnos.this,ListadoAlumnos.class));
                finish();
            }
        });
    }//onCreate
}//class
