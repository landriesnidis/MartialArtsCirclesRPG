package pers.landriesnidis.macrpg.element.npc.function;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;

import com.alibaba.fastjson.JSON;

import pers.landriesnidis.macrpg.launcher.DataLoader;
import pers.landriesnidis.ptm4j.menu.TextMenu;
import pers.landriesnidis.ptm4j.menu.events.StartEvent;
import pers.landriesnidis.ptm4j.option.Option;
import pers.landriesnidis.ptm4j.option.base.OptionHandler;
import pers.landriesnidis.ptm4j.scene.base.ISceneContext;

public class DetectionPlayerCompositionTextMenu extends TextMenu{
	
	public static final String ENCODE = "UTF-8";
	
	public static ArrayList<String> lstShortWords = new ArrayList<String>();
	public static ArrayList<String> lstLongWords = new ArrayList<String>();
	
	static{
		File folder = new File("./resource/other/DetectionPlayerCompositionTextMenu");
		if(!folder.exists())folder.mkdirs();
		
		File file1 = new File(folder.getPath() + "/lstShortWords.json");
		File file2 = new File(folder.getPath() + "/lstLongWords.json");
		
		if(file1.exists()){
			String s = DataLoader.readStringFromFile(file1.getPath(),ENCODE);
			lstShortWords = (ArrayList<String>) JSON.parseArray(s, String.class);
		}
		
		if(file2.exists()){
			String s = DataLoader.readStringFromFile(file2.getPath(),ENCODE);
			lstLongWords = (ArrayList<String>) JSON.parseArray(s, String.class);
		}
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public void onStart(StartEvent e) {
		setTitle("测测你是由什么做成的");
		
		if(!lstLongWords.isEmpty() && !lstShortWords.isEmpty()){
			setTextContent("告诉我你要测的名字：");
			setAllowReveiceText(true);
			setAllowShowSerialNumber(true);
		}else{
			setTextContent("抱歉，当前功能不可用(词库中暂无数据).");
		}
		
		Class<TextMenu> clazz = TextMenu.class;
		
		addArgsMenuOption("+", clazz);
		getLastOption().setVisibility(false);
		getLastOption().setPreparatoryExecuteHandler(new OptionHandler() {
			public boolean preparatoryExecuteHandle(String text, ISceneContext sceneContext, Object dataTag,
					Option optionContext) {
				try{
					String word = text.split(" ")[1];
					if(word.length()>4){
						lstLongWords.add(word);
					}else{
						lstShortWords.add(word);
					}
					showMessage("词语添加成功.", sceneContext, dataTag);
				}catch (Exception e) {
					showMessage("未携带参数.", sceneContext, dataTag);
				}
				return false;
			}
		});
		
		addTextOption("保存", null);
		getLastOption().setVisibility(false);
		getLastOption().setPreparatoryExecuteHandler(new OptionHandler() {
			public boolean preparatoryExecuteHandle(String text, ISceneContext sceneContext, Object dataTag,
					Option optionContext) {
				File folder = new File("./resource/other/DetectionPlayerCompositionTextMenu");
				if(!folder.exists())folder.mkdirs();
				
				File file1 = new File(folder.getPath() + "/lstShortWords.json");
				File file2 = new File(folder.getPath() + "/lstLongWords.json");
				
				if(file1.exists()) file1.delete();
				if(file2.exists()) file2.delete();
				
				try {
					file1.createNewFile();
					file2.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
				
				
				try {
					Writer wf1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file1), ENCODE));
					wf1.append(JSON.toJSONString(lstShortWords));
					wf1.flush();
					wf1.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
				
				try {
					Writer wf2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2), ENCODE));
					wf2.append(JSON.toJSONString(lstLongWords));
					wf2.flush();
					wf2.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
				showMessage("数据保存成功.", sceneContext, dataTag);
				
				return false;
			}
		});
		
		super.onStart(e);
	}
	
	private String[] getRandomWords(){
		String[] arr = new String[3];
		Random r = new Random();
		arr[0] = lstShortWords.get(r.nextInt(lstShortWords.size()));
		arr[1] = lstShortWords.get(r.nextInt(lstShortWords.size()));
		arr[2] = lstLongWords.get(r.nextInt(lstLongWords.size()));
		return arr;
	}
	
	@Override
	public boolean onTextReveived(String text, ISceneContext sceneContext, Object dataTag) {
		setTitle(String.format("%s是什么东西做成的？", text));
		String[] arr = getRandomWords();
		String result = String.format("%s 是由 %s、 %s 和 %s 这三种东西做成的！", text, arr[0], arr[1], arr[2]);
		setTextContent(result);
		
		addTextLine();
		addBackOption("返回");
		
		setAllowReveiceText(false);
		showMenu(sceneContext, dataTag);
		return true;
	}
	
}
