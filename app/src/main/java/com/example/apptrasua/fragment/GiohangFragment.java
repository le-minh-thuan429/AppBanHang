package com.example.apptrasua.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptrasua.Adapter.AdapterGioHangFragment;
import com.example.apptrasua.Comon;
import com.example.apptrasua.GioHang.ThongTinThanhToan;
import com.example.apptrasua.Models.GioHang;
import com.example.apptrasua.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GiohangFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class GiohangFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GiohangFragment() {
        // Required empty public constructor
    }

    AdapterGioHangFragment adapterGioHang;
    RecyclerView listGH;
    public  static TextView soluong, Tong;
    Button HoanTaT;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GiohangFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GiohangFragment newInstance(String param1, String param2) {
        GiohangFragment fragment = new GiohangFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_giohang, container, false);

        listGH=view.findViewById(R.id.listGH);
        HoanTaT=view.findViewById(R.id.HoanTaT);
        soluong=view.findViewById(R.id.soluong);
        soluong.setText("("+Comon.gioHangArrayList.size()+")");
        Tong=view.findViewById(R.id.Tong);
        Tong.setText(Comon.formatMoney(Tong())+" VND");

        adapterGioHang=new AdapterGioHangFragment(Comon.gioHangArrayList, getContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        listGH.setLayoutManager(linearLayoutManager);
        listGH.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();

        HoanTaT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Comon.gioHangArrayList.size()==0){
                    Toast.makeText(getContext(), "Không có sản phẩm nào trong giỏ hàng", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent=new Intent(getContext(), ThongTinThanhToan.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }
    public int Tong(){
        int tong=0;
        for(GioHang e: Comon.gioHangArrayList) {
            tong=tong+e.getTong();
        }
        return tong;
    }
}