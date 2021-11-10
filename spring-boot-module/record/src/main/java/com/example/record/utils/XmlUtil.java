package com.example.record.utils;

import cn.hutool.json.XML;
import com.example.record.excel.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author KPQ
 * @date 2021/11/10
 */
@Slf4j
public class XmlUtil {

    private static Map<String, Unmarshaller> cacheMap = new ConcurrentHashMap<>();


    public static <T> T toObject(String xml, Class<T> clazz) {
        String className = clazz.getName();
        Unmarshaller unmarshaller;
        try {
            if (cacheMap.containsKey(className)) {
                unmarshaller = cacheMap.get(className);
            } else {
                unmarshaller = JAXBContext.newInstance(clazz).createUnmarshaller();
                cacheMap.put(className, unmarshaller);
            }
            return (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            log.error("error xml to object: {}", e.getMessage(), e);
            throw new RuntimeException("xml to object failed");
        }
    }

    public static Object toJson(String xml) {
        if (!StringUtils.hasLength(xml)) {
            return "{}";
        }
        return XML.toJSONObject(xml);
    }

    public static <T> String toXml(T entity) {
        if (entity == null) {
            return "";
        }
        Marshaller marshaller;
        try {
            marshaller = JAXBContext.newInstance(entity.getClass()).createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
            StringWriter writer = new StringWriter();
            marshaller.marshal(entity, writer);
            return writer.toString().trim();
        } catch (JAXBException e) {
            log.error("toXml异常", e);
        }
        return "";
    }

    public static void main(String[] args) {
        Product product = new Product();
        product.setProductName("商品1号").setId(12L).setProductNum("123");
        String xml = toXml(product);
        System.out.println(xml);
        System.out.println(toJson(xml));
        System.out.println(toObject(xml, Product.class));
    }

}
