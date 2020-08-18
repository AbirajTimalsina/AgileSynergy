package com.example.agilesynergy;

import android.view.View;
import android.widget.Button;

import com.example.agilesynergy.fragments.innerFragments.checkoutFragment;
import com.example.agilesynergy.global.global;

import org.json.JSONObject;
import org.junit.Test;


import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class OrderingTest {
    boolean expected = true;
    boolean actual = false;

    @Test
    public void orderingtest() {
        JSONObject itemList = new JSONObject();
        try {
            itemList.put("itemid", "1234561");
            itemList.put("itemname", "Pizza");
            itemList.put("itemprice", "29.99");
            itemList.put("itemamount", "2");
        } catch (Exception e) {
            e.printStackTrace();
        }
        global.ItemLists.add(itemList);
        assertNotNull(new checkoutFragment());
    }

}
