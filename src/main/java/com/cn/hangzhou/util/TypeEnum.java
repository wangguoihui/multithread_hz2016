package com.cn.hangzhou.util;

public enum TypeEnum {
    mermbers("mermbers", "会员"), merchant("merchant", "商户"), advertiser("advertiser", "广告组"), channel("channel", "渠道"),weChat("weChat", "微信");

    private String value;
    private String description;

    private TypeEnum(String value, String description){
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
    
    public static void main(String args[]){
    	
    	 for (TypeEnum item : TypeEnum.values()) {
             System.out.println(item+":"+item.value+":"+item.description);
         }
    	 
    	 TypeEnum mermbers = TypeEnum.mermbers;
         System.out.println(mermbers+":"+mermbers.value+":"+mermbers.description);
         
    }

}
