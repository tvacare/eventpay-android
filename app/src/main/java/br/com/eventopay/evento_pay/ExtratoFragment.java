/**
 * Created by Bruno on 02/10/2017.
 */

package br.com.eventopay.evento_pay;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.eventopay.evento_pay.services.BaseEndpoint;


public class ExtratoFragment extends Fragment {
    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listar();
        myView = inflater.inflate(R.layout.extrato_layout, container, false);
        return myView;
    }

    public void listar() {
        String endpoint = "api/usuario";
        BaseEndpoint.listar(endpoint, ExtratoFragment.this);
    }
}
