package com.example.aggour.random;

import android.content.Context;

public class RNG {

    public String result = "";
    protected int many;
    protected int max;

    public RNG(String many, String max, boolean checked, Context context) {
        prep(many, max, context);
        if (checked && this.max >= this.many)
            unique(this.many, this.max);
        else if (checked && this.max < this.many)
            this.result = context.getResources().getString(R.string.error2);
        else
            simple(this.many, this.max);
    }

    protected void prep(String many, String max, Context context) {
        many += "";
        max += "";
        if (many == "" || max == "")
            this.result = context.getResources().getString(R.string.error1);
        else {
            this.many = Integer.parseInt(many);
            this.max = Integer.parseInt(max);
        }
    }

    protected void simple(int many, int max) {
        java.util.Random rand = new java.util.Random();
        int num[] = new int[many];
        for (int i = 0; i < many; i++) {
            num[i] = 1 + rand.nextInt(max);
            this.result += num[i] + " ";
        }
    }

    protected void unique(int many, int max) {
        java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
        for (int i = 1; i <= max; i++)
            list.add(i);
        java.util.Collections.shuffle(list);
        for (int i = 0; i < many; i++)
            this.result += list.get(i) + " ";
    }
}

