package com.example.nequiz_omen.abogado.adaptadores;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.nequiz_omen.abogado.R;
import com.example.nequiz_omen.abogado.entidades.Usuario;

import java.util.ArrayList;

/**
 * Created by Nequiz_OMEN on 04/07/2018.
 */

public class ListaPersonasAdapter extends RecyclerView.Adapter<ListaPersonasAdapter.PersonasViewHolder>
implements View.OnClickListener {

    ArrayList<Usuario> listaUsuario;
    private View.OnClickListener listener;  // es nuestro escuchador
    public ListaPersonasAdapter(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rm_crear_cliente, null, false);

        view.setOnClickListener(this);// este escuchara el evento de seleccion
        return new PersonasViewHolder(view);

    }

    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.campoId.setText("");
        holder.nombre.setText(listaUsuario.get(position).getNombre().toUpperCase());
        holder.telefono.setText(listaUsuario.get(position).getTel_movil());
        holder.correo.setText(listaUsuario.get(position).getE_mail().toLowerCase());     //holder.correo.setText(listaUsuario.get(position).getId().toString());

       // System.out.println("************ position " + position);
    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener; //el listener que se creo arriba en el metodo sea igual al listener que le llega
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }



    public class PersonasViewHolder extends RecyclerView.ViewHolder {

        TextView correo,nombre,telefono,textid,campoId;

        public PersonasViewHolder(View itemView) {
            super(itemView);
            campoId = (TextView) itemView.findViewById(R.id.campoId);
            nombre = (TextView) itemView.findViewById(R.id.textNombre);
            telefono = (TextView) itemView.findViewById(R.id.textTelefono);
            correo = (TextView) itemView.findViewById(R.id.textCorreo);
        }
    }


}