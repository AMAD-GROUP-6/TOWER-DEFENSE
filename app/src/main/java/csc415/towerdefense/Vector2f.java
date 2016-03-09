package csc415.towerdefense;

/**
 * Created by Sam on 3/1/2016.
 */
public class Vector2f {

    float x, y;

    public Vector2f(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2f normalized( Vector2f vector){

        float mag = vector.x * vector.x + vector.y* vector.y;
        mag = (float)Math.sqrt(mag);
        return new Vector2f(vector.x / mag, vector.y/mag);

    }

    public void normalize(){

        float mag = x*x + y*y;
        mag = (float)Math.sqrt(mag);
        x = x / mag;
        y = y/mag;

    }

    public float distance(Vector2f b){

        return (float) Math.sqrt(Math.pow(x - b.x, 2) + Math.pow(y-b.y, 2));

    }


    public Vector2f minus(Vector2f a){
        return new Vector2f(x- a.x, y- a.y);
    }

    public void add(Vector2f a){
        x = x + a.x;
        y = y + a.y;
    }

    public void subtract(Vector2f a){
        x = x - a.x;
        y = y - a.y;
    }

    public Vector2f plus(Vector2f a){
        return new Vector2f(x+ a.x, y+ a.y);
    }

    public Vector2f multipliedBy(Vector2f a){
        return new Vector2f(x*a.x, y*a.y);
    }
    public Vector2f multipliedBy(float a){
        return new Vector2f(x*a, y*a);
    }
    public void multiply(Vector2f a){
        x = x * a.x;
        y = y * a.y;
    }
    public void multiply(float a){
        x = x * a;
        y = y * a;
    }

}
