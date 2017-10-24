/**
 * Created by Thiago on 10/17/17.
 */

package br.com.eventopay.evento_pay.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.eventopay.evento_pay.R;
import br.com.eventopay.evento_pay.model.Transacao;

public class ExtratoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Transacao> lista;
    private Context context;

    public ExtratoAdapter(List<Transacao> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_adapter, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder) {
            final Transacao transacao = lista.get(position);
            ((ItemViewHolder) holder).evento.setText(transacao.getNomeEvento());
            ((ItemViewHolder) holder).saldo.setText(String.valueOf(transacao.getValor()));
            ((ItemViewHolder) holder).mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);

                    sendIntent.putExtra(Intent.EXTRA_TEXT, "EVENT PAY - Voce foi convidado para o evento "
                            + transacao.getNomeEvento()
                            +"\nAcesse: http://eventpayment-web.azurewebsites.net");
                    sendIntent.setType("text/plain");

                    context.startActivity(sendIntent);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void addList(List<Transacao> list){
        lista = list;
        notifyDataSetChanged();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder{

        private TextView evento;
        private TextView saldo;
        private LinearLayout mainLayout;

        ItemViewHolder(View itemView) {
            super(itemView);
            this.evento = (TextView) itemView.findViewById(R.id.evento);
            this.saldo = (TextView) itemView.findViewById(R.id.saldo);
            this.mainLayout = (LinearLayout) itemView.findViewById(R.id.main_layout);
        }
    }
}