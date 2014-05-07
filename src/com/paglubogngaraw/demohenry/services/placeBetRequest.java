package com.paglubogngaraw.demohenry;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import java.util.Hashtable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

public class placeBetRequest implements KvmSerializable {
    
    public int accountId;
    public int agentId;
    public int matchId;
    public int sBet;
    public float amount;
    public String betDate;
    
    public placeBetRequest(){}
    
    public placeBetRequest(SoapObject soapObject)
    {
        if (soapObject == null)
            return;
        if (soapObject.hasProperty("accountId"))
        {
            Object obj = soapObject.getProperty("accountId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                accountId = Integer.parseInt(j.toString());
            }else if (obj!= null && obj instanceof Number){
                accountId = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("agentId"))
        {
            Object obj = soapObject.getProperty("agentId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                agentId = Integer.parseInt(j.toString());
            }else if (obj!= null && obj instanceof Number){
                agentId = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("matchId"))
        {
            Object obj = soapObject.getProperty("matchId");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                matchId = Integer.parseInt(j.toString());
            }else if (obj!= null && obj instanceof Number){
                matchId = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("sBet"))
        {
            Object obj = soapObject.getProperty("sBet");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                sBet = Integer.parseInt(j.toString());
            }else if (obj!= null && obj instanceof Number){
                sBet = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("amount"))
        {
            Object obj = soapObject.getProperty("amount");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                amount = Float.parseFloat(j.toString());
            }else if (obj!= null && obj instanceof Number){
                amount = (Integer) obj;
            }
        }
        if (soapObject.hasProperty("betDate"))
        {
            Object obj = soapObject.getProperty("betDate");
            if (obj != null && obj.getClass().equals(SoapPrimitive.class)){
                SoapPrimitive j =(SoapPrimitive) obj;
                betDate = j.toString();
            }else if (obj!= null && obj instanceof String){
                betDate = (String) obj;
            }
        }
    }
    @Override
    public Object getProperty(int arg0) {
        switch(arg0){
            case 0:
                return accountId;
            case 1:
                return agentId;
            case 2:
                return matchId;
            case 3:
                return sBet;
            case 4:
                return amount;
            case 5:
                return betDate;
        }
        return null;
    }
    
    @Override
    public int getPropertyCount() {
        return 6;
    }
    
    @Override
    public void getPropertyInfo(int index, @SuppressWarnings("rawtypes") Hashtable arg1, PropertyInfo info) {
        switch(index){
            case 0:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "accountId";
                break;
            case 1:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "agentId";
                break;
            case 2:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "matchId";
                break;
            case 3:
                info.type = PropertyInfo.INTEGER_CLASS;
                info.name = "sBet";
                break;
            case 4:
                info.type = Float.class;
                info.name = "amount";
                break;
            case 5:
                info.type = PropertyInfo.STRING_CLASS;
                info.name = "betDate";
                break;
        }
    }
    
    @Override
    public void setProperty(int arg0, Object arg1) {
    }
    
}
