package com.example.nequiz_omen.abogado.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nequiz_omen.abogado.R;
import com.example.nequiz_omen.abogado.entidades.E_juicio;

import java.util.ArrayList;

/**
 * Created by Nequiz_OMEN on 04/07/2018.
 */

public class ListaJuiciosAdapter extends RecyclerView.Adapter<ListaJuiciosAdapter.JuiciosViewHolder> {

    ArrayList<E_juicio> listaUsuario;

    public ListaJuiciosAdapter(ArrayList<E_juicio> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    @Override
    public JuiciosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rm_crear_cliente,null,false);
        return new JuiciosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JuiciosViewHolder holder, int position) {
        holder.ID.setText(listaUsuario.get(position).getId().toString());
        holder.Expediente.setText(listaUsuario.get(position).getExpediente());
        holder.Cliente.setText(listaUsuario.get(position).getCliente());
    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class JuiciosViewHolder extends RecyclerView.ViewHolder {


        TextView ID,Expediente,Cliente;

        public JuiciosViewHolder(View itemView) {
            super(itemView);
            ID = (TextView) itemView.findViewById(R.id.textNombre);
            Expediente = (TextView) itemView.findViewById(R.id.textExpediente);
            Cliente = (TextView) itemView.findViewById(R.id.textCliente);
        }
    }
}
