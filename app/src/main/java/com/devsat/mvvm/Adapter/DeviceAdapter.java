package com.devsat.mvvm.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.devsat.mvvm.R;
import com.devsat.mvvm.model.DevicesModel;

import java.util.List;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> {

    Context context;
    List<DevicesModel> listLiveData;
    private OnItemClickListener clickListener;

    public DeviceAdapter(Context context, List<DevicesModel> listLiveData) {
        this.context = context;
        this.listLiveData = listLiveData;
    }

    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.device_list_item, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, int position) {

        if (listLiveData.get(position).getPlatform().equalsIgnoreCase("Sercomm G450")) {
            holder.icon.setImageResource(R.drawable.vera_plus_big);
        } else if (listLiveData.get(position).getPlatform().equalsIgnoreCase("Sercomm G550")) {
            holder.icon.setImageResource(R.drawable.vera_secure_big);
        } else
            holder.icon.setImageResource(R.drawable.vera_edge_big);

        holder.name.setText(listLiveData.get(position).getPlatform());
        holder.sn_num.setText(listLiveData.get(position).getPK_Device());

        holder.row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showDelete(holder.getAdapterPosition());
                return true;
            }
        });
    }

    private void showDelete(int adapterPosition) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("Action");
        builder.setMessage("Delete this item ?");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listLiveData.remove(adapterPosition);
                notifyDataSetChanged();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel", (dialog1, which) -> dialog1.dismiss());
        dialog.show();
    }

    @Override
    public int getItemCount() {
        return listLiveData.size();
    }

    public interface OnItemClickListener{
        void onClick(View view,int postition);
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public DevicesModel getItemAt(int position){
        return listLiveData.get(position);
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        ImageView icon;
        TextView name, sn_num;
        CardView row;

        public DeviceViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
            sn_num = itemView.findViewById(R.id.sn_num);

            row=itemView.findViewById(R.id.row);
            row.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v,getAdapterPosition());

        }
    }
}
