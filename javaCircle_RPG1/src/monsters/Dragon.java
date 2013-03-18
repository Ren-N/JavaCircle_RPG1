package monsters;

import javaCircle.kendai.Monster;
import javaCircle.kendai.R;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.ProgressBar;

public class Dragon extends Monster{

	public Dragon(Context context,ProgressBar bar){
		super(bar);
		this.hp = 3000;
		this.bar.setMax(this.hp);
		this.power = 700;
		this.defence = 500;
		this.name = "火竜";
		this.image = BitmapFactory.decodeResource(context.getResources(),R.drawable.red_dragon);
	}

	@Override
	public int attack(Monster target,int phase){
	/*	if(phase%3==0){
			target.dameged(this.power);
		}*/
		
		//3の倍数のターンのとき攻撃してくる。
		if(phase%3 ==0){
			if(this.hp>1500){
				int ran=(int)(Math.random()*10);//Math.random()は0~1を発生。×10で0~9をだす
				//1/2の確立で攻撃
				if(ran>4){
					target.dameged(this.power);
					return 1;
				}
			} else{
				//HPが少なくなると攻撃力があがる。そのうち、1/100の確立で中ダメージ。1/50の確立で大ダメージ
				int ran1=(int)(Math.random()*10); 
				int ran2=(int)(Math.random()*100);
				if(ran1 == 4){
					target.dameged((int) (this.power*1.5));
				}else if(ran2 == 5){
					target.dameged((int) (this.power*5));
				}else{
					target.dameged(this.power);
				}
				return 1;
			}
		}
		return 0;
		
	}
}
