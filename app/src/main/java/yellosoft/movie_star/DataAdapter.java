package yellosoft.movie_star;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by YelloSOFT on 09.07.2017.
 */

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Stars> starss;
    Context context;
    DataAdapter(Context context, List<Stars> starss) {
        this.context = context;
        this.starss = starss;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Stars Stars = starss.get(position);
        //Попытка выделить нужный объект
        if (starss.get(position).getclick())
        {
            Log.d("tag", starss.get(position).getclick().toString());
            holder.lila.setBackgroundColor(Color.parseColor("#FFE599"));
        }
        holder.imageView.setImageBitmap(loadBitmapFromResource(context.getResources(),Stars.getImage(),100,100));
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
        final TextView nameView;
        final TextView floorView;
        final TextView ageView;
        final LinearLayout lila;

        ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            nameView = (TextView) view.findViewById(R.id.name);
            floorView = (TextView) view.findViewById(R.id.floor);
            ageView = (TextView) view.findViewById(R.id.age);
            lila = (LinearLayout) view.findViewById(R.id.lila2);

            view.setOnLongClickListener(new View.OnLongClickListener() {
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
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    starss.get(getAdapterPosition()).setclick(true);
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
    //Попытка оптимизации
    private int calculateInSampleSize(
        BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }
    Bitmap bitmap;
    public Bitmap loadBitmapFromResource(Resources res, int resId,
                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, (int) (reqWidth * Resources.getSystem().getDisplayMetrics().density), (int) (reqHeight * Resources.getSystem().getDisplayMetrics().density));

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        bitmap = BitmapFactory.decodeResource(res, resId, options);
        return bitmap;
    }
}

