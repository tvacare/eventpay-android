package br.com.eventopay.evento_pay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bruno on 02/10/2017.
 */

public class ExtratoFragment extends android.app.Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        listar();
        myView = inflater.inflate(R.layout.extrato_layout, container, false);
        return myView;
    }

    public void listar (){
        BaseDAO dao = new BaseDAO();
        String url = "http://api-eventpayment.azurewebsites.net/api/usuario";
        try {
            dao.Listar(url, ExtratoFragment.this);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
