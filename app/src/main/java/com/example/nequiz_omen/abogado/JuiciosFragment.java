package com.example.nequiz_omen.abogado;

/**
 * Created by abhiandroid on 9/10/17.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class JuiciosFragment extends Fragment {

    TextView campoExpediente,campoCliente,campoContrario,campoTipo,campoAsunto,campoInstancia;
    String campoId;



    public JuiciosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_juicios, container, false);

        //campoId = (TextView) view.findViewById(R.id.campoId);
        campoExpediente = (TextView) view.findViewById(R.id.campoExpediente);
        campoCliente = (TextView) view.findViewById(R.id.campoCliente);
        campoContrario = (TextView) view.findViewById(R.id.campoContrario);
        campoTipo = (TextView) view.findViewById(R.id.campoTipo);
        campoAsunto = (TextView) view.findViewById(R.id.campoAsunto);
        campoInstancia = (TextView) view.findViewById(R.id.campoInstancia);



        /*CON ESTE  METOO SE CAPTURA LOS DATOS DESDE CUALQUIIER FRAGMENT*/
        SharedPreferences prefs = getActivity().getSharedPreferences("Preferences", 0);


        String ID = prefs.getString("id", "");
        String NoExpediente = prefs.getString("nombre", "0");
        String cliente = prefs.getString("clienteExtra", "0");
        String Contrario = prefs.getString("contrario", "0");
        //String ContrarioExtra = prefs.getString("contrarioExtra", "0");
        String Tipo = prefs.getString("juicio", "0");
        String Asunto = prefs.getString("asunto", "0");
        String Instancia = prefs.getString("instancia", "0");




        //Se mandan los datos para que aparescan en en el fragment
        //campoId.setText("1");
        campoExpediente.setText(NoExpediente);
        campoCliente.setText(cliente);
        campoContrario.setText(Contrario);
        campoTipo.setText(Tipo);
        campoAsunto.setText(Asunto);
        campoInstancia.setText(Instancia);


    return view;
    }

}
