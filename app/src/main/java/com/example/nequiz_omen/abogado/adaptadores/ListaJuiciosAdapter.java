package com.example.nequiz_omen.abogado.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nequiz_omen.abogado.R;
import com.example.nequiz_omen.abogado.entidades.JuiciosE;

import java.util.ArrayList;

/**
 * Created by Nequiz_OMEN on 04/07/2018.
 */

public class ListaJuiciosAdapter extends RecyclerView.Adapter<ListaJuiciosAdapter.PersonasViewHolder>
        implements View.OnClickListener{
    ArrayList<JuiciosE> listaMascotas;
    private View.OnClickListener listener;  // es nuestro escuchador


    public ListaJuiciosAdapter(ArrayList<JuiciosE> listaMascotas) {
        this.listaMascotas = listaMascotas;
    }

    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rm_crear_juicio,null,false);

        view.setOnClickListener(this);// este escuchara el evento de seleccion
        return new PersonasViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        //holder.id.setText(listaMascotas.get(position).getIdJuicios().toString());
        holder.nombre.setText(listaMascotas.get(position).getNombreExpediente());
        holder.cliente.setText(listaMascotas.get(position).getCliente_extra());
        holder.tipoJuicio.setText(listaMascotas.get(position).getJuicio());
        holder.asunto.setText(listaMascotas.get(position).getAsunto());
    }


    @Override
    public int getItemCount() {
        return listaMascotas.size();
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

        TextView id,nombre,cliente,tipoJuicio,asunto;

        public PersonasViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.textExpediente);
            cliente = (TextView) itemView.findViewById(R.id.textCliente);
            tipoJuicio = (TextView) itemView.findViewById(R.id.textTipoJuicio);
            asunto = (TextView) itemView.findViewById(R.id.textAsunto);
        }
    }


}