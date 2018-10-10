package pers.landriesnidis.macrpg.manager;

import java.util.HashMap;

import pers.landriesnidis.macrpg.prop.BaseItem;

public class PropManager {
	
	/*************************************************************/
    //						   脑残单例
    /*************************************************************/
    private static PropManager instance;
    private static PropManager getInstance() {
    	if(instance==null){
    		instance = new PropManager();
    	}
    	return instance;
    }
    private PropManager() {
    	props = new HashMap<String, BaseItem>();
	}
    
    /*************************************************************/
    //						   一堆功法
    /*************************************************************/
    private HashMap<String, BaseItem> props;
	public HashMap<String, BaseItem> getItems() {
		return props;
	}
	public void setProps(HashMap<String, BaseItem> props) {
		this.props = props;
	}
	
	public static BaseItem getItemByName(String name){
		BaseItem item = getInstance().getItems().get(name);
    	if(item==null){
    		/* TODO 根据名称从资源文件中加载物品
    		 * 
    		 */
    		// getInstance().getItems().put(name, item);
    	}
    	return item;
    }
	
	public static void addItem(String name, BaseItem item){
		getInstance().getItems().put(name, item);
	}
	
	public static void addItems(HashMap<String, BaseItem> props){
		getInstance().getItems().putAll(props);
	}
}