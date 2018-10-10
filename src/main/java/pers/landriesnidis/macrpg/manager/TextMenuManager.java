package pers.landriesnidis.macrpg.manager;

import java.util.HashMap;

import pers.landriesnidis.ptm4j.menu.TextMenu;
import pers.landriesnidis.ptm4j.menu.scritp.Luaj;
import pers.landriesnidis.ptm4j.menu.scritp.exception.MenuScriptException;

public class TextMenuManager {
	
	/*************************************************************/
    //						   脑残单例
    /*************************************************************/
    private static TextMenuManager instance;
    private static TextMenuManager getInstance() {
    	if(instance==null){
    		instance = new TextMenuManager();
    	}
    	return instance;
    }
    private TextMenuManager() {
    	menus_class = new HashMap<Class<? extends TextMenu>, TextMenu>();
    	menus_script = new HashMap<String, TextMenu>();
	}
    
    /*************************************************************/
    //						   一堆TextMenu
    /*************************************************************/
    private HashMap<Class<? extends TextMenu>, TextMenu> menus_class;
    private HashMap<String, TextMenu> menus_script;
    
//    public HashMap<Class<? extends TextMenu>, TextMenu> getMenus() {
//		return menus_class;
//	}
//	public void setMenus(HashMap<Class<? extends TextMenu>, TextMenu> menus) {
//		this.menus_class = menus;
//	}
    
    public HashMap<Class<? extends TextMenu>, TextMenu> getMenus_class() {
		return menus_class;
	}
    
    public HashMap<String, TextMenu> getMenus_script() {
		return menus_script;
	}
	
	/**
	 * 通过Class获取TextMenu对象
	 * @param menuClass
	 * @return 
	 */
    public static TextMenu getMenuByClass(Class<? extends TextMenu> menuClass){
    	TextMenu menu = getInstance().getMenus_class().get(menuClass);
    	if(menu==null){
    		menu = TextMenu.createTextMenuObject(menuClass);
    		getInstance().getMenus_class().put(menuClass, menu);
    	}
    	return menu;
    }
    
    /**
     * 通过脚本文件路径获取TextMenu对象
     * @param scriptFilePath
     * @return
     */
    public static TextMenu getMenuByScript(String scriptFilePath) {
    	TextMenu menu = getInstance().getMenus_script().get(scriptFilePath);
    	if(menu==null){
    		try {
				menu = Luaj.createTextMenuFromScriptFile(scriptFilePath);
				getInstance().getMenus_script().put(scriptFilePath, menu);
			} catch (MenuScriptException e) {
				e.printStackTrace();
			}
    	}
    	return menu;
    }
}
