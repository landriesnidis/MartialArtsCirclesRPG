package pers.landriesnidis.macrpg.prop.menu;

import pers.landriesnidis.macrpg.manager.PlayerManager;
import pers.landriesnidis.macrpg.manager.PropManager;
import pers.landriesnidis.macrpg.player.Player;
import pers.landriesnidis.macrpg.prop.BaseItem;
import pers.landriesnidis.macrpg.prop.ForceItem;
import pers.landriesnidis.macrpg.prop.enums.ItemType;
import pers.landriesnidis.ptm4j.menu.TextMenu;
import pers.landriesnidis.ptm4j.menu.events.StartEvent;
import pers.landriesnidis.ptm4j.option.Option;
import pers.landriesnidis.ptm4j.option.base.OptionHandler;
import pers.landriesnidis.ptm4j.scene.base.ISceneContext;

/**
 * 物品使用时显示的TextMenu
 * 共享
 * @author Landriesnidis
 *
 */
public class ItemMenu extends TextMenu{

	@Override
	public void onCreate() {
		super.onCreate();
		setAllowShowSerialNumber(true);
		setSkipMenuOnBack(true);
		addBackOption("使用");
		addBackOption("取消");
	}
	
	@Override
	public void onStart(StartEvent e) {
		// super.onStart(e);
		String[] arr = e.getKeyword().split("\\*");
		final String itemName = arr[0];
		final String itemNum = arr[1];
		final Player player = PlayerManager.getPlayerBySceneContext(e.getSceneContext());
		final BaseItem item = PropManager.getItemByName(itemName);
		
		// 判断该物品在资料库中是否存在
		if(item == null){
			showMessage("未知的物品，暂无法使用。", e.getSceneContext(), null);
			e.getSceneContext().returnToPreviousMenu();
			return;
		}
		
		Option option = getMenuOptions().get(0);
		
		option.setOptional(false);
		option.setPreparatoryExecuteHandler(new OptionHandler() {
			public boolean preparatoryExecuteHandle(String text, ISceneContext sceneContext, Object dataTag, Option optionContext) {
				switch (item.getItemType()) {
				case DRUG:
					player.eatDrug(itemName);
					break;
				case EQUIP:
					player.wearEquip(itemName);
					break;
				case FORCE:
					if(player.learnNewForce(itemName)){
						showMessage(String.format("功法《%s》修炼成功。", itemName), sceneContext, dataTag);
					}else{
						showMessage(String.format("修炼失败，您当前已修炼了《%s》。", itemName), sceneContext, dataTag);
					}
					break;
				case MARTIAL:
					player.learnNewMartial(itemName);
					break;
				case MATERIAL:
					break;
				case PROP:
					player.useProp(itemName);
					break;
				default:
					break;
				}
				return true;
			}
		});
		changeOption(option, item.getItemType());
		
		showInfo(itemName, buildItemInfoString(item, itemNum), getMenuFormatString(), e.getSceneContext(), null);
	}
	
	/**
	 * 根据物品类型修改Option信息
	 * @param option
	 * @param type
	 */
	private static void changeOption(Option option, ItemType type){
		option.setOptional(false);
		switch (type) {
		case DRUG:
			option.setKeyWord("服用");option.setOptional(true);
			break;
		case EQUIP:
			option.setKeyWord("装备");option.setOptional(true);
			break;
		case FORCE:
			option.setKeyWord("修炼");option.setOptional(true);
			break;
		case MARTIAL:
			option.setKeyWord("学习");option.setOptional(true);
			break;
		case MATERIAL:
			break;
		case PROP:
			option.setKeyWord("使用");option.setOptional(true);
			break;
		default:
			break;
		}
	}
	
	private static String buildItemInfoString(BaseItem item, String itemNum){
		if(item.getItemType() == ItemType.FORCE){
			ForceItem fi = ((ForceItem)item);
			return String.format("说明：%s\n类型：%s\n等级：%d\n数量：%s\n所属宗派：%s\n附带武技：%s", item.getNote(),item.getItemType(),item.getLevel(),itemNum,fi.getFactions()==null?"江湖流传":fi.getFactions(),fi.getMartial()==null?"无":fi.getMartial());
		}else{
			return String.format("说明：%s\n类型：%s\n等级：%d\n数量：%s", item.getNote(),item.getItemType(),item.getLevel(),itemNum);
		}
	}
}
