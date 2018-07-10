package com.example.nequiz_omen.abogado.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nequiz_omen.abogado.R;
import com.example.nequiz_omen.abogado.entidades.Mascota;
import com.example.nequiz_omen.abogado.entidades.Usuario;

import java.util.ArrayList;

/**
 * Created by Nequiz_OMEN on 04/07/2018.
 */

public class ListaPersonasAdapter extends RecyclerView.Adapter<ListaPersonasAdapter.PersonasViewHolder> {

    ArrayList<Usuario> listaUsuario;

    public ListaPersonasAdapter(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rm_crear_cliente,null,false);
        return new PersonasViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.nombre.setText(listaUsuario.get(position).getNombre());
        holder.telefono.setText(listaUsuario.get(position).getTel_movil());
        holder.correo.setText(listaUsuario.get(position).getE_mail());    //holder.correo.setText(listaUsuario.get(position).getId().toString());
    }


    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }


    public class PersonasViewHolder extends RecyclerView.ViewHolder {

        TextView correo,nombre,telefono;

        public PersonasViewHolder(View itemView) {

            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.textNombre);
            telefono = (TextView) itemView.findViewById(R.id.textTelefono);
            correo = (TextView) itemView.findViewById(R.id.textCorreo);
        }
    }


}