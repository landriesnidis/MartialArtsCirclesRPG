package pers.landriesnidis.macrpg.scene.village;

import pers.landriesnidis.macrpg.element.store.BaseStore;

public class VillageNotionStore extends BaseStore{

	public VillageNotionStore() {
		
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		setTitle("小渔村杂货铺");
		setTextContent("杂货店老板：需要点什么自己选吧~");
		
		addGoods("水果", 50);
		addGoods("鸡蛋", 20);
		addGoods("盐巴", 10);
		addGoods("石料", 30);
		
		setFreeRate(0.2);
		setUpdateTime(1*60*1000); // 1分钟更新一次
	}
	
}
