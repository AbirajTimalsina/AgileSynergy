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
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ItemFragment extends Fragment {

    private TextView itemName, itemPrice, itemingredient;
    private ImageView itemPicture;
    private item item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //bundle arguments not working and onCreate is not being called.
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

//        ViewGroup.LayoutParams parameter = itemPicture.getLayoutParams();
//        parameter.height=parameter.width;
        itemName.setText(global.item.getItemname());
        itemPrice.setText(global.item.getItemprice());
        itemingredient.setText(global.item.getItemingredient());
        String imagePath= global.imagePath+global.item.getItempicture();
        Picasso.get().load(imagePath).into(itemPicture);;
        return view;
    }
}