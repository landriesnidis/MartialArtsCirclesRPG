package pers.landriesnidis.macrpg.pack;

import java.util.Map.Entry;

import pers.landriesnidis.macrpg.PlayerScene;
import pers.landriesnidis.macrpg.element.ElementMenu;
import pers.landriesnidis.macrpg.player.Player;
import pers.landriesnidis.macrpg.prop.menu.ItemMenu;
import pers.landriesnidis.ptm4j.menu.events.StartEvent;

/**
 * 个人背包
 * 由于物品列表是根据不同用户动态生成的，每一次的生成都会更改Option组的内容，因此该TextMenu对象不可玩家共享使用。
 * @author Landriesnidis
 */
public class PackMenu extends ElementMenu{
	
	@Override
	public void onCreate() {
		super.onCreate();
		setTitle("我的背包");
		setAllowShowSerialNumber(true);
	}
	
	@Override
	public void onStart(StartEvent e) {
		Player player = ((PlayerScene)e.getSceneContext()).getPlayer();
		setTextContent(String.format("背包中的钱币数量：%d", player.getCoin()));
		
		// 清空Option
		getMenuOptions().clear();
		
		// 生成带分类的背包物品列表（消耗资源）
		/*
		HashMap<ItemType, ArrayList<BaseItem>> hmItems = new HashMap<ItemType, ArrayList<BaseItem>>();
		ItemType type;
		for(Entry<String, Integer> entry:player.getPack().entrySet()){
			BaseItem item = PropManager.getItemByName(entry.getKey()); 
			type = item.getItemType();
			if(hmItems.containsKey(type)){
				hmItems.get(type).add(item);
			}else{
				ArrayList<BaseItem> lst = new ArrayList<BaseItem>();
				lst.add(item);
				hmItems.put(type, lst);
			}
		}
		for(Entry<ItemType, ArrayList<BaseItem>> entry:hmItems.entrySet()){
			addTextLine(String.format("\n·-·-·%s·-·-·",ItemUtil.getTypeName(entry.getKey())));
			for(BaseItem item:entry.getValue()){
				addMenuOption(String.format("%s*%d", item.getName(),player.getPack().get(item.getName())), TextMenuManager.getMenuByClass(ItemMenu.class));
			}
		}
		*/
		
		// 生成背包物品列表，不进行分类
		addTextLine();
		if(player.getPack()!=null && !player.getPack().isEmpty()){
			for(Entry<String, Integer> entry:player.getPack().entrySet()){
				addMenuOption(String.format("%s*%d",entry.getKey(),entry.getValue()), new ItemMenu());
			}
			addTextLine();
		}
		addBackOption("退出背包");
		
		super.onStart(e);
	}
}
