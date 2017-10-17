/**
 * Created by Bruno on 02/10/2017.
 */

package br.com.eventopay.evento_pay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.eventopay.evento_pay.rest.BaseEndpoint;

import static android.content.Context.MODE_PRIVATE;


public class ExtratoFragment extends Fragment {

    private View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.myView = inflater.inflate(R.layout.extrato_layout, container, false);
        getActivity().setTitle("Extrato");
        listar();
        return this.myView;
    }

    public void listar() {

        Intent intent = new Intent(getActivity(), HomeActivity.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
 //       String usuario = preferences.getString("user", "");

        String endpoint = "api/transacao/"+ "12";
        BaseEndpoint.listar(endpoint, getActivity(), myView, "extratoLayout");
    }



}
