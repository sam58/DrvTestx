package com.sam58.drv.model;

import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by sam158 on 22.04.2015.
 */
public abstract class AbstractStoreObject implements Serializable {
    @JsonView(JSViews.Base.class)
    String id;

    private boolean isNew;

    public String getId() {
        return id;
    }



    public void setId(String id) {
        if(id==null || "new".equalsIgnoreCase(id)){
            this.id=UUID.randomUUID().toString();
            this.isNew=true;
        }else {
            this.id = id;
            this.isNew=false;
        }
    }

    //Считаю , что это один и тот же объект для простоты, если ключ не изменился.
    //неверно, но для моих целей нормально
    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        return this.id.equals( ((AbstractStoreObject)obj).id);
    }
}
