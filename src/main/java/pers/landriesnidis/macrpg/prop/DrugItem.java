package pers.landriesnidis.macrpg.prop;

import pers.landriesnidis.macrpg.prop.enums.ItemType;

public class DrugItem extends BaseItem{

	private int inc_atk;
	private int inc_def;
	private int inc_speed;
	private int inc_con;
	private int inc_hp;
	private int inc_vit;
	private int inc_luck;
	
	public DrugItem() {
		super(ItemType.DRUG);
	}

	public int getInc_atk() {
		return inc_atk;
	}

	public void setInc_atk(int inc_atk) {
		this.inc_atk = inc_atk;
	}

	public int getInc_def() {
		return inc_def;
	}

	public void setInc_def(int inc_def) {
		this.inc_def = inc_def;
	}

	public int getInc_speed() {
		return inc_speed;
	}

	public void setInc_speed(int inc_speed) {
		this.inc_speed = inc_speed;
	}

	public int getInc_con() {
		return inc_con;
	}

	public void setInc_con(int inc_con) {
		this.inc_con = inc_con;
	}

	public int getInc_hp() {
		return inc_hp;
	}

	public void setInc_hp(int inc_hp) {
		this.inc_hp = inc_hp;
	}

	public int getInc_vit() {
		return inc_vit;
	}

	public void setInc_vit(int inc_vit) {
		this.inc_vit = inc_vit;
	}

	public int getInc_luck() {
		return inc_luck;
	}

	public void setInc_luck(int inc_luck) {
		this.inc_luck = inc_luck;
	}
	
	
}
