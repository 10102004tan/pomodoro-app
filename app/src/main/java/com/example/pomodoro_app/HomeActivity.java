package com.example.pomodoro_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pomodoro_app.Entities.Clock;
import com.example.pomodoro_app.databinding.HomeLayoutBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {
    private int second = 6;
    private int minute;
    private ArrayList<Clock> clocks;
    private boolean statePlay = true;
    private HomeLayoutBinding homeLayoutBinding;
    private int posision = 0;
    private DecimalFormat format;
    private final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeLayoutBinding = HomeLayoutBinding.inflate(getLayoutInflater());
        setContentView(homeLayoutBinding.getRoot());
        format = new DecimalFormat("00");


        addClock();
        homeLayoutBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!statePlay){
                    homeLayoutBinding.btnPlay.setBackground(getDrawable(R.drawable.play));
                    statePlay = true;
                }
                posision++;
               switch (posision){
                   case 0:{
                       homeLayoutBinding.getRoot().setBackgroundColor(getColor(R.color.red));
                   }
                   break;
                   case 1:{
                       homeLayoutBinding.getRoot().setBackgroundColor(getColor(R.color.green));
                   }
                   break;
                   case 2:{
                       homeLayoutBinding.getRoot().setBackgroundColor(getColor(R.color.blue));

                   }
                   break;
               }
                homeLayoutBinding.podoromoName.setText(clocks.get(posision).getName());
                homeLayoutBinding.txtMinute.setText(String.valueOf(clocks.get(posision).getMinute()));
                homeLayoutBinding.txtSecond.setText("00");
               if (posision == clocks.size() - 1){
                   posision = -1;
               }
            }
        });

        homeLayoutBinding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (statePlay){
                    homeLayoutBinding.btnPlay.setBackground(getDrawable(R.drawable.pause));
                    homeLayoutBinding.btnNext.setVisibility(View.GONE);
                    statePlay = false;
                    try {
                        minute = Integer.parseInt(homeLayoutBinding.txtMinute.getText().toString()) - 1;
                        run(homeLayoutBinding.txtMinute,homeLayoutBinding.txtSecond,1000);
                    }catch (Exception e){

                    }

                }
                else{
                    homeLayoutBinding.btnPlay.setBackground(getDrawable(R.drawable.play));
                    statePlay = true;
                    homeLayoutBinding.btnNext.setVisibility(View.VISIBLE);
                    handler.removeCallbacksAndMessages(null);
                }

            }
        });

    }

    private void run(TextView txtMinute,TextView txtSecond,long miliseconds){

        handler.post(new Runnable() {
            @Override
            public void run() {
                --second;
                if (minute > 0){
                    if (second < 0){
                        minute--;
                        second = 59;
                    }
                    handler.postDelayed(this,miliseconds);
                }
                else{
                    if(second != 0 || minute != 0){
                        handler.postDelayed(this,miliseconds);
                    }
                }
                txtSecond.setText(format.format(second));
                txtMinute.setText(String.valueOf(minute));
            }
        });
    }

    private void addClock(){
        this.clocks = new ArrayList<>();
        this.clocks.add(new Clock(25,"Focus Time",R.color.red));
        this.clocks.add(new Clock(5,"Sort break",R.color.green));
        this.clocks.add(new Clock(15,"Long Break",R.color.red));
    }


}