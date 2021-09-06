package main;

import main.model.Case;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CaseList {
    private static int currentId = 1;
    private static Map<Integer, Case> caseMap = new ConcurrentHashMap<>();

    public static  List<Case> getCaseList() {
        return new ArrayList<>(caseMap.values());
    }

    public static synchronized Case addCase(Case newCase) {
        caseMap.put(currentId++, newCase);
        return newCase;
    }

    public static Case getCase(int caseId) {
        return caseMap.getOrDefault(caseId, null);
    }

    public static boolean removeCase(int caseId) {
        if (caseMap.containsKey(caseId)) {
            caseMap.remove(caseId);
            return true;
        }
        return false;
    }

    public static void removeAllCases() {
        caseMap = new ConcurrentHashMap<>();
        currentId = 1;
    }

    public static Case updateCase(int id, Case newCase) {
       return caseMap.computeIfPresent(id,(k,v)->newCase);
    }

}
