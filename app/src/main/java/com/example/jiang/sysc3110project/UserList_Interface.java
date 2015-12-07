package com.example.jiang.sysc3110project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jiang on 05/12/2015.
 */
public class UserList_Interface extends AppCompatActivity {
    private Controller controller;
    ArrayList<String> username = new ArrayList<String>();
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        controller = (Controller)getIntent().getSerializableExtra("controller");
        setUserList();
    }

    public void setUserList(){
        Simulation simulator = controller.getSimulator();
        users = simulator.getUsers();
        /*for(User user : users){
            username.add(user.getName());
        }*/
        ListView listview = (ListView) findViewById(R.id.userlist);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User select = users.get(position);
                Intent intent = new Intent(UserList_Interface.this, UserInformation.class);
                intent.putExtra("selectedUser", select);
                startActivity(intent);
            }
        });
    }
}
