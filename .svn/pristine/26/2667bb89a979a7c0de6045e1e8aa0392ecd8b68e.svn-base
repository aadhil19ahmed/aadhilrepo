package com.example.aadhilahmed.mapboxdeliveries1.Models;

import com.example.aadhilahmed.mapboxdeliveries1.Adapter.RightExpandableListViewAdapter;

/**
 * Created by aadhil.ahmed on 21-Nov-17.
 */

public class ChildViewModel {
    private String name;
    private boolean checkStatus;

    public ChildViewModel(String name,boolean checkStatus){
        this.name=name;
        this.checkStatus=checkStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ChildViewModel))return false;
        ChildViewModel temp=(ChildViewModel)obj;
        return name.equals(temp.getName());
    }

    @Override
    public int hashCode() {
        return 31*name.hashCode();
    }
}
