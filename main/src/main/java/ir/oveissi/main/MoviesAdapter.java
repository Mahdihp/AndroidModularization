package ir.oveissi.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.oveissi.core.pojo.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    ItemClickListener itemClickListener;
    private Context mContext;
    private List<Movie> itemsData;

    MoviesAdapter(Context mContext, List<Movie> itemsData) {
        this.mContext = mContext;
        this.itemsData = itemsData;
    }

    void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    void clear() {
        this.itemsData.clear();
        notifyDataSetChanged();
    }

    void addItem(Movie post) {
        this.itemsData.add(post);
        notifyItemInserted(this.itemsData.size() - 1);
    }

    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_movie, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Movie tempItem = itemsData.get(position);
        viewHolder.bind(tempItem);
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public interface ItemClickListener {
        void ItemClicked(int position, Movie item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvMovieTitle;
        ImageView imPoster;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tvMovieTitle = itemLayoutView.findViewById(R.id.tvMovieTitle);
            imPoster = itemLayoutView.findViewById(R.id.imPoster);
            itemLayoutView.setOnClickListener(this);

        }

        void bind(Movie item) {
            tvMovieTitle.setText(item.title);
            Picasso.with(mContext)
                    .load(item.poster)
                    .into(imPoster);
        }


        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.ItemClicked(getAdapterPosition(),
                        itemsData.get(getAdapterPosition()));
            }

        }
    }
}