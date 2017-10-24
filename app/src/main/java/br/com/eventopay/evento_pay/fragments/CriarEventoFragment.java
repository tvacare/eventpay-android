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


public class CriarEventoFragment extends Fragment {

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.criar_evento_layout, container, false);
        getActivity().setTitle(getString(R.string.newEvent));
        return myView;
    }
}