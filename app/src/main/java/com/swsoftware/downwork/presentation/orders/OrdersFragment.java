package com.swsoftware.downwork.presentation.orders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.swsoftware.downwork.R;
import com.swsoftware.downwork.data.dto.OrderDto;

import java.util.List;

public class OrdersFragment extends Fragment {

    private final OrdersViewModel ordersViewModel = new OrdersViewModel();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        ProgressBar loading = view.findViewById(R.id.progress);

        SharedPreferences preferences =
                requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);

        ordersViewModel.getOrders(preferences.getInt("userId", -1)).observe(getViewLifecycleOwner(), new Observer<List<OrderDto>>() {
            @Override
            public void onChanged(List<OrderDto> orderList) {
                if (orderList.size() > 0) {
                    OrdersAdapter adapter = new OrdersAdapter(orderList);
                    recyclerView.setAdapter(adapter);
                    loading.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}