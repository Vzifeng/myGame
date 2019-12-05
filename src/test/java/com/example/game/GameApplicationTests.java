package com.example.game;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameApplicationTests {

    @Autowired
    RabbitMessagingTemplate rabbitMessagingTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;



    @Test
    public void testExportWord2() throws Exception {
        String tmpFile = "classpath:template.doc";
        String expFile = "D:/result1.doc";
        Map<String, String> datas = new HashMap<String, String>();
        datas.put("title", "标题部份");
        datas.put("content", "这里是内容，测试使用POI导出到Word的内容！");
        datas.put("author", "妹妹v");
        datas.put("url", "http://www.baidu.com");

        build(ResourceUtils.getFile(tmpFile), datas, expFile);
    }

    private void build(File tmpFile, Map<String, String> contentMap, String exportFile) throws Exception {
        FileInputStream tempFileInputStream = new FileInputStream(tmpFile);
        HWPFDocument document = new HWPFDocument(tempFileInputStream);
        // 读取文本内容
        Range bodyRange = document.getRange();
        // 替换内容
        for (Map.Entry<String, String> entry : contentMap.entrySet()) {
            bodyRange.replaceText("${" + entry.getKey() + "}", entry.getValue());
        }

        //导出到文件
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.write(byteArrayOutputStream);
        OutputStream outputStream = new FileOutputStream(exportFile);
        outputStream.write(byteArrayOutputStream.toByteArray());
        outputStream.close();
    }

    @Test
    public void producerRabbitmq(){
        rabbitMessagingTemplate.convertAndSend("news.add","收到消息了吗？收到回话");
    }

    @Test
    public void comsumerRabbitmq(){
        Message message = rabbitTemplate.receive("news.add");
        System.out.println("收到的消息是："+new String(message.getBody()));
    }

}
