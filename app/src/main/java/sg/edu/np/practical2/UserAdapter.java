package sg.edu.np.practical2;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    ArrayList<User> data;

    public UserAdapter (ArrayList<User> input) {
        data = input;
    }

    public UserViewHolder onCreateViewHolder (
            ViewGroup parent,
            int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recyclerview_user, parent, false);
        return new UserViewHolder(item);
    }

    public void onBindViewHolder(UserViewHolder holder, int position){
        User list_items = data.get(position);
        holder.name.setText(list_items.getName());
        holder.desc.setText(list_items.getDescription());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public int getItemCount() {return data.size(); }

    private void alertDialogBox(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity);
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
                startActivity(intent);
            }
        });
        builder.show();
    }
}
