/**
 * Created by Bruno on 02/10/2017.
 */

package br.com.eventopay.evento_pay.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.eventopay.evento_pay.adapters.ExtratoAdapter;
import br.com.eventopay.evento_pay.R;
import br.com.eventopay.evento_pay.model.Transacao;
import br.com.eventopay.evento_pay.rest.BaseEndpoint;


public class ExtractFragment extends Fragment {

    private View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.myView = inflater.inflate(R.layout.extrato_layout, container, false);
        getActivity().setTitle(getString(R.string.extract));
        listar();
        return this.myView;
    }

    public void listar() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int usuario = preferences.getInt("user", 0);

        RecyclerView recyclerView = (RecyclerView) myView.findViewById(R.id.extratoList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        List<Transacao> list = new ArrayList<>();
        ExtratoAdapter adapter = new ExtratoAdapter(list, getContext());
        recyclerView.setAdapter(adapter);

        String endpoint = "api/transacao/"+ usuario;
        BaseEndpoint.listar(endpoint, getActivity(), myView, "extractLayout", adapter);
    }
}