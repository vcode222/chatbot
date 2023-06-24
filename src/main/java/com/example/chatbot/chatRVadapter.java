package com.example.chatbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gtappdevlopers.chatbot.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class chatRVadapter extends RecyclerView.Adapter {
    private ArrayList<chatsmodal>chatsmodalArrayList;
    private Context context;

    public chatRVadapter(ArrayList<chatsmodal> chatsModalArrayList, Context context) {
        this.chatsmodalArrayList=chatsModalArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg_rv_item,parent,false);
                        return new userviewholder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg_rv_item,parent,false);
                        return new BotViewholder(view);
        }
        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        chatsmodal chatsModal = chatsmodalArrayList.get(position);
        switch (chatsModal.getSender()){
            case "user":
                ((userviewholder)holder).userTV.setText(chatsModal.getMessage());
                break;
            case"bot":
                ((BotViewholder)holder).botMsgtv.setText(chatsModal.getMessage());
                break;
        }
    }
    @Override
    public int getItemViewType(int position)
    {
       switch (chatsmodalArrayList.get(position).getSender())
       {
           case "user":
               return 0;
           case "bot":
               return 1;
           default:
               return -1;
       }
    }

    @Override
    public int getItemCount() {
       return chatsmodalArrayList.size();
    }

    public static class userviewholder extends RecyclerView.ViewHolder{
     TextView userTV;

        public  userviewholder(@NonNull View itemView) {
            super(itemView);
            userTV = itemView.findViewById(R.id.idTVUser);
        }
    }

public static class BotViewholder extends RecyclerView.ViewHolder{
TextView botMsgtv;
    public BotViewholder(@NonNull View itemView) {
        super(itemView);
        botMsgtv = itemView.findViewById(R.id.idTVbot);
    }
}

}
