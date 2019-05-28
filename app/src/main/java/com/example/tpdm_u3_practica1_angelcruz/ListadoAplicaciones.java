package com.example.tpdm_u3_practica1_angelcruz;

import android.content.DialogInterface;
import android.content.Intent;
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

public class ListadoAplicaciones extends AppCompatActivity {
    Button regresar;
    ListView listaaplicacion;
    DatabaseReference realtime;
    List<Aplicaciones> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_aplicaciones);

        listaaplicacion=findViewById(R.id.listaAplicacion);
        regresar=findViewById(R.id.btnRegresarListaAplicacion);
        realtime= FirebaseDatabase.getInstance().getReference();

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ListadoAplicaciones.this,Principal.class);
                startActivity(i);
                finish();
            }//onClick
        });

        realtime.child("Aplicacion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datos=new ArrayList<>();

                if (dataSnapshot.getChildrenCount()<=0){
                    Toast.makeText(ListadoAplicaciones.this, "No exixten aplicaciones registradas",Toast.LENGTH_SHORT).show();
                    return;
                }//if
                for(final DataSnapshot snap:dataSnapshot.getChildren()){
                    realtime.child("Aplicacion").child(snap.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Aplicaciones a=dataSnapshot.getValue(Aplicaciones.class);

                            if(a!=null){
                                a.setId(snap.getKey());
                                datos.add(a);
                            }//if
                            cargarDatosAplicacion();
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

        listaaplicacion.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alerta=new AlertDialog.Builder(ListadoAplicaciones.this);
                alerta.setTitle("Alerta")
                        .setMessage("¿Desea modificar/eliminar fecha aplicacion seleccionada?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i=new Intent(ListadoAplicaciones.this, DatosAplicaciones.class);
                                i.putExtra("Id",datos.get(position).getId());
                                i.putExtra("FechaAplicacion",datos.get(position).getFechaaplicacion());
                                i.putExtra("Aplicador",datos.get(position).getAplicador());
                                i.putExtra("Aula",datos.get(position).getAula());
                                i.putExtra("HoraInicio",datos.get(position).getHorainicio());
                                i.putExtra("HoraFin",datos.get(position).getHorafin());
                                startActivity(i);
                                finish();
                            }//onClick
                        })
                .setNegativeButton("No",null).show();
            }//onItemClick
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
                Intent i=new Intent(ListadoAplicaciones.this,RegistroAplicaciones.class);
                startActivity(i);
                finish();
                break;
        }//switch
        return super.onOptionsItemSelected(item);
    }//onOptionsItemSelected

    private void cargarDatosAplicacion(){
        if (datos.size()==0){
            return;
        }//if
        String fecha[]=new String[datos.size()];
        for (int i=0; i<fecha.length; i++){
            Aplicaciones a=datos.get(i);
            fecha[i]=a.fechaaplicacion;
        }//for
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,fecha);
        listaaplicacion.setAdapter(adapter);
    }//cargarDatosAplicacion
}//class
