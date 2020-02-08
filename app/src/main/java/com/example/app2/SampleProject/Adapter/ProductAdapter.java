package com.example.app2.SampleProject.Adapter;

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

import com.example.app2.SampleProject.DetailActivity;
import com.example.app2.SampleProject.Model.Product;
import com.example.app2.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    List<Product> products;
    Context context;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_row, parent, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {
        final Product product = products.get(position);
        holder.txtTitle.setText(product.getTitle());
        holder.txtPrice.setText(product.getPrice() + "تومان");
        holder.txtPprice.setText(product.getPprice() + "تومان");
        holder.id = product.getId();

       // Picasso.get().load(product.getPic()).into(holder.imgIcon);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", product.getId());
                intent.putExtra("img", product.getPic());

                //zamani ke az jaee ghair az activity be jaee digar intent
                // mizanim haman bayad addFlags ro benevisim.
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        CardView parent;
        String id;
        TextView txtTitle;
        TextView txtPprice;
        TextView txtPrice;
        ImageView imgIcon;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = (TextView) itemView.findViewById(R.id.txt_productRow_title);
            txtPprice = (TextView) itemView.findViewById(R.id.txt_productRow_pprice);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_productRow_price);
            imgIcon = (ImageView) itemView.findViewById(R.id.img_productRow_icon);
            parent = (CardView) itemView.findViewById(R.id.card_productRow_parent);

        }
    }
}
