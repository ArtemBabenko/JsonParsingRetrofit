package json_parsing_retrofit.jsonparsingretrofit.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import json_parsing_retrofit.jsonparsingretrofit.R;
import json_parsing_retrofit.jsonparsingretrofit.models.Contact;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<Contact> list;
    private Context context;

    public ContactAdapter(List<Contact> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Contact message = list.get(position);
        Picasso.with(context).load(message.getProfilePic()).fit().centerCrop().into(holder.userImage);
        holder.userName.setText(message.getName());
        holder.userEmail.setText(message.getEmail());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView userImage;
        TextView userName;
        TextView userEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            userImage = (ImageView) itemView.findViewById(R.id.imageView);
            userName = (TextView) itemView.findViewById(R.id.textViewName);
            userEmail = (TextView) itemView.findViewById(R.id.textViewEmail);
        }
    }

}
