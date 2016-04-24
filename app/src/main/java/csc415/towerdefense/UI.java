package csc415.towerdefense;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by Sam on 3/5/2016.
 */
public class UI {

    public static Bitmap imageBackground;
    public static Bitmap imageTowerBase;
    public static Bitmap imagePelletTowerTurret;
    public static Bitmap imageCancel;
    public static Bitmap imageConfirm;
    public static Bitmap imageMoney;
    public static Bitmap imageHearts;
    public static Bitmap imageOverlay;
    public static Bitmap imageTowerHighlight;
    public static Bitmap imageTowerHighlightRed;
    public static Bitmap imagePopupBackground;
    public static Bitmap imageFreezeTowerTurret;
    public static Bitmap imageBombTowerTurret;
    public static Bitmap imagePoisonTowerTurret;
    public static Bitmap imageGameOver;

    public static Bitmap imageNextButton;
    public static Bitmap imageBackButton;

    public static Bitmap imageUpgradeButton;
    public static Bitmap imagePercentInterest;

    public static Bitmap imageReadyButton;

    public boolean isPlacing = false;
    public boolean isMenu = false;
    public boolean isMain = true;
    public boolean isPage2 = false;
    public boolean isUpgradeMenu = false;
    public boolean isUpgradeInterest = false;
    public boolean isSellMenu = false;

    public boolean isDead = false;
    public int framesToDisplayDeathScreen = 90;//3 seconds

    public Tower aboutToBuild = null;

    public Paint textPaint;
    public Paint popupTextPaint;
    public Paint smallTextPaint;


    public Paint sellPaint;
    public Paint moneyGainedPaint;

    public Paint upgradePaint;
    public Paint moneyLostPaint;

    public Paint radiusPaint;

    public final float FONT_SIZE = 64;
    public final float SMALL_FONT_SIZE = 20;

    public boolean showPopup = false;
    public String popupText = "";
    public int framesSincePopup;
    public int FRAMES_TO_DISPLAY_POPUP = 60; //2 seconds

    Context gameviewContext;

    public UI(Context gameviewContext) {
        this.gameviewContext = gameviewContext;
        textPaint = new Paint();
        smallTextPaint = new Paint();
        popupTextPaint = new Paint();
        sellPaint = new Paint();
        upgradePaint = new Paint();
        radiusPaint = new Paint();

        radiusPaint.setARGB(128, 255, 0, 0);

        smallTextPaint.setColor(Color.WHITE);
        smallTextPaint.setTextSize(20);

        popupTextPaint.setColor(Color.WHITE);
        popupTextPaint.setTextSize(32);

        textPaint.setTextAlign(Paint.Align.RIGHT);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(FONT_SIZE);

        moneyGainedPaint = new Paint(textPaint);
        moneyLostPaint = new Paint(textPaint);

        moneyGainedPaint.setColor(Color.GREEN);
        moneyLostPaint.setColor(Color.RED);

        sellPaint.setTextSize(28);
        sellPaint.setColor(Color.BLACK);
        sellPaint.setTextAlign(Paint.Align.CENTER);

        upgradePaint.setTextSize(28);
        upgradePaint.setColor(Color.BLACK);
        upgradePaint.setTextAlign(Paint.Align.CENTER);
    }


