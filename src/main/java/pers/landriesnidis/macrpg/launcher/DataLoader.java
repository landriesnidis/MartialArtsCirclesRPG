package pers.landriesnidis.macrpg.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;

import pers.landriesnidis.macrpg.manager.PropManager;
import pers.landriesnidis.macrpg.prop.BaseItem;
import pers.landriesnidis.macrpg.prop.DrugItem;
import pers.landriesnidis.macrpg.prop.EquipItem;
import pers.landriesnidis.macrpg.prop.ForceItem;
import pers.landriesnidis.macrpg.prop.MartialItem;
import pers.landriesnidis.macrpg.prop.MaterialItem;
import pers.landriesnidis.macrpg.prop.PropItem;
import pers.landriesnidis.macrpg.prop.enums.ItemType;

public class DataLoader {
	public static final String FOLDER_PATH = "./resource/prop/";
	public static final HashMap<ItemType, String> TYPE_FILE_MAP;
	public static final HashMap<ItemType, Class<? extends BaseItem>> TYPE_CLASS_MAP;
	
	
	static{
		TYPE_FILE_MAP = new HashMap<ItemType, String>();
		TYPE_FILE_MAP.put(ItemType.PROP, "道具.json");
		TYPE_FILE_MAP.put(ItemType.DRUG, "丹药.json");
		TYPE_FILE_MAP.put(ItemType.EQUIP, "装备.json");
		TYPE_FILE_MAP.put(ItemType.FORCE, "功法.json");
		TYPE_FILE_MAP.put(ItemType.MARTIAL, "武技.json");
		TYPE_FILE_MAP.put(ItemType.MATERIAL, "材料.json");
		
		TYPE_CLASS_MAP = new HashMap<ItemType, Class<? extends BaseItem>>();
		TYPE_CLASS_MAP.put(ItemType.PROP, PropItem.class);
		TYPE_CLASS_MAP.put(ItemType.DRUG, DrugItem.class);
		TYPE_CLASS_MAP.put(ItemType.EQUIP, EquipItem.class);
		TYPE_CLASS_MAP.put(ItemType.FORCE, ForceItem.class);
		TYPE_CLASS_MAP.put(ItemType.MARTIAL, MartialItem.class);
		TYPE_CLASS_MAP.put(ItemType.MATERIAL, MaterialItem.class);
	}
	
	public static void loadResourceFile(){
		for(Entry<ItemType, String> ft:TYPE_FILE_MAP.entrySet()){
			// System.out.println("正在加载资源：" + ft.getValue());
			List<? extends BaseItem> lst = readJsonArrayFile(FOLDER_PATH + ft.getValue(),TYPE_CLASS_MAP.get(ft.getKey()));
			// System.out.println("可加载资源数量：" + lst.size());
			HashMap<String, BaseItem> props = new HashMap<String, BaseItem>();
			for(BaseItem item:lst){
				item.setPropType(ft.getKey());
				props.put(item.getName(), item);
				// System.out.println("\t物品：" + item.getName());
			}
			PropManager.addItems(props);
		}
	}
	
	public static String readStringFromFile(String fileName){
		String encoding = "GBK";  
        return readStringFromFile(fileName,encoding);
	}
	
	public static String readStringFromFile(String fileName,String encoding){
		File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }
        try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static <T> List<T> readJsonArrayFile(String fileName, Class<T> clazz){
        String jsonString = readStringFromFile(fileName);
        return JSON.parseArray(jsonString, clazz);
	}
}
