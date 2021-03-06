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

public class RegistroAplicaciones extends AppCompatActivity {
    EditText fecha, aplicador, aula, horainicio, horafin;
    Button registrar, cancelar;
    DatabaseReference realtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_aplicaciones);

        fecha=findViewById(R.id.txtFechaAplicacionTOEFL);
        aplicador=findViewById(R.id.txtNombreAplicadorTOEFL);
        aula=findViewById(R.id.txtAulaAplicacionTOEFL);
        horainicio=findViewById(R.id.txtHoraInicioAplicacionTOEFL);
        horafin=findViewById(R.id.txtHoraFinAplicacionTOEFL);
        registrar=findViewById(R.id.btnInsertarAplicacionTOEFL);
        cancelar=findViewById(R.id.btnCancelarAplicacionTOEFL);

        realtime= FirebaseDatabase.getInstance().getReference();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aplicaciones aplicaciones=new Aplicaciones(fecha.getText().toString(), aplicador.getText().toString(), aula.getText().toString(), horainicio.getText().toString(), horafin.getText().toString());
                realtime.child("Aplicacion").push().setValue(aplicaciones)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(RegistroAplicaciones.this,"Exito!! "+fecha.getText().toString()+" insertado",Toast.LENGTH_SHORT).show();
                                fecha.setText("");
                                aplicador.setText("");
                                aula.setText("");
                                horainicio.setText("");
                                horafin.setText("");
                            }//onSuccess
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(RegistroAplicaciones.this,"Error!! "+fecha.getText().toString()+" no se inserto",Toast.LENGTH_SHORT).show();
                            }//onFailure
                        });
            }//onClick
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroAplicaciones.this, ListadoAplicaciones.class));
                finish();
            }//onClick
        });
    }//onCreate
}//class
