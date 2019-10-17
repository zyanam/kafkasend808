package com.cccts.kafkasend808;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Util {
    public byte[] getBytesBySpace(String str) {
        String[] strs = str.split(" ");
        return getBytes(strs);
    }

    public byte[] getBytes(String str) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i += 2) {
            String s = str.substring(i, i + 2);
            list.add(s);
        }
        return getBytes(list.toArray(new String[list.size()]));
    }

    private byte[] getBytes(String[] strs) {

        byte[] bs = new byte[strs.length];

        for (int i = 0; i < strs.length; i++) {
            bs[i] = (byte) Integer.parseInt(strs[i], 16);
        }
        return bs;
    }
}
