package personal.marriageproposal;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import personal.marriageproposal.dummy.DummyContent;

/**
 * An activity representing a single Hint detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link HintListActivity}.
 */
public class HintDetailActivity extends AppCompatActivity {

    private static final int TEXT_ID = 0;
    private static final String TAG = "HintDetailActivity";
    private String hint_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(HintDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(HintDetailFragment.ARG_ITEM_ID));
            HintDetailFragment fragment = new HintDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.hint_detail_container, fragment)
                    .commit();

            hint_id = arguments.getString(HintDetailFragment.ARG_ITEM_ID);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.Hint hint = DummyContent.Hint.getById(hint_id);
                if (Integer.parseInt(hint_id) < DummyContent.getCount()) {
                    Toast.makeText(getApplicationContext(), "Você já encontrou esta pista. Vá para próxima.",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Bundle args = new Bundle();
                args.putString("password", hint.password);
                showDialog(0, args);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, HintListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Dialog onCreateDialog(int id, final Bundle args) {
        if (id != 0) return null;
        // Use an EditText view to get user input.
        final EditText input = new EditText(this);
        input.setId(TEXT_ID);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resposta")
                .setMessage("Digite a palavra secreta:")
                .setView(input);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                String password = args.getString("password");
                if (value.equalsIgnoreCase(password)) {
                    DummyContent.incrementCount();

                    Intent intent = new Intent(getApplicationContext(), HintListActivity.class);
                    finish();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Parabéns, você acertou! Vá para próxima pista.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Resposta incorreta! Tente novamente.", Toast.LENGTH_LONG).show();
                }
                Log.d(TAG, "Resposta: " + password);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        return builder.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        if (id == 0) {
            EditText text = (EditText) dialog.findViewById(TEXT_ID);
            text.setText("");
        }
    }
}
