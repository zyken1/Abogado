package com.example.nequiz_omen.abogado.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nequiz_omen.abogado.R;
import com.example.nequiz_omen.abogado.entidades.Usuario;

import java.util.ArrayList;

/**
 * Created by Nequiz_OMEN on 04/07/2018.
 */

public class ListaJuiciosAdapter extends RecyclerView.Adapter<ListaJuiciosAdapter.PersonasViewHolder> {

    ArrayList<Usuario> listaUsuario;

    public ListaJuiciosAdapter(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rm_crear_cliente,null,false);
        return new PersonasViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.id.setText(listaUsuario.get(position).getId().toString());
        holder.nombre.setText(listaUsuario.get(position).getNombre());
        holder.telefono.setText(listaUsuario.get(position).getTelefono());
    }


    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }


    public class PersonasViewHolder extends RecyclerView.ViewHolder {

        TextView id,nombre,telefono;

        public PersonasViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.textExpediente);
            nombre = (TextView) itemView.findViewById(R.id.textNombre);
            telefono = (TextView) itemView.findViewById(R.id.textCorreo);
        }
    }


}