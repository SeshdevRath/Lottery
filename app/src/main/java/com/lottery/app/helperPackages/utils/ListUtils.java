package com.lottery.app.helperPackages.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ListUtils {
    public static boolean isValidList(List<?> list) {
        return list != null && list.size() > 0;
    }

    public static boolean isValidSet(Set<?> set) {
        return set != null && set.size() > 0;
    }

    public static List removeItemFromList(Object object, List inputList) {
        for (Iterator<String> iterator = inputList.listIterator(); iterator.hasNext(); ) {
            Object currentObject = iterator.next();
            if (currentObject != null && currentObject.equals(object)) {
                iterator.remove();
            }
        }
        return inputList;
    }

    public static <T> T[] getArrFromList(List<T> inputList) {
        if (isValidList(inputList)) {
            T[] objectArr = (T[]) Array.newInstance(inputList.get(0).getClass(), inputList.size());
            for (int counter = 0; counter < inputList.size(); counter++) {
                T objectFromList = inputList.get(counter);
                objectArr[counter] = objectFromList;
            }
            return objectArr;
        }
        return null;
    }

    public static <T> boolean compareListObjects(List<T> objectList1, List<T> objectList2, Class eventClass) {
        if (objectList1 == null && objectList2 == null)
            return true;
        else if (objectList1 == null || objectList2 == null)
            return false;
        else if (objectList1.size() != objectList2.size())
            return false;
        else if (objectList1.size() == 0)
            return false;
        else {
            for (int i = 0; i < objectList1.size(); i++) {
                Object object1 = objectList1.get(i);
                Object object2 = objectList2.get(i);

                if (object1!=null && object2!=null
                        && object1.getClass().getSimpleName().equals(object2.getClass().getSimpleName())
                        && object1.getClass().getSimpleName().equals(eventClass.getSimpleName())) {
                    if (!(eventClass.cast(objectList1.get(i))).equals(eventClass.cast(objectList2.get(i))))
                        return false;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    public static boolean compareStringSetObjects(Set<String> set1, Set<String> set2) {
        if (set1 == null && set2 == null)
            return true;
        else if (set1 == null || set2 == null)
            return false;
        else if (set1.size() != set2.size())
            return false;
        else if (set1.size() == 0)
            return false;
        else {
            return set1.containsAll(set2);
        }
    }

    public static <T> ArrayList<T> convertSetToList(Set<T> set) {
        ArrayList<T> list = new ArrayList<T>(set.size());
        list.addAll(set);
        return list;
    }

    public static <T> HashSet<T> convertListToSet(List<T> list) {
        HashSet<T> set = new HashSet<>();
        if (isValidList(list))
            set.addAll(list);
        return set;
    }

    public static <T> boolean existsElement(List<T> list, int position) {
        return (position >= 0 && isValidList(list) && list.size() >= position + 1);
    }

    public static boolean isValidArray(Object[] inputArray) {
        return inputArray!=null && inputArray.length > 0;
    }

    public static <T> boolean compareArray(T arr[], T arr2[], Class eventClass) {
        if (arr == null || arr2 == null)
            return false;
        else if (arr.length != arr2.length)
            return false;
        else {
            for (int i = 0; i < arr.length; i++) {
                if (!arr[i].equals(arr2[i]))
                    return false;
            }
            return true;
        }
    }
}
