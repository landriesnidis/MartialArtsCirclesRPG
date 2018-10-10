package pers.landriesnidis.macrpg.element;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

import pers.landriesnidis.macrpg.prop.menu.DealMenu;
import pers.landriesnidis.macrpg.utils.TimeRecorder;
import pers.landriesnidis.ptm4j.menu.events.StartEvent;

public class BaseTrade extends ElementMenu{
	
	// 商品列表
	private HashMap<String, Price> goods;
	// 价格浮动率
	private double freeRate;
	// 时间记录器
	private TimeRecorder tr;

	@Override
	public void onCreate() {
		super.onCreate();
		goods = new HashMap<String, Price>(); 
	}
	
	@Override
	public void onStart(StartEvent e) {
		// 如果【设置了计时】并且【时间到】 则 更新商品价格
		if(tr!=null && tr.isTimeUp()){
			updatePrice();
			getMenuOptions().clear();
		}
		
		if(getMenuOptions().isEmpty()){
			// 生成商品列表
			for(Entry<String, Price> entry:goods.entrySet()){
				final String goodsName = entry.getKey();
				final int presentPrice = entry.getValue().getPresentPrice();
				
				addMenuOption(String.format("%s $%d", goodsName, presentPrice),new DealMenu());
			}
			addTextLine();
			addBackOption("返回");
		}
		super.onStart(e);
	}
	
	public void addGoods(String goodsName, int price){
		goods.put(goodsName, new Price(price));
	}
	
	public void removeGoods(String goodsName){
		goods.remove(goodsName);	
	}
	
	public void updatePrice(){
		// 如果未设置价格浮动
		if(freeRate <= 0.0)return;
		// 随机变动所有商品的价格
		for(Price price:goods.values()){
			int priceMaxDiff = (int) (price.originalPrice*freeRate);
			int priceDiff = new Random().nextInt(priceMaxDiff*2) - priceMaxDiff;
			price.setPresentPrice(price.originalPrice+priceDiff);
		}
	}
	
	public void setUpdateTime(int time){
		if(tr==null) tr=new TimeRecorder(time);
	}
	
	public double getFreeRate() {
		return freeRate;
	}
	
	public void setFreeRate(double freeRate) {
		this.freeRate = freeRate;
	}
	
	/**
	 * 价格类型
	 * 包含原价和现价
	 * @author Landriesnidis
	 */
	public class Price{
		private int originalPrice;
		private int presentPrice;
		
		public Price(int originalPrice) {
			this.originalPrice = originalPrice;
			this.presentPrice = originalPrice;
		}
		
		public Price(int originalPrice, int presentPrice) {
			this.originalPrice = originalPrice;
			this.presentPrice = presentPrice;
		}

		public int getOriginalPrice() {
			return originalPrice;
		}

		public void setOriginalPrice(int originalPrice) {
			this.originalPrice = originalPrice;
		}

		public int getPresentPrice() {
			return presentPrice;
		}

		public void setPresentPrice(int presentPrice) {
			this.presentPrice = presentPrice;
		}
	}
}


