package ke.mush.recyclerviewanimations.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ke.mush.recyclerviewanimations.R;

/**
 * Created by mush on 2/9/18.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private static final int ITEM_COUNT = 64;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.row_empty_card, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