    public void draw(Canvas c) {

        if (!isSellMenu && !isUpgradeMenu && !isPlacing && isMain) {
            c.drawBitmap(imageBackground, 0, 640, null);

            c.drawBitmap(imageMoney, 565, 640 + 165 + 65 + 10, null);
            c.drawText(GameView.money + "", 565 - 10, 640 + 165 + 65 + 10 + 55, textPaint);

            c.drawText(GameView.currentWave.waveNumber + "", 640 - 10, 64, textPaint);

            c.drawBitmap(imageHearts, 565, 640 + 165, null);

            c.drawText(GameView.lives + "", 565 - 10, 640 + 165 + 55, textPaint);


            c.drawBitmap(imageTowerBase, 10, 640 + 10, null);
            c.drawBitmap(imagePelletTowerTurret, 10, 640 + 10, null);

            c.drawBitmap(imageTowerBase, 10 + 145 + 10, 640 + 10, null);
            c.drawBitmap(imageFreezeTowerTurret, 10 + 145 + 10, 640 + 10, null);

            c.drawBitmap(imageTowerBase, 10 + 145 + 10 + 145 + 10, 640 + 10, null);
            c.drawBitmap(imageBombTowerTurret, 10 + 145 + 10 + 145 + 10, 640 + 10, null);


            c.drawBitmap(imageTowerBase, 10 + 145 + 10 + 145 + 10 + 145 + 10, 640 + 10, null);
            c.drawBitmap(imagePoisonTowerTurret, 10 + 145 + 10 + 145 + 10 + 145 + 10, 640 + 10, null);

            c.drawBitmap(imageTowerBase, 10, 640 + 10 + 145 + 10, null);
            c.drawBitmap(imageMoney, 10 + 40, 640 + 10 + 145 + 10 + 25, null);
            drawCenteredTextAtPoint(c, sellPaint, "Sell", 10 + 72, 640 + 145 + 124 + 10);

            c.drawBitmap(imageTowerBase, 10 + 145 + 10, 640 + 10 + 145 + 10, null);
            c.drawBitmap(imageNextButton, 10 + 145 + 10, 640 + 10 + 145 + 10, null);
            drawCenteredTextAtPoint(c, upgradePaint, "Next", 10 + 72 + 145 + 10, 640  + 145 + 10 + 128);
        }


        if (!isSellMenu && !isUpgradeMenu && !isPlacing && isPage2) {
            c.drawBitmap(imageBackground, 0, 640, null);

            c.drawBitmap(imageMoney, 565, 640 + 165 + 65 + 10, null);
            c.drawText(GameView.money + "", 565 - 10, 640 + 165 + 65 + 10 + 55, textPaint);

            c.drawText(GameView.currentWave.waveNumber + "", 640 - 10, 64, textPaint);

            c.drawBitmap(imageHearts, 565, 640 + 165, null);

            c.drawText(GameView.lives + "", 565 - 10, 640 + 165 + 55, textPaint);


           // c.drawBitmap(imageTowerBase, 10, 640 + 10, null);
           // c.drawBitmap(imagePelletTowerTurret, 10, 640 + 10, null);

           // c.drawBitmap(imageTowerBase, 10 + 145 + 10, 640 + 10, null);
            //c.drawBitmap(imageFreezeTowerTurret, 10 + 145 + 10, 640 + 10, null);

           // c.drawBitmap(imageTowerBase, 10 + 145 + 10 + 145 + 10, 640 + 10, null);
           // c.drawBitmap(imageBombTowerTurret, 10 + 145 + 10 + 145 + 10, 640 + 10, null);


           c.drawBitmap(imageTowerBase, 10 + 145 + 10 + 145 + 10 + 145 + 10, 640 + 10, null);
           c.drawBitmap(imageMoney, 10 + 145 + 10 + 145 + 10 + 145 + 10 + 30 + 32, 640 + 10 + 39, null);
           c.drawBitmap(imagePercentInterest, 10 + 145 + 10 + 145 + 10 + 145 + 10 - 12 + 30, 640 + 10 + 8 + 43, null);


           c.drawBitmap(imageTowerBase, 10, 640 + 10 + 145 + 10, null);
            c.drawBitmap(imageMoney, 10 + 40, 640 + 10 + 145 + 10 + 25, null);
            drawCenteredTextAtPoint(c, sellPaint, "Sell", 10 + 72, 640 + 145 + 124 + 10);

            c.drawBitmap(imageTowerBase, 10 + 145 + 10, 640 + 10 + 145 + 10, null);
            c.drawBitmap(imageBackButton, 10 + 145 + 10, 640 + 10 + 145 + 10, null);
            drawCenteredTextAtPoint(c, upgradePaint, "Back", 10 + 72 + 145 + 10, 640  + 145 + 10 + 128);
        }

        if (isPlacing || isSellMenu || isUpgradeMenu || isUpgradeInterest) {

            if (aboutToBuild != null) {
                if((GameView.map.placeable[(int) aboutToBuild.pos.y][(int) aboutToBuild.pos.x] == -1 || GameView.map.placeable[(int) aboutToBuild.pos.y][(int) aboutToBuild.pos.x] == 1) && isPlacing) {
                    radiusPaint.setARGB(128, 255, 0, 0);
                }else{
                    radiusPaint.setARGB(128, 255, 255, 255);
                }
                c.drawCircle(aboutToBuild.pos.x * 64 + 32, aboutToBuild.pos.y * 64 + 32, aboutToBuild.range, radiusPaint);
                if (isPlacing)
                    c.drawBitmap(imageTowerHighlight, aboutToBuild.pos.x * 64 - 5, aboutToBuild.pos.y * 64 - 5, null);
                else
                    c.drawBitmap(imageTowerHighlight, aboutToBuild.pos.x * 64 - 5, aboutToBuild.pos.y * 64 - 5, null);

                aboutToBuild.draw(c);
            }

            c.drawBitmap(imageBackground, 0, 640, null);
            c.drawBitmap(imageMoney, 565, 640 + 165 + 65 + 10, null);
            c.drawText(GameView.money + "", 565 - 10, 640 + 165 + 65 + 10 + 55, textPaint);
            c.drawBitmap(imageOverlay, 0, 640, null);
            c.drawBitmap(imageCancel, 10, 640 + 10, null);
            c.drawBitmap(imageConfirm, 10, 640 + 10 + 145 + 10, null);




            if (isSellMenu) {
                if (aboutToBuild != null) {

                    if (aboutToBuild.cost / 2 >= 1000) {
                        moneyGainedPaint.setTextSize(50);
                    } else {
                        moneyGainedPaint.setTextSize(64);
                    }
                    c.drawText("+ " + (int) (aboutToBuild.cost / 2), 565 - 10, 640 + 165 + 55, moneyGainedPaint);
                }
            }

            if (isPlacing) {
                if (aboutToBuild != null) {
                    if (aboutToBuild.cost >= 1000) {
                        moneyLostPaint.setTextSize(50);
                    } else {
                        moneyLostPaint.setTextSize(64);
                    }

                    c.drawText("- " + aboutToBuild.cost, 565 - 10, 640 + 165 + 55, moneyLostPaint);
                    aboutToBuild.drawStats(c, popupTextPaint);
                }
            }

            if (isUpgradeMenu) {
                if (aboutToBuild != null) {

                    if (aboutToBuild.nextUpgradeCost >= 1000) {
                        moneyLostPaint.setTextSize(50);
                    } else {
                        moneyLostPaint.setTextSize(64);
                    }

                    c.drawText("- " + aboutToBuild.nextUpgradeCost, 565 - 10, 640 + 165 + 55, moneyLostPaint);
                    aboutToBuild.drawStats(c, popupTextPaint);

                }
            }

            if(isUpgradeInterest){


                c.drawText("- " + GameView.interestLevelCost, 565 - 10, 640 + 165 + 55, moneyLostPaint);

                c.drawText("Interest rate: ", 145 + 10 + 50, 640 + 80, popupTextPaint);
                c.drawText("" + String.format("%.1f", (GameView.interest * 100)) + "%", 145 + 10 + 50, 640 + 120, popupTextPaint);
                c.drawText("  →   " + String.format("%.1f", (GameView.getNextInterest() * 100)) + "%", 10 + 145 + 10 + 100, 640 + 120, popupTextPaint);

            }


            if (isSellMenu) {

            }

        }

        if (showPopup) {
            c.drawBitmap(imagePopupBackground, 80, 80, null);

            //c.drawText("Not enough money.", 320, 250, popupTextPaint);
            drawCenteredTextAtPoint(c, popupTextPaint, popupText, 110, 120);
            framesSincePopup++;
            if (framesSincePopup >= FRAMES_TO_DISPLAY_POPUP) {
                showPopup = false;
            }
        } else {
            framesSincePopup = 0;
        }

        if (GameView.currentWave.isFinished && !isPlacing && !isMenu && !isUpgradeMenu && !isSellMenu) {
            c.drawBitmap(imageReadyButton, 640 - 192, 640 - 128, null);
        }


        if(isDead){
            drawDeathScreen(c);
            framesToDisplayDeathScreen--;
            if(framesToDisplayDeathScreen <= 0){
                Intent myIntent = new Intent(gameviewContext, MainMenu.class);
                gameviewContext.startActivity(myIntent);
            }
        }

    }


