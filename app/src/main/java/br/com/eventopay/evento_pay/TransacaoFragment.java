package br.com.eventopay.evento_pay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import br.com.eventopay.evento_pay.rest.BaseEndpoint;

/**
 * Created by Bruno on 02/10/2017.
 */

public class TransacaoFragment extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.transacao_layout, container, false);
        getActivity().setTitle("Transferir");
        carregaSpinner();
        return myView;
    }

    public void carregaSpinner(){
        String endpoint = "api/evento";
        BaseEndpoint.listar(endpoint, getActivity(), myView, "spinnerLayout", null);

        Spinner mySpinner = (Spinner) getActivity().findViewById(R.id.spinnerEventos);

    }
}
