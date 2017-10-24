/**
 * Created by Bruno on 02/10/2017.
 */

package br.com.eventopay.evento_pay.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import br.com.eventopay.evento_pay.R;

public class CreditoFragment extends Fragment {

    View myView;
    TextView valor;
    SeekBar seekBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.credito_layout, container, false);
        iniciaListener(myView);
        getActivity().setTitle("Comprar Crédito");
        return myView;
    }

    public void iniciaListener(View view){
        valor = (TextView) view.findViewById(R.id.txtValor);
        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                valor.setText("R$ " + progress + ",00");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}