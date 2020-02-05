package com.example.san4es.myapplication;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;





    class  BattleView   extends SurfaceView implements SurfaceHolder.Callback {
        public Bitmap bitmap;
        public Paint paint;
        /** Use true - player is making move, false - pc
         * used to determine who making move and red appropriate
         * (red/green) circle notification. */

        public boolean turn = true;
        /**
         * 1 if player won
         * 2 if enemy won,
         * -1 if nobody have won yet.
         */
        public int winner = -1;
        //dynamic cell size for viewing at miscellaneous screens.
        public static  int cellSize ;
        //start of  player's board
        public int xStart = 0, yStart = 0;
        //
        public int endEnemyFieldY;
        public  int startEnemyFieldX;
        //updated model
        public int[][] player;
        public int[][] enemy;

        private DrawThread drawThread;

        public  BattleView  (Context context) {
            super(context);
            getHolder().addCallback(this);
           paint = new Paint();

            bitmap = BitmapFactory.decodeResource(
                    getResources(),
                    R.drawable.battle2);

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            drawThread = new DrawThread(getHolder());
            drawThread.setRunning(true);
            drawThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            drawThread.setRunning(false);
            while (retry) {
                try {
                    drawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }

public void startgameofwifi(Canvas canvas){
    paint.setColor(Color.rgb(105,105,105));
    paint.setTextSize(72);
    int xxx=getApproxXToCenterText("Tap on the screen)!",72,getWidth());
    canvas.drawText("Tap on the screen)",xxx,
            getHeight()/2,paint );

}
        public static int getApproxXToCenterText(String text, int fontSize, int widthToFitStringInto) {
            Paint p = new Paint();
           // p.setTypeface(typeface);
            p.setTextSize(fontSize);
            float textWidth = p.measureText(text);
            int xOffset = (int)((widthToFitStringInto-textWidth)/2f) - (int)(fontSize/2f);
            return xOffset;
        }
        public boolean checkWinnerone(Canvas canvas) {
            if(winner != -1) {
                paint.setColor(Color.MAGENTA);
                paint.setTextSize(72);
                //canvas.drawRect(150, 150, getWidth() * 0.7f, getHeight() * 0.7f, paint);
                if(winner == 1){
                    int xxx=getApproxXToCenterText("You won!",72,getWidth());
                    canvas.drawText("You won!",xxx,
                            getHeight()/2,paint );}
                else { int xxx=getApproxXToCenterText("You won!",72,getWidth());
                    canvas.drawText("Game over!",xxx,
                        getHeight()/2, paint );}
                return true;
            }
            return false;
        }


        public boolean checkWinnertwo(Canvas canvas) {
            if(winner != -1) {
                paint.setColor(Color.MAGENTA);
                paint.setTextSize(72);
                //canvas.drawRect(150, 150, getWidth() * 0.7f, getHeight() * 0.7f, paint);
                if(winner == 1){
                    int xxx=getApproxXToCenterText("GREEN is WINNER!!!",72,getWidth());
                    canvas.drawText("GREEN is WINNER!!!!",xxx,
                            getHeight()/2,paint );}
                else { int xxx=getApproxXToCenterText("RED is WINNER!!!!",72,getWidth());
                    canvas.drawText("RED is WINNER!",xxx,
                            getHeight()/2, paint );}
                return true;
            }
            return false;
        }

        public boolean checkWinnerwyfi(Canvas canvas) {
            if(winner != -1) {
                paint.setColor(Color.MAGENTA);
                paint.setTextSize(72);
                //canvas.drawRect(150, 150, getWidth() * 0.7f, getHeight() * 0.7f, paint);
                if(Wyfy.serverClass!=null){
                if(winner == 1){
                    int xxx=getApproxXToCenterText("You WON!",72,getWidth());
                    canvas.drawText("You WON!",xxx,
                            getHeight()/2,paint );}
                else { int xxx=getApproxXToCenterText("Game over!)",72,getWidth());
                    canvas.drawText("Game over!)",xxx,
                            getHeight()/2, paint );}}
                            else{
                    if(winner == 1){
                        int xxx=getApproxXToCenterText("You WON!",72,getWidth());
                        canvas.drawText("Game over!)",xxx,
                                getHeight()/2,paint );}
                    else { int xxx=getApproxXToCenterText("Game over!)",72,getWidth());
                        canvas.drawText("You WON!",xxx,
                                getHeight()/2, paint );}

                }
                return true;
            }
            return false;
        }


        public void drawShips(Canvas canvas) {
            Paint rp = new Paint();
            for(int i = 0; i < BattleField.N; ++i)
                for(int j = 0; j < BattleField.N;++j) {
                    if(SeaBattleMain.type_of_game==1){
                        if (player[i][j] == BattleField.SHIP) {
                            rp.setARGB(150, 0, 150, 100);
                            canvas.drawRect( j * cellSize,i * cellSize,
                                    (j + 1) * cellSize, (i + 1) * cellSize, rp);
                        }}
                    if(typeofgame==3&& Wyfy.serverClass!=null){
                        if (player[i][j] == BattleField.SHIP) {
                            rp.setARGB(150, 0, 150, 100);
                            canvas.drawRect( j * cellSize,i * cellSize,
                                    (j + 1) * cellSize, (i + 1) * cellSize, rp);
                        }}
                    if(player[i][j] == BattleField.ATTACKED){
                        rp.setARGB(150, 0, 0, 100);
                        canvas.drawRect( j * cellSize,i * cellSize,
                                (j + 1) * cellSize,(i + 1) * cellSize,  rp);
                    }
                    else if(player[i][j] == BattleField.ATTACKED_SHIP) {
                        rp.setARGB(150, 250, 0, 0);
                        canvas.drawRect( j * cellSize, i * cellSize,
                                (j + 1) * cellSize,(i + 1) * cellSize, rp);
                    }

                }
            canvas.translate(startEnemyFieldX,0);

            for(int i = 0; i < BattleField.N; ++i)
                for(int j = 0; j < BattleField.N;++j) {
                if(typeofgame==3&& Wyfy.clientClass!=null){
                   if (enemy[i][j] == BattleField.SHIP) {
                        rp.setARGB(150, 0, 150, 100);
                        canvas.drawRect( j * cellSize,i * cellSize,
                                (j + 1) * cellSize, (i + 1) * cellSize, rp);
                    }}
                    if (enemy[i][j] == BattleField.ATTACKED) {
                        rp.setARGB(150, 0, 0, 100);
                        //rp.setARGB(150, 196, 0, 171);
                        canvas.drawRect( j * cellSize, i * cellSize,
                                (j + 1) * cellSize, (i + 1) * cellSize, rp);
                    }
                    else if(enemy[i][j] == BattleField.ATTACKED_SHIP) {
                        rp.setARGB(150, 250, 0, 0);
                        canvas.drawRect(j * cellSize,i * cellSize,
                                (j + 1) * cellSize, (i + 1) * cellSize,  rp);
                    }
                }

        }


       public void drawBoardplayer(Canvas canvas) {
            cellSize = getWidth()/(2*BattleField.N ) - 8;
            paint.setAntiAlias(true);
            paint.setColor(Color.WHITE);
            //draw player
            int n = BattleField.N * cellSize;
            //vertical lines
            for(int i = 0; i <= n; i += cellSize)
                canvas.drawLine(i, yStart, i, n, paint );

            //horizontal lines
            for(int i = 0; i <= n; i += cellSize)
                canvas.drawLine(xStart, i, n, i, paint );

        }

        private void drawBoard(Canvas canvas) {
            cellSize = getWidth()/(2*BattleField.N ) - 8;
            paint.setAntiAlias(true);
            paint.setColor(Color.WHITE);
            //draw player
            int n = BattleField.N * cellSize;
            //vertical lines
            for(int i = 0; i <= n; i += cellSize)
                canvas.drawLine(i, yStart, i, n, paint );
            paint.setTextSize(72);
            paint.setColor(Color.RED);
            canvas.drawText("Красный!",cellSize,
                    n+72,paint );
            paint.setColor(Color.WHITE);
            //horizontal lines

            for(int i = 0; i <= n; i += cellSize)
                canvas.drawLine(xStart, i, n, i, paint );
            //draw indicator
            if(turn)
                paint.setColor(getResources().getColor(R.color.colorPrimary));
            else
                paint.setColor(Color.RED);

            canvas.drawCircle(n+(getWidth()-20*cellSize)/2, getHeight()/2,20,paint);
            //draw enemy
            startEnemyFieldX = n + (getWidth()-20*cellSize);
            endEnemyFieldY = cellSize * BattleField.N;
            canvas.translate(startEnemyFieldX,0);
            paint.setTextSize(72);
            paint.setColor(getResources().getColor(R.color.colorPrimary));
            canvas.drawText("Зеленый!",cellSize,
                    n+72,paint );
            paint.setColor(Color.BLACK);
            //vertical lines
            for(int i = 0; i <= n; i += cellSize)
                canvas.drawLine(i, yStart, i, n, paint );
            //horizontal lines
            for(int i = 0; i <= n; i += cellSize)
                canvas.drawLine(xStart, i, n, i, paint );
        }

public int pereris=0;
        public void setBoard(int[][] player, int[][] enemy){
            this.player = new int[BattleField.N][BattleField.N];
            this.enemy = new int[BattleField.N][BattleField.N];
            for(int i = 0; i <BattleField.N; ++i)
                for(int j = 0; j <BattleField.N; ++j){
                    this.player[i][j] = player[i][j];
                    this.enemy[i][j] = enemy[i][j];
                }
                pereris=1;
        }

        public void setCell(boolean player){
            int[][] field;
            if(player)
                field = this.player;
            else field = this.enemy;

        }

        public void setTurn(boolean t) {
            this.turn = t;
        }

        public void setWinner(int winner) {
            this.winner = winner;
        }

        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            // Defines the extra padding for the shape name text
            int textPadding = 10;
            int shapeWidth = 100;
            int contentWidth = shapeWidth;
            // Resolve the width based on our minimum and the measure spec
            int minw = contentWidth + getPaddingLeft() + getPaddingRight();
            int w = resolveSizeAndState(minw, widthMeasureSpec, 0);
            // Ask for a height that would let the view get as big as it can
            int shapeHeight = 100;
            int minh = shapeHeight + getPaddingBottom() + getPaddingTop();
            int h = resolveSizeAndState(minh, heightMeasureSpec, 0);
            // Calling this method determines the measured width and height
            // Retrieve with getMeasuredWidth or getMeasuredHeight methods later
            setMeasuredDimension(w, h);
        }

        public int getEndEnemyFieldY() {
            return endEnemyFieldY;
        }

        public int getCellSize() {
            return cellSize;
        }

        public int getStartEnemyFieldX() {
            return startEnemyFieldX;
        }

        public int typeofgame=1;
public int Start=1;
        class DrawThread extends Thread {

            private boolean running = false;
            private SurfaceHolder surfaceHolder;

            public DrawThread(SurfaceHolder surfaceHolder) {
                this.surfaceHolder = surfaceHolder;
            }

            public void setRunning(boolean running) {
                this.running = running;
            }

            @Override
            public void run() {
                Canvas canvas;
                while (running) {
                    canvas = null;
                    try {
                        if(pereris==1) {
                        canvas = surfaceHolder.lockCanvas(null);
                        if (canvas == null)
                            continue;
                            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
                            canvas.drawBitmap(bitmap, 0, 0, null);
                            canvas.save();
                            drawBoard(canvas);
                            canvas.restore();
                            if(Start==1&&typeofgame==3)
                            startgameofwifi(canvas);

                            canvas.save();
                            drawShips(canvas);
                            canvas.restore();
                            if(typeofgame==1)
                            checkWinnerone(canvas);
                            if(typeofgame==2)
                                checkWinnertwo(canvas);
                            if(typeofgame==3)
                                checkWinnerwyfi(canvas);
                         pereris=0;
                        }

                    } finally {
                        if (canvas != null) {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }

}









