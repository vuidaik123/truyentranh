package com.example.kimvui.truyentranh;

import java.io.Serializable;
import java.util.List;

public class Comic  implements Serializable {
    public String Name;
    public String Image;
    public String Catogery;
    public List<Chapter> Chapters;
    public Comic(){}
    public Comic(String name,String image,String catogery,List<Chapter> chapters){
        this.Name=name;
        this.Image=image;
        this.Catogery=catogery;
        this.Chapters=chapters;
    }


}
