package monsters;

import javaCircle.kendai.Monster;
import javaCircle.kendai.R;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.ProgressBar;

public class Dragon extends Monster{

	public Dragon(Context context,ProgressBar bar){
		super(bar);
		this.hp = 2000;
		this.bar.setMax(this.hp);
		this.power = 350;
		this.defence = 500;
		this.name = "火竜";
		this.image = BitmapFactory.decodeResource(context.getResources(),R.drawable.red_dragon);
	}

}
