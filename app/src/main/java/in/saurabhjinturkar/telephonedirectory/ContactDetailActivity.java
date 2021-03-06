package in.saurabhjinturkar.telephonedirectory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.stefanomunarini.telephonedirectory.R;

import in.saurabhjinturkar.telephonedirectory.database.services.ContactService;


/**
 * An activity representing a single Contact detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ContactListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link ContactDetailFragment}.
 */
public class ContactDetailActivity extends ActionBarActivity {

    private ContactService contactService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contactService = new ContactService(this);

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
            arguments.putString(ContactDetailFragment.ARG_ITEM_ID,
                    contactService.getContact(Integer.parseInt(getIntent().getStringExtra(ContactDetailFragment.ARG_ITEM_ID))).getId());
            ContactDetailFragment fragment = new ContactDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contact_detail_container, fragment)
                    .commit();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contact_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                navigateUpTo(new Intent(this, ContactListActivity.class));
                return true;
//            case R.id.action_edit:
//                Intent intent = new Intent(this, NewContact.class);
//                intent.putExtra(MyDBAdapter.KEY_ID, ContactDetailFragment.mContact.getId());
//                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
