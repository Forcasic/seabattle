package com.example.san4es.myapplication;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static android.os.SystemClock.sleep;

public class Wyfy extends AppCompatActivity implements View.OnTouchListener{
    Button btnOnOff,btnDiscover,btnSent,btnres;
    ListView listViev;
    int deystvie=-1;
    static final int PRINYT = 0;
    static final int OTPRAVIT = 1;
    static final int CLIENT = 1;
    static final int SERVER = 1;
    volatile boolean playerTurn = true;
    volatile boolean enemyTurn = false;

    String MSGS=new String();
    String MSGP=new String();

    Socket socket;
    int hod=-1;
    BufferedReader in;
    // Вывод автоматически выталкивается из буфера PrintWriter'ом
    PrintWriter out;
    ServerSocket serverSocket;
    int shipkol[];
int Select=0;
    private BattleView mBattleView;
    private SeaBattleGame mSeaBattleGame;

    int hv=1;
    int player=1;
    int[][] play;
    int[][] play1;
    TextView read_msg_box, ConnectonStatus;
    EditText writeMsg;
    WifiManager wifiManager;
    WifiP2pManager nManager;
    WifiP2pManager.Channel mChannel;
    BroadcastReceiver MReceiver;
    IntentFilter mFilter;
    ArrayList<WifiP2pDevice> peers=new ArrayList<WifiP2pDevice>();
    String [] devicenameArray;
    WifiP2pDevice [] devicearray;
    static final  int MESSAGE_READ=1;
    static  ServerClass serverClass;
    static  ClientClass clientClass;
    String msg;

