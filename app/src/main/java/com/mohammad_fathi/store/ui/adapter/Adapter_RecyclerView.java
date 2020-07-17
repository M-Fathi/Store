package com.mohammad_fathi.store.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.mohammad_fathi.store.R;
import com.mohammad_fathi.store.model.room.entity.Cart_item_entity;
import com.mohammad_fathi.store.ui.ProductDetails;
import com.mohammad_fathi.store.viewmodel.Items_ViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_RecyclerView extends RecyclerView.Adapter<Adapter_RecyclerView.MyViewHolder> {


    private final Context context;
    private List<Cart_item_entity> list;
    private Items_ViewModel items_viewModel;
    Cart_item_entity cart_item_entity;
    private final Adapter_RecyclerView context2 = this;


    public Adapter_RecyclerView(Context context, List<Cart_item_entity> list, Items_ViewModel items_viewModel) {

        this.context = context;
        this.list = list;
        this.items_viewModel = items_viewModel;
    }

    public Adapter_RecyclerView(Context context, Items_ViewModel items_viewModel) {

        this.context = context;
        this.items_viewModel = items_viewModel;
    }


    public void setList(List<Cart_item_entity> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mcontext = parent.getContext(); // این خیلی مهمه که از context مربوط به parent استفاده بشه
        View view = LayoutInflater.from(mcontext).inflate(R.layout.cart_recyclerview_items, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Create an instance from entity class
        cart_item_entity = list.get(position);

        // set item from database to recyclerView items
        holder.tv_recyclerItem_Cart_title.setText(cart_item_entity.getItem_Title());
        holder.tv_recyclerItem_Cart_category.setText((cart_item_entity.getItem_category()));
        holder.tv_recyclerItem_Cart_num.setText(String.valueOf(cart_item_entity.getItem_quantity()));
        holder.tv_recyclerItem_Cart_price.setText(String.valueOf(cart_item_entity.getItem_price()));
        Picasso.with(context).load(cart_item_entity.getItem_picUrl()).into(holder.img_cardView);


        holder.img_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent=new Intent(view.getContext(), ProductDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("item_id_cart",cart_item_entity.getItem_id());
                intent.putExtra("class_name","Cart_Fragment");
                context.startActivity(intent);// اکتیویتی یا intent جدید باید در همون context  که ایجاد شده استارت بشود*/
            }
        });


//---------------------- On Click for Delete button ---------------------------------------
        holder.ll_cart_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items_viewModel.delete(cart_item_entity);
                list = items_viewModel.selectAll_CartItems();
                setList(list);
            }
        });

//---------------------- On Click for Minus and plus ---------------------------------------
        holder.card_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = holder.tv_recyclerItem_Cart_num.getText().toString();
                int num = Integer.parseInt(str);
                num++;
                holder.tv_recyclerItem_Cart_num.setText(String.valueOf(num));


            }
        });
        holder.card_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = holder.tv_recyclerItem_Cart_num.getText().toString();
                int num = Integer.parseInt(str);
                num--;
                holder.tv_recyclerItem_Cart_num.setText(String.valueOf(num));
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        // define widget item in Cart_item
        TextView tv_recyclerItem_Cart_title, tv_recyclerItem_Cart_category, tv_recyclerItem_Cart_num, tv_recyclerItem_Cart_price;
        ImageView img_cardView, card_add;
        ImageButton card_minus;
        LinearLayout ll_cart_delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // initialize widget items
            tv_recyclerItem_Cart_title = itemView.findViewById(R.id.tv_recyclerItem_Cart_title);
            tv_recyclerItem_Cart_category = itemView.findViewById(R.id.tv_recyclerItem_Cart_category1);
            tv_recyclerItem_Cart_num = itemView.findViewById(R.id.tv_recyclerItem_Cart_num);
            tv_recyclerItem_Cart_price = itemView.findViewById(R.id.tv_recyclerItem_Cart_price);
            img_cardView = itemView.findViewById(R.id.img_cardView);
            ll_cart_delete = itemView.findViewById(R.id.ll_cart_delete);
            card_minus = itemView.findViewById(R.id.card_minus);
            card_add = itemView.findViewById(R.id.card_add);

        }
    }


}
