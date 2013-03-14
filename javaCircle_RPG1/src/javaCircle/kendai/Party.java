package javaCircle.kendai;

import android.graphics.Canvas;
import android.graphics.Point;

public class Party {
	protected Monster mon1;
	protected Monster mon2;
	protected Monster mon3;
	protected int dispWidth;
	protected int dispHeight;
	//コンストラクタ
	public Party(Monster mon1,Monster mon2,Monster mon3,Point point){
		this.mon1 = mon1;
		this.mon2 = mon2;
		this.mon3 = mon3;
		this.dispHeight = point.y;
		this.dispWidth = point.x;
	}
	
	//攻撃するモンスターを選択
	public Monster getMonster(int num){
		if(num == 1){
			return mon1;
		}
		else if(num == 2){
			return mon2;
		}
		else if(num == 3){
			return mon3;
		}
		return null;
	}
	//モンスターを入れる
	public void setMonster(Monster mon){
		if(mon1 == null){
			mon1 = mon;
		}
		else if(mon2 == null){
			mon2 = mon;
		}
		else if(mon3 == null){
			mon3 = mon;
		}
		//引数がnullなら全部nullにする
		else mon1 = mon2 = mon3 = null;
	}
	//モンスターの描画
	public void allDraw(Canvas canvas){
		if(this.mon1!=null){
			mon1.draw(canvas, 0, 4*dispHeight/5);
		}
		else if(this.mon2!=null){
			mon2.draw(canvas, 1*dispWidth/3, 4*dispHeight/5);
		}
		else if(this.mon3!=null){
			mon3.draw(canvas, 2*dispWidth/3, 4*dispHeight/5);
		}
	}
}
