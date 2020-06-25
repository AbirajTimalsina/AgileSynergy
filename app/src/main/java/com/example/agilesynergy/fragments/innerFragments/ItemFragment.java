package com.example.agilesynergy.fragments.innerFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agilesynergy.R;
import com.example.agilesynergy.global.global;
import com.example.agilesynergy.models.item;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ItemFragment extends Fragment implements View.OnClickListener{

    private TextView itemName, itemPrice, itemingredient, txtItemAmount;
    private ImageView itemPicture;
    private item item;
    private Button btnAdd,btnSubtract, btnOrder;

    private Integer Amount;

    JSONArray itemArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //bundle arguments not working and onCreate is not being called.
        if (getArguments() != null) {
            item = getArguments().getParcelable("itemObject");
            String check= item.getItemname();
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
        btnAdd = view.findViewById(R.id.btnadd);
        btnSubtract = view.findViewById(R.id.btnsubtract);
        btnOrder = view.findViewById(R.id.btnorder);
        txtItemAmount=view.findViewById(R.id.txtamount);

        btnAdd.setOnClickListener(this);
        btnSubtract.setOnClickListener(this);
        btnOrder.setOnClickListener(this);

//        ViewGroup.LayoutParams parameter = itemPicture.getLayoutParams();
//        parameter.height=parameter.width;
        itemName.setText(global.item.getItemname());
        itemPrice.setText(global.item.getItemprice());
        itemingredient.setText(global.item.getItemingredient());
        String imagePath= global.imagePath+global.item.getItempicture();
        Picasso.get().load(imagePath).into(itemPicture);;
        return view;
    }
    private JSONObject itemList=new JSONObject();
    private JSONArray itemLists= new JSONArray();;
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnadd:
                Amount=Integer.parseInt(txtItemAmount.getText().toString());
                Amount+=1;
                txtItemAmount.setText(Amount.toString());
                return;
            case R.id.btnsubtract:
                Amount=Integer.parseInt(txtItemAmount.getText().toString());
                Amount-=1;
                txtItemAmount.setText(Amount.toString());
                return;

            case R.id.btnorder:

                try {
                    itemList.put("itemid" , global.item.get_id());
                    itemList.put("itemname",global.item.getItemname());
                    itemList.put("itemprice",global.item.getItemprice());
                    itemList.put("itemamount",txtItemAmount.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                itemLists.put(itemList);
                break;
        }
    }
}