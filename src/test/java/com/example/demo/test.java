package com.example.demo;

import com.example.demo.model.entity.LogParam;
import com.example.demo.model.entity.TestVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Field;
import io.swagger.annotations.ApiModelProperty;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import io.swagger.annotations.ApiModelProperty;

@Data
class Person {
    private String name;
    private int age;
    private String slogan;
    private List<Person> personList = new ArrayList<>();

    // 省略构造函数和getter/setter方法

    // toString方法用于方便输出人员信息

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", slogan='" + slogan + '\'' +
                ", personList=" + personList +
                '}';
    }
}

public class test {
    public static void main2(String[] args) {
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person();
        person1.setName("张三");
        person1.setAge(23);
        person1.setSlogan("口号B");
        Person person2 = new Person();
        person2.setName("李四");
        person2.setAge(23);
        person2.setSlogan("口号B");
        Person person3 = new Person();
        person3.setName("王五");
        person3.setAge(23);
        person3.setSlogan("口号B");
        Person person4 = new Person();
        person4.setName("赵六");
        person4.setAge(21);
        person4.setSlogan("口号C");
        Person person5 = new Person();
        person5.setName("三七");
        person5.setAge(22);
        person5.setSlogan("口号A");
        Person person6 = new Person();
        person6.setName("四八");
        person6.setAge(21);
        person6.setSlogan("口号C");
        Person person7 = new Person();
        person7.setName("阿是");
        person7.setAge(23);
        person7.setSlogan("口号D");
        Person person8 = new Person();
        person8.setName("设定");
        person8.setAge(26);
        person8.setSlogan("口号T");
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);
        personList.add(person8);

        List<Person> groupSlogans = getGroupSlogans(personList, 3);
        for (Person groupSlogan : groupSlogans) {
            System.out.println(groupSlogan.toString());
        }

    }

    public static List<Person> getGroupSlogans(List<Person> personList, int groupSize) {
        List<List<Person>> groupedPersons = new ArrayList<>();
        int numGroups = (int) Math.ceil((double) personList.size() / groupSize);
        for (int i = 0; i < numGroups; i++) {
            int startIndex = i * groupSize;
            int endIndex = Math.min(startIndex + groupSize, personList.size());
            groupedPersons.add(personList.subList(startIndex, endIndex));
        }

        List<Person> groupSlogans = new ArrayList<>();
        for (List<Person> group : groupedPersons) {
            Person person = new Person();
            String slogan = findGroupSlogan(group);
            person.setSlogan(slogan);
            person.getPersonList().addAll(group);
            groupSlogans.add(person);
        }

        return groupSlogans;
    }

    private static String findGroupSlogan(List<Person> group) {
        Map<String, Integer> sloganCounts = new HashMap<>();
        for (Person person : group) {
            String slogan = person.getSlogan();
            sloganCounts.put(slogan, sloganCounts.getOrDefault(slogan, 0) + 1);
        }

        String groupSlogan = null;
        if (sloganCounts.size() == 1) {
            groupSlogan = group.get(0).getSlogan();
        } else if (sloganCounts.size() == 2) {
            for (Map.Entry<String, Integer> entry : sloganCounts.entrySet()) {
                if (entry.getValue() == 2) {
                    groupSlogan = entry.getKey();
                    return groupSlogan;
                }
            }
        }

        int maxAge = Integer.MIN_VALUE;
        String defaultSlogan = group.get(0).getSlogan(); // 默认取第一个人的口号
        for (Person person : group) {
            int age = person.getAge();
            if (age > maxAge) {
                maxAge = age;
                groupSlogan = person.getSlogan();
            }
        }

        if (groupSlogan == null) {
            groupSlogan = defaultSlogan;
        }
        return groupSlogan;
    }

    public static void main(String[] args) {
        String s = generateSwaggerDoc(new TestVo());
        System.out.println(s);
    }



        public static String generateSwaggerDoc(Object object) {
            StringBuilder swaggerDoc = new StringBuilder();

            Class<?> clazz = object.getClass();

            // 处理字段级别的注解信息
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(LogParam.class) && field.isAnnotationPresent(ApiModelProperty.class)) {
                    LogParam fieldAnnotation = field.getAnnotation(LogParam.class);
                    ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
                    String fieldName = field.getName();
                    String fieldTitle = fieldAnnotation.title();
                    String fieldDescription = apiModelProperty.value();
                    String fieldType = getFieldType(field);
                    String fieldEntry = fieldType + "类型    " + fieldName + ":   " + fieldTitle + "（" + fieldDescription + "）";
                    swaggerDoc.append(fieldEntry).append(System.lineSeparator());
                }
            }
            return swaggerDoc.toString();
        }

        private static String getFieldType(Field field) {
            Type fieldType = field.getGenericType();
            if (fieldType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) fieldType;
                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                StringBuilder genericType = new StringBuilder();
                genericType.append(parameterizedType.getRawType().getTypeName()).append("<");
                for (int i = 0; i < typeArguments.length; i++) {
                    if (i > 0) {
                        genericType.append(", ");
                    }
                    genericType.append(typeArguments[i].getTypeName());
                }
                genericType.append(">");
                return genericType.toString();
            }
            return fieldType.getTypeName();
        }
    }


