/**
 * Created by Bruno on 02/10/2017.
 */

package br.com.eventopay.evento_pay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.com.eventopay.evento_pay.rest.BaseEndpoint;

import static android.content.Context.MODE_PRIVATE;


public class EventoFragment extends Fragment {
    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.evento_layout, container, false);
        listar();
        getActivity().setTitle("Eventos");
        return myView;
    }

    public void listar() {
        String endpoint = "api/evento";
        BaseEndpoint.listar(endpoint, getActivity(), myView, "eventoLayout");
    }


}
