package com.gtappdevlopers.chatbot;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chatbot.RETRO2;
import com.example.chatbot.chatRVadapter;
import com.example.chatbot.chatsmodal;
import com.example.chatbot.msgmodal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gtappdevlopers.chatbot.R;

import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MainActivity extends AppCompatActivity {
    private RecyclerView chatsRV;
    private EditText userMsgEdt;
    private FloatingActionButton sendMsgFAB;
    private final  String BOT_key = "bot";
    private final  String USER_key = "user";
    private ArrayList<chatsmodal>chatsModalArrayList;
    private com.example.chatbot.chatRVadapter chatRVadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatsRV = findViewById(R.id.idRVchats);
        userMsgEdt = findViewById(R.id.idEdtMessage);
        sendMsgFAB = findViewById(R.id.idFABsend);
        chatsModalArrayList = new ArrayList<>();
        chatRVadapter = new chatRVadapter(chatsModalArrayList,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        chatsRV.setLayoutManager(manager);
        chatsRV.setAdapter(chatRVadapter);



        sendMsgFAB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(userMsgEdt.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "please enter  your message", Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMsgEdt.getText().toString());
                userMsgEdt.setText("");
            }

        });
    }
    private  void getResponse(String message){
        chatsModalArrayList.add(new chatsmodal(message,USER_key));
        chatRVadapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=166278&key=5q4rbTQxg4d0QmSD&uid=[uid]&msg="+message;
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
      RETRO2 retro2= retrofit.create(RETRO2.class);
        Call<msgmodal> call = retro2.getMessage(url);
        call.enqueue(new Callback<msgmodal>()
        {
            @Override
            public void onResponse(Call<msgmodal>call,Response<msgmodal>response)
{
    if(response.isSuccessful())
    {
        msgmodal modal=response.body();
        chatsModalArrayList.add(new chatsmodal(modal.getCnt(),BOT_key));
        chatRVadapter.notifyDataSetChanged();


    }
            }

            @Override
            public void onFailure(Call<msgmodal> call, Throwable t) {
                chatsModalArrayList.add(new chatsmodal("please revert your question",BOT_key));
                chatRVadapter.notifyDataSetChanged();

            }

        });
    }
}