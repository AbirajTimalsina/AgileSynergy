package com.example.agilesynergy.fragments.innerFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.item;

import org.w3c.dom.Text;

public class ItemFragment extends Fragment {

    TextView itemName, itemPrice, itemingredient;
    ImageView itemPicture;

    item item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = getArguments().getParcelable("itemObject");
            String abc= item.getItemname();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        itemName = view.findViewById(R.id.textviewitemname);
        itemPrice = view.findViewById(R.id.textviewitempirce);
        itemingredient = view.findViewById(R.id.textviewitemingredient);
        itemPicture = view.findViewById(R.id.imageviewitempicture);

        itemName.setText(global.item.getItemname());
        return view;
    }
}