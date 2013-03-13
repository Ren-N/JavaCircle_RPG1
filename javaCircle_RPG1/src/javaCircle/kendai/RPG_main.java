package javaCircle.kendai;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RPG_main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rpg_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rpg_main, menu);
		return true;
	}

}
