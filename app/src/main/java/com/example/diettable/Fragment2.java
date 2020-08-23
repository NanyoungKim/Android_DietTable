package com.example.diettable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

public class Fragment2 extends Fragment {

    private Fragment1ViewModel mViewModel;

    private View view;

    private Button btn1, btn2, btn3, btn4, btn5,btn6,btn7,btn8,btn9,btn10, btn11, btn12, btn13, btn14, btn15;

    public static Fragment2 newInstance() {
        return new Fragment2();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment2_fragment, container, false);




//        btn1 = view.findViewById(R.id.btn_category1);
//        btn2= view.findViewById(R.id.btn_category2);
//        btn3 = view.findViewById(R.id.btn_category3);
//        btn4 = view.findViewById(R.id.btn_category4);
//        btn5 = view.findViewById(R.id.btn_category5);
//        btn6 = view.findViewById(R.id.btn_category6);
//        btn7 = view.findViewById(R.id.btn_category7);
//        btn8 = view.findViewById(R.id.btn_category8);
//        btn9 = view.findViewById(R.id.btn_category9);
//        btn10 = view.findViewById(R.id.btn_category10);
//        btn11 = view.findViewById(R.id.btn_category11);
//        btn12 = view.findViewById(R.id.btn_category12);
//        btn13 = view.findViewById(R.id.btn_category13);
//        btn14 = view.findViewById(R.id.btn_category14);
//        btn15 = view.findViewById(R.id.btn_category15);



//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();   //뭔가 담을 수 있는 꾸러미
//
//                bundle.putString("fromFrag1", "프래그먼트1");
//                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//
//            }
//        });



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(Fragment1ViewModel.class);
        // TODO: Use the ViewModel
    }

}
