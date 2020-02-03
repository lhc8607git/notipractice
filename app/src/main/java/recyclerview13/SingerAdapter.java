package recyclerview13;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.noti.R;

import java.util.ArrayList;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder> {  // SingerAdapter안에 ViewHolder라는 것을 정의할려고 하기 때문에 적어주는 겁니다.
    Context context;

    ArrayList<SingerItem> items = new ArrayList<SingerItem>(); // 데이터 저장장

    OnItemClickListener listener;

    public static interface  OnItemClickListener{
        public void onItemClick(ViewHolder holder, View view, int position);
    }

   public SingerAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // itemXML 셋팅
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       View itemView = inflater.inflate(R.layout.singer_item,parent,false);

       return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int position) {
       SingerItem item = items.get(position);
        viewholder.setItem(item); //viewholder가 데이터를 날 수 있게 됩니다!!★

        viewholder.setOnItemClickListener(listener);

    }

    public void addItem(SingerItem item){
       items.add(item);
    }

    public void additems(ArrayList<SingerItem> items){
       this.items = items;
    }

    public SingerItem getItem(int position){
       return items.get(position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
       this.listener = listener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{  // ViewHolder는 각각의 아이템을 위한 "뷰"를 담고 있을 수가 있습니다. // 뷰가 전달이 됩니다.
        TextView textView;
        TextView textView2;

        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView) {   // ★singer_item.xml이 itemView에 들어가 있다.
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
            textView2 = (TextView) itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        // 넣는거
        public void setItem(SingerItem item){
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
        }

        public void setOnItemClickListener(OnItemClickListener listener){
            this.listener = listener;
        }
    }

}
