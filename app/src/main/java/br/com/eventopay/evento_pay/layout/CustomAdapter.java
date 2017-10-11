package br.com.eventopay.evento_pay.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.eventopay.evento_pay.R;
import br.com.eventopay.evento_pay.model.Usuario;

/**
 * Created by kiddo on 10/10/17.
 */

public class CustomAdapter extends ArrayAdapter<Usuario> {

    public CustomAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public CustomAdapter(Context context, int resource, List<Usuario> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.custom_adapter, null);
        }

        Usuario usuario = getItem(position);

        if (usuario != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.nome);
            TextView tt2 = (TextView) v.findViewById(R.id.sexo);
//            TextView tt3 = (TextView) v.findViewById(R.id.saldo);

            if (tt1 != null) {
                tt1.setText(usuario.getNome());
            }

            if (tt2 != null) {
                tt2.setText(usuario.getSexo());
            }

//            if (tt3 != null) {
//                tt3.setText((int) usuario.getSaldo());
//            }
        }

        return v;
    }
}