package javaCircle.kendai;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ProgressBar;

public class Monster {

	protected int hp;
	protected int power;
	protected int defence;
	protected String name;
	protected Bitmap image;
	protected ProgressBar bar1;
	//コンストラクタ
	public Monster(ProgressBar bar1){
		this.bar1 = bar1;
	}
	
	//攻撃!!
	public void attack(Monster target){
		target.dameged(this.power);
	}
	//ダメージ!!
	public void dameged(int power){
		this.hp -= power - this.defence;
		this.bar1.setProgress(this.hp);
	}
	public String getName(){
		return this.name;
	}
	public int getHP(){
		return this.hp;
	}
	//描画
	public void draw(Canvas canvas,int x,int y){
    	Paint paint = new Paint();
    	canvas.drawBitmap(image,x,y,paint);
    }
}
