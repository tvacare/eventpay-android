/**
 * Created by Bruno on 02/10/2017.
 */

package br.com.eventopay.evento_pay;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import br.com.eventopay.evento_pay.model.Evento;
import br.com.eventopay.evento_pay.rest.BaseEndpoint;
import br.com.eventopay.evento_pay.sqlite.EventoSQLiteDao;


public class MeusEventosFragment extends Fragment {
    ListView listView;
    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.meus_eventos_layout, container, false);
        listar();
        getActivity().setTitle("Meus Eventos");
        return myView;
    }

    public void listar() {
        listView = (ListView) getActivity().findViewById(R.id.meusEventosList);

        //Recupera os produtos cadastrados no banco
        EventoSQLiteDao dao = new EventoSQLiteDao(getActivity());
        List<Evento> lista =  dao.list();

        //Cria o adaptador para exibir a lista de produtos no ListView
        ListAdapter adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,lista);
        listView.setAdapter(adapter);
    }


}
