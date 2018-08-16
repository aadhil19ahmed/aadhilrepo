package com.example.aadhilahmed.mapboxdeliveries1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.aadhilahmed.mapboxdeliveries1.MapboxOperator;
import com.example.aadhilahmed.mapboxdeliveries1.Models.ChildViewModel;
import com.example.aadhilahmed.mapboxdeliveries1.Models.Tasks;
import com.example.aadhilahmed.mapboxdeliveries1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.aadhilahmed.mapboxdeliveries1.Activity.ManagerActivity.addressMarkers;
import static com.example.aadhilahmed.mapboxdeliveries1.Activity.ManagerActivity.mapView;
import static com.example.aadhilahmed.mapboxdeliveries1.MyApplicationClass.deliveryDetails;

/**
 * Created by aadhil.ahmed on 15-Nov-17.
 */

public class RightExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private Map<String, List<ChildViewModel>> expandableListDetail;
    public static List<Tasks> checkedTaskList = new ArrayList<>();
    static int checkedBoxesCount;
    private MapboxOperator operator;
    private static List<ChildViewHolder> checkedViewHolders=new ArrayList<>();

    public RightExpandableListViewAdapter(Context context, List<String> expandableListTitle, Map<String,
            List<ChildViewModel>> expandableListDetail,MapboxOperator operator) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
        this.operator=operator;
    }

    @Override
    public int getGroupCount() {
        return expandableListTitle.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return expandableListDetail.get(expandableListTitle.get(groupPosition)).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return expandableListTitle.get(groupPosition);
    }

    @Override
    public ChildViewModel getChild(int groupPosition, int childPosition) {
        return expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = getGroup(groupPosition);
        GroupViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expanded_list_group, null);
            holder = new GroupViewHolder();
            holder.listTitleTextView = (TextView) convertView.findViewById(R.id.txtExpandedListTitle);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        holder.listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewModel expandedListText = getChild(groupPosition, childPosition);
        final ChildViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expanded_list_item, null);
            holder = new ChildViewHolder();
            holder.expandedListTextView = (TextView) convertView.findViewById(R.id.txtExpandedListItem);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.expandeditem_chkbox);
            convertView.setTag(holder);
        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        holder.expandedListTextView.setText(expandedListText.getName());
        if (expandedListText.isCheckStatus()) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChildViewModel model;
                if (holder.checkBox.isChecked()) {
                    checkedBoxesCount++;
                    model = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                    model.setCheckStatus(true);
                    expandableListDetail.get(expandableListTitle.get(groupPosition)).set(childPosition, model);
                    checkedViewHolders.add(holder);
                    notifyDataSetChanged();
                    checkedTaskList.addAll(checkForMatch("" + getChild(groupPosition, childPosition).getName()));
                    operator.drawMarkerMap(mapView,checkedTaskList,context);
                } else {
                    checkedBoxesCount--;
                    if(checkedBoxesCount==0){
                        operator.drawMarkerMap(mapView,deliveryDetails,context);
                    }else {
                        Tasks uncheckedTask=getTaskObject(""+getChild(groupPosition,childPosition).getName());
                        checkedTaskList.remove(uncheckedTask);
                        operator.drawMarker(mapView,uncheckedTask,context);
                    }
                    model = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                    model.setCheckStatus(false);
                    checkedViewHolders.remove(holder);
                    expandableListDetail.get(expandableListTitle.get(groupPosition)).set(childPosition, model);
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }

    private Tasks getTaskObject(String uncheckedField) {
        Tasks task=null;
        for (int i = 0; i < deliveryDetails.size(); i++) {
            if (uncheckedField.equals(deliveryDetails.get(i).getAddress()) ||
                    uncheckedField.equals(deliveryDetails.get(i).getDelivery_type()) ||
                    uncheckedField.equals(deliveryDetails.get(i).getProduct_id()) ||
                    uncheckedField.equals((deliveryDetails.get(i).getProduct())) ||
                    uncheckedField.equals(deliveryDetails.get(i).getOrder_status()))
            {
                task=deliveryDetails.get(i);
            }
        }
        return task;
    }

    private List<Tasks> checkForMatch(String checkedField) {
        List<Tasks> matchFinder = new ArrayList<>();
        for (int i = 0; i < deliveryDetails.size(); i++) {
            if (checkedField.equals(deliveryDetails.get(i).getAddress()) ||
                    checkedField.equals(deliveryDetails.get(i).getDelivery_type()) ||
                    checkedField.equals(deliveryDetails.get(i).getProduct_id()) ||
                    checkedField.equals((deliveryDetails.get(i).getProduct())) ||
                    checkedField.equals(deliveryDetails.get(i).getOrder_status())) {
                if (!checkedTaskList.contains(deliveryDetails.get(i))) {
                    matchFinder.add(deliveryDetails.get(i));
                }
            }else {
                MapboxOperator.clearMarker(addressMarkers.get(i),mapView);
            }
        }
        return matchFinder;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void clearChecks() {
        if (checkedBoxesCount != 0) {
            for (List<ChildViewModel> value : expandableListDetail.values()) {
                for (ChildViewModel sample : value) {
                    sample.setCheckStatus(false);
                }
            }
            notifyDataSetChanged();
            checkedViewHolders.clear();
            operator.drawMarkerMap(mapView, deliveryDetails, context);
        }
    }

    public class GroupViewHolder {
        TextView listTitleTextView;
    }

    public class ChildViewHolder {
        TextView expandedListTextView;
        CheckBox checkBox;
    }

}
