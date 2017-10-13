package br.com.eventopay.evento_pay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Bruno on 02/10/2017.
 */

public class CriarEventoFragment extends Fragment {
    View myView;
    EditText valorUnitario;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.criar_evento_layout, container, false);
        getActivity().setTitle("Novo Evento");
        return myView;
    }
}
