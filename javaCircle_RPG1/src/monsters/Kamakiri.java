package monsters;

import android.content.Context;
import android.graphics.BitmapFactory;
import javaCircle.kendai.Monster;
import javaCircle.kendai.R;


public class Kamakiri extends Monster{
	public Kamakiri(Context context){
		this.hp = 5000;
		this.power = 800;
		this.defence = 100;
		this.name = "かまきりの中のかまきり";
		this.image = BitmapFactory.decodeResource(context.getResources(),R.drawable.kamakiri);
	}


}
