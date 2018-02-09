package ke.mush.recyclerviewanimations.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mush on 2/9/18.
 */

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private int spacing;

    public ItemOffsetDecoration(int itemOffset) {
        spacing  = itemOffset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(spacing, spacing, spacing, spacing);
    }
}
