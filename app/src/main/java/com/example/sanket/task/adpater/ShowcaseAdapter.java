package com.example.sanket.task.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sanket.task.R;
import com.example.sanket.task.models.Showcase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanket on 04/06/17.
 */

public class ShowcaseAdapter extends RecyclerView.Adapter<ShowcaseAdapter.V>{
    private List<Showcase> l =new ArrayList<>();
    private LayoutInflater layout;
    private Context mcontext;

    static class V extends RecyclerView.ViewHolder
    {
        TextView id,title,description,year;
        public V(View itemView)
        {
            super(itemView);
            id = (TextView)itemView.findViewById(R.id.showcase_id);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
            year = (TextView)itemView.findViewById(R.id.year);
        }
    }

    public ShowcaseAdapter(Context context, List<Showcase> l)
    {
        this.mcontext = context;
        layout=LayoutInflater.from(context);
        this.l=l;
        notifyItemRangeChanged(0,l.size());
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layout.inflate(R.layout.item_layout,parent,false);
        V viewHolder=new V(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(V holder, int position)
    {
        Showcase current =l.get(position);
        holder.id.setText(mcontext.getString(R.string.id,String.valueOf(current.getId())));
//        holder.id.setText(""+current.getId());
        holder.title.setText(mcontext.getString(R.string.title,current.getTitle()));
        holder.description.setText(mcontext.getString(R.string.description,current.getDescription()));
        holder.year.setText(mcontext.getString(R.string.year,current.getYear()));

    }

    @Override
    public int getItemCount()
    {
        return l.size();
    }

}