    Button[] pole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.wifi);
        init();
        exqListener();
    }
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what){
                case MESSAGE_READ:
                    byte[] readBuff=(byte[]) msg.obj;
                    String tempMsg=new String(readBuff,0,msg.arg1);
                    read_msg_box.setText(tempMsg);
                    break;
            }
            return true;
        }
    });
    @Override public void onBackPressed(){
        if(Wyfy.serverClass!=null){
            write("0");
            try {

               serverClass.serverSocket.close();
              serverClass.socket.close();
              serverClass.serverSocket=null;
                serverClass.socket=null;
                serverClass=null;


            } catch (Exception e) {
                e.printStackTrace();
            }}
        else{
            write("0");
            try {
              // clientClass.stop();
               clientClass.socket.close();
               clientClass.socket=null;
               clientClass=null;

            } catch (Exception e) {
                e.printStackTrace();
            }}

        finish();
        Intent intent = new Intent(Wyfy.this, SeaBattleMain.class);
        startActivity(intent);

    }
    private void exqListener() {

        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (wifiManager.isWifiEnabled()==false)
                wifiManager.setWifiEnabled(true);
                nManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        ConnectonStatus.setText("Поиск...");
                    }

                    @Override
                    public void onFailure(int reason) {
                        ConnectonStatus.setText("Discavary Starting Failed");
                    }
                });
            }
        });
        listViev.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final WifiP2pDevice device = devicearray[position];
                WifiP2pConfig config = new WifiP2pConfig();
                config.deviceAddress = device.deviceAddress;

                nManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Connected to" + device.deviceName, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(int reason) {
                        Toast.makeText(getApplicationContext(), "not connected to" + device.deviceName, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public void goTo() {
        pole=new Button[100];
        setContentView(R.layout.drav);
        pole[0]=findViewById(R.id.button00);
        pole[1]=findViewById(R.id.button01);
        pole[2]=findViewById(R.id.button02);
        pole[3]=findViewById(R.id.button03);
        pole[4]=findViewById(R.id.button04);
        pole[5]=findViewById(R.id.button05);
        pole[6]=findViewById(R.id.button06);
        pole[7]=findViewById(R.id.button07);
        pole[8]=findViewById(R.id.button08);
        pole[9]=findViewById(R.id.button09);
        pole[10]=findViewById(R.id.button10);
        pole[11]=findViewById(R.id.button11);
        pole[12]=findViewById(R.id.button12);
        pole[13]=findViewById(R.id.button13);
        pole[14]=findViewById(R.id.button14);
        pole[15]=findViewById(R.id.button15);
        pole[16]=findViewById(R.id.button16);
        pole[17]=findViewById(R.id.button17);
        pole[18]=findViewById(R.id.button18);
        pole[19]=findViewById(R.id.button19);
        pole[20]=findViewById(R.id.button20);
        pole[21]=findViewById(R.id.button21);
        pole[22]=findViewById(R.id.button22);
        pole[23]=findViewById(R.id.button23);
        pole[24]=findViewById(R.id.button24);
        pole[25]=findViewById(R.id.button25);
        pole[26]=findViewById(R.id.button26);
        pole[27]=findViewById(R.id.button27);
        pole[28]=findViewById(R.id.button28);
        pole[29]=findViewById(R.id.button29);
        pole[30]=findViewById(R.id.button30);
        pole[31]=findViewById(R.id.button31);
        pole[32]=findViewById(R.id.button32);
        pole[33]=findViewById(R.id.button33);
        pole[34]=findViewById(R.id.button34);
        pole[35]=findViewById(R.id.button35);
        pole[36]=findViewById(R.id.button36);
        pole[37]=findViewById(R.id.button37);
        pole[38]=findViewById(R.id.button38);
        pole[39]=findViewById(R.id.button39);
        pole[40]=findViewById(R.id.button40);
        pole[41]=findViewById(R.id.button41);
        pole[42]=findViewById(R.id.button42);
        pole[43]=findViewById(R.id.button43);
        pole[44]=findViewById(R.id.button44);
        pole[45]=findViewById(R.id.button45);
        pole[46]=findViewById(R.id.button46);
        pole[47]=findViewById(R.id.button47);
        pole[48]=findViewById(R.id.button48);
        pole[49]=findViewById(R.id.button49);
        pole[50]=findViewById(R.id.button50);
        pole[51]=findViewById(R.id.button51);
        pole[52]=findViewById(R.id.button52);
        pole[53]=findViewById(R.id.button53);
        pole[54]=findViewById(R.id.button54);
        pole[55]=findViewById(R.id.button55);
        pole[56]=findViewById(R.id.button56);
        pole[57]=findViewById(R.id.button57);
        pole[58]=findViewById(R.id.button58);
        pole[59]=findViewById(R.id.button59);
        pole[60]=findViewById(R.id.button60);
        pole[61]=findViewById(R.id.button61);
        pole[62]=findViewById(R.id.button62);
        pole[63]=findViewById(R.id.button63);
        pole[64]=findViewById(R.id.button64);
        pole[65]=findViewById(R.id.button65);
        pole[66]=findViewById(R.id.button66);
        pole[67]=findViewById(R.id.button67);
        pole[68]=findViewById(R.id.button68);
        pole[69]=findViewById(R.id.button69);
        pole[70]=findViewById(R.id.button70);
        pole[71]=findViewById(R.id.button71);
        pole[72]=findViewById(R.id.button72);
        pole[73]=findViewById(R.id.button73);
        pole[74]=findViewById(R.id.button74);
        pole[75]=findViewById(R.id.button75);
        pole[76]=findViewById(R.id.button76);
        pole[77]=findViewById(R.id.button77);
        pole[78]=findViewById(R.id.button78);
        pole[79]=findViewById(R.id.button79);
        pole[90]=findViewById(R.id.button80);
        pole[91]=findViewById(R.id.button81);
        pole[92]=findViewById(R.id.button82);
        pole[93]=findViewById(R.id.button83);
        pole[94]=findViewById(R.id.button84);
        pole[95]=findViewById(R.id.button85);
        pole[96]=findViewById(R.id.button86);
        pole[97]=findViewById(R.id.button87);
        pole[98]=findViewById(R.id.button88);
        pole[99]=findViewById(R.id.button89);
        pole[80]=findViewById(R.id.button90);
        pole[81]=findViewById(R.id.button91);
        pole[82]=findViewById(R.id.button92);
        pole[83]=findViewById(R.id.button93);
        pole[84]=findViewById(R.id.button94);
        pole[85]=findViewById(R.id.button95);
        pole[86]=findViewById(R.id.button96);
        pole[87]=findViewById(R.id.button97);
        pole[88]=findViewById(R.id.button98);
        pole[89]=findViewById(R.id.button99);
        play=new int[20][20];
        TextView b=findViewById(R.id.textView4);

        if(serverClass!=null){
        b.setTextColor(getResources().getColor(R.color.colorPrimary));
        b.setText("Зеленый");}
        else{
            b.setTextColor(Color.RED);
            b.setText("Красный");
        }
        shipkol=new int[5];
        shipkol[1]=0;
        shipkol[2]=0;
        shipkol[3]=0;
        shipkol[4]=0;



        findViewById(R.id.fire).setEnabled(false);
        for(int nn=0;nn<10;nn++)
            for(int jjj=0;jjj<10;jjj++){
                play[nn][jjj]=0;
            }

    }
    public int RetKordOfShip(int i,int j){
        int si=i;
        int sj=j;
        int lenght=0;
        if(play[i][j]==1){
            play[i][j]=0;
            lenght++;
        }
        //chek top
        while(true){
            i++;
            if(play[i][j]==1) {
                play[i][j]=0;
                lenght++;
            }
            else
                break;
        }
        i=si;
        j=sj;
        //chek down
        while(true){
            i--;
            if(i>=0){
                if(play[i][j]==1) {
                    play[i][j]=0;
                    lenght++;
                }else break;}
            else break;
        }
        i=si;
        j=sj;
        //chek left
        while(true){
            j--;
            if(j>=0){
                if(play[i][j]==1) {
                    play[i][j]=0;
                    lenght++;
                }else break;}
            else break;
        }
        i=si;
        j=sj;
        //chek right
        while(true){
            j++;
            if(play[i][j]==1) {
                play[i][j]=0;
                lenght++;
            }
            else break;
        }
        return lenght;
    }


    public void chekkolship() {
        if(shipkol[1]==4&&Select==1){
            findViewById(R.id.one).setEnabled(false);
            if(hv==1)
                findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1));
            else findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1));
            Select=0;
        }
        if(shipkol[2]==3&&Select==2){
            findViewById(R.id.two).setEnabled(false);
            if(hv==1)
                findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2));
            else findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2v));
            Select=0;
        }
        if(shipkol[3]==2&&Select==3){
            findViewById(R.id.three).setEnabled(false);
            if(hv==1)
                findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3));
            else findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3v));
            Select=0;
        }
        if(shipkol[4]==1&&Select==4){
            findViewById(R.id.four).setEnabled(false);
            if(hv==1)
                findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4));
            else findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4v));
            Select=0;
        }


    }

    public void poleee(View v) {
        int b=0;



        if(v.getId()!=R.id.one&&v.getId()!=R.id.one&&v.getId()!=R.id.two&&v.getId()!=R.id.three
                &&v.getId()!=R.id.four&&v.getId()!=R.id.refresh&&v.getId()!=R.id.fire
                &&v.getId()!=R.id.auto){
            int roll=0;
            int xi=0;
            int yj=0;
            for(int nn=0;nn<10;nn++)
                for(int jjj=0;jjj<10;jjj++){
                    if(pole[roll].getId()==v.getId()){
                        xi=nn;
                        yj=jjj;
                    }

                    roll++;
                }
            if(play[xi][yj]==0){
                if(v.getId()!=R.id.one&&v.getId()!=R.id.one&&v.getId()!=R.id.two&&v.getId()!=R.id.three
                        &&v.getId()!=R.id.four&&v.getId()!=R.id.refresh&&v.getId()!=R.id.fire
                        &&v.getId()!=R.id.auto&&Select!=0){
                    Ship s = new Ship(Ship.generateShipPositionhand(Select,hv,xi,yj));

                    BattleField battleFiel=new BattleField();

                    if(battleFiel.addShiphand(s,play)){
                        shipkol[Select]++;
                        int mmmm=0;
                        for(int nn=0;nn<10;nn++)
                            for(int jjj=0;jjj<10;jjj++)
                                if(mmmm<s.getPosition().length)
                                    if(s.getPosition()[mmmm][0]==nn&&s.getPosition()[mmmm][1]==jjj){
                                        play[nn][jjj]=1;
                                        mmmm++;
                                    }
                        roll=0;
                        for(int nn=0;nn<10;nn++)
                            for(int jjj=0;jjj<10;jjj++){
                                pole[roll].setBackgroundColor(getResources().getColor(R.color.stock));

                                if(play[nn][jjj]==1){
                                    pole[roll].setBackgroundColor(Color.GREEN);
                                }

                                roll++;
                            }
                    }
                }

            } else {
                int s=RetKordOfShip(xi,yj);
                shipkol[s]--;
            }}
        if(v.getId()==R.id.refresh){
            if(hv==1){
                hv=2;
                findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2v));
                findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3v));
                findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4v));
                switch(Select){
                    case 0:
                        break;
                    case 1:findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1vib));
                        break;
                    case 2:findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2vvib));
                        break;
                    case 3:findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3vvib));
                        break;
                    case 4:findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4vvib));
                        break;
                }
            }
            else{
                hv=1;
                findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2));
                findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3));
                findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4));
                switch(Select){
                    case 0:
                        break;
                    case 1:findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1vib));
                        break;
                    case 2:findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2vib));
                        break;
                    case 3:findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3vib));
                        break;
                    case 4:findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4vib));
                        break;
                }
            }

        }

        if(v.getId()==R.id.one){
            if(Select!=1) {
                Select = 1;
                if(hv==1){
                    findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2));
                    findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3));
                    findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4));
                    findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1vib));}
                else {
                    findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2v));
                    findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3v));
                    findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4v));
                    findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1vib));}
            } else
            if(Select==1){Select=0;
                if(hv==1)
                    findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1));
                else findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1));}
        }
        if(v.getId()==R.id.two){
            if(Select!=2) {
                Select = 2;
                if(hv==1){
                    findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1));
                    findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3));
                    findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4));
                    findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2vib));}
                else { findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1));
                    findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3v));
                    findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4v));
                    findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2vvib));}
            }else if(Select==2){Select=0;
                if(hv==1)
                    findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2));
                else findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2v));
            }
        }
        if(v.getId()==R.id.three){
            if(Select!=3) {
                Select = 3;
                if(hv==1){
                    findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1));
                    findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2));
                    findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4));
                    findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3vib));}
                else {
                    findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1));
                    findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2v));
                    findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4v));
                    findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3vvib));}
            }else if(Select==3){Select=0;
                if(hv==1)
                    findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3));
                else findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3v));}
        }
        if(v.getId()==R.id.four){
            if(Select!=4) {
                Select = 4;
                if(hv==1){ findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1));
                    findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2));
                    findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3));
                    findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4vib));}
                else{ findViewById(R.id.one).setBackgroundDrawable(getResources().getDrawable(R.drawable.s1));
                    findViewById(R.id.two).setBackgroundDrawable(getResources().getDrawable(R.drawable.s2v));
                    findViewById(R.id.three).setBackgroundDrawable(getResources().getDrawable(R.drawable.s3v));
                    findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4vvib));}
            }else if(Select==4){Select=0;
                if(hv==1)
                    findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4));
                else findViewById(R.id.four).setBackgroundDrawable(getResources().getDrawable(R.drawable.s4v));}
        }

        if(v.getId()==R.id.fire){
          /*  Button uuuboy=(Button) findViewById(R.id.fire);
            uuuboy.setText("Ожидание");
            uuuboy.setEnabled(false);
            TextView uuboy=(TextView) findViewById(R.id.textView);
            uuuboy.setText("Ожидание соперника)");*/
                mBattleView = new BattleView(this);
                mBattleView.setOnTouchListener(this);
                setContentView(mBattleView);
                mSeaBattleGame = new SeaBattleGame();
                //update view model
            if(serverClass!=null){
                mSeaBattleGame.battleField.setPlayerField(play);
                int posi=0;
                for(int i=0;i<10;i++)
                    for(int j=0;j<10;j++){
                       MSGS= MSGS+ String.valueOf(play[i][j]);
                        posi++;
                    }

                write(MSGS);
                  read();
                posi=0;
                for(int i=0;i<10;i++)
                    for(int j=0;j<10;j++){
                        play[i][j]=(MSGP.charAt(posi))-'0';
                        posi++;
                    }
                mSeaBattleGame.battleField.setEnemyField(play);
                mBattleView.typeofgame=3;
                mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(), mSeaBattleGame.getEnemyPlacement());

            }
            else
            if(clientClass!=null){
                mSeaBattleGame.battleField.setEnemyField(play);

                int posi=0;
                for(int i=0;i<10;i++)
                    for(int j=0;j<10;j++){
                        MSGS= MSGS+ String.valueOf(play[i][j]);
                        posi++;
                    }
                write(MSGS);
                read();
                 posi=0;
                for(int i=0;i<10;i++)
                    for(int j=0;j<10;j++){
                        play[i][j]=(MSGP.charAt(posi))-'0';
                       posi++;
                    }
                mSeaBattleGame.battleField.setPlayerField(play);
                mBattleView.typeofgame=3;
                mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(), mSeaBattleGame.getEnemyPlacement());

            }

        }

        if(v.getId()==R.id.auto){
            mSeaBattleGame = new SeaBattleGame();
            //            //update view model
            play=mSeaBattleGame.getPlayerPlacement();

            shipkol[1]=4;
            shipkol[2]=3;
            shipkol[3]=2;
            shipkol[4]=1;


        }
        int rol=0;
        for(int nn=0;nn<10;nn++)
            for(int jjj=0;jjj<10;jjj++){
                pole[rol].setBackgroundColor(getResources().getColor(R.color.stock));

                if(play[nn][jjj]==1){
                    pole[rol].setBackgroundColor(Color.GREEN);
                }


                rol++;
            }
        for(int nn=0;nn<10;nn++)
            for(int jjj=0;jjj<10;jjj++){
                if(play[nn][jjj]==1)b++;
            }
        try{
            if(b==20)findViewById(R.id.fire).setEnabled(true);
            else findViewById(R.id.fire).setEnabled(false);

            findViewById(R.id.one).setEnabled(true);
            findViewById(R.id.two).setEnabled(true);
            findViewById(R.id.three).setEnabled(true);
            findViewById(R.id.four).setEnabled(true);
            chekkolship();}
        catch(Exception jjk){}


    }

