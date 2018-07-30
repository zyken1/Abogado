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

public class HonorariosFragment extends Fragment {
    TextView campoCosto_juicio,campo_resta_pago,campo_abono,campo_fecha_pago;


    public HonorariosFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_honorarios, container, false);



        campoCosto_juicio = (TextView) view.findViewById(R.id.campoCosto_juicio);
        campo_resta_pago = (TextView) view.findViewById(R.id.campo_resta_pago);
        campo_abono = (TextView) view.findViewById(R.id.campo_abono);
        campo_fecha_pago = (TextView) view.findViewById(R.id.campo_fecha_pago);


        /*CON ESTE  METOO SE CAPTURA LOS DATOS DESDE CUALQUIIER FRAGMENT*/
        SharedPreferences prefs = getActivity().getSharedPreferences("Preferences", 0);


        String costoJuicio = prefs.getString("costoJuicio", "0");
        String restaPago = prefs.getString("restaPago", "0");
        String abono = prefs.getString("abono", "0");
        String fechaPago = prefs.getString("fechaPago", "0");





        //Se mandan los datos para que aparescan en en el fragment
        //campoId.setText("1");
        campoCosto_juicio.setText(costoJuicio);
        campo_resta_pago.setText(restaPago);
        campo_abono.setText(abono);
        campo_fecha_pago.setText(fechaPago);



        return view;
    }

}
