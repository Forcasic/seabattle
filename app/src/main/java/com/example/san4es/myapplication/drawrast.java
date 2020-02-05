package com.example.san4es.myapplication;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



    class drawrast extends SurfaceView implements SurfaceHolder.Callback {
        public Paint paint;
        private DrawThread drawThread;
         public Bitmap bitmap;
        private int winner = -1;
        //dynamic cell size for viewing at miscellaneous screens.
        private static  int cellSize ;
        //start of  player's board
        private int xStart = 0, yStart = 0;
        //
        private int endEnemyFieldY;
        private  int startEnemyFieldX;
        //updated model
        private int[][] player;
        private int[][] enemy;
        public drawrast(Context context) {
            super(context);
            paint = new Paint();
            bitmap = BitmapFactory.decodeResource(
                    getResources(),
                    R.drawable.battle1);
            getHolder().addCallback(this);
        }

        private void drawShips(Canvas canvas) {
            Paint rp = new Paint();
            for(int i = 0; i < BattleField.N; ++i)
                for(int j = 0; j < BattleField.N;++j) {
                    if(SeaBattleMain.type_of_game==1){
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
                    if(SeaBattleMain.type_of_game==1){
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
        private void drawBoard(Canvas canvas) {
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
                        canvas = surfaceHolder.lockCanvas(null);
                        if (canvas == null)
                            continue;
                        canvas.drawColor(Color.GREEN);
                    } finally {
                        if (canvas != null) {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }

    }

