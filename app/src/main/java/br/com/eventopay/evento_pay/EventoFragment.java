package br.com.eventopay.evento_pay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bruno on 02/10/2017.
 */

public class EventoFragment extends android.app.Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.evento_layout, container, false);
        return myView;
    }
}
