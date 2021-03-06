package personal.marriageproposal;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import personal.marriageproposal.dummy.DummyContent;

/**
 * A fragment representing a single Hint detail screen.
 * This fragment is either contained in a {@link HintListActivity}
 * in two-pane mode (on tablets) or a {@link HintDetailActivity}
 * on handsets.
 */
public class HintDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy title this fragment is presenting.
     */
    private DummyContent.Hint mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HintDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy title specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load title from a title provider.
            DummyContent dummyContent = new DummyContent(getContext());
            mItem = dummyContent.getItemsMap().get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.title);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.hint_detail, container, false);

        // Show the dummy title as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.hint_detail)).setText(mItem.details);
        }

        return rootView;
    }
}
