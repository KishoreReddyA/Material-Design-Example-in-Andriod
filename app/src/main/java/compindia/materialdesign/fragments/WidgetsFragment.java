package compindia.materialdesign.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import compindia.materialdesign.R;

/**
 * Created by compindi on 09-11-2017.
 */

public class WidgetsFragment extends Fragment
{
    Spinner spinner;
    String[] items={"select item...","spinner item 1","spinner item 2","spinner item 3","spinner item 4","spinner item 5"};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.widgets_fragment,container,false);
        spinner=view.findViewById(R.id.spinner);
        ArrayAdapter spinAdapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,items);
        spinner.setAdapter(spinAdapter);
        return view;
    }
}
