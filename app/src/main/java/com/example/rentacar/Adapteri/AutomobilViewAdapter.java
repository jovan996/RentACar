package com.example.rentacar.Adapteri;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentacar.Modeli.AutomobilItemModel;
import com.example.rentacar.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AutomobilViewAdapter extends BaseAdapter {

    private Context context;

    private LayoutInflater inflater;

    private List<AutomobilItemModel> listaModel;

    private ArrayList<AutomobilItemModel> lista;

    public AutomobilViewAdapter(Context context, List<AutomobilItemModel> listaModel){
        this.context=context;
        this.listaModel=listaModel;
        this.inflater=LayoutInflater.from(context);
        this.lista=new ArrayList<AutomobilItemModel>();
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
        holder.detalji.setText(lista.get(position).getCijenaPoDanu());
        File file=new File("/slike");  //neki folder sa slikama
        holder.slika.setImageURI(Uri.fromFile(file));
        holder.dugme.setText("Prikazi detalje");

        return convertView;
    }
}
