package pers.landriesnidis.macrpg.scene.village;

import pers.landriesnidis.macrpg.PlayerScene;
import pers.landriesnidis.macrpg.element.map.BaseMap;
import pers.landriesnidis.macrpg.element.map.MapType;
import pers.landriesnidis.macrpg.element.npc.common.GodStickNPC;
import pers.landriesnidis.macrpg.manager.TextMenuManager;
import pers.landriesnidis.ptm4j.enums.ActionType;
import pers.landriesnidis.ptm4j.menu.events.StartEvent;

public class VillageMap extends BaseMap{
	
	public VillageMap() {
		super(MapType.UNKNOW);
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		setTitle("小渔村");
		setAllowShowSerialNumber(true);
		
		addTextLine("·-·-·NPC·-·-·");
		addMenuOption("老村长", TextMenuManager.getMenuByClass(VillageHeadNPC.class));
		addMenuOption("老神棍", TextMenuManager.getMenuByClass(GodStickNPC.class));
		
		addTextLine("·-·-·店铺·-·-·");
		addMenuOption("杂货铺", TextMenuManager.getMenuByClass(VillageNotionStore.class));
		addMenuOption("渔村客栈", TextMenuManager.getMenuByClass(VillageInnStore.class));
		
		
		addTextLine("·-·-·地图·-·-·");
		addMenuOption("小河边", TextMenuManager.getMenuByScript("./script/map/VillageRiverMap.lua"));
		addMenuOption("竹林", TextMenuManager.getMenuByClass(VillageBambooMap.class));
		addMenuOption("后山矿洞", TextMenuManager.getMenuByClass(VillageMineMap.class));
		
		addTextLine("\n");
		addBackOption("离开");
	}
	
	@Override
	public void onStart(StartEvent e) {
//		Player player = ((PlayerScene)e.getSceneContext()).getPlayer();
		
		// 如果最后一项Option的类型是返回类型 并且 当前TextMenu是根TextMenu
		if(getLastOption().getActionType()==ActionType.BACK && ((PlayerScene)e.getSceneContext()).getRootMenu().equals(this)){
			getLastOption().setVisibility(false);
		}else{
			getLastOption().setVisibility(true);
		}
		
		setTextContent("欢迎@name来到这个平静的小渔村。");
		super.onStart(e);
	}
}
