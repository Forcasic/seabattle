package com.example.san4es.myapplication;
import java.lang.Object;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.*;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.os.SystemClock.sleep;


public class SeaBattleMain extends Activity implements View.OnTouchListener {
    private BattleView mBattleView;
    Button[] pole;
 int Select=0;


    private SeaBattleGame mSeaBattleGame;

     static int type_of_game=0;
int shipkol[];

 int hv=1;
    int player=1;
    int[][] play;
    int[][] play1;
    volatile boolean playerTurn = true;
    volatile boolean enemyTurn = false;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initApp();

    }

    private void initApp() {
        setContentView(R.layout.main);
    }


    /**
     * Handler to click "Start" button at the main layout;

     */
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

            b.setTextColor(getResources().getColor(R.color.colorPrimary));
        b.setText("Зеленый");
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


    public void exit(View v) {
this.finish();
       System.exit(1);
    }
    public void wyfy(View v) {
       finish();
        Intent intent = new Intent(SeaBattleMain.this, Wyfy.class);

        startActivity(intent);

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
        int b=0;//Постановка выбранного корабля
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
            //Постановка выбранного корабля на соответствующую координату
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

            }
            // в случае если в этой координате есть уже корабль то он удаляется
            else {
                    int s=RetKordOfShip(xi,yj);
                    shipkol[s]--;
                }}
           //поворот короблей
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
                        break;}
            }

        }
// выбор одиночного для постановки в поле игры
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
        // выбор двойного для постановки в поле игры
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
          // выбор тройного для постановки в поле игры
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
        // выбор самого большого для постановки в поле игры
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
        //кнопка в бой, она разблокируется после постановки всех кораблей
        if(v.getId()==R.id.fire){
            if(type_of_game==1){
            mBattleView = new BattleView(this);
            mBattleView.setOnTouchListener(this);
            setContentView(mBattleView);
             mSeaBattleGame = new SeaBattleGame();
            //update view model
                mBattleView.typeofgame=type_of_game;
            mSeaBattleGame.battleField.setPlayerField(play);
            mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(), mSeaBattleGame.getEnemyPlacement());
            mBattleView.invalidate();}
            else if(type_of_game==2){
                if(player==1) {
                    play1=new int[20][20];
                    for(int nn=0;nn<10;nn++)
                        for(int jjj=0;jjj<10;jjj++){

                        play1[nn][jjj]=play[nn][jjj];
                            play[nn][jjj]=0;


                        }
                    TextView we=findViewById(R.id.textView4);

                    we.setTextColor(Color.RED);
                    we.setText("Красный");
                    Select=0;
                    shipkol[1]=0;
                    shipkol[2]=0;
                    shipkol[3]=0;
                    shipkol[4]=0;
                    findViewById(R.id.one).setEnabled(true);
                    findViewById(R.id.two).setEnabled(true);
                    findViewById(R.id.three).setEnabled(true);
                    findViewById(R.id.four).setEnabled(true);
                    findViewById(R.id.fire).setEnabled(false);
                    player=2;
                }
                else {
                    mBattleView = new BattleView(this);
                    mBattleView.setOnTouchListener(this);
                    setContentView(mBattleView);
                    mSeaBattleGame = new SeaBattleGame();
                    //update view model
                    mBattleView.typeofgame=type_of_game;
                    mSeaBattleGame.battleField.setPlayerField(play1);
                    mSeaBattleGame.battleField.setPlayer2Field(play);
                    mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(), mSeaBattleGame.getEnemyPlacement());
                    mBattleView.invalidate();
                }
                }
            }
//автоматическая расстановка
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
    public void goToBattle(View v) {
        type_of_game=1;
        setContentView(R.layout.drav);
        goTo();

    }

    public void goToBattle1(View v) {
        type_of_game=2;
        setContentView(R.layout.drav);
        goTo();
    }
