package javaCircle.kendai;

import android.graphics.Canvas;
import android.graphics.Point;

public class Enemy extends Party{
	//コンストラクタ
	public Enemy(Monster mon1,Monster mon2,Monster mon3,Point point){
		super(mon1,mon2,mon3,point);
	}

	//モンスターの描画
	@Override
	public void allDraw(Canvas canvas){
		if(this.mon1!=null){
			mon1.draw(canvas, 2*dispWidth/7, 2*dispHeight/5);
		}
		else if(this.mon2!=null){
			mon2.draw(canvas, 1*dispWidth/7, 2*dispHeight/5);
		}
		else if(this.mon3!=null){
			mon3.draw(canvas, 0, 2*dispHeight/5);
		}
	}
}
