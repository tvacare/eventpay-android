/**
 * Created by Bruno on 02/10/2017.
 */

package br.com.eventopay.evento_pay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.eventopay.evento_pay.rest.BaseEndpoint;


public class ExtratoFragment extends Fragment {

    private View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.myView = inflater.inflate(R.layout.extrato_layout, container, false);
        listar();
        return this.myView;
    }

    public void listar() {
        String endpoint = "api/usuario";
        BaseEndpoint.listar(endpoint, getActivity(), myView);
    }
}
