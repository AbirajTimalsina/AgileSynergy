package com.example.agilesynergy;

import com.example.agilesynergy.classes.LoginBLL;
import com.example.agilesynergy.classes.userPurchase;
import com.example.agilesynergy.fragments.innerFragments.checkoutFragment;
import com.example.agilesynergy.global.global;

import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class purchaseTest {


    @Test
    public void testingPurchase() {

//        //CURRENTLY NOT WORKING DUE TO MOCK. IN build.gradle of app it is said to return default for json which is null.
//        LoginBLL loginBLL = new LoginBLL("098765432", "54321");
//        loginBLL.checkUser()r;
        final JSONObject itemList = new JSONObject();
        try {
            itemList.put("itemname", "Pizza");
            itemList.put("itemprice", "29.99");
        } catch (Exception e) {
            e.printStackTrace();
        }
        global.ItemLists.add(itemList);
        assertTrue(new userPurchase().userPurchaseFood());
    }

}
