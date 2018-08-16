package com.example.aadhilahmed.mapboxdeliveries1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aadhilahmed.mapboxdeliveries1.ChoroplethAndCheckBox.ChoroplethManipulator;
import com.example.aadhilahmed.mapboxdeliveries1.ChoroplethAndCheckBox.ItemClickListener;
import com.example.aadhilahmed.mapboxdeliveries1.ChoroplethAndCheckBox.RangeOfCheckedItems;
import com.example.aadhilahmed.mapboxdeliveries1.MapboxOperator;
import com.example.aadhilahmed.mapboxdeliveries1.Models.CheckBoxItem;
import com.example.aadhilahmed.mapboxdeliveries1.R;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import static com.example.aadhilahmed.mapboxdeliveries1.Activity.ChoroplethActivity.choroplethMapView;
import static com.example.aadhilahmed.mapboxdeliveries1.Activity.ChoroplethActivity.jsonRawArray;

/**
 * Created by aadhil.ahmed on 25-Oct-17.
 */

public class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxAdapter.ViewHolder>{

    private List<CheckBoxItem> listItems;
    private Context context;
    private int[] selectedRangeArray;
    public List<String> checkedRanges=new ArrayList<>();

    public CheckBoxAdapter(List<CheckBoxItem> listItems,Context context){
        this.listItems=listItems;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v=LayoutInflater.from(parent.getContext())
               .inflate(R.layout.legend_adapter,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.image.setImageResource(listItems.get(position).getImage());
        holder.rangeinfo.setText(listItems.get(position).getRange());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                CheckBox chk=(CheckBox)v;

                if(chk.isChecked())
                {
                    checkedRanges.add(listItems.get(pos).getRange());
                }else if(!chk.isChecked()){
                    checkedRanges.remove(listItems.get(pos).getRange());
                }
                selectedRangeArray=new int[50];
                RangeOfCheckedItems range=new RangeOfCheckedItems();
                selectedRangeArray=range.limitFinder(checkedRanges.size(),checkedRanges);

                try{
                    MapboxOperator.clearMap(choroplethMapView);
                    ChoroplethManipulator manipulator=new ChoroplethManipulator(jsonRawArray,checkedRanges,selectedRangeArray);
                    manipulator.choroplethSetter();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView image;
        public TextView rangeinfo;
        public CheckBox checkBox;

        ItemClickListener itemClickListener;


        public ViewHolder(View itemView) {
            super(itemView);

            image=(ImageView)itemView.findViewById(R.id.colorLegend);
            rangeinfo=(TextView)itemView.findViewById(R.id.textLegend);
            checkBox=(CheckBox)itemView.findViewById(R.id.checkboxLegend);

            checkBox.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener ic){
            this.itemClickListener=ic;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(v,getLayoutPosition());
        }
    }
}


