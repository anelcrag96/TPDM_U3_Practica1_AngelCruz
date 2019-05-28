package com.example.tpdm_u3_practica1_angelcruz;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.MacAddress;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListadoAlumnos extends AppCompatActivity {
    Button regresar;
    ListView listaalumnos;
    DatabaseReference realtime;
    List<Alumnos> datosalumnos;
    ArrayAdapter adaptador;
    List lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_alumnos);

        listaalumnos=findViewById(R.id.listaAlumno);
        regresar=findViewById(R.id.btnRegresarListaAlumno);

        lista=new ArrayList();

        realtime= FirebaseDatabase.getInstance().getReference();
        adaptador=new ArrayAdapter(ListadoAlumnos.this, android.R.layout.simple_list_item_1,lista);

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ListadoAlumnos.this, Principal.class);
                startActivity(i);
                finish();
            }
        });

        realtime.child("Alumno").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datosalumnos=new ArrayList<>();

                if (dataSnapshot.getChildrenCount()<=0){
                    Toast.makeText(ListadoAlumnos.this, "No exixten alumnos registrados",Toast.LENGTH_SHORT).show();
                    return;
                }
                for (final DataSnapshot snap:dataSnapshot.getChildren()){
                    realtime.child("Alumno").child(snap.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Alumnos d=dataSnapshot.getValue(Alumnos.class);

                            if(d!=null){
                                d.setId(snap.getKey());
                                datosalumnos.add(d);
                            }//if
                            cargarDatosAlumno();
                        }//onDataChange

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }//onCancelled
                    });
                }//for
            }//onDataChange

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }//onCancelled
        });

        listaalumnos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(ListadoAlumnos.this);
                alerta.setTitle("Alerta")
                        .setMessage("¿Deseas modificar/eliminar el alumno seleccionado?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i=new Intent(ListadoAlumnos.this, DatosAlumnos.class);
                                i.putExtra("Id",datosalumnos.get(position).getId());
                                i.putExtra("NoControl",datosalumnos.get(position).getNocontrol());
                                i.putExtra("Nombre",datosalumnos.get(position).getNombre());
                                i.putExtra("Apellidos",datosalumnos.get(position).getApellidos());
                                i.putExtra("Carrera",datosalumnos.get(position).getCarrera());
                                i.putExtra("FechaAplicacion",datosalumnos.get(position).getFechaaplicacion());
                                startActivity(i);
                                finish();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
            }
        });
    }//onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.opciones,menu);
        return super.onCreateOptionsMenu(menu);
    }//onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.opcion_insertar:
                Intent insertar=new Intent(this, RegistroAlumnos.class);
                startActivity(insertar);
                finish();
                break;
        }//switch
        return super.onOptionsItemSelected(item);
    }//onOptionsItemSelected

    private void cargarDatosAlumno(){
        if (datosalumnos.size()==0){
            return;
        }//if
        String nombre[]=new String[datosalumnos.size()];
        for (int i=0; i<nombre.length; i++){
            Alumnos a=datosalumnos.get(i);
            nombre[i]=a.nombre;
        }//for
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,nombre);
        listaalumnos.setAdapter(adapter);
    }//cargarDatosAlumno
}//class
