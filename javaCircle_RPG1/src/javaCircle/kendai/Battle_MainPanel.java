package javaCircle.kendai;


import monsters.Dragon;
import monsters.Kamakiri;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Battle_MainPanel extends SurfaceView implements Callback,Runnable{
	//【メンバ】
	private SurfaceHolder holder;
	private Thread gameloop;
	private boolean loop = true;
	private Party player;
	private Enemy enemy;
	private boolean AtkPressed = false;
	private boolean EscPressed = false;
	private int num1,num2;
	private Bitmap backImage;
	private Context context;
	private TextView textview;
	private Handler mHandler;
	private Point point;
	private int dispHeight,dispWidth;
	
	//【コンストラクタ】=================================================================================================
	public Battle_MainPanel(Context context,SurfaceView sv,TextView textview,Handler a,Point point,ProgressBar bar1,ProgressBar bar2) {
		super(context);
		this.context = context;
		this.textview = textview;
		this.mHandler = a;
		this.point = point;
		this.dispHeight = point.y;
		this.dispWidth = point.x;
		/*xmlで生成したBattle_MainPanel(SurfaceView)のholderを得て,callbackにこのクラスに追加。
		 * そうすると生成時にsurfaceCreatedが実行される。*/
		holder = sv.getHolder();
		holder.addCallback(this);
		//自分のモンスターのセット
		Kamakiri kama1 = new Kamakiri(context,bar2);
		player = new Party(kama1,null,null,point);
		//敵モンスターのセット
		/*できればランダムにするアルゴリズムを作る*/
		Dragon dra1 = new Dragon(context,bar1);
		enemy = new Enemy(dra1,null,null,point);
			
	}//=========================================================================================================
	
	//【メソッド】===================================================================================================
	//文字表示-----------------------------------------------------------------------------------------------
	/*文字表示画面に渡すテキストを渡す*/
	public void setText(String text){
		//UI操作のためhandlerによりlooperにメッセージのキューイング
        Message msg = Message.obtain(); 
        msg.obj = text;
        //ハンドラへのメッセージ送信
        mHandler.sendMessage(msg);
	}
	
	//攻撃!!------------------------------------------------------------------------------------------------
	/*activityでモンスター選択用のnum2=1を定義。選択するとnum=3のように代入。選択しない場合、初期のnum=1で実行。
	num1は自分のどのモンスターで攻撃するか。*/
	private void Attack(int num1,int num2){
		Monster attacker;
		Monster target;
		//引数でどのモンスターで攻撃するかを選択。
		attacker = player.getMonster(num1);
		//引数でどのモンスターを攻撃するか選択。
		target = enemy.getMonster(num2); 
		//攻撃!!
		attacker.attack(target);
		
		String text = attacker.getName() + "は" +target.getName() + "を攻撃。";
		this.setText(text);
		
		//敵モンスターの点滅(今回はnum2=1のとき。これもifで場合わけ。今回は割愛。)
		for(int i=0;i<2;i++){
			Canvas canvas = holder.lockCanvas();
			this.setStage(canvas);
			if(player.getMonster(1)!=null){
    		player.getMonster(1).draw(canvas, 0, 4*dispHeight/5);
			}
			if(player.getMonster(2)!=null){
				player.getMonster(2).draw(canvas, 1*dispWidth/3, 4*dispHeight/5);
			}
	    	if(player.getMonster(3)!=null){
	    		player.getMonster(3).draw(canvas, 2*dispWidth/3, 4*dispHeight/5);
	    	}
	    	/*if(enemy.getMonster(2)!=null){
	    		enemy.getMonster(2).draw(canvas, 2*dispWidth/7, 2*dispHeight/5);
	    	}
	    	if(enemy.getMonster(3)!=null){
	    		enemy.getMonster(3).draw(canvas, 1*dispWidth/7, 2*dispHeight/5);
	    	}*/
	    	holder.unlockCanvasAndPost(canvas);
	    	try{
				Thread.sleep(20);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
	    	canvas = holder.lockCanvas();
			this.setStage(canvas);
	    	if(player.getMonster(1)!=null){
	    		player.getMonster(1).draw(canvas, 0, 4*dispHeight/5);
	    	}
	    	if(player.getMonster(2)!=null){
	    		player.getMonster(2).draw(canvas, 1*dispWidth/3, 4*dispHeight/5);
	    	}
	    	if(player.getMonster(3)!=null){
	    		player.getMonster(3).draw(canvas, 2*dispWidth/3, 4*dispHeight/5);
	    	}
	    	if(enemy.getMonster(1)!=null){
	    		enemy.getMonster(1).draw(canvas, 2*dispWidth/7, 2*dispHeight/5);
	    	}
	    	if(enemy.getMonster(2)!=null){
	    		enemy.getMonster(2).draw(canvas, 2*dispWidth/7, 2*dispHeight/5);
	    	}
	    	if(enemy.getMonster(3)!=null){
	    		enemy.getMonster(3).draw(canvas, 1*dispWidth/7, 2*dispHeight/5);
	    	}
	    	holder.unlockCanvasAndPost(canvas);
	    	try{
				Thread.sleep(20);
			} catch(InterruptedException e){
				e.printStackTrace();
			}

		}
		
	}
	
	//逃げる!!-----------------------------------------------------------------------------------------------------
	private void Escape(){
		loop = false;	
		
		String text = "逃げた";	/*逃げる失敗のアルゴリズムを入れると面白いかも。*/
		this.setText(text);
	}
	//ステージの読み込み-------------------------------------------------------------------------------------------------
	private void setStage(Canvas canvas){
		/*ステージによって違う画像を読み込ませるアルゴリズムを作るとなおよい*/
		backImage = BitmapFactory.decodeResource(context.getResources(),R.drawable.stage1);
		Paint paint = new Paint();
    	canvas.drawBitmap(backImage,0,0,paint);
	}
	//=================================================================================================================

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		//モンスター画像の設置--------------------------------------------------------------------------
		Canvas canvas = holder.lockCanvas();
		this.setStage(canvas);
    	player.allDraw(canvas);
    	enemy.allDraw(canvas);
    	holder.unlockCanvasAndPost(canvas);
    	//戦闘開始-----------------------------------------------------------------------------------
    	String text1;
    	if(enemy.getMonster(3) !=null){
    			   text1 = enemy.getMonster(1).getName() + "があらわれた。\n"+
    					   enemy.getMonster(2).getName() + "があらわれた。\n"+
    				       enemy.getMonster(3).getName() + "があらわれた。\n";
    	}else if(enemy.getMonster(2) != null){
    			   text1 = enemy.getMonster(1).getName() + "があらわれた。\n"+
       					   enemy.getMonster(2).getName() + "があらわれた。\n";
    	}else{
    			   text1 = enemy.getMonster(1).getName() + "があらわれた。\n";
    	}
    	this.setText(text1);
    	gameloop = new Thread(this);
		gameloop.start();
		//activityの変更処理
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		gameloop=null;
		//データの保存とか?
		
	}

	//【ゲームのメイン処理】==========================================================================================================
	@Override
	public void run() {
		int phase = 1;	//ターン。行動するとプラスされていく。
		while(loop){
			//ボタンが押されると実行、phaseが進む。
			if(AtkPressed){
				AtkPressed = false;
				this.Attack(num1, num2);
				phase++;
			} else if(EscPressed){
				EscPressed = false;
				this.Escape();
				//文字が表示されて少し待機してから画面遷移。
				/*loopをfalseにせずEscap()で画面遷移してもいいが、thread.sleep等のthreadの関数を他の場所に書くと見にくいため。*/
				try{
        			Thread.sleep(100);
        		} catch(InterruptedException e){
        			e.printStackTrace();
        		}
				phase++;
			}
			//敵の行動。引数にphase。phaseによって攻撃頻度をモンスターごとに変える。あるいはランダム。attack()は各モンスターごとにOverrideする
			//enemy.getMonster(1).attack(phase);
			//enemy.getMonster(2).attack(phase);
			//enemy.getMonster(3).attack(phase);
			
			//どちらかのHPが０になれば終了。あるいはモンスターが全滅したらtrueにして発生させる。
			//if(自分のHP==0)		GameOver画面へ遷移-----------------------------------------------------------------
			
			//else if(敵のHP==0) 非戦闘画面へ遷移（経験値、入手アイテム等の追加、テキスト表示などの処理）--------------------------------
			if(enemy.getMonster(1).getHP() <0){
				//敵モンスター以外を再描画
				Canvas canvas = holder.lockCanvas();
				this.setStage(canvas);
		    	player.allDraw(canvas);
		    	if(enemy.getMonster(2)!=null){
		    		enemy.getMonster(2).draw(canvas, 2*dispWidth/7, 2*dispHeight/5);
		    	}
		    	if(enemy.getMonster(3)!=null){
		    		enemy.getMonster(3).draw(canvas, 1*dispWidth/7, 2*dispHeight/5);
		    	}
		    	holder.unlockCanvasAndPost(canvas);
				//仮の処理
				loop = false;
				String text = enemy.getMonster(1).getName() + "を倒した";
				this.setText(text);
			}
		}
		//画面が遷移せずにループを抜けた場合は「逃げる」が成功とみなして、非戦闘画面へ遷移。
	}//============================================================================================================================
	
	
	//【ボタンが押されたときのメソッド】=============================================================================
	public void attackButton(int num1,int num2){
		this.num1 = num1; //自分のモンスター
		this.num2 = num2; //敵のモンスター	
		AtkPressed = true;
	}
	public void EscapeButton(){
		EscPressed = true;
	}
	//======================================================================================================


}
