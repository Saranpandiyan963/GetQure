package app.saran.getqure;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HospitalDetailAdapter extends RecyclerView.Adapter<HospitalDetailAdapter.ViewHolder> {

    private List mHospital;
    public OnNoteClickListener monNoteClickListener;

    public HospitalDetailAdapter(List hospital,OnNoteClickListener monNoteClickListener) {
        mHospital = hospital;
        this.monNoteClickListener = monNoteClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View HospitalView = inflater.inflate(R.layout.hospital_items,parent,false);
        ViewHolder viewHolder = new ViewHolder(HospitalView,monNoteClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        HospitalDetails hospitalDt = (HospitalDetails) mHospital.get(position);

        TextView HNameView = holder.hName;
        HNameView.setText(hospitalDt.getHospital_Name());
        TextView DNameView = holder.dName;
        DNameView.setText(hospitalDt.getDoctor_Name());
        TextView DescView = holder.descr;
        DescView.setText(hospitalDt.getDescription());



    }

    @Override
    public int getItemCount() {
        return mHospital.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView hName,dName,descr;
        public OnNoteClickListener onNoteClickListener;


        public ViewHolder(@NonNull View itemView, final OnNoteClickListener onNoteClickListener) {
            super(itemView);

            hName = (TextView) itemView.findViewById(R.id.hospitalName);
            dName = (TextView) itemView.findViewById(R.id.doctorName);
            descr = (TextView) itemView.findViewById(R.id.description);
            this.onNoteClickListener = onNoteClickListener;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNoteClickListener.onNoteClick(getAdapterPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }


    public interface OnNoteClickListener{
        void onNoteClick(int position);
    }
}
