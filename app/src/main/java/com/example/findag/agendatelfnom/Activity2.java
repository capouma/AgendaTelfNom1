package com.example.findag.agendatelfnom;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//Rama 3
public class Activity2 extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        // Definimos el EditText nombre y telefono y el boton Cambiar.
        final EditText edtNombre = (EditText) findViewById(R.id.edtNombre2);
        final EditText edtTelefono = (EditText) findViewById(R.id.edtTelefono2);
        Button btnCambiar = (Button) findViewById(R.id.btnCambiar);

        //Creamos un objeto de tipo Contactos para recoger el objeto que enviamos en nuestro intent
        Contactos aEditar = (Contactos) getIntent().getSerializableExtra("editando");

        // Insertamos los datos recogidos en nuestro objeto aEditar en nuestraos EditText nombre y telefono.
        edtNombre.setText(aEditar.getNombre());
        edtTelefono.setText(aEditar.getTelefono());

        //Creamos el evento onClick de nuestro boton Cambiar.
        btnCambiar.setOnClickListener(new View.OnClickListener()
        {
            //Sobreescribimos el metodo onClick para que recoja los cambios realizados en el contacto y los envie a la Activity1.
            @Override
            public void onClick(View v)
            {
                Intent modificado = new Intent();
                Contactos editado = new Contactos(edtNombre.getText().toString(), edtTelefono.getText().toString());
                modificado.putExtra("nombreEditado", editado.getNombre());
                modificado.putExtra("telefonoEditado", editado.getTelefono());
                setResult(RESULT_OK, modificado);
                finish();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
