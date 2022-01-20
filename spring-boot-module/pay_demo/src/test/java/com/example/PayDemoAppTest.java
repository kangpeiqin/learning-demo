package com.example;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;

/**
 * 数据库密码加密与解密
 *
 * @author KPQ
 * @date 2021/10/12
 */
public class PayDemoAppTest {

    @Test
    public void testEncrypt() {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");          // 加密的算法，这个算法是默认的
        config.setPassword("--------");                        // 加密的密钥
        standardPBEStringEncryptor.setConfig(config);
        String pass = "!!!!!";
        String encPass = standardPBEStringEncryptor.encrypt(pass);
        System.out.println(encPass);
    }

    @Test
    public void testDe() throws Exception {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();

        config.setAlgorithm("PBEWithMD5AndDES");
        config.setPassword("12345");
        standardPBEStringEncryptor.setConfig(config);
        String encryptedText = "gTC/CPKmMyrz3dl6B7iriENVDnwhjRZ3";
        String plainText = standardPBEStringEncryptor.decrypt(encryptedText);
        System.out.println(plainText);
    }

    @Test
    public void encrypt() {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        //加密所需的密钥
        textEncryptor.setPassword("21242");
        //要加密的数据（数据库的用户名或密码）
        String username = textEncryptor.encrypt("root");
        String password = textEncryptor.encrypt("123445");
        System.out.println(username);
        System.out.println("====================");
        System.out.println(password);
    }
}
