package com.example.diettable;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Long> UniqueFoodcodeList;
    private List<FoodInfo> FoodObject;
    private List<FoodInfo> UniqueFList;
    private  Context context;


    private static View.OnClickListener onClickListener;

    private Map<FoodInfo, Boolean> checkedMap = new HashMap<>();
    private ArrayList<FoodInfo> ingredientPerFood = new ArrayList();
    private ArrayList<FoodInfo> ingredientToOrder = new ArrayList<>();
    //private Button btn_order;
    private int menuCnt = 0;
    private boolean onBind;

    private ArrayList<Integer> listItem;




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewFood;
        public CheckBox check_box;




        public MyViewHolder(View v) {
            super(v);
            textViewFood = v.findViewById(R.id.TextView_food);
            check_box = (CheckBox) v.findViewById(R.id.check_box);


        }
    }


    public interface DataTransferInterface{
        //public void setValues(ArrayList<FoodInfo> ingredientToOrder);
    }
    DataTransferInterface dtInterface;
    // Provide a suitable constructor (depend s on the kind of dataset)
    public MyAdapter(List<Long> myDataset, List<FoodInfo> uniqueFlist, List<FoodInfo> myDataset2, Context context, Button btn_order) {

        UniqueFoodcodeList = myDataset;
        FoodObject = myDataset2;
        UniqueFList = uniqueFlist;
        btn_order = btn_order;
        listItem = new ArrayList();
        //onClickListener = onClick;

    }

    public MyAdapter(DataTransferInterface dtInterface){
        this.dtInterface = dtInterface;
    }




    // Create new views (invoked by the layout manager)`
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);

        final MyViewHolder vh = new MyViewHolder(v);
        context=  parent.getContext();

        vh.check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //눌렸나 안 눌렸나를 checkedMap 에 저장한다

                //if(!onBind){
                final int checkBoxPosition = vh.getAdapterPosition();
                FoodInfo food = UniqueFList.get(vh.getAdapterPosition());
                Vector<FoodInfo> vecfood = getGroup(food.getFoodCode());        //체크된 푸드코드의 한 그룹을 벡터에 저장한다.


                Log.d("체크 확인", "체크됨!!!");


//                    for(int i = 0; i<vecfood.size(); i++){  //vecfood 에는 음식 한개에 들어가는 재료들 수만큼 객체 들어가있음
//                        checkedMap.put(vecfood.elementAt(i), isChecked);
//                    }
                    checkedMap.put(food, isChecked);


                    if(isChecked){
                        menuCnt++;

                        for(int i = 0; i<listItem.size(); i++){
                            if(listItem.get(i) == checkBoxPosition){
                                return;
                            }

                        }
                        listItem.add(checkBoxPosition);

                        for(int i = 0; i<vecfood.size(); i++){

                            ingredientPerFood.add(vecfood.elementAt(i));

                            System.out.println("What is Checked? " + vecfood.elementAt(i).getFoodName());
                        }
                        choiceFoods();
                        ingredientPerFood.clear();
                        vecfood.clear();

                    }
                else{


                    for(int i = 0; i<listItem.size(); i++){
                        if(listItem.get(i) == checkBoxPosition){
                            listItem.remove(i);
                            break;
                        }
                    }



                    for(int i = 0; i<vecfood.size(); i++){
                        ingredientToOrder.remove(vecfood.elementAt(i));
                    }
                }


                }

            //}




        });










        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //FoodInfo food = mDataset.get(position);
        //holder.textViewFood.setText(String.valueOf(food.getFoodCode()));

        //FoodInfo food = FoodObject.get(position);
        FoodInfo food = UniqueFList.get(position);

        holder.textViewFood.setText(String.valueOf(UniqueFoodcodeList.get(position)));

        //holder.check_box.setText(food.getFoodName());
        holder.check_box.setText(getFoodName(UniqueFoodcodeList.get(position)));
       // onBind = true;
        boolean isChecked = checkedMap.get(food) == null ? false : checkedMap.get(food);   //그 객체가 체크 됐는지 (발주 넣을 메뉴 : checkedMap.get(food)

        //holder.check_box.setOnCheckedChangeListener(null);




        holder.check_box.setChecked(isChecked);




        //onBind = false;


        Intent intent = new Intent("custom-message");
        intent.putExtra("result", ingredientToOrder);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);




        //holder.textViewFoodName.setText(food.getFoodName());

        /*int ingredNum = food.getIngredNum();
        holder.TextView_ingredNum.setText(String.valueOf(ingredNum));

        String ingredName = food.getIngredName();
        holder.TextView_ingredName.setText(ingredName);

        int portion = food.getPortion();
        holder.TextView_portion.setText(String.valueOf(portion));*/




        //holder.rootView.setTag(position);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {     //음식 종류 수
        return UniqueFoodcodeList == null ? 0 : UniqueFoodcodeList.size();
    }

    //public FoodInfo getFoodInfo(int position){
    //    return mDataset2 != null ? mDataset2.get(position) : null;
   // }


    public String getFoodName(Long fcode){
        String ans = "";
        for(int i = 0; i< FoodObject.size(); i++){
            if(fcode == FoodObject.get(i).getFoodCode()){
                ans =  FoodObject.get(i).getFoodName();
                break;
            }
        }
        return ans;
    }



    public Vector<FoodInfo> getGroup(long fcode){
        Vector<FoodInfo> vecIngred = new Vector();
        vecIngred.removeAllElements();
        boolean chk = false;

        for(int i = 0; i < FoodObject.size(); i++){   //전체 객체 돌면서
            if(fcode == FoodObject.get(i).getFoodCode()){
                chk = true;
                vecIngred.add(FoodObject.get(i));

            }
            else if((fcode != FoodObject.get(i).getFoodCode()) && (chk == true)){
                break;
            }
        }

        return vecIngred;
    }


    public boolean choiceFoods(){

        boolean result = ingredientToOrder.addAll(ingredientPerFood);     //체크된 객체들이 담긴 리스트를 리스트에 넣는다.
//        if(result){
//            System.out.println(ingredientPerFood.get(0));
//            Log.d("추가", ingredientPerFood.get(0).getFoodName());
//            notifyDataSetChanged();
//        }

        System.out.println("몇개 보내져?" + ingredientToOrder.size());

        return result;

    }


    public ArrayList<FoodInfo> getOrderedFList(){

        return this.ingredientToOrder == null ? null : this.ingredientToOrder;
    }



}