    public boolean touched(Vector2f location) {
        if(!isDead) {
            if (showPopup) {
                showPopup = false;
                framesSincePopup = 0;
                return false;
            }


            if (GameView.currentWave.isFinished && !isPlacing && !isMenu && !isUpgradeMenu && !isSellMenu) {
                if (location.x > 640 - 192 && location.x < 640) {
                    if (location.y > 640 - 128 && location.y < 640) {

                        GameView.startNextWave();
                        return false;
                    }
                }
            }

            if (isUpgradeInterest) {

                if (location.y < 640) {

                    for (Tower t : GameView.towers) {
                        if (t.pos.x == (int) location.x / 64 && t.pos.y == (int) location.y / 64) {
                            aboutToBuild = t;
                            break;
                        }
                    }

                    if (aboutToBuild != null) {
                        isUpgradeMenu = true;
                        isMain = false;
                        isPage2 = false;
                        isUpgradeInterest = false;
                    }


                    return false;
                }

                if (location.x > 10 && location.x < 155) {
                    //First row of buttons
                    if (location.y > 650 && location.y < 795) {

                        isPage2 = true;
                        isUpgradeInterest = false;

                    }
                    //Second row of buttons
                    if (location.y > 805 && location.y < 950) {

                        if (GameView.money > GameView.interestLevelCost) {
                            GameView.upgradeInterest(true);
                        }

                    }

                }
            }

            if (isMain) {

                if (location.y < 640) {

                    for (Tower t : GameView.towers) {
                        if (t.pos.x == (int) location.x / 64 && t.pos.y == (int) location.y / 64) {
                            aboutToBuild = t;
                            break;
                        }
                    }

                    if (aboutToBuild != null) {
                        isUpgradeMenu = true;
                        isMain = false;
                        isPage2 = false;
                        isUpgradeInterest = false;
                    }


                    return false;
                }


                //First column of buttons
                if (location.x > 10 && location.x < 155) {
                    //First row of buttons
                    if (location.y > 650 && location.y < 795) {
                        Log.d("Touched button:", " Button 1");

                        if (GameView.money >= new PelletTower(-10000, 10000).cost) {
                            isPlacing = true;
                            isMain = false;
                            aboutToBuild = new PelletTower(4, 5);
                        } else {
                            //show indication of not enough money
                            popupText = "Not enough money.\nCost: " + new PelletTower(-10000, 10000).cost + "\n\nPellet tower";
                            showPopup = true;
                            FRAMES_TO_DISPLAY_POPUP = 60;
                        }
                    }
                    //Second row of buttons
                    if (location.y > 805 && location.y < 950) {
                        Log.d("Touched button:", " Button 5");

                        isSellMenu = true;
                        isMain = false;

                    }

                }
                //Second column of buttons
                if (location.x > 165 && location.x < 310) {
                    //First row of buttons
                    if (location.y > 650 && location.y < 795) {
                        Log.d("Touched button:", " Button 2");

                        if (GameView.money >= new FreezeTower(-10000, 10000).cost) {
                            isPlacing = true;
                            isMain = false;
                            aboutToBuild = new FreezeTower(4, 5);
                        } else {
                            //show indication of not enough money
                            popupText = "Not enough money.\nCost: " + new FreezeTower(-10000, 10000).cost+ "\n\nFreeze tower";
                            showPopup = true;
                            FRAMES_TO_DISPLAY_POPUP = 60;
                        }

                    }
                    //Second row of buttons
                    if (location.y > 805 && location.y < 950) {

                        isPage2 = true;
                        isMain = false;

                    }
                }
                //Third column of buttons
                if (location.x > 320 && location.x < 465) {
                    //First row of buttons
                    if (location.y > 650 && location.y < 795) {
                        Log.d("Touched button:", " Button 3");
                        if (GameView.money >= new CannonTower(-10000, 10000).cost) {
                            isPlacing = true;
                            isMain = false;
                            aboutToBuild = new CannonTower(4, 5);
                        } else {
                            //show indication of not enough money
                            popupText = "Not enough money.\nCost: " + new CannonTower(-10000, 10000).cost+ "\n\nCannon tower";
                            showPopup = true;
                            FRAMES_TO_DISPLAY_POPUP = 60;
                        }

                    }
                    //Second row of buttons
                    if (location.y > 805 && location.y < 950) {
                        // GameView.currentWave = new Wave(GameView.currentWave.waveNumber + 10);
                        // GameView.currentWave.isFinished = true;
                    }
                }
                //Fourth column of buttons
                if (location.x > 475 && location.x < 620) {
                    //First row of buttons
                    if (location.y > 650 && location.y < 795) {

                        if (GameView.money >= new PoisonTower(-10000, 10000).cost) {
                            isPlacing = true;
                            isMain = false;
                            aboutToBuild = new PoisonTower(4, 5);
                        } else {
                            //show indication of not enough money
                            popupText = "Not enough money.\nCost: " + new PoisonTower(-10000, 10000).cost+ "\n\nPoison tower";
                            showPopup = true;
                            FRAMES_TO_DISPLAY_POPUP = 60;
                        }
                    }
                    //Second row of buttons
                    if (location.y > 805 && location.y < 950) {
                        //GameView.money += 10000;
                    }
                }
                return false;

            } //End main ui buttons


            if (isPage2) {

                if (location.y < 640) {

                    for (Tower t : GameView.towers) {
                        if (t.pos.x == (int) location.x / 64 && t.pos.y == (int) location.y / 64) {
                            aboutToBuild = t;
                            break;
                        }
                    }

                    if (aboutToBuild != null) {
                        isUpgradeMenu = true;
                        isPage2 = false;
                        isMain = false;
                        isUpgradeInterest = false;
                    }


                    return false;
                }


                //First column of buttons
                if (location.x > 10 && location.x < 155) {
                    //First row of buttons
                    if (location.y > 650 && location.y < 795) {


                    }
                    //Second row of buttons
                    if (location.y > 805 && location.y < 950) {
                        isSellMenu = true;
                        isMain = false;
                        isPage2 = false;

                    }

                }
                //Second column of buttons
                if (location.x > 165 && location.x < 310) {
                    //First row of buttons
                    if (location.y > 650 && location.y < 795) {


                    }
                    //Second row of buttons
                    if (location.y > 805 && location.y < 950) {

                        isPage2 = false;
                        isMain = true;

                    }
                }
                //Third column of buttons
                if (location.x > 320 && location.x < 465) {
                    //First row of buttons
                    if (location.y > 650 && location.y < 795) {


                    }
                    //Second row of buttons
                    if (location.y > 805 && location.y < 950) {

                    }
                }
                //Fourth column of buttons
                if (location.x > 475 && location.x < 620) {
                    //First row of buttons
                    if (location.y > 650 && location.y < 795) {

                        isUpgradeInterest = true;
                        isPage2 = false;

                    }
                    //Second row of buttons
                    if (location.y > 805 && location.y < 950) {

                    }
                }
                return false;

            } //End 2nd page ui buttons


            if (isPlacing) {

                if (location.y < 640) {
                    aboutToBuild.pos = new Vector2f((int) location.x / 64, (int) location.y / 64);

                    return true;
                }

                if (location.x > 10 && location.x < 155) {
                    //First row of buttons
                    if (location.y > 650 && location.y < 795) {

                        aboutToBuild = null;

                        isMain = true;
                        isPlacing = false;
                    }
                    //Second row of buttons
                    if (location.y > 805 && location.y < 950) {
                        Log.d("Touched button:", " Button 5");
                        if (GameView.map.placeable[(int) aboutToBuild.pos.y][(int) aboutToBuild.pos.x] == 0) {
                            Log.d("pressed confirm", "pressed confirm");
                            GameView.towers.add(aboutToBuild);
                            GameView.map.placeable[(int) aboutToBuild.pos.y][(int) aboutToBuild.pos.x] = 1;

                            GameView.money -= aboutToBuild.cost;

                            aboutToBuild = null;

                            isMain = true;
                            isPlacing = false;
                        }
                    }

                }

                return false;

            } //end isPlacing

            if (isMenu) {


            } //end isMenu

            if (isSellMenu) {

                if (location.y < 640) {

                    for (Tower t : GameView.towers) {
                        if (t.pos.x == (int) location.x / 64 && t.pos.y == (int) location.y / 64) {
                            aboutToBuild = t;
                            break;
                        }
                    }

                    return false;
                }

                if (location.x > 10 && location.x < 155) {
                    //First row of buttons
                    if (location.y > 650 && location.y < 795) {
                        aboutToBuild = null;

                        isMain = true;
                        isUpgradeInterest = false;
                        isSellMenu = false;
                    }
                    //Second row of buttons
                    if (location.y > 805 && location.y < 950) {
                        if (aboutToBuild != null) {

                            GameView.towers.remove(aboutToBuild);
                            GameView.map.placeable[(int) aboutToBuild.pos.y][(int) aboutToBuild.pos.x] = 0;
                            GameView.money += (int) (aboutToBuild.cost / 2);
                            aboutToBuild = null;
                            isMain = true;
                            isSellMenu = false;
                            aboutToBuild = null;
                        }

                    }

                }

            } //end isSellMenu


            if (isUpgradeMenu) {
                if (location.y < 640) {

                    aboutToBuild = null;

                    for (Tower t : GameView.towers) {
                        if (t.pos.x == (int) location.x / 64 && t.pos.y == (int) location.y / 64) {
                            aboutToBuild = t;
                            break;
                        }
                    }

                    if (aboutToBuild == null) {
                        isMain = true;
                        isUpgradeMenu = false;
                    }

                    return false;
                }


                if (location.x > 10 && location.x < 155) {
                    //First row of buttons
                    if (location.y > 650 && location.y < 795) {


                        isMain = true;
                        isUpgradeMenu = false;
                        aboutToBuild = null;
                        //cancel
                    }
                    //Second row of buttons
                    if (location.y > 805 && location.y < 950) {
                        //confirm
                        if (GameView.money >= aboutToBuild.nextUpgradeCost) {
                            aboutToBuild.upgradeTower(true);
                        } else {
                            popupText = "Not enough money.";
                            showPopup = true;
                            FRAMES_TO_DISPLAY_POPUP = 60;
                        }

                    }

                }


            } // end isUpgradeMenu
        }
        return false;
    }

    private Rect textBounds = new Rect();

    public void drawCenteredTextAtPoint(Canvas canvas, Paint paint, String text, float cx, float cy) {
        String[] parts = text.split("\n"); //android cannot draw multiline text, kill me
        paint.getTextBounds(text, 0, text.length(), textBounds);
        for (int i = 0; i < parts.length; i++) {
            canvas.drawText(parts[i], cx, cy - textBounds.exactCenterY() + (textBounds.height() + 4) * i, paint);
        }


    }

    public void drawDeathScreen(Canvas c){

        c.drawBitmap(imageGameOver, 0, 0, null);

    }


}
