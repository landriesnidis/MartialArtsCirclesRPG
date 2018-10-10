package pers.landriesnidis.macrpg.scene.village;

import pers.landriesnidis.macrpg.element.npc.BaseNPC;

public class VillageHeadNPC extends BaseNPC{
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		setTitle("老村长");
		setAllowShowSerialNumber(true);
		setTextContent("@name，欢迎你来到我们小村，有什么不明白的地方我可以为你解答：");
		
		addTextOption("如何查看已有物品？", "在任意地图中输入“背包”即可查看个人的背包信息。");
		addTextOption("如何查看玩家当前状态？", "在任意地图中输入“状态 ”即可查看玩家的当前状态。");
		addTextOption("玩家活力值如何恢复？", "在收集资源的过程中会消耗一定量的活力值。每分钟会恢复1点活力，当活力满100点就不会再增加了。");
		addTextOption("如何购买/出售多个物品？", "在商品的购买/出售Option后+空格+数量。例如：购买 10");
		
		addBackOption("离开");
	}
	
}
