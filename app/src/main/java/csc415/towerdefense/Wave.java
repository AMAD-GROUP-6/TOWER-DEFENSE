package csc415.towerdefense;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Sam on 3/5/2016.
 */
public class Wave extends Thread {


    //doesn't allow more than 1 wave at a time.
    public CopyOnWriteArrayList<Enemy> enemies = new CopyOnWriteArrayList<Enemy>();

    int waveNumber = 0;
    float delaySeconds = 0.75f;

    float speed = 2f;
    int health = 10;

    boolean isFinished = false;

    int numEnemiesToSpawn = 1;
    int numEnemiesSpawned = 0;

    public int livesAtBeginning = 100;

    public Wave(int wave)
    {
        waveNumber = wave;
        numEnemiesToSpawn = (int) Math.min(8 + 2 * waveNumber, 100);

        if(waveNumber < 46){
            numEnemiesToSpawn = 8 + 2 * waveNumber;
        }else{
            numEnemiesToSpawn = 100;
        }

        livesAtBeginning = GameView.lives;

        //1, 6, 11, etc.
        if(waveNumber + 4 % 5 == 0){
            delaySeconds = 0.75f;
            speed = 2f;
        }
        //2, 7, 12, etc.
        else if(waveNumber + 3 % 5 == 0){
            delaySeconds = 0.15f;
            speed = 2f;
        }
        //3, 8, 13, etc.
        else if(waveNumber + 2 % 5 == 0){
            delaySeconds = 0.75f;
            speed = 4f;
        }
        //4, 9, 14, etc.
        else if(waveNumber + 1 % 5 == 0){
            delaySeconds = 0.5f;
            speed = 3f;
        }
        //5, 10, 15, etc.
        else if(waveNumber + 0 % 5 == 0){
            delaySeconds = 0.5f;
            speed = 2.5f;
        }



    }

    @Override
    public void run() {
        if(waveNumber != 0){
            while (numEnemiesSpawned < numEnemiesToSpawn) {

                int temp = (int)(Math.random() * 16);
                                           //w, h, health, speed, reward
                enemies.add(new BasicEnemy(16 + temp, 16 + temp, (long)(10 * Math.pow(1.1, waveNumber)), speed, (long)waveNumber/2 + 1));
                numEnemiesSpawned++;

                try {
                    Thread.sleep((int) (delaySeconds * 1000));
                } catch (Exception e) {

                }

            }

            if(waveNumber % 5 == 0){
                int temp = 0;
                while(temp <= waveNumber){

                    try{
                        Thread.sleep(5000);
                    }catch(Exception e){}

                    enemies.add(new BasicEnemy(64, 64, (long)((waveNumber < 25 ? 1.4f: 1) * 100) * (long) Math.pow(1.1, waveNumber), 2, waveNumber * 50));
                    temp+=25;

                }

            }


            while(enemies.size() > 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }

            if(GameView.lives == livesAtBeginning){
                GameView.money *= (1+GameView.interest);
                GameView.ui.popupText = "Congratulations!\n\nYou have completed this\nwave without losing any lives.\n\nYou have received\n" + String.format("%.1f", GameView.interest*100) + "% interest on\nyour coins.";
                GameView.ui.showPopup = true;
                GameView.ui.FRAMES_TO_DISPLAY_POPUP = 120;
            }

            GameView.saveGameInfo();
        }



        isFinished = true;
    }


}
