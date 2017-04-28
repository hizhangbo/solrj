package main.utils.common;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by zhangbo on 2017/4/10.
 */
public class ReflectHelper {

    public static <T> HashMap<String,Object> GetObjectField(T model) throws IllegalAccessException {

        HashMap<String,Object> result = new HashMap<String, Object>();

        Field[] fs = model.getClass().getDeclaredFields();
        Field.setAccessible(fs, true);
        for(int i = 0 ; i < fs.length; i++){
            Field f = fs[i];
            Object val = f.get(model);

            result.put(f.getName(), val);
        }
        return result;
    }
}
