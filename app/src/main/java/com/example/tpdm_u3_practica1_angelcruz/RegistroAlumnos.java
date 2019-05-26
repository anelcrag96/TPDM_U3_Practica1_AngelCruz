package com.example.tpdm_u3_practica1_angelcruz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistroAlumnos extends AppCompatActivity {
    EditText nocontrol, nombre, apellidos, carrera, fechaaplicacion;
    Button insertar, cancelar;
    //DatabaseReference realtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_alumnos);

        nocontrol=findViewById(R.id.txtNoControl);
        nombre=findViewById(R.id.txtNombre);
        apellidos=findViewById(R.id.txtApellidos);
        carrera=findViewById(R.id.txtCarrera);
        fechaaplicacion=findViewById(R.id.txtFechaAplicacion);

        insertar=findViewById(R.id.btnInsertarAlumno);
        cancelar=findViewById(R.id.btnCancelarAlumno);

        //realtime = FirebaseDatabase.getInstance().getReference();

        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumnos alumnos=new Alumnos(nocontrol.getText().length(), nombre.getText().toString(), apellidos.getText().toString(), carrera.getText().toString(), fechaaplicacion.getText().toString());

                
            }
        });
    }
}
