package com.jesse.util;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;

public class AliyunBatchPublishSMSMessage {
	public static void sendMessage(String templateCode, String code, String product, String phone) {
		/**
		 * Step 1. 获取主题引用
		 */
		CloudAccount account = new CloudAccount("LTAIFdYb8ALKDj4Z", "eDER0EEoOwXZ2IA8ohYp630PEsz9Vq", "http://1962190170004059.mns.cn-shenzhen.aliyuncs.com");
		MNSClient client = account.getMNSClient();
		CloudTopic topic = client.getTopicRef("rongwei1-sms-topic");
		/**
		 * Step 2. 设置SMS消息体（必须）
		 * 
		 * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
		 */
		RawTopicMessage msg = new RawTopicMessage();
		msg.setMessageBody("sms-message");
		/**
		 * Step 3. 生成SMS消息属性
		 */
		MessageAttributes messageAttributes = new MessageAttributes();
		BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
		// 3.1 设置发送短信的签名（SMSSignName）
		batchSmsAttributes.setFreeSignName("股先森");
		// 3.2 设置发送短信使用的模板（SMSTempateCode）
		// batchSmsAttributes.setTemplateCode("SMS_61045004");
		batchSmsAttributes.setTemplateCode(templateCode);
		// 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
		BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
		smsReceiverParams.setParam("code", code);
		smsReceiverParams.setParam("product", product);
		// 3.4 增加接收短信的号码
		batchSmsAttributes.addSmsReceiver(phone, smsReceiverParams);
		messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
		try {
			/**
			 * Step 4. 发布SMS消息
			 */
			TopicMessage ret = topic.publishMessage(msg, messageAttributes);
			
			System.out.println("MessageId: " + ret.getMessageId());
			System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
		} catch (ServiceException se) {
			System.out.println(se.getErrorCode() + se.getRequestId());
			System.out.println(se.getMessage());
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		client.close();
	}
}
