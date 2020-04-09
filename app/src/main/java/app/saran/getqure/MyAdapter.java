package app.saran.getqure;

import android.app.LauncherActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.ListIterator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<itemView> listitem;
    private Context context;

    public MyAdapter(List<itemView> listitem, Context context) {
        this.listitem = listitem;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return  new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        itemView listItem = listitem.get(position);

        holder.Heading.setText(listItem.getHeading());
        holder.description.setText(listItem.getDescription());

    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Heading;
        private TextView description;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Heading = (TextView) itemView.findViewById(R.id.Heading);
            description = (TextView) itemView.findViewById(R.id.Description);


        }
    }


}


