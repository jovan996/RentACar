package com.example.rentacar.Adapteri;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentacar.Aktivnosti.DetailViewActivity;
import com.example.rentacar.Modeli.AutomobilItemModel;
import com.example.rentacar.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class AutomobilViewAdapter extends BaseAdapter implements Filterable {

    private Context context;

    private LayoutInflater inflater;

    private List<AutomobilItemModel> listaModel;

    private ArrayList<AutomobilItemModel> lista;  //ova druga lista je zbog genericnosti jer sada parametar u konstruktoru moze biti bilo kakva kolekcija

    public AutomobilViewAdapter(Context context, List<AutomobilItemModel> listaModel){
        this.context=context;
        this.listaModel=listaModel;
        this.inflater=LayoutInflater.from(context);
        this.lista=new ArrayList<AutomobilItemModel>();
        this.lista.addAll(listaModel);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<AutomobilItemModel> filtriranaLista = new ArrayList<>();

            if (constraint.toString().isEmpty()) {
                filtriranaLista.addAll(lista);
            }
            else {
                for (AutomobilItemModel model : lista) {
                    String naslov = (model.getMarka() + model.getModel()).toLowerCase(Locale.getDefault());
                    if (naslov.contains(constraint.toString().toLowerCase(Locale.getDefault()))) {
                        filtriranaLista.add(model);
                    }
                }
            }

            FilterResults rezultati = new FilterResults();
            rezultati.values = filtriranaLista;

            return rezultati;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaModel.clear();
            listaModel.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder{

        private TextView naslov;

        private TextView detalji;

        private ImageView slika;

        private Button dugme;
    }

    @Override
    public int getCount() {
        return listaModel.size();
    }

    @Override
    public Object getItem(int position) {
        return listaModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

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
        String str = context.getResources().getString(R.string.cijena);
        String str1 = context.getResources().getString(R.string.dan_d);
        holder.naslov.setText(listaModel.get(position).getMarka()+ " " +listaModel.get(position).getModel());
        holder.detalji.setText(str + Integer.toString(listaModel.get(position).getCijenaPoDanu()) + "€/" + str1);
        String putanjaId = listaModel.get(position).getSlikaPutanja().replace("R.drawable.", "");
        int id = context.getResources().getIdentifier(putanjaId, "drawable", context.getPackageName());
        holder.slika.setImageResource(id);
        //holder.dugme.setText("@string/prikazi_detalje");

        holder.dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailViewActivity.class);
                intent.putExtra("faId", listaModel.get(position).getFaId());
                context.startActivity(intent);
            }
        });
        return convertView;
    }

}
