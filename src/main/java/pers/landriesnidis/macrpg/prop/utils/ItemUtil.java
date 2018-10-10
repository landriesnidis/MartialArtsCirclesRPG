package pers.landriesnidis.macrpg.prop.utils;

import pers.landriesnidis.macrpg.prop.enums.ItemType;

public class ItemUtil {
	public static String getTypeName(ItemType type){
		switch(type){
		case DRUG:		// 药物
			return "药物";
		case EQUIP:		// 装备
			return "装备";
		case FORCE:		// 功法
			return "功法";
		case MARTIAL:	// 武技
			return "武技";
		case MATERIAL:	// 材料
			return "材料";
		case PROP:		// 道具
			return "道具";
		default:
			return "未知";
		}
	}
}
