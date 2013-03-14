package javaCircle.kendai;

public class Party {
	Monster mon1;
	Monster mon2;
	Monster mon3;
	//コンストラクタ
	public Party(Monster mon1,Monster mon2,Monster mon3){
		this.mon1 = mon1;
		this.mon2 = mon2;
		this.mon3 = mon3;
	}
	
	//攻撃するモンスターを選択
	public Monster getMonster(int num){
		if(num == 1){
			return mon1;
		}
		else if(num == 2){
			return mon2;
		}
		else if(num == 3){
			return mon3;
		}
		return null;
	}
	//モンスターを入れる
	public void setMonster(Monster mon){
		if(mon1 == null){
			mon1 = mon;
		}
		else if(mon2 == null){
			mon2 = mon;
		}
		else if(mon3 == null){
			mon3 = mon;
		}
		//引数がnullなら全部nullにする
		else mon1 = mon2 = mon3 = null;
	}
}