int start=0;
    @Override
    public boolean onTouch(View v, MotionEvent e) {

        if (start == 0) {
           mBattleView.Start=0;
            mBattleView.pereris=1;
            start=1;
            if (clientClass != null)
                Firsthodforlient();

        } else {
            int x = (int) e.getX();
            int y = (int) e.getY();
            int cellSize = mBattleView.getCellSize();
            int startBoardX = mBattleView.getStartEnemyFieldX();
            int endBoardX = cellSize * BattleField.N + startBoardX;
            int endBoardY = mBattleView.getEndEnemyFieldY();
            if (serverClass != null) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //click only over enemy board to hit
                        if (x >= startBoardX && y <= endBoardY &&
                                x <= endBoardX) {
                            int ip = y / cellSize;
                            int jp = (x - startBoardX) / cellSize;
                            String msg = ip + " " + jp;
                            write(msg);
                            if (playerAttackEnemy(ip, jp) == true) {
                                //  sleep(500);

                                write("0");
                                if (mSeaBattleGame.getWinner() != -1) {
                                    return true;
                                }
                                hod = 2;

                                // sleep(500);
                                while (true) {
                                    read();
                                    // sleep(500);
                                    if (MSGP.equals("-1") == false) {
                                        if (MSGP.equals("0")) {
                                            MSGP = "-1";
                                            break;
                                        }
                                        ip = (MSGP.charAt(0)) - '0';
                                        jp = (MSGP.charAt(2)) - '0';
                                        enemyAttackPlayer(ip, jp);


                                    }
                                }
                                return true;
                            }
                        }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_MOVE:
                }
                return true;
            } else if (clientClass != null) {
                switch (e.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        //click only over enemy board to hit
                        //  enemyTurn=true;
                        int ip = y / cellSize;
                        int jp = (x) / cellSize;
                        if (jp <= 9 && ip <= 9) {
                            ip = y / cellSize;
                            jp = (x) / cellSize;
                            String msg = ip + " " + jp;
                            write(msg);
                            if (enemyAttackPlayer(ip, jp) == true) {
                                // sleep(500);

                                write("0");
if (mSeaBattleGame.getWinner() != -1) {
                                  return true;
                                }

                                hod = 2;


                                while (true) {
                                    read();
                                    //sleep(500);
                                    if (MSGP.equals("-1") == false) {
                                        if (MSGP.equals("0")) {
                                            MSGP = "-1";
                                            break;
                                        }
                                        ip = MSGP.charAt(0) - '0';
                                        jp = MSGP.charAt(2) - '0';
                                        playerAttackEnemy(ip, jp);


                                    }
                                }
                                return true;
                            }
                        }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_MOVE:
                }
                return true;
            }


            return true;
        }
        return true;
    }
        public void Firsthodforlient () {
            while (true) {
                read();
                // sleep(500);
                if (MSGP.equals("-1") == false) {
                    if (MSGP.equals("0")) {
                        MSGP = "-1";

                        break;
                    }
                    int ip, jp;
                    ip = (int) MSGP.charAt(0) - '0';
                    jp = (int) MSGP.charAt(2) - '0';

                    playerAttackEnemy(ip, jp);

                }
            }
        }




    private boolean playerAttackEnemy(int i, int j){
        mBattleView.setTurn(playerTurn);

        if (playerTurn) {
            if (!mSeaBattleGame.attackEnemy(i, j)) {
                //unsuccessful
                mBattleView.setTurn(false);
                mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                        mSeaBattleGame.getEnemyPlacement());

                //in fact draw red circle that means enemy turn now

                //turn enemy move
                enemyTurn = true;
                playerTurn = false;
                return true;
            }
            //check winner
            int winner = mSeaBattleGame.getWinner();
            if (winner != -1) {
                mBattleView.setOnTouchListener(null);
                mBattleView.setWinner(winner);
                mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                        mSeaBattleGame.getEnemyPlacement());

                return false;
            }
            mBattleView.setTurn(true);
            mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                    mSeaBattleGame.getEnemyPlacement());

            return false;
        }
        return false;
    }
    private boolean enemyAttackPlayer(int i, int j) {
        if (enemyTurn) {
            mBattleView.setTurn(false);

            //while enemy is attacking only player's ships
            if (!mSeaBattleGame.attackPlayer(i, j)) {
                mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                        mSeaBattleGame.getEnemyPlacement());
                playerTurn = true;
                mBattleView.setTurn(true);

                enemyTurn = false;
                return true;
            }
            int winner = mSeaBattleGame.getWinner();
            if (winner != -1) {
                mBattleView.setOnTouchListener(null);
                mBattleView.setWinner(winner);
                mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                        mSeaBattleGame.getEnemyPlacement());

                return false;
            }

            //hitting finished, cause enemy fluffed
            mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                    mSeaBattleGame.getEnemyPlacement());

            mBattleView.setTurn(false);


            return false;
        }
        return false;
    }




    public void init(){

         btnDiscover=findViewById(R.id.discover);
        listViev=findViewById(R.id.peerListView);

        ConnectonStatus=findViewById(R.id.connectionStatus);
        wifiManager= (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        nManager=(WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel=nManager.initialize(this,getMainLooper(),null);

        MReceiver=new WifiDirectBroadcastRiceirver(nManager,mChannel,this);
        mFilter=new IntentFilter();
        mFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        wifiManager.setWifiEnabled(false);
        wifiManager.setWifiEnabled(true);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    1001);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method

        }else{
            nManager.requestPeers(mChannel, peerListListener);
            //do something, permission was previously granted; or legacy device
        }



        if (wifiManager.isWifiEnabled()) {
            nManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                    ConnectonStatus.setText("Поиск...");
                }
                @Override
                public void onFailure(int reason) {
                    ConnectonStatus.setText("Discavary Starting Failed");
                }
            });
        }

    }
    WifiP2pManager.PeerListListener peerListListener=new WifiP2pManager.PeerListListener(){
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peerList){
            if(!peerList.getDeviceList().equals(peers)){
                peers.clear();
                peers.addAll(peerList.getDeviceList());
                devicenameArray=new String[peerList.getDeviceList().size()];
                devicearray=new WifiP2pDevice[peerList.getDeviceList().size()];
                int index=0;
                for(WifiP2pDevice device:peerList.getDeviceList()){
                    devicenameArray[index]=device.deviceName;
                    devicearray[index]=device;
                    index++;
                }
                ArrayAdapter <String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,devicenameArray);
                listViev.setAdapter(adapter);
            }
            if(peers.size()==0){
                ConnectonStatus.setText("No Found...");
                Toast.makeText(getApplicationContext(),"No Device Found",Toast.LENGTH_SHORT).show();
                return;
            }

        }
    };
    WifiP2pManager.ConnectionInfoListener connectionInfoListener=new WifiP2pManager.ConnectionInfoListener() {
        @Override
        public void onConnectionInfoAvailable(WifiP2pInfo info) {
            final InetAddress groupOwnerAddress=info.groupOwnerAddress;
            if (info.groupFormed&&info.isGroupOwner){
                ConnectonStatus.setText("Host");
                MSGP="-1";
                   serverClass=new ServerClass();
                    serverClass.start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                    while(true) {
                  if(Sinhron(0)==-1) {
                    //  playserverStart();
                      goTo();
                      dey=0;
                      break;
                  }}

            }
            else if (info.groupFormed){
                MSGP = "-1";
                clientClass = new ClientClass(groupOwnerAddress);
                clientClass.start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true){

                    if (Sinhron(0) == -1) {
                        dey = 0;
                        ConnectonStatus.setText("GATOVA");
                       // playclientStart();
                        goTo();
                        break;

                    }}


            }
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(MReceiver,mFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MReceiver);
    }



    public class ServerClass extends Thread{
        Socket socket;
        ServerSocket serverSocket;
        BufferedReader in;
        // Вывод автоматически выталкивается из буфера PrintWriter'ом
        PrintWriter out;

        @Override
        public void run(){
            try {
                serverSocket=new ServerSocket(7711);
                // socket.setTcpNoDelay(true);
                socket=serverSocket.accept();
                System.out.println("Connection accepted: " + socket);
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                while(true){

                if(Sinhron(1)==-1)
                    break;}
                while(true){
                    if(deystvie==PRINYT){
                        MSGP=in.readLine();
                        deystvie=-1;

                        while(true){
                            if(Sinhron(1)==-1)
                                break;}
                        System.out.println("Prinyl"+MSGP);
                      //  sleep(100);
                    }

                    if(deystvie==OTPRAVIT){
                out.println(MSGS);
                        out.flush();
                    System.out.println("otptav"+MSGS);
                    deystvie=-1;
                        while(true){
                            if(Sinhron(1)==-1)
                                break;}
                    }
        }
            }
            catch (Exception h){
                h.printStackTrace();
            }
        }

    }

    public void write(String msg){
        MSGS=msg;
        dey=1;
        deystvie=OTPRAVIT;
sleep(100);
        while(true){
            if(Sinhron(0)==-1)
                break;
        }
    }
    public void read(){
        if(deystvie!=OTPRAVIT)
            dey=1;
        deystvie=PRINYT;
       sleep(100);
        while(true){
            if(Sinhron(0)==-1)
                break;
        }
    }
