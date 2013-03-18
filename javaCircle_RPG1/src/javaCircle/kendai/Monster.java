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
	protected ProgressBar bar;
	//コンストラクタ
	public Monster(ProgressBar bar){
		this.bar = bar;
	}
	
	//攻撃!!
	public void attack(Monster target){
		target.dameged(this.power);
	}
	//ダメージ!!
	public void dameged(int power){
		this.hp -= (power - this.defence);
		this.bar.setProgress(this.hp);
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
	//敵用攻撃メソッド
	public int attack(Monster target, int phase) {
		// TODO 自動生成されたメソッド・スタブ
		//攻撃後は１、そうでないときは０を返す。呼び出し元でphaseに+=してループで繰り返し攻撃が実行されるのを防ぐ。
		return 0;
	}
}
