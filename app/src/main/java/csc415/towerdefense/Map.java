package csc415.towerdefense;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by Sam on 3/1/2016.
 */
public class Map {

    public Vector2f[] checkPoints;

    public Bitmap image;


    //-1 = part of path
    //0 = empty
    //1 = occupied
    public byte[][] placeable = new byte[10][10];

    public Map(Bitmap map, Vector2f[] checkpoints, Point[] placeableExclusions){
        image = Bitmap.createScaledBitmap(map, 640, 640, false);
        checkPoints = checkpoints;

        for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++){
                placeable[i][j] = 0;
            }

        for(Point p : placeableExclusions){
            placeable[p.y][p.x] = -1;
        }

    }


    public void draw(Canvas c) {

        c.drawBitmap(image, 0, 0, null);

    }


}
