package com.Util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

public class MyObjectMapper {

    private static final Logger logger = LoggerFactory.getLogger(MyObjectMapper.class);

    @Inject
    public MyObjectMapper() {
    }

    public static ObjectMapper getDefaultMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        Hibernate4Module hbm = new Hibernate4Module();
        hbm.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
        mapper.registerModule(hbm);
        mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE)
                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return mapper;
    }

    public static String getJsonString(Object object) throws Exception {
        try {
            if (object != null)
                return getDefaultMapper().writeValueAsString(object);
        } catch (Exception e) {
            logger.error("Failed to convert object to json", e);
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public static String getJsonStringWithoutException(Object object){
        try {
            if (object != null)
                return getDefaultMapper().writeValueAsString(object);
        } catch (Exception e) {
            logger.error("Failed to convert object to json", e);
        }
        return null;
    }
//
//    public static String getJsonStringNoException(Object object) {
//        try{
//            if (object != null)
//                return getDefaultMapper().writeValueAsString(object);
//        } catch (Exception e) {
//            logger.error("Failed to convert object to json", e);
//        }
//        return null;
//    }
//
//    public static String getDefaultJson(Object object) throws ApiException{
//        try{
//            if (object != null)
//                return new ObjectMapper().writeValueAsString(object);
//        } catch (Exception e) {
//            logger.error("Failed to convert object to json", e);
//            throw new ApiException(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//        return null;
//    }
//
    public static <T> T getClassObjectByDefaultMapper(String jsonString, Class<T> valueType) throws Exception {
        try {
            return getDefaultMapper().readValue(jsonString, valueType);
        } catch (Exception e) {
            throw new Exception("Unable to serilize string to map");
        }
    }


    public static <T> List<T> getListOfClassObjectByDefaultMapper(String jsonString, Class<T> valueType) throws Exception {
        try {
            TypeFactory typeFactory = getDefaultMapper().getTypeFactory();
            CollectionType listType = typeFactory.constructCollectionType(List.class,valueType);
            List<T> list = getDefaultMapper().readValue(jsonString, listType);
            return list;
        } catch (Exception e) {
            throw new Exception("Unable to serilize string to map");
        }
    }





//
    public static <T> T getClassObjectByDefaultMapper(String jsonString, TypeReference<Map<Integer, Integer>> valueType) throws Exception{
        try{
            return getDefaultMapper().readValue(jsonString, valueType);
        } catch (Exception e) {
            throw new Exception("Unable to serilize string to map");
        }
    }
//
//    private static class IgnoreInheritedAttributes extends JacksonAnnotationIntrospector {
//
//        private Class aClass;
//
//        private IgnoreInheritedAttributes(Class aClass) {
//            this.aClass = aClass;
//        }
//
//        @Override
//        public boolean hasIgnoreMarker(final AnnotatedMember m) {
//            return m.getDeclaringClass() == aClass || super.hasIgnoreMarker(m);
//        }
//    }
//
//    public static ObjectMapper getIgnoreInheritedMapper(Class aclass){
//        ObjectMapper mapper = getDefaultMapper();
//        mapper.setAnnotationIntrospector(new IgnoreInheritedAttributes(aclass));
//        return mapper;
//    }
//}
}