int hod=1;
    @Override
    public boolean onTouch(View v, MotionEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        int cellSize = mBattleView.getCellSize();

        int startBoardX = mBattleView.getStartEnemyFieldX();
        int endBoardX = cellSize * BattleField.N + startBoardX;
        int endBoardY = mBattleView.getEndEnemyFieldY();
        if(type_of_game==1){
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                
                if (x >= startBoardX && y <= endBoardY &&
                        x <= endBoardX) {
                    int ip = y / cellSize;
                    int jp = (x - startBoardX) / cellSize;
                    return playerAttackEnemy(ip, jp);
                }

            case MotionEvent.ACTION_UP:
                enemyAttackPlayer();
            case MotionEvent.ACTION_MOVE:

        }}
        if(type_of_game==2){
if (hod==1){
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //click only over enemy board to hit
                   if (x >= startBoardX && y <= endBoardY &&
                           x <= endBoardX) {
                        int ip = y / cellSize;
                        int jp = (x - startBoardX) / cellSize;
                        if(playerAttackEnemy(ip, jp)==true){
                            hod=2;
                            return true;
                        }
                        else return true;


                    }

                case MotionEvent.ACTION_UP:

                case MotionEvent.ACTION_MOVE:
                    //user incorrect touch
            }
            return true;
        }
else{
    switch (e.getAction()) {
        case MotionEvent.ACTION_DOWN:

            //click only over enemy board to hit
          //  if (x >= startBoardX && y <= endBoardY &&
                  //  x <= endBoardX) {
                int ip = y / cellSize;
                int jp = (x ) / cellSize;
                if(jp<=9&&ip<=9)
            if(enemyAttackPlayer(ip, jp)==true){
                hod=1;
                return true;
            }
            else return true;
           // }

        case MotionEvent.ACTION_UP:

        case MotionEvent.ACTION_MOVE:
            //user incorrect touch
    }
}}



        return true;
    }


    private boolean playerAttackEnemy(int i, int j){
        mBattleView.setTurn(playerTurn);
        mBattleView.invalidate();
        if (playerTurn) {
            if (!mSeaBattleGame.attackEnemy(i, j)) {
                //unsuccessful
                mBattleView.setTurn(false);
                mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                        mSeaBattleGame.getEnemyPlacement());

                //in fact draw red circle that means enemy turn now
                mBattleView.invalidate();
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
                mBattleView.invalidate();
                return false;
            }
            mBattleView.setTurn(true);
            mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                    mSeaBattleGame.getEnemyPlacement());
            mBattleView.invalidate();
            return false;
        }
        return false;
    }
    private boolean enemyAttackPlayer(int i, int j) {
        if (enemyTurn) {
            mBattleView.setTurn(false);
            mBattleView.invalidate();
            //while enemy is attacking only player's ships
            if (!mSeaBattleGame.attackPlayer(i, j)) {
                mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                        mSeaBattleGame.getEnemyPlacement());
                playerTurn = true;
                mBattleView.setTurn(true);
                mBattleView.invalidate();
                enemyTurn = false;
                return true;
            }
                int winner = mSeaBattleGame.getWinner();
                if (winner != -1) {
                    mBattleView.setOnTouchListener(null);
                    mBattleView.setWinner(winner);
                    mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                            mSeaBattleGame.getEnemyPlacement());
                   mBattleView.invalidate();
                    return false;
                }

            //hitting finished, cause enemy fluffed
            mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                    mSeaBattleGame.getEnemyPlacement());

            mBattleView.setTurn(false);
            mBattleView.invalidate();

            return false;
        }
        return false;
    }

    private boolean enemyAttackPlayer() {
        if (enemyTurn) {
            mBattleView.setTurn(false);
            mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                    mSeaBattleGame.getEnemyPlacement());
            mBattleView.invalidate();

            //while enemy is attacking only player's ships
            while (mSeaBattleGame.attackPlayer()) {
           /*   BattleView.wait=1;
                try {
                    sleep(500);

                } catch (Exception ie) {}*/
                mBattleView.setTurn(false);
                mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                        mSeaBattleGame.getEnemyPlacement());

                // mBattleView.onDraw1();
               // mBattleView.onDraw1();

                int winner = mSeaBattleGame.getWinner();

                mBattleView.invalidate();
                try {
                     sleep(500);
                } catch (Exception ie) {}
                if (winner != -1) {
                    mBattleView.setOnTouchListener(null);
                    mBattleView.setWinner(winner);
                    mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                            mSeaBattleGame.getEnemyPlacement());
                   mBattleView.invalidate();


                    return false;
                }
            }
            //hitting finished, cause enemy fluffed
            mBattleView.setBoard(mSeaBattleGame.getPlayerPlacement(),
                    mSeaBattleGame.getEnemyPlacement());
            playerTurn = true;
            mBattleView.setTurn(true);
            mBattleView.invalidate();
            enemyTurn = false;
        }
        return true;
    }



    @Override public void onBackPressed(){
        this.finish();
        Intent intent = new Intent(SeaBattleMain.this, SeaBattleMain.class);
        startActivity(intent);

    }

}
