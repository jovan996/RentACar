package com.example.rentacar.Adapteri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentacar.Modeli.AutomobilItemModel;
import com.example.rentacar.Modeli.KomentarItemModel;
import com.example.rentacar.R;

import java.util.ArrayList;
import java.util.List;

public class KomentarViewAdapter extends BaseAdapter {

    private Context context;

    private LayoutInflater inflater;

    private List<KomentarItemModel> listaModel;

    public KomentarViewAdapter(Context context, List<KomentarItemModel> listaModel){
        this.context=context;
        this.listaModel = new ArrayList<>(listaModel);
        this.inflater=LayoutInflater.from(context);
    }

    public class ViewHolder{

        private TextView naslov;

        private TextView tekst;

        private ImageView slika;

        private TextView datum;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        KomentarViewAdapter.ViewHolder holder;
        if (convertView == null) {
            holder=new KomentarViewAdapter.ViewHolder();
            convertView=inflater.inflate(R.layout.item_komentar,null);
            holder.naslov=convertView.findViewById(R.id.komentarAutor);
            holder.tekst=convertView.findViewById(R.id.komentarTekst);
            holder.slika=convertView.findViewById(R.id.komentarAvatar);
            holder.datum=convertView.findViewById(R.id.komentarDatum);
            convertView.setTag(holder);
        } else{
            holder = (KomentarViewAdapter.ViewHolder)convertView.getTag();
        }

        holder.naslov.setText(listaModel.get(position).getKorisnikIme()+ " " + listaModel.get(position).getKorisnikPrezime());
        holder.tekst.setText(listaModel.get(position).getKomentarTekst());
        holder.slika.setImageResource(R.drawable.img_avatar);
        holder.datum.setText(listaModel.get(position).getKomentarDatum().toString());

        return convertView;
    }
}
