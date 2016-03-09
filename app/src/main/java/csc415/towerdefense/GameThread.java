package csc415.towerdefense;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Sam on 3/1/2016.
 */
public class GameThread extends Thread{

    private final int FPS = 30;

    private SurfaceHolder surfaceHolder;
    private GameView gameView;
    public boolean running;

    public static Canvas canvas;

    public GameThread(SurfaceHolder s, GameView g){
        super();
        surfaceHolder = s;
        gameView = g;
    }


    @Override
    public void run(){
        running = true;
        float targetTime = 1000/FPS;
        long timeStart = System.currentTimeMillis();
        long timeEnd = System.currentTimeMillis() + (int)targetTime + 1, deltaTime;

        Log.d("target time", targetTime + "");

        while(running && !GameScreen.isPaused){

            if(timeEnd - timeStart > targetTime){
                //Log.d("deltaTime", (timeEnd - timeStart) + "");
                timeStart = System.currentTimeMillis();

                try {
                    canvas = this.surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {

                        this.gameView.update();
                        this.gameView.draw(canvas);
                    }
                } catch (Exception e) {}
                finally{
                    if(canvas!=null)
                    {
                        try {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                        catch(Exception e){e.printStackTrace();}
                    }
                }

            }else {
                try {
                    Thread.sleep((long) (targetTime - (timeEnd - timeStart)));
                } catch (Exception e) {}
            }

            timeEnd = System.currentTimeMillis();
        }

    }



}
