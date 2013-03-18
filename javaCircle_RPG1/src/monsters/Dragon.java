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
	public String attack(Monster target){
			if(this.hp>1500){
				int ran=(int)(Math.random()*10);//Math.random()は0~1を発生。×10で0~9をだす
				//1/2の確立で攻撃
				if(ran>4){
					target.dameged(this.power);
					return getName() + "の攻撃!!";
				}
			} else{
				//HPが少なくなると攻撃力があがる。そのうち、1/10の確立で中ダメージ。1/50の確立で大ダメージ
				int ran1=(int)(Math.random()*10); 
				int ran2=(int)(Math.random()*100);
				if(ran1 == 0){
					target.dameged((int) (this.power*1.5));
					return getName() + "の中攻撃!!";
				}else if(ran2 >49){
					target.dameged((int) (this.power*5));
					return getName() + "の特大攻撃!!";
				}else{
					target.dameged(this.power);
					return getName() + "の攻撃!!";
				}
			}
			return null;
	}
	@Override
	public boolean phaseCheack(int phase){
		if(phase%3==0){
			return true;
		}
		return false;
	}
}
