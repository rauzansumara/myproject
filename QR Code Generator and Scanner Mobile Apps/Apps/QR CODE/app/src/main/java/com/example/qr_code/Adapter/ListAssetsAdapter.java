package com.example.qr_code.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.qr_code.Database.Assets;
import com.example.qr_code.Database.DatabaseHelper;
import com.example.qr_code.R;
import com.example.qr_code.RecyclerView.DetailsAssetsActivity;

import java.util.ArrayList;

public class ListAssetsAdapter extends RecyclerView.Adapter<ListAssetsAdapter.MyViewHolder> {
	// class is defined for storing and representing the data from the database.
	///<summary>
	///the class consists of three attributes.
	///</summary>

    Context context;
    ArrayList<Assets> assetsList;

    DatabaseHelper db;
	
	// ListAssetsAdapter() is a built in mthod defined in 'RecyclerView' package.
	// The function is executed to store data in a temporary memory slot and then to represent it on the screen.
    public ListAssetsAdapter(Context context, ArrayList<Assets> assets) {
        this.context = context;
        this.assetsList = assets;
        db = new DatabaseHelper(context);
    }
	
	// onCreateViewHolder() is a built in method defined in 'RecyclerView' package.
	// This method calls onCreateViewHolder(ViewGroup, int) to create a new RecyclerView.
	// ViewHolder and initializes some private fields to be used by RecyclerView. 
	@NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.assets_list, parent, false);

        return new MyViewHolder(itemview);
    }

	// onBindViewHolder() is a built in method defined in 'RecyclerView' package.
	// Called by RecyclerView to display the data at the specified position. 
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Assets assets = assetsList.get(position);

        holder.tv.setText("Assets name : "+assets.getName());
        holder.tvuri.setText("Amount : "+assets.getAmount());

        holder.rl.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("id", assets.getId());

                Intent intent = new Intent(context, DetailsAssetsActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }
	
	// getItemCount() is a builtin method defined in 'RecyclerView' package.
	// Returns the total number of items in the data set held by the adapter. 
    @Override
    public int getItemCount() {
        return assetsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
		//the class is defined for temporarily storing the data
		///<summary>
		///The class consists of three attributes.
		///</summary>

        LinearLayout rl;
        TextView tv, tvuri;

		// MyViewHolder() constructor is defined the objects 'r1','tv' and 'tvuri' 
        public MyViewHolder(View itemView) {
            super(itemView);

            rl = itemView.findViewById(R.id.rl_assets);
            tv = itemView.findViewById(R.id.assets_name);
            tvuri = itemView.findViewById(R.id.assets_description);
        }
    }
}
