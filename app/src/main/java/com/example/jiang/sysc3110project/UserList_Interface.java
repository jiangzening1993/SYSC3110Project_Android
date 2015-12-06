package com.example.jiang.sysc3110project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Created by Jiang on 05/12/2015.
 */
public class UserList_Interface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        setUserList();
    }

    public void setUserList(){
        TextView userlist = (TextView)findViewById(R.id.userlist);
        ArrayList<User> users = Controller.getSimulator().getUsers();
        String str = "";
        String newLine = System.getProperty("line.separator");
        for(User user : users){
            str += user.toString() + newLine;
        }
        userlist.setText(str);
    }
}
