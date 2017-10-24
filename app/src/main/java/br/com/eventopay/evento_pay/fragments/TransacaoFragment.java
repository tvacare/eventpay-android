/**
 * Created by Bruno on 02/10/2017.
 */

package br.com.eventopay.evento_pay.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.eventopay.evento_pay.R;
import br.com.eventopay.evento_pay.rest.BaseEndpoint;


public class TransacaoFragment extends Fragment {
    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.transacao_layout, container, false);
        getActivity().setTitle("Transferir");
        carregaSpinner();
        return myView;
    }

    public void carregaSpinner(){
        String endpoint = "api/evento";
        BaseEndpoint.listar(endpoint, getActivity(), myView, "spinnerLayout", null);
    }
}