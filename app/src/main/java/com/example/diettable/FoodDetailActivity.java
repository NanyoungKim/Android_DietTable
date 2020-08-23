package com.example.diettable;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodDetailActivity extends Activity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter2;
    private RecyclerView.LayoutManager layoutManager;
    private FoodInfo food;
    private TextView TextView_ingredNum, TextView_ingredName, TextView_portion;

     ArrayList<FoodInfo> orderedFoodList = new ArrayList<>();

     Map<Long, Double> ingredientMap = new HashMap<>();




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_detail);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view2);

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




        setComp();
        getFoodDetail();
        sumIngred();
        setFoodInfo();


        mAdapter2 = new MyAdapter2(ingredientMap);
        recyclerView.setAdapter(mAdapter2);


    }


    //기본 컴포넌트 셋팅
    public void setComp() {
        TextView_ingredNum = findViewById(R.id.TextView_ingredNum);
        //TextView_ingredName = findViewById(R.id.TextView_ingredName);
        TextView_portion = findViewById(R.id.TextView_portion);






    }


    //이전 액티비티에서 받아오는 인텐트
    public void getFoodDetail() {
        Intent intent = getIntent();

        orderedFoodList = (ArrayList<FoodInfo>) intent.getSerializableExtra("orderedFList");

        Log.d("주문됐나", orderedFoodList.toString());


//        if(intent != null) {
//            Bundle bld = intent.getExtras();
//
//            Object obj = bld.get("food");
//            if(obj != null && obj instanceof FoodInfo) {
//                this.food = (FoodInfo) obj;
//            }
//        }
    }


    //음식 재료 주문할 양 계산하기
    public void sumIngred(){

        for(int i =0; i<orderedFoodList.size(); i++){


            if(ingredientMap.containsKey(orderedFoodList.get(i).getIngredNum())){       //이미 있으면
                double curPortion = ingredientMap.get(orderedFoodList.get(i).getIngredNum());



                ingredientMap.replace(orderedFoodList.get(i).getIngredNum(), curPortion + orderedFoodList.get(i).getPortion());
                System.out.println(i + "번째는 : " + orderedFoodList.get(i).getIngredNum() + "/ curPortion: " + curPortion + "/ por : " + orderedFoodList.get(i).getPortion());




            }
            else{   //없으면
                ingredientMap.put(orderedFoodList.get(i).getIngredNum(), orderedFoodList.get(i).getPortion());
                System.out.println(i + "번째 : " + orderedFoodList.get(i).getIngredNum() +  "/ por : " + orderedFoodList.get(i).getPortion());
            }

        }





    }







    //이전 액티비티에서 받아오는 인텐트에서 정보를 확인하여 뉴스표시
    public void setFoodInfo() {





        if(this.food != null) {

            Long ingredNum = this.food.getIngredNum();
            if(ingredNum != null){
                TextView_ingredNum.setText(String.valueOf(ingredNum));
            }

            String ingredName = this.food.getIngredName();
            if(ingredName != null){
                TextView_ingredName.setText(ingredName);
            }

            Double portion = this.food.getPortion();
            if(portion != null){
                TextView_portion.setText(Double.toString(portion));
            }


        }
    }



}
