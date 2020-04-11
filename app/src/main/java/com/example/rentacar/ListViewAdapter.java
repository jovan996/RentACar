package com.example.rentacar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private Context context;

    private LayoutInflater inflater;

    private List<ModelListItem> listaModel;

    private ArrayList<ModelListItem> lista;

    public ListViewAdapter(Context context,List<ModelListItem> listaModel){
        this.context=context;
        this.listaModel=listaModel;
        this.inflater=LayoutInflater.from(context);
        this.lista=new ArrayList<ModelListItem>();
        this.lista.addAll(listaModel);
    }

    public class ViewHolder{

        private TextView naslov;

        private TextView detalji;

        private ImageView slika;

        private Button dugme;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=inflater.inflate(R.layout.item_automobil,null);
            holder.naslov=convertView.findViewById(R.id.naslov);
            holder.detalji=convertView.findViewById(R.id.detalji);
            holder.slika=convertView.findViewById(R.id.slikaAutomobila);
            holder.dugme=convertView.findViewById(R.id.prikaziDetalje);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.naslov.setText(lista.get(position).getMarka()+ " " +lista.get(position).getModel());

        return null;
    }
}
