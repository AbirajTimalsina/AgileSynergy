package com.example.agilesynergy.fragments.innerFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.agilesynergy.R;
import com.example.agilesynergy.fragments.MenuFragment;
import com.example.agilesynergy.global.global;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ItemFragment extends Fragment implements View.OnClickListener {

    private TextView itemName, itemPrice, itemingredient, txtItemAmount;
    private ImageView itemPicture;
    private ImageButton imageBackButton;
    private Button btnAdd, btnSubtract, btnOrder;
    private Integer Amount;
    private JSONObject itemList = new JSONObject();

    public ItemFragment(){

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
        btnAdd = view.findViewById(R.id.btnadd);
        btnSubtract = view.findViewById(R.id.btnsubtract);
        btnOrder = view.findViewById(R.id.btnorder);
        txtItemAmount = view.findViewById(R.id.txtamount);
        btnAdd.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnOrder.setOnClickListener(this);
        itemName.setText(global.item.getItemname());
        itemPrice.setText(global.item.getItemprice());
        itemingredient.setText(global.item.getItemingredient());
        String imagePath = global.imagePath + global.item.getItempicture();
        Picasso.get().load(imagePath).into(itemPicture);
        imageBackButton = view.findViewById(R.id.backbutton);
        imageBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new MenuFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnadd:
                Amount = Integer.parseInt(txtItemAmount.getText().toString());
                Amount += 1;
                txtItemAmount.setText(Amount.toString());
                return;
            case R.id.btnsubtract:
                Amount = Integer.parseInt(txtItemAmount.getText().toString());
                if (Amount > 0) {
                    Amount -= 1;
                    txtItemAmount.setText(Amount.toString());
                }
                return;
            case R.id.btnorder:
                if (txtItemAmount.getText().toString().equals("0")) {
                    Toast.makeText(getActivity(), "Please Select Amount.", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    itemList.put("itemid", global.item.get_id());
                    itemList.put("itemname", global.item.getItemname());
                    itemList.put("itemprice", global.item.getItemprice());
                    itemList.put("itemamount", txtItemAmount.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                global.ItemLists.add(itemList); //onclick the data is saved to global static variable
                break;
        }
    }
}