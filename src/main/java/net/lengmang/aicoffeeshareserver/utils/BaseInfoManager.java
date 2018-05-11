package net.lengmang.aicoffeeshareserver.utils;

import net.lengmang.aicoffeeshareserver.sql.bean.BaseInfo;
import net.lengmang.aicoffeeshareserver.sql.repository.BaseInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 这个类缓存所有的基础信息到！！！运行内存中！！！
 * 缓解每一次查询对数据库IO的压力
 * 注意：管理员从后台修改完任何产品信息，都要清空一次这个内存对象，并不会清空数据库数据
 */
@Component
public class BaseInfoManager {

    private static BaseInfoManager baseInfoManager = null;

    @Autowired
    private BaseInfoRepository baseInfoRepository;

    private List<BaseInfo> baseInfo;

    @PostConstruct
    private void init() {
        baseInfoManager = this;
        baseInfoManager.baseInfoRepository = baseInfoRepository;
    }


    private BaseInfoManager() {
    }

    /**
     * 获取数据库的产品信息
     */
    private List<BaseInfo> get() {
        if (baseInfo == null || baseInfo.size() == 0) {
            baseInfo = this.baseInfoRepository.findAll();
        }
        return baseInfo;
    }

    /**
     * 清空数据库的产品信息
     */
    private void clear() {
        if (baseInfo != null) {
            baseInfo.clear();
        }
    }

    public static List<BaseInfo> getBaseInfo() {
        if (baseInfoManager == null) {
            baseInfoManager = new BaseInfoManager();
        }
        return baseInfoManager.get();
    }

    public static void clearBaseInfo() {
        if (baseInfoManager == null) {
            baseInfoManager = new BaseInfoManager();
        }
        baseInfoManager.clear();
    }
}
