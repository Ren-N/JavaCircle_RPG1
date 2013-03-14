package javaCircle.kendai;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class RPG_main extends Activity {
	private SurfaceView sur;
	private Battle_MainPanel mainpanel;
	private TextView text;
	private Button button1;
	private Button button2;
	private int num1 =1;
	private int num2 =1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rpg_main);
		//テキスト-------------------------------------------------------------------------------------
		text = (TextView)findViewById(R.id.textView1);
		text.setBackgroundColor(Color.BLACK);
		text.setTextColor(Color.WHITE);
		text.setTextSize(20.0f);
		/*他のクラスでtextViewUIを扱うのでHandlerを使って橋渡し*/
		Handler mHandler =  new Handler(){
            //メッセージ受信
            public void handleMessage(Message message) {
                //メッセージの表示
                text.setText((String) message.obj);
            };
        };
      //ディスプレイのサイズの取得-----------------------------------------------------------------------------
      		WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
      		Display disp = wm.getDefaultDisplay();
      		int dispHeight = disp.getWidth();
      		int dispWidth = disp.getHeight();
      		Point point = new Point(dispWidth,dispHeight);	/*xとyの値をもてるクラス*/
        //メインパネル----------------------------------------------------------------------------------------
		/*xmlで生成したsurfaceviewの参照*/
		sur = (SurfaceView)findViewById(R.id.Battle_MainPanel);
		mainpanel = new Battle_MainPanel(this, sur,text,mHandler,point);
		//ボタン---------------------------------------------------------------------------------------------
		button1 = (Button)findViewById(R.id.button1);
		button2 = (Button)findViewById(R.id.button2);
		/*ボタンのアクション*/
		button1.setOnTouchListener(new View.OnTouchListener(){
			  public boolean onTouch(View v, MotionEvent event){
			    if (event.getAction() == MotionEvent.ACTION_DOWN) {
			      //押したとき
			    } else if (event.getAction() == MotionEvent.ACTION_UP) {
			      //離したとき
			    	mainpanel.attackButton(num1, num2);
			    }
			    return false;
			  }
			});
		button2.setOnTouchListener(new View.OnTouchListener(){
			  public boolean onTouch(View v, MotionEvent event){
			    if (event.getAction() == MotionEvent.ACTION_DOWN) {
			      //押したとき
			    } else if (event.getAction() == MotionEvent.ACTION_UP) {
			      //離したとき
			    	mainpanel.EscapeButton();
			    }
			    return false;
			  }
			});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rpg_main, menu);
		return true;
	}

}
