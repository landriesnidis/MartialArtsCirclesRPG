
------------------------------------------------------------------------------------------------------
--                                     ↓↓↓  固定代码  ↓↓↓
------------------------------------------------------------------------------------------------------

-- * * * * * * * * * * * * * *
-- *    需引用的Java类全称    *
-- * * * * * * * * * * * * * *
TEXTMENU_CLASS_FULL_NAME    = "pers.landriesnidis.macrpg.element.store.BaseStore"
OPTION_HANDLER_INTERFACE    = "pers.landriesnidis.ptm4j.option.base.OptionHandler"
MENU_LIFECYCLE_INTERFACE    = "pers.landriesnidis.ptm4j.menu.base.IMenuLifeCycle"

-- * * * * * * * * * * * * * *
-- *    实例化TextMenu对象    *
-- * * * * * * * * * * * * * *
textMenu = luajava.newInstance(TEXTMENU_CLASS_FULL_NAME)

-- * * * * * * * * * * * * * *
-- *     TextMenu生命周期     *
-- * * * * * * * * * * * * * *
textMenuLifeCycle_callback  = {}
textMenuLifeCycle_jproxy    = luajava.createProxy(MENU_LIFECYCLE_INTERFACE, textMenuLifeCycle_callback)  

-- * * * * * * * * * * * * * *
-- *      获取对象(重要)      *
-- * * * * * * * * * * * * * *
function create()
    textMenu:setMenuLifeCycleCallback(textMenuLifeCycle_jproxy)
    return textMenu
end

------------------------------------------------------------------------------------------------------
--                                     ↓↓↓  接口实现  ↓↓↓
------------------------------------------------------------------------------------------------------

-- 钓鱼功能具体实现
fishingHandler = {
    preparatoryExecuteHandle = function (text, sceneContext, dataTag, optionContext)
        
        return true
    end  
}  
-- 钓鱼功能的代码块接口代理
fishingHandler_jproxy   = luajava.createProxy(OPTION_HANDLER_INTERFACE, fishingHandler)  

------------------------------------------------------------------------------------------------------
--                                     ↓↓↓  生命周期  ↓↓↓
------------------------------------------------------------------------------------------------------

-- 当TextMenu对象创建时被触发
function textMenuLifeCycle_callback.onCreate()
    textMenu:setTitle("小渔村铁匠铺")
    textMenu:setTextContent("“叮~叮~叮~”... 铁匠大叔：需要点什么？")
    
    textMenu:addGoods("铁矿", 150)
    textMenu:addGoods("银矿", 320)
    textMenu:addGoods("金矿", 680)
    textMenu:addGoods("木剑", 75)
    textMenu:addGoods("砍柴刀", 500)
    -- textMenu:addTextOption("钓鱼","此功能暂未开放。")
    -- textMenu:getOption("钓鱼"):setPreparatoryExecuteHandler(fishingHandler_jproxy);
    textMenu:addBackOption("返回")

    -- 价格浮动比例
    textMenu:setFreeRate(0.2)
    -- 价格更新时间
	textMenu:setUpdateTime(1*60*1000)
end

-- 当TextMenu在Scene中加载时触发
function textMenuLifeCycle_callback.onLoad(e)
    
end

-- 当TextMenu开始执行时被触发
function textMenuLifeCycle_callback.onStart(e)
    -- 手动输出TextMenu的目录信息
    textMenu:showMenu(e:getSceneContext(),nil)
end

-- 当TextMenu被新TextMenu暂时覆盖时被触发
function textMenuLifeCycle_callback.onStop(e)
    
end

-- 当TextMenu因为退回重新加载时被触发
function textMenuLifeCycle_callback.onBack(e)
    
end

-- 当TextMenu从Scene中卸载时被触发
function textMenuLifeCycle_callback.onUnload()

end

