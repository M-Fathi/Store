package com.mohammad_fathi.store.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammad_fathi.store.R;
import com.mohammad_fathi.store.model.room.entity.Discount_Items_entity;
import com.mohammad_fathi.store.ui.ProductDetails;
import com.mohammad_fathi.store.viewmodel.Items_ViewModel;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class Adapter_recyclerView_discountItems_FragHome extends RecyclerView.Adapter<Adapter_recyclerView_discountItems_FragHome.MyViewHolder> {

    private Context context;
    private List<Discount_Items_entity> List;
    private Items_ViewModel items_viewModel;
    Discount_Items_entity entity;

    public Adapter_recyclerView_discountItems_FragHome(Items_ViewModel items_viewModel) {
        List=new ArrayList<>();
        this.items_viewModel=items_viewModel;
    }

    public Adapter_recyclerView_discountItems_FragHome(Context context, List<Discount_Items_entity> List) {
        this.List = List;
        this.context = context;
    }

    public Adapter_recyclerView_discountItems_FragHome(Context context) {
        this.context = context;
    }

    public void setList(List<Discount_Items_entity> list){
        this.List=list;
        notifyDataSetChanged();
    }

    //-----------Create RecyclerView  -------------------------------------------------------
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mcontext = parent.getContext(); // این خیلی مهمه که از context مربوط به parent استفاده بشه
        View view = LayoutInflater.from(mcontext).inflate(R.layout.fragment_home_recycler_view_items, null);
        return new MyViewHolder(view);


        //View view = LayoutInflater.from(mcontext).inflate(R.layout.recyclerview_items,parent, false);
        //اگر به اشتباه از روش بالا استفاده گردد RecyclerView خودش رو اپدیت نمی کند
    }

    //-----------Attach entities to RecyclerView -------------------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        entity = List.get(position);

        holder.tv_itemName_Frag_Home_recyclerItems.setText(entity.getItem_name());
        holder.tv_itemDiscount_Frag_Home_recyclerItems.setText(String.valueOf(entity.getDiscount_percent()));
        holder.tv_itemPrice_Frag_Home_recyclerItems.setText(String.valueOf(entity.getItem_price()));
        Picasso.with(context).load(entity.getItem_pic()).into(holder.img_Frag_Home_recyclerItems);
        //holder.img_Frag_Home_recyclerItems.setImageResource(entity.getItem_pic());

        //-----------onClick for RecyclerView _ On whole layout -------------------------------------------------------
        holder.LL_discount_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // --اینجا می توانیم از  view Model استفاده کنیم

                Intent intent=new Intent(view.getContext(), ProductDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("item_id_discount",entity.getItem_id());
                intent.putExtra("class_name","Home_Fragment");
                context.startActivity(intent);// اکتیویتی یا intent جدید باید در همون context  که ایجاد شده استارت بشود

            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_itemName_Frag_Home_recyclerItems, tv_itemDiscount_Frag_Home_recyclerItems, tv_itemPrice_Frag_Home_recyclerItems;
        ImageView img_Frag_Home_recyclerItems;
        LinearLayout LL_discount_item;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_Frag_Home_recyclerItems=itemView.findViewById(R.id.img_Frag_Home_recyclerItems);
            tv_itemName_Frag_Home_recyclerItems = itemView.findViewById(R.id.tv_itemName_Frag_Home_recyclerItems);
            tv_itemDiscount_Frag_Home_recyclerItems = itemView.findViewById(R.id.tv_itemDiscount_Frag_Home_recyclerItems);
            tv_itemPrice_Frag_Home_recyclerItems = itemView.findViewById(R.id.tv_itemPrice_Frag_Home_recyclerItems);
            LL_discount_item= itemView.findViewById(R.id.LL_discount_item);

        }
    }

}
