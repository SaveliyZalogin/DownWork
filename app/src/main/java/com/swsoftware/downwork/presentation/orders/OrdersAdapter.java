package com.swsoftware.downwork.presentation.orders;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swsoftware.downwork.R;
import com.swsoftware.downwork.data.dto.OrderDto;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    private final List<OrderDto> orders;

    public OrdersAdapter(List<OrderDto> orders) {
        this.orders = orders;
    }
    static public class OrdersViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView fromUserName;
        TextView state;
        TextView price;

        public OrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fromUserName = itemView.findViewById(R.id.fromUser);
            state = itemView.findViewById(R.id.state);
            price = itemView.findViewById(R.id.price);
        }
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_preview_layout, parent, false);
        return new OrdersViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull OrdersViewHolder holder, int position) {
        OrderDto order = orders.get(position);
        holder.title.setText(order.getProject().getTitle());
        holder.fromUserName.setText(order.getFromUser().getUserName());
        holder.state.setText((order.getState() == 0) ? "В процессе" : "Завершен");
        holder.price.setText(order.getProject().getPrice() + "₽");
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
