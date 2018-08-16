package com.example.aadhilahmed.mapboxdeliveries1.ChoroplethAndCheckBox;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by aadhil.ahmed on 26-Oct-17.
 */

public class RangeOfCheckedItems {
    int limits[]=new int[50];

    public int[] limitFinder(int num, List<String> checkedItems){
        int position=0;
        for(int i=0;i<num;i++){
            String s=checkedItems.get(i);
            StringTokenizer st=new StringTokenizer(s,"-");
            limits[position]=Integer.parseInt(st.nextToken());
            limits[++position]=Integer.parseInt(st.nextToken());
            ++position;
        }
        return limits;
    }
}
