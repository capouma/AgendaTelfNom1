package com.example.findag.agendatelfnom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;


public class Activity1 extends Activity
{
    // Definimos las variables que nos serviran para el control de nuestra Toast.
    final int noNomTelf = 1;
    final int noNom = 2;
    final int noMatch = 3;

    // Definimos los EditTex nombre y telefono que necesitaremos.
    EditText edtNombre;
    EditText edtTelefono;

    // Definimos el ArrayList en el que guardaremos nuestros contactos.
    ArrayList<Contactos> insertContac = new ArrayList<Contactos>();

    // Variable que contendra la posicion de nuestro contacto modificado.
    int reEscribe;

    //Definimos nuestra variable static que sera nuestro requestCode(esta variable es la que identifica el intent que enviamos).
    private final static int CONTACTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1);

        //Definimos el boton Guardar y el boton Editar
        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);
        Button btnEditar = (Button) findViewById(R.id.btnEditar);

        // Definimos el onClick del boton Guardar.
        btnGuardar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                edtNombre = (EditText) findViewById(R.id.edtNombre);
                edtTelefono = (EditText) findViewById(R.id.edtTelefono);
                //Comprobamos que tanto el campo nombre y telefono estan cubiertos, si n lo estan salta la Toast.
                if (edtNombre.getText().toString().equals("") || edtTelefono.getText().toString().equals(""))
                {
                    showToast(noNomTelf);
                    return;
                }
                else
                {
                    insertContac.add(new Contactos(edtNombre.getText().toString(), edtTelefono.getText().toString()));

                    //Dejamos los campos del nombre y el telefono en blanco
                    edtNombre.setText("");
                    edtTelefono.setText("");
                }

            }
        });

        // Definimos el onClick del boton Editar
        btnEditar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Boolean encuentraNom = false;
                // Creamos nuestro intent.
                Intent pantalla2 = new Intent(Activity1.this, Activity2.class);

                // Comprobamos si al hacer click tenemos almacenado algun contacto.
                if(insertContac.size() == 0)
                {
                    showToast(noNom);
                    return;
                }
                else
                {
                    // Recorremos nuestro ArrayList para buscar coincidencias con el nombre solicitado.
                    for(int i = 0; i < insertContac.size(); i++)
                    {
                        if(insertContac.get(i).nombre.equals(edtNombre.getText().toString()))
                        {
                            reEscribe = i;
                            Contactos contactoEdit = new Contactos(insertContac.get(i).nombre, insertContac.get(i).telefono);
                            pantalla2.putExtra("editando", contactoEdit);
                            encuentraNom = true;
                            startActivityForResult(pantalla2,CONTACTO);
                        }
                    }
                    if (encuentraNom == false)
                    {
                        showToast(noMatch);
                    }

                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Este metodo recibira un entero que dependiendo del valor que lleve conseguiremos que se nos muestre una Toast con uno u otro mensaje.
    public void showToast(int comprueba)
    {
        Context context = getApplicationContext();
        int duracion = Toast.LENGTH_SHORT;
        CharSequence texto;

        Toast toast=null;

        if(comprueba == noNomTelf)
        {
            texto = getResources().getString(R.string.noNomTelf);
            toast = Toast.makeText(context, texto, duracion);
        }

        else if(comprueba == noMatch)
        {
            texto = getResources().getString(R.string.noMatch);
            toast = Toast.makeText(context, texto, duracion);
        }
        else if(comprueba == noNom)
        {
            texto = getResources().getString(R.string.noNombre);
            toast = Toast.makeText(context, texto, duracion);
        }
        toast.show();
    }

    // Con el metodo onActivityResult recogeremos el valor del intent de la segunda pantalla
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {

    }
}
