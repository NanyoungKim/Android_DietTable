package com.example.diettable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import static android.util.Log.*;

public class FoodActivity  extends FragmentActivity implements MyAdapter.DataTransferInterface {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private MyAdapter mAdap;


    private RecyclerView.LayoutManager layoutManager;
    //private String[] mDataset;
    ArrayList<FoodInfo> foodList = new ArrayList<>();


    ArrayList<Long> f_codeList = new ArrayList<>();
    ArrayList<Long> UniqueFcodeList = new ArrayList<>();
    ArrayList<FoodInfo> UniqueFList = new ArrayList<>();


    Button btn_order;

    ArrayList<FoodInfo> resultList = new ArrayList<>();



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }



    private String getJsonString() {
        String json = "";

        try {
            InputStream is = getAssets().open("recipie_new.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void jsonParsing(String json) {


        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray foodArray = jsonObject.getJSONArray("food");

            for (int i = 0; i < foodArray.length(); i++) {
                //for(int i = 0; i<9921; i++){

                JSONObject foodObject = foodArray.getJSONObject(i);

                FoodInfo food = new FoodInfo();


                //Log.d("food", foodObject.toString());

                food.setFoodCode(foodObject.getLong("요리코드"));
                food.setFoodName(foodObject.getString("요리명"));
                food.setIngredNum(foodObject.getLong("식품번호"));
                food.setIngredName(foodObject.getString("식품명"));
                food.setPortion(foodObject.getDouble("1인량"));


                if (!UniqueFcodeList.contains(food.getFoodCode())) {  //없으면
                    UniqueFcodeList.add(food.getFoodCode());            //중복 없는 요리코드 리스트
                    UniqueFList.add(food);

                }

                foodList.add(food);



               mAdapter = new MyAdapter(UniqueFcodeList, UniqueFList, foodList, FoodActivity.this, btn_order);
                recyclerView.setAdapter(mAdapter);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            resultList = (ArrayList<FoodInfo>) intent.getSerializableExtra("result");

            System.out.println(resultList.size());
            //Toast.makeText(FoodActivity.this, resultList).size( , Toast.LENGTH_SHORT).show();

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_food1);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //버튼 검색 & 연결
        btn_order = (Button)findViewById(R.id.btn_order);



        String j = getJsonString();
        jsonParsing(j);


        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("custom-message"));

         final Intent intent = new Intent(getApplicationContext(), FoodDetailActivity.class);

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼의 이벤트가 감지되었을 때 호출되는 메소드
                //버튼일 클릭되었을 때 하고자하는 작업을 이곳에서 한다.
                //mAdap.choiceFoods();
                //mAdap.getOrderedFList();


                //resultList = mAdap.getOrderedFList();
                mAdap = new MyAdapter(FoodActivity.this);
                recyclerView.setAdapter(mAdap);


                System.out.println(("FList : ??? " + resultList.size()));
                intent.putExtra("orderedFList", (Serializable) resultList);        //여기서 못 받아와서 null 값 보내게 됨.




                System.out.println("보낼 갯수 " + mAdap.getOrderedFList().size());

                //버튼의 이벤트가 감지되었을 때 호출되는 메소드
                //버튼일 클릭되었을 때 하고자하는 작업을 이곳에서 한다.
                //mAdap.choiceFoods();
                //mAdap.getOrderedFList();






                d("보내졌나", intent.toString());


                startActivity(intent);

                Toast.makeText(FoodActivity.this, "선택되었습니다.", Toast.LENGTH_LONG).show();




            }
        });






    }



}

