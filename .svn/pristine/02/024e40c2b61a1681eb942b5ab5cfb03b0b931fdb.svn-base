package com.example.aadhilahmed.mapboxdeliveries1.ChoroplethAndCheckBox;

import com.example.aadhilahmed.mapboxdeliveries1.Models.CheckBoxItem;
import com.example.aadhilahmed.mapboxdeliveries1.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by aadhil.ahmed on 25-Oct-17.
 */

public class PopulateCheckboxList {

        static List<String>  adapterLegend= Arrays.asList("0-10","10-20","20-50","50-100","100-200","200-500","500-1000","1000-10000");
        static int[] images={R.drawable.colormap1,
                R.drawable.colormap2,
                R.drawable.colormap3,
                R.drawable.colormap4,
                R.drawable.colormap5,
                R.drawable.colormap6,
                R.drawable.colormap7,
               R.drawable.colormap8 };

        public static List<CheckBoxItem> fetchAdapterList(){
            List<CheckBoxItem> listSaver=new ArrayList<>();
            for(int i=0;i<8;i++){
                CheckBoxItem item=new CheckBoxItem(images[i],adapterLegend.get(i));
                listSaver.add(item);
            }
            return listSaver;
        }


}