int dey=0;
    public int Sinhron(int deystv){

        synchronized(Thread.currentThread().getName()){
            if(deystv==1)
            dey=deystvie;
            return dey;
        }
    }
    public class ClientClass extends Thread{
        Socket socket;
        Socket socket1;
        String hostAdd;
        BufferedReader in;
        // Вывод автоматически выталкивается из буфера PrintWriter'ом
        PrintWriter out;
        public  ClientClass(InetAddress hostAddress){
            hostAdd=hostAddress.getHostAddress();
            // socket=new Socket();
        }
        @Override
        public void run(){
            try {
                socket=new Socket(hostAdd, 7711);
                socket1=socket;
                //socket.connect(new InetSocketAddress(hostAdd, 7711), 500);
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                while(true){
                    if(Sinhron(1)==-1)
                        break;
                }
                while(true){
                    if(deystvie==PRINYT){
                        MSGP=in.readLine();
                        deystvie=-1;
                        while(true){
                            if(Sinhron(1)==-1)
                                break;
                        }
                        System.out.println("prynal"+MSGP);
                        //      sleep(100);
                    }

                    if(deystvie==OTPRAVIT){
                        out.println(MSGS);
                        deystvie=-1;
                        out.flush();
                        while(true){
                            if(Sinhron(1)==-1)
                                break;
                        }
                        System.out.println("otprav"+MSGS);}
                }
            }
            catch (Exception h) {
                h.printStackTrace();
            }
        }

    }

}

