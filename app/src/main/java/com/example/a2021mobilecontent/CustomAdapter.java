package com.example.a2021mobilecontent;import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {


    private ArrayList<Caffeine> arrayList;
    private Context context;
    //어댑터에서 액티비티 액션을 가져올 때 context가 필요한데 어댑터에는 context가 없다.
    //선택한 액티비티에 대한 context를 가져올 때 필요하다.

    public CustomAdapter(ArrayList<Caffeine> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    //실제 리스트뷰가 어댑터에 연결된 다음에 뷰 홀더를 최초로 만들어낸다.
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.caffeine_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getPhoto1())
                .into(holder.iv_profile1);
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getPhoto2())
                .into(holder.iv_profile2);
        holder.name1.setText(arrayList.get(position).getName1());
        holder.name2.setText(arrayList.get(position).getName2());
        //holder.tv_cafeName.setText(arrayList.get(position).getCafeName());
        //holder.tv_address.setText(arrayList.get(position).getAddress());
        //holder.tv_phone.setText(arrayList.get(position).getPhone());
        //holder.tv_likeNum.setText(String.valueOf(arrayList.get(position).getLikeNum()));

        Caffeine item = arrayList.get(position);

            // 값 설정 ( set )

            //Here it is simply write onItemClick listener here
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();

                    //Toast.makeText(context, item.getCafeName() +"", Toast.LENGTH_LONG).show();
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
        ImageView iv_profile1;
        ImageView iv_profile2;
        TextView name1;
        TextView name2;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_profile1 = itemView.findViewById(R.id.iv_profile1);
            this.iv_profile2 = itemView.findViewById(R.id.iv_profile2);
            this.name1=itemView.findViewById(R.id.name1);
            this.name2=itemView.findViewById(R.id.name2);
            //this.tv_cafeName = itemView.findViewById(R.id.tv_cafeName);
            //this.tv_address = itemView.findViewById(R.id.tv_address);
            //this.tv_phone = itemView.findViewById(R.id.tv_phone);
            //this.tv_likeNum = itemView.findViewById(R.id.tv_likeNum);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            ViewHolder(View itemView) {
                super(itemView) ;

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = getAdapterPosition() ;
                        if (pos != RecyclerView.NO_POSITION) {
                            // 데이터 리스트로부터 아이템 데이터 참조.
                            Caffeine item = arrayList.get(pos) ;

                            //String a=item.getCafeName();
                            System.out.println(arrayList);

                        }
                    }
                });
            }
        }


    }}