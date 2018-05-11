package net.lengmang.aicoffeeshareserver.controller;

import com.google.gson.JsonArray;
import net.lengmang.aicoffeeshareserver.sql.bean.Account;
import net.lengmang.aicoffeeshareserver.sql.repository.AccountRepository;
import net.lengmang.aicoffeeshareserver.sql.repository.OrderFormRepository;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.StringWriter;

/**
 * Created by YuLong on 2017/10/18.
 */
@Component
public class WeChatEvent {

    private static WeChatEvent weChatEvent = null;

    @Autowired
    private AccountRepository accountRepository;

    @PostConstruct
    private void init() {
        weChatEvent = this;
        weChatEvent.accountRepository = accountRepository;
    }

    private WeChatEvent() {
    }

    public static String doEverything(String xml) {
        return weChatEvent.doThing(xml);
    }

    private String doThing(String xml) {
        try {
            // 转换字符串成xml对象
            Document doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element rootElt = doc.getRootElement();

            //构建需要返回响应的xml
            Document return_document = DocumentHelper.createDocument();
            Element return_root = return_document.addElement("xml");
            return_root.addElement("ToUserName").addCDATA(rootElt.element("FromUserName").getText());
            return_root.addElement("FromUserName").addCDATA(rootElt.element("ToUserName").getText());
            return_root.addElement("CreateTime").setText(System.currentTimeMillis() + "");

            // 区分时间类型
            String type = rootElt.element("MsgType").getText();
            switch (type) {
                // MsgType == event
                case "event":
                    String event = rootElt.element("Event").getText();
                    switch (event) {
                        // 用户关注公众号
                        case "subscribe":
                            return_root.addElement("MsgType").addCDATA("text");
                            return_root.addElement("Content").addCDATA("欢迎关注冷芒科技测试号");
                            Account account = accountRepository.readByOpenId(rootElt.element("FromUserName").getText());
                            if (account == null) {
                                account = new Account();
                                account.setOpenId(rootElt.element("FromUserName").getText());
                                account.setAccountInfo(new JsonArray());
                                account.setCount(0);
                                accountRepository.save(account);
                            }
                            break;
                        // 用户取消关注公众号
                        case "unsubscribe":
                            break;
                        default:
                            break;
                    }
                    break;
            }
            // 设置XML文档格式
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1
            outputFormat.setEncoding("UTF-8");
            outputFormat.setSuppressDeclaration(true); //是否生产xml头
            outputFormat.setIndent(false); //设置是否缩进
            outputFormat.setNewlines(false); //设置是否换行

            StringWriter stringWriter = new StringWriter();
            XMLWriter xmlWriter = new XMLWriter(stringWriter, outputFormat);
            xmlWriter.write(return_document);
            String return_xml = stringWriter.toString();
            xmlWriter.close();
            return return_xml;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
