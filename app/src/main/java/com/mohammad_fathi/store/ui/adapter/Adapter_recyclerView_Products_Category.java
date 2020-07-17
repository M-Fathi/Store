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
import com.mohammad_fathi.store.model.room.entity.Products;
import com.mohammad_fathi.store.ui.ProductDetails;
import com.mohammad_fathi.store.viewmodel.Items_ViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_recyclerView_Products_Category extends RecyclerView.Adapter<Adapter_recyclerView_Products_Category.MyViewHolder> {

    private Context context;
    private List<Products> List;
    private Items_ViewModel items_viewModel;
    Products entity;

    public Adapter_recyclerView_Products_Category(Items_ViewModel items_viewModel,Context context) {
        List=new ArrayList<>();
        this.items_viewModel=items_viewModel;
        this.context=context;
    }

    public Adapter_recyclerView_Products_Category(Context context, List<Products> List) {
        this.List = List;
        this.context = context;
    }

    public void setList(List<Products> list){
        this.List=list;
        notifyDataSetChanged();
    }

    //-----------Create RecyclerView  -------------------------------------------------------
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context mcontext = parent.getContext(); // این خیلی مهمه که از context مربوط به parent استفاده بشه
        View view = LayoutInflater.from(mcontext).inflate(R.layout.category_recyclerview_items, null);
        return new MyViewHolder(view);

    }

    //-----------Attach entities to RecyclerView -------------------------------------------------------
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        entity = List.get(position);

        holder.tv_cat_brand.setText(entity.getProduct_BrandName());
        holder.tv_cat_name.setText(entity.getProduct_Name_en());
        holder.tv_cat_price.setText(String.valueOf(entity.getProduct_Price())+ "تومان");
        Picasso.with(context).load(entity.getProduct_img_url()).into(holder.rv_category_img);

        //-----------onClick for RecyclerView _ On whole layout -------------------------------------------------------
        holder.LL_category_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // --اینجا می توانیم از  view Model استفاده کنیم

                Intent intent=new Intent(view.getContext(), ProductDetails.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("item_id_product",entity.getProduct_ID());
                intent.putExtra("class_name","Category_Fragment");
                context.startActivity(intent);// اکتیویتی یا intent جدید باید در همون context  که ایجاد شده استارت بشود*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_cat_brand, tv_cat_name, tv_cat_price;
        ImageView rv_category_img;
        LinearLayout LL_category_item;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rv_category_img=itemView.findViewById(R.id.rv_category_img);
            tv_cat_brand = itemView.findViewById(R.id.tv_cat_brand);
            tv_cat_name = itemView.findViewById(R.id.tv_cat_name);
            tv_cat_price = itemView.findViewById(R.id.tv_cat_price);
            LL_category_item= itemView.findViewById(R.id.LL_category_item);

        }
    }



}
