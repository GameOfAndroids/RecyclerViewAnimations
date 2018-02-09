package ke.mush.recyclerviewanimations.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ke.mush.recyclerviewanimations.R;
import ke.mush.recyclerviewanimations.model.AnimationItem;
import ke.mush.recyclerviewanimations.recyclerview.GridRecyclerView;
import ke.mush.recyclerviewanimations.recyclerview.ItemOffsetDecoration;

/**
 * Created by mush on 2/9/18.
 */

public abstract class BaseFragment extends Fragment {
    private final Handler handler = new Handler();

    private AnimationItem[] animationItems;
    private AnimationItem selectedAnimationItem;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.spinner)
    Spinner spinner;
    private Activity mActivity;

    /**
     * Get the layout to use for the demo
     *
     * @return the resource id
     */
    protected abstract int getLayoutResId();

    /**
     * Get the adapter you wanna use for the RecyclerView
     *
     * @return recyclerViewAdapter
     */
    protected abstract RecyclerView.Adapter getRecyclerViewAdapter();

    /**
     * Get the layout manager to use with the RecyclerView
     *
     * @param context the context
     * @return the layout manager
     */
    protected abstract RecyclerView.LayoutManager getLayoutManager(Context context);

    /**
     * Get the array of animations to choose from
     *
     * @return the array
     */
    protected abstract AnimationItem[] getAnimationItems();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, baseView);
        return baseView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        try {
            animationItems = getAnimationItems();
            selectedAnimationItem = animationItems[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

        setupRecyclerView();
        setUpSpinner();
        runLayoutAnimations(recyclerView, selectedAnimationItem);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mActivity == null) {
            mActivity = (Activity) context;
        }
    }

    @OnClick(R.id.btn_reload)
    void btnReloadClicked() {
        runLayoutAnimations(recyclerView, selectedAnimationItem);
    }

    ;


    private void setupRecyclerView() {
        final Context context = recyclerView.getContext();
        final int spacing = getResources().getDimensionPixelOffset(R.dimen.default_spacing_small);
        recyclerView.setLayoutManager(getLayoutManager(context));
        recyclerView.setAdapter(getRecyclerViewAdapter());
        recyclerView.addItemDecoration(new ItemOffsetDecoration(spacing));
    }

    private void setUpSpinner() {
        final List<String> animationItemNames = new ArrayList<>();
        for (final AnimationItem animationItem : animationItems) {
            animationItemNames.add(animationItem.getName());
        }

        final Context context = recyclerView.getContext();
        //Apply another theme to make the recyclerview text visible
        final ContextThemeWrapper themedContext = new ContextThemeWrapper(context, R.style.Theme_AppCompat);
        spinner.setAdapter(new ArrayAdapter<>(themedContext, R.layout.row_spinner_item, animationItemNames));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedAnimationItem = animationItems[i];
                runLayoutAnimations(recyclerView, selectedAnimationItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void runLayoutAnimations(RecyclerView recyclerView, AnimationItem selectedAnimationItem) {
        final Context context = recyclerView.getContext();

        final LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, selectedAnimationItem.getResourceId());

        recyclerView.setLayoutAnimation(layoutAnimationController);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }

    ;
}
