package pers.landriesnidis.macrpg.prop.menu;

import java.util.HashMap;
import java.util.regex.Pattern;

import pers.landriesnidis.macrpg.PlayerScene;
import pers.landriesnidis.macrpg.manager.PlayerManager;
import pers.landriesnidis.macrpg.manager.PropManager;
import pers.landriesnidis.macrpg.player.Player;
import pers.landriesnidis.macrpg.prop.BaseItem;
import pers.landriesnidis.macrpg.prop.ForceItem;
import pers.landriesnidis.macrpg.prop.enums.ItemType;
import pers.landriesnidis.macrpg.prop.utils.ItemUtil;
import pers.landriesnidis.ptm4j.menu.TextMenu;
import pers.landriesnidis.ptm4j.menu.events.StartEvent;
import pers.landriesnidis.ptm4j.option.Option;
import pers.landriesnidis.ptm4j.option.base.OptionHandler;
import pers.landriesnidis.ptm4j.scene.base.ISceneContext;

public class DealMenu extends TextMenu{
	
	@Override
	public void onCreate() {
		super.onCreate();
		setAllowShowSerialNumber(true);
		
		// 这里之所以使用ArgsMenuOption是为了能接收交易数量的参数
		addArgsMenuOption("购买",TextMenu.class);
		addArgsMenuOption("出售",TextMenu.class);
		addBackOption("取消");
	}
	
	@Override
	public void onStart(StartEvent e) {
		String[] arr = e.getKeyword().split(" \\$");
		final String goodsName = arr[0];
		final int presentPrice = Integer.parseInt(arr[1]);
		
		final BaseItem item = PropManager.getItemByName(goodsName);
		final Player player = PlayerManager.getPlayerBySceneContext(e.getSceneContext());
		final HashMap<String, Integer> pack = player.getPack();
		
		setTitle(e.getKeyword());
		setTextContent(buildItemInfoString(item, pack.containsKey(goodsName)?pack.get(goodsName):0));
		
		getOption("购买").setPreparatoryExecuteHandler(new OptionHandler() {
			public boolean preparatoryExecuteHandle(String text, ISceneContext sceneContext, Object dataTag,
					Option optionContext) {
				Player player = ((PlayerScene)sceneContext).getPlayer();
				// 获取商品购买的数量
				int num = getGoodsCount(text);
				// 判断余额是否足够
				if(player.coinChange(0-presentPrice*num)){
					player.addPropToPack(goodsName, num);
					showMessage(String.format("成功购买 %s*%d", goodsName, num), sceneContext, dataTag);
				}else{
					showMessage(String.format("购买 %s*%d 失败，钱币不足。(当前钱币：%d)", goodsName, num, player.getCoin()), sceneContext, dataTag);
				}
				sceneContext.returnToPreviousMenu();
				return false;
			}
		});
		
		getOption("出售").setPreparatoryExecuteHandler(new OptionHandler() {
			public boolean preparatoryExecuteHandle(String text, ISceneContext sceneContext, Object dataTag,
					Option optionContext) {
				Player player = ((PlayerScene)sceneContext).getPlayer();
				// 获取商品购买的数量
				int num = getGoodsCount(text);
				// 判断背包内物品是否足够
				if(player.removePropFromPack(goodsName, num)){
					int totalAmount = presentPrice*num;
					player.coinChange(totalAmount);
					showMessage(String.format("成功出售 %s*%d，本次交易得到钱币：%d。(当前钱币：%d)", goodsName, num, totalAmount, player.getCoin()), sceneContext, dataTag);
				}else{
					showMessage(String.format("出售 %s*%d 失败，物品数量不足。(当前拥有：%d)", goodsName, num, pack.containsKey(goodsName)?pack.get(goodsName):0), sceneContext, dataTag);
				}
				sceneContext.returnToPreviousMenu();
				return false;
			}
		});
		super.onStart(e);
	}
	
	private int getGoodsCount(String text){
		if(!text.contains(" ")) return 1;
		String numString = text.split(" ")[1];
		if(Pattern.compile("^[-\\+]?[\\d]*$").matcher(numString).matches()){
			try{
				return Integer.parseInt(numString);
			}catch (NumberFormatException e) {
				return 1;
			}
		}
		return 1;
	}
	
	private String buildItemInfoString(BaseItem item, int itemNum){
		if(item.getItemType() == ItemType.FORCE){
			ForceItem fi = ((ForceItem)item);
			return String.format("已拥有数量：%d\n说明：%s\n类型：%s\n等级：%d\n所属宗派：%s\n附带武技：%s", itemNum,item.getNote(),ItemUtil.getTypeName(item.getItemType()),item.getLevel(),fi.getFactions()==null?"江湖流传":fi.getFactions(),fi.getMartial()==null?"无":fi.getMartial());
		}else{
			return String.format("说明：%s\n类型：%s\n等级：%d\n数量：%s", item.getNote(),ItemUtil.getTypeName(item.getItemType()),item.getLevel(),itemNum);
		}
	}
}
