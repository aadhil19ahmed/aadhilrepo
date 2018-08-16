package com.example.aadhilahmed.mapboxdeliveries1.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aadhilahmed.mapboxdeliveries1.Models.Tasks;
import com.example.aadhilahmed.mapboxdeliveries1.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by aadhil.ahmed on 24-Oct-17.
 */

public class DeliveryListAdapter extends RecyclerView.Adapter<DeliveryListAdapter.ViewHolder>{

    private List<Tasks> deliveryTasks;
    private Context context;

    public DeliveryListAdapter(List<Tasks> deliveryTasks,Context context){
        this.deliveryTasks=deliveryTasks;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.row_task_item3,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.product_id.setText(deliveryTasks.get(position).getProduct_id());
        holder.productName.setText(deliveryTasks.get(position).getProduct());
        holder.orderId.setText(deliveryTasks.get(position).getOrder_id());
        holder.address.setText(deliveryTasks.get(position).getAddress());
        holder.deliveryStatus.setText(deliveryTasks.get(position).getOrder_status());
        if(deliveryTasks.get(position).getOrder_status().equals("undelivered")){
            holder.deliveryStatus.setTextColor(context.getResources().getColor(R.color.color_dark_red));
        }else if(deliveryTasks.get(position).getOrder_status().equals("delivered")){
            holder.deliveryStatus.setTextColor(context.getResources().getColor(R.color.color_dark_green));
        }else {
            holder.deliveryStatus.setTextColor(context.getResources().getColor(R.color.color_yellow));
        }

        holder.deliveryStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!deliveryTasks.get(position).getOrder_status().equals("delivered")){
                    final AlertDialog.Builder adb=new AlertDialog.Builder(context);
                    adb.setTitle("Confirm Order Status");
                    adb.setMessage("Are you sure the order is complete");
                    adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            holder.deliveryStatus.setBackgroundColor(context.getResources().getColor(R.color.color_dark_green));
                            holder.deliveryStatus.setText("delivered");
                        }
                    });

                    adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    adb.show();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return deliveryTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView product_id;
        public TextView productName;
        public TextView orderId;
        public TextView deliveryStatus;
        public TextView address;


        public ViewHolder(View itemView) {
            super(itemView);

            product_id=(TextView)itemView.findViewById(R.id.lblProductIdData);
            productName=(TextView)itemView.findViewById(R.id.lblNameData);
            orderId=(TextView)itemView.findViewById(R.id.lblOrderIdStatus);
            address=(TextView)itemView.findViewById(R.id.lblAddressData);
            deliveryStatus=(TextView)itemView.findViewById(R.id.lblStatusData);



        }

    }
}
