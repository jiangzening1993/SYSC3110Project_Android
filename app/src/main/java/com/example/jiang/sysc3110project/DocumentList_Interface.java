package com.example.jiang.sysc3110project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Set;

/**
 * Created by Jiang on 05/12/2015.
 */
public class DocumentList_Interface extends AppCompatActivity {
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.document);
        controller = (Controller)getIntent().getSerializableExtra("controller");
        setDocumentList();
    }

    public void setDocumentList(){
        Simulation simulator = controller.getSimulator();
        Set<Document> documents = simulator.getDocuments();
        String str = "";
        String newLine = System.getProperty("line.separator");
        for(Document document : documents){
            str += document.toString() + newLine;
        }
        TextView documentlist = (TextView)findViewById(R.id.documentlist);
        documentlist.setText(str);
    }
}
