package com.example.PickBeforeGo.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.PickBeforeGo.R;
import com.example.PickBeforeGo.components.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductRVAdapter extends RecyclerView.Adapter<ProductRVAdapter.Viewholder> {

    private final Context context;
    private final ArrayList<Product> productArrayList;
    private final ClickListener clickListener;

    //constructor
    public ProductRVAdapter(@NonNull Context context, ArrayList<Product> productArrayList, ClickListener clickListener) {
        this.context = context;
        this.productArrayList = productArrayList;
        this.clickListener = clickListener;

    }

    @NonNull
    @Override
    public ProductRVAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //to inflate layout for each item of recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent,false);
        Viewholder viewholder = new Viewholder(view);
        viewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewholder.getAdapterPosition();
                if (clickListener != null) {
                    clickListener.onItemClick(position, viewholder.productName, viewholder.imageUrl, viewholder.productDescription, viewholder.productID, String.valueOf(viewholder.productPrice), viewholder.isFavourite, viewholder.inStock, viewholder.isPromo, viewholder.discountPercent, viewholder.restockTime);
                }
            }
        });
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRVAdapter.Viewholder holder, int position) {
        //set data to textview, imageview of each card layout
        if (position == productArrayList.size() - 1) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
            params.bottomMargin = 100;
            holder.itemView.setLayoutParams(params);
        }
        Product product = productArrayList.get(position);
        holder.productName = product.getProductName();
        holder.productPrice = product.getPrice();
        holder.productDescription = product.getDescription();
        holder.productID = product.getProductID();
        holder.imageUrl = product.getImageURL();
        Picasso.get().load(holder.imageUrl).placeholder(R.drawable.placeholder_product_pic).into(holder.imgProduct);
        holder.txtProductName.setText(holder.productName);
        holder.txtPrice.setText("$" + (holder.productPrice));
        holder.isFavourite = product.getIsFavourite();
        holder.inStock = product.getInStock();
        holder.isPromo = product.getIsPromo();
        if (!holder.inStock) {
            holder.txtPromoStock.setText("No Stock");
            holder.txtPromoStock.setBackgroundColor(context.getResources().getColor(R.color.noStockRed));
        }
        else if (holder.isPromo) {
            holder.txtPromoStock.setText("Promo " + Math.round(product.getDiscountPercent()) + "%");
            holder.txtPromoStock.setBackgroundColor((context.getResources().getColor(R.color.inStockGreen)));
        }
        else if (holder.inStock && !holder.isPromo){
            holder.txtPromoStock.setVisibility(View.INVISIBLE);
            holder.txtPromoStock.setVisibility(View.GONE);
        }
        holder.discountPercent = product.getDiscountPercent();
        holder.restockTime = product.getNextRestockTime();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private final ImageView imgProduct;
        private final TextView txtProductName;
        private final TextView txtPrice;
        private final TextView txtPromoStock;
        private String productName;
        private String imageUrl;
        private String productDescription;
        private String productID;
        private String productPrice;
        private Boolean isFavourite, inStock, isPromo;
        private Double discountPercent;
        private String restockTime;

        public Viewholder(@NonNull View itemView){
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtPrice = itemView.findViewById(R.id.txtProductPrice);
            txtPromoStock = itemView.findViewById(R.id.txtPromoStock);
        }
    }

    public interface ClickListener {
        void onItemClick(int position, String productName, String imageUrl, String description, String productID, String productPrice, Boolean isFavourite, Boolean inStock, Boolean isPromo, Double discountPercent, String restockTime);

    }
}