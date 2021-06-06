package sg.edu.np.practical2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView desc;
    ImageView image;

    public UserViewHolder (View itemView) {
        super (itemView);
        name = itemView.findViewById(R.id.nameView);
        desc = itemView.findViewById(R.id.descView);
        image = (ImageView) itemView.findViewById(R.id.userImage);
    }
}
