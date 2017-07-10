package yellosoft.movie_star;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YelloSOFT on 09.07.2017.
 */

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Stars> starss;

    DataAdapter(Context context, List<Stars> starss) {
        this.starss = starss;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Stars Stars = starss.get(position);
        holder.imageView.setImageResource(Stars.getImage());
        holder.nameView.setText(Stars.getName());
        holder.floorView.setText(Stars.getfloor());
        holder.ageView.setText(Stars.getage());
    }

        @Override
        public int getItemCount() {
            return starss.size();
        }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView, floorView, ageView;

        ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            nameView = (TextView) view.findViewById(R.id.name);
            floorView = (TextView) view.findViewById(R.id.floor);
            ageView = (TextView) view.findViewById(R.id.age);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Внимание!");
                    builder.setMessage("Удалить выбранного актёра?");
                    builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            starss.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                        }
                    });
                    builder.setNegativeButton("Нет", null);
                    builder.create().show();
                    return false;
                }
            });
        }
    }
}
