package pers.landriesnidis.macrpg.prop;

import pers.landriesnidis.macrpg.prop.enums.ItemType;

public class BaseItem{
	private String name;
	private String note;
	private int level;
	private ItemType propType;
	
	public BaseItem() {
	}
	
	public BaseItem(ItemType propType) {
		this.propType = propType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public ItemType getItemType() {
		return propType;
	}
	public void setPropType(ItemType propType) {
		this.propType = propType;
	}
	
	// 矿物，草药，功法，武器，药品
	
	// http://news.4399.com/lsmsy/xinde/m/689634.html
}
