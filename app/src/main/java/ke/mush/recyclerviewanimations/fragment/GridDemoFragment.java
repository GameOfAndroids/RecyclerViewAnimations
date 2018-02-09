package ke.mush.recyclerviewanimations.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import ke.mush.recyclerviewanimations.R;
import ke.mush.recyclerviewanimations.model.AnimationItem;

/**
 * Created by mush on 2/9/18.
 */

public class GridDemoFragment extends BaseFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.frag_grid;
    }

    @Override
    protected RecyclerView.Adapter getRecyclerViewAdapter() {
        return new CardAdapter();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager(Context context) {
        return new GridLayoutManager(context, 4);
    }

    @Override
    protected AnimationItem[] getAnimationItems() {
        return new AnimationItem[] {
                new AnimationItem("Slide from bottom", R.anim.grid_layout_animation_from_bottom),
                new AnimationItem("Scale", R.anim.grid_layout_animation_scale),
                new AnimationItem("Scale random", R.anim.grid_layout_animation_scale_random)
        };
    }
}
