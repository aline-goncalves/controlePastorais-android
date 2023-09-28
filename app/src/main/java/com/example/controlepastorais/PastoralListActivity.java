package com.example.controlepastorais;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.persistence.PastoralDatabase;
import com.example.utils.UtilsGUI;
import java.util.ArrayList;
import java.util.List;

public class PastoralListActivity extends AppCompatActivity {
    private ListView listViewPastorals;
    private List<Pastoral> pastorals = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private ArrayAdapter<String> pastoralArrayAdapter;

    public static final String NAME = "NAME";
    public static final String COORDINATOR = "COORDINATOR";
    public static final String PATRON_SAINT = "PATRON_SAINT";
    public static final String INTEREST_ACTIVITIES = "INTEREST_ACTIVITIES";
    public static final boolean IS_MOVEMENT = false;
    public static final int POSITION = -1;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pastoral_list);

        findComponentsFromView();
        onItemSelected();
        registerForContextMenu(listViewPastorals);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        PastoralDatabase database = PastoralDatabase.getDatabase(this);
        String pastoralName = (String) listViewPastorals.getItemAtPosition(info.position);
        Pastoral pastoral = database.pastoralDao().queryForName(pastoralName);

        switch(item.getItemId()){
            case R.id.delete_menu_item:
                delete(pastoral);
                return true;

            case R.id.edit_menu_item:
                edit(pastoral);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    public void delete(Pastoral pastoral){
        String message = getString(R.string.confirm_deletion_message) + " " + pastoral.getName() + "?";

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch(which){
                            case DialogInterface.BUTTON_POSITIVE:
                                PastoralDatabase database = PastoralDatabase.getDatabase(PastoralListActivity.this);
                                database.pastoralDao().delete(pastoral);
                                pastoralArrayAdapter.remove(pastoral.getName());
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

        UtilsGUI.confimAction(this, message, listener);
        populatePastoralsList();
    }

    public void edit(Pastoral pastoral){
        PastoralActivity.editPastoral(this, pastoral);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_options, menu);
        return true;
    }

    public void callAboutAppAuthorshipActivity(View view){
        Intent intent = new Intent(this, AppAuthorshipActivity.class);
        startActivity(intent);
    }

    public void callPastoralActivity(View view){
        PastoralActivity.newPastoral(this);
    }

    private void findComponentsFromView(){
        listViewPastorals = findViewById(R.id.listViewPastorais);
        setAdapterOnListView();
    }

    private void setAdapterOnListView(){
        populatePastoralsList();
        listViewPastorals.setAdapter(pastoralArrayAdapter);
    }

    private void populatePastoralsList(){
        PastoralDatabase database = PastoralDatabase.getDatabase(this);
        pastorals = database.pastoralDao().queryAll();
        names = new ArrayList<>();

        for (Pastoral p : pastorals) {
            names.add(p.getName());
        }

        pastoralArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                names);

        listViewPastorals.setAdapter(pastoralArrayAdapter);

        PastoralActivity.FORM_FILLED = 0;
        pastoralArrayAdapter.notifyDataSetChanged();
    }

    private void sendToastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void onItemSelected() {
        listViewPastorals.setOnItemClickListener((adapterView, view, position, id) -> sendToastMessage(getString(R.string.fill_all_fields) + listViewPastorals.getItemAtPosition(position).toString()));
    }

    @Override
    public void onBackPressed() {
        cancel();
    }

    public void cancel(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    public List<String> getPastoralsNames() {
        return names;
    }
}
