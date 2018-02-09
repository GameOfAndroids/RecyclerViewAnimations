package ke.mush.recyclerviewanimations;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ke.mush.recyclerviewanimations.fragment.GridDemoFragment;
import ke.mush.recyclerviewanimations.fragment.ListDemoFragment;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_list_fragment)
    Button btnListFragment;
    @BindView(R.id.btn_grid_fragment)
    Button btnGridFragment;
    @BindView(R.id.button_container)
    LinearLayout llButtonContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            final int count = getSupportFragmentManager().getBackStackEntryCount();
            llButtonContainer.setAlpha(count > 0 ? 0f : 1f);
        }
    }

    @OnClick(R.id.btn_list_fragment)
    void btnListFragmentClicked() {
        showFragment(new ListDemoFragment());
    }

    @OnClick(R.id.btn_grid_fragment)
    void btnGridFragmentClicked() {
        showFragment(new GridDemoFragment());
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            llButtonContainer.animate().alpha(1f).setDuration(1000).start();
        }
        super.onBackPressed();
    }

    void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
        llButtonContainer.animate().alpha(0f).setDuration(1000).start();
    }
}
