package sg.edu.np.practical2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    private final static String TAG = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<User> userList = new ArrayList<>();

        for (int i = 0; i < 20; i++){
            String name = "Name " + String.valueOf(randomNum());
            String desc = "Description " + String.valueOf(randomNum());
            if (i % 2 == 0) {
                User user = new User(name, desc, i + 1, false);
                userList.add(user);
            }
            else {
                User user = new User(name, desc, i + 1, true);
                userList.add(user);
            }

        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        UserAdapter uAdapter = new UserAdapter(userList);
        LinearLayoutManager uLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(uLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(uAdapter);


        int temp = userList.size();
        Log.v(TAG, String.valueOf(temp));

        /*
        Intent intent = new Intent(ListActivity.this, UserAdapter.class);
        intent.putExtra("randomNum", String.valueOf(randomNum()));
        startActivity(intent);

        /*
        ImageView image = (ImageView) findViewById(R.id.androidImage);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Run dialog");
                alertDialogBox();
            }
        });
        */

    }

    public int randomNum(){
        Random num = new Random();
        int value = num.nextInt(10000000);
        return value;
    }

    /*
    private void alertDialogBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile");
        builder.setMessage("MADness");
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which){
                finish();
            }
        });
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.putExtra("randomNumber", String.valueOf(randomNum()));
                Log.v(TAG, "Clicked view");
                startActivity(intent);
            }
        });
        builder.show();
    }
    */

}