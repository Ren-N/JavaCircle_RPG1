package javaCircle.kendai;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;


public class Battle_MainPanel extends SurfaceView implements Callback,Runnable{
	//メンバ
	private SurfaceHolder holder;
	private Thread gameloop;
	private boolean loop = true;
	private Party player;
	private Enemy enemy;
	private boolean AtkPressed = false;
	private boolean EscPressed = false;
	private int num1,num2;
	//コンストラクタ
	public Battle_MainPanel(Context context,SurfaceView sv) {
		super(context);
		//xmlで生成したBattle_MainPanel(surfaceview)のholderを得て,callbackにこのクラスに追加。そうすると生成時にsurfacecreatedが実行される。
		holder = sv.getHolder();
		holder.addCallback(this);
		//自分のモンスターのセット
		/*kamakiri kama2 = new kamakiri();
		 * party.setMonster(kama2);*/
		/*party = new Party(kama2,null,null);*/
		//敵モンスターのセット
		//できればランダムにするアルゴリズムを作る
		/*Kamakiri kama1 = new Kamakiri();
		 * enemy.setMonster(kama1);*/
		/*enemy = new Enemy(kama1,null,null);*/
		
		
	}
	
	//メソッド===================================================================================================
	//文字表示メソッド
	/*文字表示画面に渡すテキストを渡す。mainActivityのテキストフィールドへ渡す。*/
	public String setText(String text){
		return text;
	}
	//攻撃!!
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
	}
	
	//逃げる!!
	private void Escape(){
		loop = false;	
		
		String text = "逃げた";	/*逃げる失敗のアルゴリズムを入れると面白いかも。*/
		this.setText(text);
	}
	//===========================================================================================

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		gameloop = new Thread(this);
		gameloop.start();
		//activityの変更処理
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		gameloop=null;
		//データの保存とか?
		
	}

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
			//if(自分のHP==0)		GameOver画面へ遷移
			//else if(敵のHP==0) 非戦闘画面へ遷移（経験値、入手アイテム等の追加、テキスト表示などの処理）
			if(enemy.getMonster(1).getHP() <0){
				//仮の処理
				loop = false;
				String text = enemy.getMonster(1).getName() + "を倒した";
				this.setText(text);
			}
		}
		//画面が遷移せずにループを抜けた場合は「逃げる」が成功とみなして、非戦闘画面へ遷移。
	}
	
	
	//ボタンが押されたときのメソッド
	public void attackButton(int num1,int num2){
		this.num1 = num1;
		this.num2 = num2;
		AtkPressed = true;
	}
	public void EscapeButton(){
		EscPressed = true;
	}



}
