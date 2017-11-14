package compindia.materialdesign.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import compindia.materialdesign.MainActivity;
import compindia.materialdesign.ModelData;
import compindia.materialdesign.R;
import compindia.materialdesign.ThemeInterface;

/**
 * Created by compindi on 09-11-2017.
 */

public class ThemeFragment extends Fragment {
    ListView listView;
    ArrayList<ModelData> list;
    ThemeInterface themeInterface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.theme_fragment, container, false);
        listView = view.findViewById(R.id.listview);
        listView.setAdapter(new CustomAdapter(getActivity()));
        themeInterface= (ThemeInterface) getContext();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                themeInterface.changeTheme(list.get(i).getThemeStyle());
            }
        });
        return view;
    }

    class CustomAdapter extends BaseAdapter {
        LayoutInflater inflater;

        public CustomAdapter(Context context) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list = new ArrayList<>();
            list.add(new ModelData(R.string.txt_red, R.color.red, R.style.RedTheme));
            list.add(new ModelData(R.string.txt_pink, R.color.pink, R.style.PinkTheme));
            list.add(new ModelData(R.string.txt_purple, R.color.purple, R.style.PurpleTheme));
            list.add(new ModelData(R.string.txt_indigo, R.color.indigo, R.style.IndigoTheme));
            list.add(new ModelData(R.string.txt_blue, R.color.blue, R.style.BlueTheme));
            list.add(new ModelData(R.string.txt_teal, R.color.teal, R.style.TealTheme));
            list.add(new ModelData(R.string.txt_green, R.color.green, R.style.GreenTheme));
            list.add(new ModelData(R.string.txt_orange, R.color.orange, R.style.OrangeTheme));
            list.add(new ModelData(R.string.txt_deep_orange, R.color.deep_orange, R.style.DeepOrangeTheme));
            list.add(new ModelData(R.string.txt_brown, R.color.brown, R.style.BrownTheme));
            list.add(new ModelData(R.string.txt_blue_gray, R.color.blue_gray, R.style.BlueGrayTheme));
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View buttonsView = inflater.inflate(R.layout.theme_button, null);
            Button button = buttonsView.findViewById(R.id.theme_btn);
            button.setText(list.get(i).getBtntxt());
            button.setBackgroundColor(getResources().getColor(list.get(i).getBtnColor()));
            return buttonsView;
        }
    }
}

