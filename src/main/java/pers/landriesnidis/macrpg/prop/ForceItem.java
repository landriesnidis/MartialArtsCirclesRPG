package pers.landriesnidis.macrpg.prop;

import pers.landriesnidis.macrpg.prop.enums.ItemType;

/**
 * 内功项
 * @author Administrator
 *
 */
public class ForceItem extends BaseItem{
	private String factions;
	private int inc_atk;
	private int inc_def;
	private int inc_speed;
	private int inc_con;
	private int cd_min;
	private String martial;
	
	public ForceItem() {
		super(ItemType.FORCE);
	}
 	public String getFactions() {
		return factions;
	}
	public void setFactions(String factions) {
		this.factions = factions;
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
	public void setInc_con(int inc_hp) {
		this.inc_con = inc_hp;
	}
	public int getCd_min() {
		return cd_min;
	}
	public void setCd_min(int cd_min) {
		this.cd_min = cd_min;
	}
	public String getMartial() {
		return martial;
	}
	public void setMartial(String martial) {
		this.martial = martial;
	}
}
