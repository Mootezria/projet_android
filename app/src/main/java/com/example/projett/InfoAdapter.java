package com.example.projett;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder>{
    public InfoAdapter(Context context, List<InfoModel> infoModelList) {
        this.context = context;
        this.infoModelList = infoModelList;
    }

    Context context;
    List<InfoModel> infoModelList;
    @NonNull
    @Override
    public InfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent , false);

        return new ViewHolder(v);
    }
    public void filterList(List<InfoModel> filteredList) {
        infoModelList = filteredList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull InfoAdapter.ViewHolder holder, int position) {
        InfoModel studentModel = infoModelList.get(position);
        holder.prix.setText(studentModel.getPrix());
        holder.type.setText (studentModel.getType());
        holder.size.setText (studentModel.getSize());
        holder.nb_chambre.setText (studentModel.getNb_chambre());
        holder.nb_pieces.setText (studentModel.getNb_pieces());
        holder.Localition.setText (studentModel.getLocalition());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity5.class);
                intent.putExtra("prix", infoModelList.get(holder.getAdapterPosition()).getPrix());
                intent.putExtra("type", infoModelList.get(holder.getAdapterPosition()).getType());
                intent.putExtra("size", infoModelList.get(holder.getAdapterPosition()).getSize());
                intent.putExtra("nbc", infoModelList.get(holder.getAdapterPosition()).getNb_chambre());
                intent.putExtra("nbp", infoModelList.get(holder.getAdapterPosition()).getNb_pieces());
                intent.putExtra("desc", infoModelList.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("acc", infoModelList.get(holder.getAdapterPosition()).getAccees());
                intent.putExtra("ann", infoModelList.get(holder.getAdapterPosition()).getAnnexes());
                intent.putExtra("equip", infoModelList.get(holder.getAdapterPosition()).getEquip());
                intent.putExtra("espac", infoModelList.get(holder.getAdapterPosition()).getEspace_exter());
                context.startActivity(intent);
            }
        });
        String imageUrl=null;
        imageUrl = studentModel.getImage();
        Glide.with(context).load(imageUrl).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return infoModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView recCard;
        TextView prix , type , size , nb_pieces,nb_chambre,Localition ,description,espace_exter,Accees,equip,annexes ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            prix = itemView.findViewById(R.id.prix);
            type = itemView.findViewById(R.id.type);
            size = itemView.findViewById(R.id.size);
            nb_pieces = itemView.findViewById(R.id.nb_pieces);
            nb_chambre = itemView.findViewById(R.id.nb_chambre);
            Localition = itemView.findViewById(R.id.localition);
            recCard = itemView.findViewById(R.id.card);


        }
    }
}
