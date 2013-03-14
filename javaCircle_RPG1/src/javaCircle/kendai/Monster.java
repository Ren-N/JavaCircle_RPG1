package javaCircle.kendai;

public class Monster {

	private int hp;
	private int power;
	private int defence;
	private String name;
	
	//攻撃!!
	public void attack(Monster target){
		target.dameged(this.power);
	}
	//ダメージ!!
	public void dameged(int power){
		this.hp -= power - this.defence;
	}
	public String getName(){
		return this.name;
	}
	public int getHP(){
		return this.hp;
	}
}
