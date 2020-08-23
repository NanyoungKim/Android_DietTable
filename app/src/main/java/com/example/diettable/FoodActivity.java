package com.example.diettable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
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


    Button btn_order, btn1, btn2, btn3, btn4, btn5,btn6,btn7,btn8,btn9,btn10, btn11, btn12, btn13, btn14, btn15;

    ArrayList<FoodInfo> resultList = new ArrayList<>();



    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }



    private String getJsonString(int category) {
        String json = "";
        String fileName = "recipie_category" + String.valueOf(category) + "json";


        try {
            InputStream is = getAssets().open(fileName);
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
        //setContentView(R.layout.fragment1_fragment);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view1);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        btn1 = (Button)findViewById(R.id.btn_category1);
        btn2= (Button)findViewById(R.id.btn_category2);
        btn3 = (Button)findViewById(R.id.btn_category3);
        btn4 = (Button)findViewById(R.id.btn_category4);
        btn5 = (Button)findViewById(R.id.btn_category5);
        btn6 = (Button)findViewById(R.id.btn_category6);
        btn7 = (Button)findViewById(R.id.btn_category7);
        btn8 = (Button)findViewById(R.id.btn_category8);
        btn9 = (Button)findViewById(R.id.btn_category9);
        btn10 = (Button)findViewById(R.id.btn_category10);
        btn11 = (Button)findViewById(R.id.btn_category11);
        btn12 = (Button)findViewById(R.id.btn_category12);
        btn13 = (Button)findViewById(R.id.btn_category13);
        btn14 = (Button)findViewById(R.id.btn_category14);
        btn15 = (Button)findViewById(R.id.btn_category15);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment1 = new Fragment1();
                transaction.replace(R.id.frame, fragment1);
                String j = getJsonString(1);
                jsonParsing(j);
                transaction.commit();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment2 = new Fragment2();
                transaction.replace(R.id.frame, fragment2);
                String j = getJsonString(2);
                jsonParsing(j);
                transaction.commit();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment3 = new Fragment3();
                transaction.replace(R.id.frame, fragment3);
                String j = getJsonString(3);
                jsonParsing(j);
                transaction.commit();

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment4 = new Fragment4();
                transaction.replace(R.id.frame, fragment4);
                transaction.commit();

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment5 = new Fragment5();
                transaction.replace(R.id.frame, fragment5);
                transaction.commit();

            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment6 = new Fragment6();
                transaction.replace(R.id.frame, fragment6);
                transaction.commit();

            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment7 = new Fragment7();
                transaction.replace(R.id.frame, fragment7);
                transaction.commit();

            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment8 = new Fragment8();
                transaction.replace(R.id.frame, fragment8);
                transaction.commit();

            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment9 = new Fragment9();
                transaction.replace(R.id.frame, fragment9);
                transaction.commit();

            }
        });
        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment10 = new Fragment10();
                transaction.replace(R.id.frame, fragment10);
                transaction.commit();

            }
        });
        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment11 = new Fragment11();
                transaction.replace(R.id.frame, fragment11);
                transaction.commit();

            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment12 = new Fragment12();
                transaction.replace(R.id.frame, fragment12);
                transaction.commit();

            }
        });
        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment13 = new Fragment13();
                transaction.replace(R.id.frame, fragment13);
                transaction.commit();

            }
        });
        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment14 = new Fragment14();
                transaction.replace(R.id.frame, fragment14);
                transaction.commit();

            }
        });
        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment15 = new Fragment15();
                transaction.replace(R.id.frame, fragment15);
                transaction.commit();

            }
        });

















        //버튼 검색 & 연결
        btn_order = (Button)findViewById(R.id.btn_order);



//        String j = getJsonString();
//        jsonParsing(j);


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

