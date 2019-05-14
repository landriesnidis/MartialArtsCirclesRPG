package pers.landriesnidis.macrpg.prop.enums;

public enum ItemType {
	FORCE("功法"),		// 功法
	MARTIAL("武技"),		// 武技
	DRUG("药品"),		// 药品
	EQUIP("装备"),		// 装备
	MATERIAL("材料"),	// 材料
	PROP("道具");		// 道具
	
	private String typeName;
	
	private ItemType(String typeName) {
		this.typeName = typeName;
	}
	
	@Override
	public String toString() {
		return typeName;
	}
}
