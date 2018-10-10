package pers.landriesnidis.macrpg.prop;

import pers.landriesnidis.macrpg.prop.enums.ItemType;

public class MartialItem extends BaseItem{

	public MartialItem() {
		super(ItemType.MARTIAL);
	}

	private String factions;
	private String equip_type;
	private int atk;
	private double atk_ratio;
	private int hp;
	private double hp_ratio;
	
	public String getFactions() {
		return factions;
	}
	public void setFactions(String factions) {
		this.factions = factions;
	}
	public String getEquip_type() {
		return equip_type;
	}
	public void setEquip_type(String equip_type) {
		this.equip_type = equip_type;
	}
	public int getAtk() {
		return atk;
	}
	public void setAtk(int atk) {
		this.atk = atk;
	}
	public double getAtk_ratio() {
		return atk_ratio;
	}
	public void setAtk_ratio(double atk_ratio) {
		this.atk_ratio = atk_ratio;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public double getHp_ratio() {
		return hp_ratio;
	}
	public void setHp_ratio(double hp_ratio) {
		this.hp_ratio = hp_ratio;
	}
}
