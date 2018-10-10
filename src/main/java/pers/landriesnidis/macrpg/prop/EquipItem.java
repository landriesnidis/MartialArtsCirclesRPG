package pers.landriesnidis.macrpg.prop;

import pers.landriesnidis.macrpg.prop.enums.ItemType;

public class EquipItem extends BaseItem{

	public EquipItem() {
		super(ItemType.EQUIP);
	}
	
	private String equip_part;
	private String equip_type;
	private int inc_atk;
	private int inc_def;
	private int inc_speed;
	private int inc_con;
	
	public String getEquip_part() {
		return equip_part;
	}
	public void setEquip_part(String equip_part) {
		this.equip_part = equip_part;
	}
	public String getEquip_type() {
		return equip_type;
	}
	public void setEquip_type(String equip_type) {
		this.equip_type = equip_type;
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
}
