package monsters;

import javaCircle.kendai.Monster;
import javaCircle.kendai.R;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.ProgressBar;


public class Kamakiri extends Monster{
	public Kamakiri(Context context,ProgressBar bar1){
		super(bar1);
		this.hp = 5000;
		this.power = 800;
		this.defence = 100;
		this.name = "かまきりの中のかまきり";
		this.image = BitmapFactory.decodeResource(context.getResources(),R.drawable.kamakiri);
	}


}
