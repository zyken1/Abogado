package com.example.nequiz_omen.abogado;

/**
 * Created by abhiandroid
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TramitesFragment extends Fragment {
    TextView campoEtapa,campoTramite,campoFechaTramite,campoTipo,campoAsunto,campoInstancia;
    String campoId;


    public TramitesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tramites, container, false);



        campoEtapa = (TextView) view.findViewById(R.id.campoEtapa);
        campoTramite = (TextView) view.findViewById(R.id.campoTramite);
        campoFechaTramite = (TextView) view.findViewById(R.id.campoFechaTramite);



        /*CON ESTE  METOO SE CAPTURA LOS DATOS DESDE CUALQUIIER FRAGMENT*/
        SharedPreferences prefs = getActivity().getSharedPreferences("Preferences", 0);


        String etapa = prefs.getString("etapa", "0");
        String tramite = prefs.getString("tramite", "0");
        String fechaTramite = prefs.getString("fechaTramite", "0");





        //Se mandan los datos para que aparescan en en el fragment
        //campoId.setText("1");
        campoEtapa.setText(etapa);
        campoTramite.setText(tramite);
        campoFechaTramite.setText(fechaTramite);
        /*campoCliente.setText(cliente);
        campoContrario.setText(Contrario);
        campoInstancia.setText(Instancia);*/


        return view;
    }

}
