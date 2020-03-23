package com.ybs.note.service;

import com.ybs.note.utils.SendSms;
import org.springframework.stereotype.Service;

/**
 * SmsService
 *
 * @author Paulson
 * @date 2020/3/23 22:58
 */

@Service
public class SmsService {

    public String sendSms(String telPhone){
        return SendSms.sendCode(telPhone);
    }
}
