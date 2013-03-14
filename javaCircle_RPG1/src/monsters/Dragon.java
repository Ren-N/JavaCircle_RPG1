package monsters;

import javaCircle.kendai.Monster;
import javaCircle.kendai.R;
import android.content.Context;
import android.graphics.BitmapFactory;

public class Dragon extends Monster{

	public Dragon(Context context){
		this.hp = 5000;
		this.power = 350;
		this.defence = 500;
		this.name = "火竜";
		this.image = BitmapFactory.decodeResource(context.getResources(),R.drawable.red_dragon);
	}

}
