package com.example.a2021mobilecontent.adaptr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a2021mobilecontent.R;
import com.example.a2021mobilecontent.data.Usercaffeine;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomViewHolder> {
    private ArrayList<Usercaffeine> arrayList;
    private Context context;


    public UserAdapter(ArrayList<Usercaffeine> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    //실제 리스트뷰가 어댑터에 연결된 다음에 뷰 홀더를 최초로 만들어낸다.
    public UserAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.useritem, parent, false);
        UserAdapter.CustomViewHolder holder = new UserAdapter.CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserAdapter.CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getPhoto())
                .into(holder.iv_profile);

        holder.name.setText(arrayList.get(position).getName());
        holder.amount.setText(arrayList.get(position).getAmount()+"");
        Usercaffeine item = arrayList.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();



            }
        });

        holder.iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });



    }



    class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            // 레이아웃 객체화 findViewById
        }
    }

    @Override
    public int getItemCount() {
        // 삼항 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView name;
        TextView amount;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile = itemView.findViewById(R.id.iv_profile);
            this.name=itemView.findViewById(R.id.name);
            this.amount=itemView.findViewById(R.id.amount);


        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ViewHolder(View itemView) {
                super(itemView);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            // 데이터 리스트로부터 아이템 데이터 참조.
                            Usercaffeine item = arrayList.get(pos);


                            System.out.println(arrayList);

                        }
                    }
                });

            }
        }


    }
}

