package br.com.eventopay.evento_pay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import br.com.eventopay.evento_pay.rest.BaseEndpoint;

/**
 * Created by Bruno on 02/10/2017.
 */

public class CreditoFragment extends Fragment {
    View myView;
    EditText valor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.credito_layout, container, false);
        return myView;
    }


}
